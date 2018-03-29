package com.lzh.account.model.entity.generator;

import java.util.Date;

public class TAccountUserBalanceTcc {
    private Long id;

    private Date createTime;

    private Date updateTime;

    private Date deleteTime;

    private Integer deleteFlag;

    private Date expireTime;

    private Long amount;

    private Integer status;

    private Long tAccountUserId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long gettAccountUserId() {
        return tAccountUserId;
    }

    public void settAccountUserId(Long tAccountUserId) {
        this.tAccountUserId = tAccountUserId;
    }
}