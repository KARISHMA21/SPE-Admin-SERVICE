package com.admin_service.service.serviceinteface;

import com.admin_service.bean.entity.EntityRes;

import java.util.List;

public interface EntityRegService {
   public List<EntityRes> getEntityById(String pid);
   public List<EntityRes> getEntityByProviderIDs(List<String> providerEids);
}
