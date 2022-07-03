package com.zjjzfy.mobile.controller.pad;

/**
 * @author: hsmz
 * @date: 2019/4/2 下午3:25
 */
public class EmployeeInfoBean {
    /**

     * id : 21
     * name : 超级用户
     * password : ******
     * remark : 管理员
     * role : 0
     * status : 0
     */

    private String branchid;
    private String employeeid;
    private int id;
    private String name;
    private String password;
    private String remark;
    private int role;
    private int status;

    public String getBranchid() {
        return branchid;
    }

    public void setBranchid(String branchid) {
        this.branchid = branchid;
    }

    public String getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
