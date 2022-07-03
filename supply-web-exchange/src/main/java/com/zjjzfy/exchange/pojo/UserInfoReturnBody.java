package com.zjjzfy.exchange.pojo;

/**
 * @author jackshenonly
 */
public class UserInfoReturnBody {
    /**
     * 用户id
     */
    private Integer uid;
    /**
     * 登陆账号
     */
    private String username;
    /**
     * 用户昵称
     */
    private String name;
    /**
     * 所属组织机构号
     */
    private String branchno;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranchno() {
        return branchno;
    }

    public void setBranchno(String branchno) {
        this.branchno = branchno;
    }
}
