package com.admin_service.service.impl;

import com.admin_service.bean.entity.EntityRes;
import com.admin_service.repository.EntityRepository;
import com.admin_service.service.serviceinteface.EntityRegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class EntityRegServiceImpl implements EntityRegService {
    @Autowired
    private EntityRepository entityRepository;

    @Override
    public List<EntityRes> getEntityById(String pid) {
        List<EntityRes> entityReg=null;

//        String q="select e from entity_reg  e JOIN entity_patient_mapping em on e.eid=em.eid  where em.pid="+pid;
        entityReg= entityRepository.getEntityById(pid);
        System.out.println(entityReg);
        return entityReg;
    }

    @Override
    public List<EntityRes> getEntityByProviderIDs(List<String> providerEids) {
        List<EntityRes> entityReg=null;
        System.out.println("The Entity IDs whose connection details are requester are: "+providerEids);
        entityReg= entityRepository.getEntityByProviderIDs(providerEids);
        System.out.println(entityReg);
        return entityReg;
    }
}
