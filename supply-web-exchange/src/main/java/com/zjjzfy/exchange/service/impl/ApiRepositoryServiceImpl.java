package com.zjjzfy.exchange.service.impl;

import com.zjjzfy.common.entity.SupplyResult;
import com.zjjzfy.exchange.cache.OrgCache;
import com.zjjzfy.exchange.pojo.RepositoryReturnBody;
import com.zjjzfy.exchange.service.BaseService;
import com.zjjzfy.exchange.service.ApiRepositoryService;
import com.zjjzfy.interfaces.TbRepositoryMapper;
import com.zjjzfy.pojo.TbOrgInfo;
import com.zjjzfy.pojo.TbRepository;
import com.zjjzfy.pojo.TbRepositoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jackshenonly
 * @description 类说明:库存查询相关
 * @date 2020-05-15 13:45
 */
@Service
public class ApiRepositoryServiceImpl extends BaseService implements ApiRepositoryService {
    @Autowired
    private TbRepositoryMapper tbRepositoryMapper;

    @Override
    public SupplyResult repositoryOne(Integer productId, Integer uid, String branchno) {
        RepositoryReturnBody rrb = new RepositoryReturnBody();
        rrb.setProductId(productId);
        TbOrgInfo orgInfo = OrgCache.getInstance().getOrgInfobyBranchno(branchno);
        if (orgInfo == null) {
            return SupplyResult.build(404, "未知的机构号:" + branchno);
        }
        TbRepository trq = new TbRepository();
        trq.setProductId(productId);
        trq.setSupplyOrgId(orgInfo.getId());
        trq.setSupplierId(uid);
        TbRepository tr = tbRepositoryMapper.selectOne(trq);
        if (tr == null) {
            rrb.setQuantity(0);
        } else {
            rrb.setQuantity(tr.getQuantity());
        }


        return SupplyResult.build(200, "查询单个礼品库存成功!", rrb);
    }

    @Override
    public SupplyResult repositoryMore(List<Integer> productIds, Integer uid, String branchno) {
        TbOrgInfo orgInfo = OrgCache.getInstance().getOrgInfobyBranchno(branchno);
        if (orgInfo == null) {
            return SupplyResult.build(404, "未知的机构号:" + branchno);
        }
        TbRepositoryExample tre = new TbRepositoryExample();
        tre.createCriteria().andSupplyOrgIdEqualTo(orgInfo.getId())
                .andSupplierIdEqualTo(uid)
                .andProductIdIn(productIds);
        List<TbRepository> tbRepositoryList = tbRepositoryMapper.selectByExample(tre);

        List<RepositoryReturnBody> rrbs = new ArrayList<>();

        for (TbRepository tr : tbRepositoryList) {
            RepositoryReturnBody rrb = new RepositoryReturnBody();
            rrb.setProductId(tr.getProductId());
            rrb.setQuantity(tr.getQuantity());
            rrbs.add(rrb);
            // 已经查询出来的就 移除对应的productId
            productIds.remove(tr.getProductId());
        }

        if (productIds.size() > 0) {
            // 数量不等的话，没查到的礼品 需要补个0，方便前端显示
            for (Integer pid : productIds) {
                RepositoryReturnBody rrb = new RepositoryReturnBody();
                rrb.setProductId(pid);
                rrb.setQuantity(0);
                rrbs.add(rrb);
            }
        }


        return SupplyResult.build(200, "多个礼品库存查询成功！", rrbs);
    }

    @Override
    public SupplyResult repositoryMore(String productIds, Integer uid, String branchno) {
        List<Integer> ids = new ArrayList<>();
        try {
            String delimiter = ",";
            for (String id : productIds.split(delimiter)) {
                ids.add(Integer.valueOf(id));
            }
            if (ids.size() == 0) {
                return SupplyResult.build(501, "多个礼品查库存查询参数异常:" + productIds);
            }
        } catch (Exception e) {
            log.error("多个礼品查库存查询参数异常", e);
            return SupplyResult.build(502, "多个礼品查库存查询参数异常:" + productIds);

        }
        return repositoryMore(ids, uid, branchno);
    }
}
