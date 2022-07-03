package com.zjjzfy.exchange.cache;

import com.zjjzfy.exchange.common.SessionKey;
import com.zjjzfy.exchange.utils.IpUtil;
import com.zjjzfy.interfaces.TbIpOrgMapper;
import com.zjjzfy.interfaces.TbOrgInfoMapper;
import com.zjjzfy.pojo.TbOrgInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: jackshenonly
 * Date: 2019/4/29
 * Time: 13:57
 */
@Component
public class OrgCache {
    private static OrgCache instance = null;

    @Value("${myconfig.org-refresh-interval}")
    private int interval;

    /**
     * 上次刷新时间。
     */
    private long refreshTime;

    private List<TbOrgInfo> orgs;

    private Map<String,String> ip2BranchNo = new HashMap<>();

    @Autowired
    private TbOrgInfoMapper tbOrgInfoMapper;
    @Autowired
    private TbIpOrgMapper tbIpOrgMapper;

    @PostConstruct
    public void init(){
        setInstance(this);
        refresh();
    }

    public void refresh(){
        instance.setRefreshTime(System.currentTimeMillis());
        instance.setOrgs(instance.tbOrgInfoMapper.selectAll());
        instance.tbIpOrgMapper.selectAll().stream().forEach(x -> instance.getIp2BranchNo().put(x.getIp(),x.getBranchno()));

    }

    /**
     * 通过id，选出机构信息
     * @param id
     * @return
     */
    public TbOrgInfo getOrgInfobyId(int id){
        Optional<TbOrgInfo> op = instance.getOrgs().stream().filter(org -> org.getId() == id).findFirst();
        return op.orElse(null);
    }

    /**
     * 通过机构号，选出机构信息
     * @param branchno
     * @return
     */
    public TbOrgInfo getOrgInfobyBranchno(String branchno){
        Optional<TbOrgInfo> op = instance.getOrgs().stream().filter(org -> org.getBranchno().equals(branchno)).findFirst();
        return op.orElse(null);
    }

    /**
     * 通过request，选出机构信息
     * @param request
     * @return
     */
    public TbOrgInfo getOrgInfobyRequest(HttpServletRequest request){
        String branchno = getBranchnoByRequest(request);
        return getOrgInfobyBranchno(branchno);
    }

    /**
     * 从请求中的cookies获取机构号
     * @param request
     * @return
     */
    public String getBranchnoByRequest(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies == null){
            return "NONE";
        }else {
            for (Cookie cookie : cookies){
                if(SessionKey.COOKIE_BRANCHID.equals(cookie.getName())){
                    return cookie.getValue();
                }
            }
        }
        return "NONE";
    }

    /**
     * 通过ip,获取机构信息
     * @param ip
     * @return
     */
    public TbOrgInfo getOrgInfobyIp(String ip){
        String branchno = instance.getIp2BranchNo().get(ip);
        return getOrgInfobyBranchno(branchno);
    }

    public TbOrgInfo getOrgInfobyIp(HttpServletRequest request){
        String ip = IpUtil.getIpAddr(request);
        return getOrgInfobyIp(ip);
    }




    public static OrgCache getInstance() {

        long now = System.currentTimeMillis();
        if(now - instance.getRefreshTime() > instance.getInterval()){
            instance.refresh();
        }
        return instance;

    }

    public static void setInstance(OrgCache instance) {
        OrgCache.instance = instance;
    }

    public TbOrgInfoMapper getTbOrgInfoMapper() {
        return tbOrgInfoMapper;
    }

    public void setTbOrgInfoMapper(TbOrgInfoMapper tbOrgInfoMapper) {
        this.tbOrgInfoMapper = tbOrgInfoMapper;
    }

    public List<TbOrgInfo> getOrgs() {
        return orgs;
    }

    public void setOrgs(List<TbOrgInfo> orgs) {
        this.orgs = orgs;
    }

    public Map<String, String> getIp2BranchNo() {
        return ip2BranchNo;
    }

    public void setIp2BranchNo(Map<String, String> ip2BranchNo) {
        this.ip2BranchNo = ip2BranchNo;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public long getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(long refreshTime) {
        this.refreshTime = refreshTime;
    }
}
