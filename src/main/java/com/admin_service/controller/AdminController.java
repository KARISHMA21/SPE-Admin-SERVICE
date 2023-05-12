package com.admin_service.controller;

import com.admin_service.bean.model.MinorAccounts;
import com.admin_service.bean.model.UpdatedProfile;
import com.admin_service.bean.response.PatientDemographicsResponse;
import com.admin_service.bean.response.PatientRegResponse;
import com.admin_service.service.serviceinteface.GetMinorsService;
import com.admin_service.service.serviceinteface.PatientDemographicService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@CrossOrigin(origins = "*",allowedHeaders = "*")

public class AdminController {

    private final PatientDemographicService patientDemographicService;
    private final GetMinorsService getMinorsService;
    public AdminController(PatientDemographicService patientDemographicService,GetMinorsService getMinorsService) {

        this.getMinorsService = getMinorsService;
        this.patientDemographicService = patientDemographicService;



    }
    @GetMapping("/get-minor-accounts/{pid}")
    public ResponseEntity<List<MinorAccounts>> getMinors(@PathVariable String pid ) {
        System.out.println("========================== PID : "+pid+"==========================");
        ResponseEntity<List<MinorAccounts>> response =getMinorsService.getMinorAccounts(pid);
//        patientDemographics.setPid(pid);
        return ResponseEntity.ok(response.getBody());
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

    @PostMapping("/update-patient-profile")
//    /his/"+eid+"/patient-register
    public ResponseEntity registerPatientRequest(@RequestBody UpdatedProfile updatedProfile) {
        System.out.println("Update profile Request Received with");
        System.out.println(       updatedProfile.toString());


        updatedProfile= patientDemographicService.processProfileUpdate(updatedProfile);
//        patientDemographics.setPid(pid);
//        if(!registeredPatient.isAlreadyRegistered())

//        JSONObject jsonObject = new JSONObject(pid);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(updatedProfile);
    }
}
