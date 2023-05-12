package com.admin_service.bean.response;

import com.admin_service.bean.entity.EntityRes;

import java.util.List;

public class EntityResponseback {
    public List<EntityRes> getEntityRegs() {
        return entityRegs;
    }

    public void setEntityRegs(List<EntityRes> entityRegs) {
        this.entityRegs = entityRegs;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "EntityResponseback{" +
                "entityRegs=" + entityRegs +
                ", pid='" + pid + '\'' +
                '}';
    }

    List<EntityRes> entityRegs;
    private String pid;

}
