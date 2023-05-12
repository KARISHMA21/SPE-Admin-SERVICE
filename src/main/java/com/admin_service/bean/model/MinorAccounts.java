package com.admin_service.bean.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MinorAccounts {

    private String pid ;
    private String name ;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
