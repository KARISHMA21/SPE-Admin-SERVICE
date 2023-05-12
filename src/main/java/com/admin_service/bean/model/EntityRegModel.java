package com.admin_service.bean.model;

import com.admin_service.bean.entity.PatientEntity;

import java.util.Set;

public class EntityRegModel {
    private String eid;
    private  String ename;
    private  String etype;
    private String eip;
    private int port;
    Set<PatientEntity> patientIds;
}
