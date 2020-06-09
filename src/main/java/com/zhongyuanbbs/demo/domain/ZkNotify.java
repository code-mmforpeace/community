package com.zhongyuanbbs.demo.domain;

import java.util.Date;
import java.io.Serializable;

/**
 * 提醒(ZkNotify)实体类
 *
 * @author makejava
 * @since 2020-06-06 09:54:33
 */
public class ZkNotify implements Serializable {
    private static final long serialVersionUID = 799036010570175980L;
    
    private Integer id;
    
    private Integer creater;
    
    private Integer receiver;
    
    private Integer type;
    
    private Date createTime;
    
    private Integer status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCreater() {
        return creater;
    }

    public void setCreater(Integer creater) {
        this.creater = creater;
    }

    public Integer getReceiver() {
        return receiver;
    }

    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}