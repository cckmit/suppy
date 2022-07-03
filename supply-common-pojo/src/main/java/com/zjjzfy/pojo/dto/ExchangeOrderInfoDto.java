package com.zjjzfy.pojo.dto;

import com.zjjzfy.pojo.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: jackshenonly
 * Date: 2019/5/7
 * Time: 11:02
 */
public class ExchangeOrderInfoDto {

    private JfDeliverOrder main;
    private List<Detail> list;
    private TbUserInfo userInfo;
    private TbOrgInfo orgInfo;

    private JfDeliverOrderExtra extra;

    public boolean hasOrgInfo(){
        return orgInfo==null?false:true;
    }
    public boolean hasUserInfo(){
        return userInfo==null?false:true;
    }

    public JfDeliverOrder getMain() {
        return main;
    }

    public void setMain(JfDeliverOrder main) {
        this.main = main;
    }

    public List<Detail> getList() {
        return list;
    }

    public void setList(List<Detail> list) {
        this.list = list;
    }

    public TbUserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(TbUserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public TbOrgInfo getOrgInfo() {
        return orgInfo;
    }

    public void setOrgInfo(TbOrgInfo orgInfo) {
        this.orgInfo = orgInfo;
    }

    public JfDeliverOrderExtra getExtra() {
        return extra;
    }

    public void setExtra(JfDeliverOrderExtra extra) {
        this.extra = extra;
    }

    public static class Detail {
        private JfDeliverDetail detail;
        private TbProduct product;

        public Detail() {
        }

        public JfDeliverDetail getDetail() {
            return detail;
        }

        public void setDetail(JfDeliverDetail detail) {
            this.detail = detail;
        }

        public TbProduct getProduct() {
            return product;
        }

        public void setProduct(TbProduct product) {
            this.product = product;
        }
    }

    public static Detail createDetail(){
        return new Detail();
    }
}


