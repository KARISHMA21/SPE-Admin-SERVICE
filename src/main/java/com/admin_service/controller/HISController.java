package com.admin_service.controller;

import com.admin_service.bean.response.PatientDemographicsResponse;
import com.admin_service.bean.response.PatientRegResponse;
import com.admin_service.service.serviceinteface.PatientDemographicService;
import com.admin_service.service.serviceinteface.PatientRegService;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequestMapping(value = "/api/v1/his")
public class HISController {
//    private final EntityRegService entityRegService;
//
//    public EntityController(EntityRegService entityRegService, GetMedicalRecordsService getMedicalRecordsService) {
//
//        this.getMedicalRecordsService = getMedicalRecordsService;
//    }
    private final PatientDemographicService patientDemographicService;
    private final PatientRegService patientRegsvc;
    public HISController(PatientDemographicService patientDemographicService, PatientRegService patientRegsvc) {
        this.patientDemographicService = patientDemographicService;
        this.patientRegsvc=patientRegsvc;
    }
    @PostMapping("/get-patient-demographics/{pid}")
    public ResponseEntity<PatientDemographicsResponse> getPatientDemographicsById(@PathVariable String pid ) {
        System.out.println("========================== PID : "+pid+"==========================");
        PatientDemographicsResponse patientDemographics =new PatientDemographicsResponse();
//        patientDemographics.setEntityRegs(patientDemographicService.getPatientDemographics(pid));
        patientDemographics=patientDemographicService.getPatientDemographics(pid);
//        patientDemographics.setPid(pid);
        return ResponseEntity.ok(patientDemographics);
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

