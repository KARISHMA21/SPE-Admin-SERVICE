package com.admin_service.controller;

import com.admin_service.bean.response.PatientDemographicsResponse;
import com.admin_service.bean.response.PatientRegResponse;
import com.admin_service.service.serviceinteface.PatientDemographicService;
import com.admin_service.service.serviceinteface.PatientRegService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/v1/patient")
public class PatientController {
//    private final EntityRegService entityRegService;
//
//    public EntityController(EntityRegService entityRegService, GetMedicalRecordsService getMedicalRecordsService) {
//
//        this.getMedicalRecordsService = getMedicalRecordsService;
//    }

    private final PatientRegService patientRegsvc;
    public PatientController( PatientRegService patientRegsvc) {
        this.patientRegsvc=patientRegsvc;
    }

///api/v1/his/patient-register/
    @PostMapping("/{eid}/patient-register")
//    /his/"+eid+"/patient-register
    public ResponseEntity registerPatientRequest(@RequestBody PatientDemographicsResponse patientDemo,@PathVariable String eid) {
        System.out.println("Register Request Received with");
        System.out.println(       patientDemo.toString());
        PatientRegResponse registeredPatient=new PatientRegResponse();

        registeredPatient =patientRegsvc.processPatientReg(patientDemo,eid);
//        patientDemographics.setPid(pid);
//        if(!registeredPatient.isAlreadyRegistered())
        System.out.println("Registering Patient - "+registeredPatient.getPid());
            patientRegsvc.createCredentials(registeredPatient);
//        JSONObject jsonObject = new JSONObject(pid);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(registeredPatient);
    }



}

