package com.admin_service.service.impl;

import com.admin_service.bean.entity.EntityReg;
import com.admin_service.bean.entity.EntityRes;
import com.admin_service.bean.entity.PatientEntity;
import com.admin_service.bean.model.FinalRecords;
import com.admin_service.bean.model.ListFinalRecord;
import com.admin_service.bean.response.MedicalRecordResponse;
import com.admin_service.bean.response.PatientDemographicsResponse;
import com.admin_service.bean.response.PatientRegResponse;
import com.admin_service.repository.EntityRepository;
import com.admin_service.repository.PatientRepository;
import com.admin_service.security.model.AuthRequest;
import com.admin_service.service.serviceinteface.PatientRegService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class PatientRegServiceImpl implements PatientRegService {

    private RestTemplate restTemplate_patient;
    @Value("${admin.client.id}")
    private String adminClientId;

    @Value("${admin.client.secret}")
    private String adminClientSecret;

    public PatientRegServiceImpl(@Value("${patient_service.base.url}"+"/api/v1")String patientBaseURL, RestTemplateBuilder builder){
        this.restTemplate_patient= builder.rootUri(patientBaseURL).build();
//        this.restTemplate_his=builder.build();
    }

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private EntityRepository entityRepository;

    @Override
    public PatientRegResponse processPatientReg(PatientDemographicsResponse patientDemo,String registering_entityId){


        PatientRegResponse registeredPatient=new PatientRegResponse();
        System.out.println("Patient identification ID is "+patientDemo.getUniqueid());
        Optional<PatientEntity> result = patientRepository.findByUniqueid(patientDemo.getUniqueid());
        PatientEntity updated=new PatientEntity();
        PatientEntity requested=new PatientEntity();

        if (result.isPresent()) {
            updated = result.get();
            registeredPatient.setAlreadyRegistered(true);
            registeredPatient.setPid(updated.getPid());
            registeredPatient.setName(updated.getName());
            registeredPatient.setEmail(updated.getEmail());
            registeredPatient.setPhone(updated.getPhone());
            // do something with the entity
        } else {


            String uniquePId = String.format("PAT%s", RandomStringUtils.randomNumeric(4));
//                , UUID.randomUUID().toString().replace("-", ""));
            // you could left this check
            while (patientRepository.existsById(uniquePId)) {
                String.format("PAT%s", RandomStringUtils.randomNumeric(4));


//            UUID.randomUUID().toString().replace("-", "");
            }
            System.out.println("The generated Patient Id is : "+ uniquePId);
            requested.setPid(uniquePId);
            requested.setUniqueid(patientDemo.getUniqueid());
            requested.setDob(patientDemo.getDob());

            requested.setName(patientDemo.getName());
            requested.setAge(patientDemo.getAge());
            requested.setGender(patientDemo.getGender());
            requested.setPhone(patientDemo.getPhone());
            requested.setEmail(patientDemo.getEmail());
            requested.setAddress(patientDemo.getAddress());

            requested.setMinor_incapacitated(Boolean.parseBoolean(patientDemo.getMinor_incapacitated()));
            if(patientDemo.getGuardian_id()!=null && !(patientDemo.getGuardian_id().equals(""))) {
                String guardianId= patientDemo.getGuardian_id();
                PatientEntity guardian = patientRepository.findById(guardianId).orElseThrow(() -> new RuntimeException("Guardian not found"));

//            guardian.setPid(patientDemo.getGuardian_id());
                requested.setGuardian(guardian);
//            patientRepository.save(guardian);
            }
            requested.setDisabled(Boolean.parseBoolean(patientDemo.getDisabled()));
            requested.setHaswebappaccess(Boolean.parseBoolean(patientDemo.getHaswebappaccess()));

            EntityReg VisitedEntity =entityRepository.findById(registering_entityId).orElseThrow(() -> new RuntimeException("Requesting Entity not found"));

            VisitedEntity.getPatientIds().add(requested);

            requested.getEntityIds().add(VisitedEntity);

            patientRepository.save(requested);



            registeredPatient.setPid(requested.getPid());
            registeredPatient.setName(requested.getName());
            registeredPatient.setEmail(requested.getEmail());
            registeredPatient.setAlreadyRegistered(false);
            registeredPatient.setPhone(requested.getPhone());




        }



        System.out.println("Registering -- 115 "+(registeredPatient.toString()));

        return registeredPatient;


    }

    @Override
   public void createCredentials(PatientRegResponse patientreg){
//
//        MedicalRecordResponse medicalRecordResponse =new MedicalRecordResponse();
//        List<FinalRecords> finalRecordsList=new ArrayList<>();
//        ResponseEntity<ListFinalRecord> finalRecordResponseEntity=null;
        //set data by making a rest api call
        String patient="/auth/authenticate-admin";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        //get the token for communication
        AuthRequest patientcred=new AuthRequest();

        patientcred.setUsername(adminClientId);
        patientcred.setPassword(adminClientSecret);

//                MultiValueMap<String, String> body = new LinkedMultiValueMap<String,String>();
//
//                body.add("username", adminClientId);
//                body.add("password", adminClientSecret);
        HttpEntity<?> request_token = new HttpEntity<>(patientcred, headers);
        System.out.println(request_token.getBody());

        String Token= restTemplate_patient.postForObject(patient,request_token, String.class,"");
        System.out.println(Token);


        headers.set("Authorization", "Bearer "+Token);


        patient="/patient/create-account";

        HttpEntity<?> request_records = new HttpEntity<>(patientreg, headers);
        ResponseEntity resp=restTemplate_patient.postForObject(patient,request_records, ResponseEntity.class,"");



        return;
    }

//    @Override
//    public void addPatientVisitMapping(PatientDemographicsResponse patientDemo){
////        EntityReg VisitedEntity = new EntityReg();
//
//        VisitedEntity;
//
//    }


}
