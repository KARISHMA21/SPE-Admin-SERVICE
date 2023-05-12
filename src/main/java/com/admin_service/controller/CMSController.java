package com.admin_service.controller;

import com.admin_service.bean.entity.EntityRes;
import com.admin_service.bean.entity.PatientEntity;
import com.admin_service.bean.model.ToastRequestBody;
import com.admin_service.bean.response.EntityResponseback;
import com.admin_service.service.serviceinteface.EntityRegService;
import com.admin_service.service.serviceinteface.GetMedicalRecordsService;
import com.admin_service.service.serviceinteface.PatientNotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/cms")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class CMSController {
    private final EntityRegService entityRegService;
    private final PatientNotificationService patientNotificationService;

    private final GetMedicalRecordsService getMedicalRecordsService;
    public CMSController(EntityRegService entityRegService,
                         GetMedicalRecordsService getMedicalRecordsService,
                         PatientNotificationService patientNotificationService) {

        this.getMedicalRecordsService = getMedicalRecordsService;
        this.entityRegService = entityRegService;
        this.patientNotificationService = patientNotificationService;
    }


    @CrossOrigin(origins = "*",allowedHeaders = "*")
    @GetMapping ("/get-medical-records/{pid}")
    public ResponseEntity<String> getEntityById(@PathVariable String pid ) {

        EntityResponseback entityResponse =new EntityResponseback();
        entityResponse.setEntityRegs(entityRegService.getEntityById(pid));
        entityResponse.setPid(pid);
        //System.out.println(entityResponse.toString());
        ResponseEntity<String> medicalRecordResponse=getMedicalRecordsService.getMedicalRecords(entityResponse);

        return ResponseEntity.ok().body(medicalRecordResponse.getBody());
    }

    @PostMapping ("/get-consented-hospital-detail")
    public ResponseEntity<EntityResponseback> getEntityByProviderId(@RequestParam(value = "provider-ids") List<String> providerEids ) {

        EntityResponseback entityResponse =new EntityResponseback();
        entityResponse.setEntityRegs(entityRegService.getEntityByProviderIDs(providerEids));
//        entityResponse.setPid("PAT01");
        return ResponseEntity.ok(entityResponse);
    }

    @GetMapping("/get-patient-mapping/{pid}")
    public ResponseEntity<List<EntityRes>> getPatientMapping(@PathVariable String pid){
        List<EntityRes> entityRegList = entityRegService.getEntityById(pid);
        return ResponseEntity.ok().body(entityRegList);
    }

    @PostMapping ("/notify")
    public ResponseEntity<String> requestToast(@RequestBody ToastRequestBody toastRequestBody) {
        PatientEntity patient = patientNotificationService.fetchPatientToNotify(toastRequestBody.getPid());
        Integer status = patientNotificationService.notifyConsentAction(
                toastRequestBody.getMessage(),
                patient.getEmail(),
                patient.getName(),
                patient.getPhone());
        if(status==1){
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.badRequest().build();
        }
    }
}

