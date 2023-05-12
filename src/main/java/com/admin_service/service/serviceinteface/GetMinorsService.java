package com.admin_service.service.serviceinteface;

import com.admin_service.bean.model.MinorAccounts;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GetMinorsService {
    public ResponseEntity<List<MinorAccounts>> getMinorAccounts(String pid);

}
