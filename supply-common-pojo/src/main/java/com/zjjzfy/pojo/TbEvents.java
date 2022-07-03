package com.zjjzfy.pojo;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "tb_events")
public class TbEvents implements Serializable {

    @Id
    private Integer id;
    private String  name;
    private Integer status;
    private Date createTime;
    private Date startTime;
    private Date endTime;
    private Integer evtRank;
    private Integer evtClass;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getEvtRank() {
        return evtRank;
    }

    public void setEvtRank(Integer evtRank) {
        this.evtRank = evtRank;
    }

    public Integer getEvtClass() {
        return evtClass;
    }

    public void setEvtClass(Integer evtClass) {
        this.evtClass = evtClass;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", evtRank=").append(evtRank);
        sb.append("]");
        return sb.toString();
    }
}