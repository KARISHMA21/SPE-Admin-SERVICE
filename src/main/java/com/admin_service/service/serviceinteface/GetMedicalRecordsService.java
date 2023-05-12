package com.admin_service.service.serviceinteface;
import com.admin_service.bean.model.ListFinalRecord;
import com.admin_service.bean.response.EntityResponseback;
import org.springframework.http.ResponseEntity;


public interface GetMedicalRecordsService {
   public ResponseEntity<String> getMedicalRecords(EntityResponseback entityResponseback);

}
