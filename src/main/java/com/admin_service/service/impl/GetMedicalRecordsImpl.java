package com.admin_service.service.impl;//package com.example.backend.service;
//

import com.admin_service.bean.entity.EntityRes;
import com.admin_service.bean.model.FinalRecords;
import com.admin_service.bean.model.ListFinalRecord;
import com.admin_service.bean.response.EntityResponseback;
import com.admin_service.bean.response.MedicalRecordResponse;
import com.admin_service.security.model.AuthRequest;
import com.admin_service.service.serviceinteface.GetMedicalRecordsService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GetMedicalRecordsImpl implements GetMedicalRecordsService {
//  @Autowired
    private RestTemplate restTemplate_cms;
    @Value("${admin.client.id}")
    private String adminClientId;

    @Value("${admin.client.secret}")
    private String adminClientSecret;
    public GetMedicalRecordsImpl(@Value("${cms_service.base.url}"+"/api/v1")String cmsBaseURL, RestTemplateBuilder builder){
        this.restTemplate_cms= builder.rootUri(cmsBaseURL).build();
//        this.restTemplate_his=builder.build();
    }

 public ResponseEntity<String> getMedicalRecords(EntityResponseback entityResponse){
                MedicalRecordResponse medicalRecordResponse =new MedicalRecordResponse();
                 List<FinalRecords> finalRecordsList=new ArrayList<>();
                ResponseEntity<String> finalRecordResponseEntity=null;
                 //set data by making a rest api call
                 String cms="/auth/authenticate-admin";
                 HttpHeaders headers = new HttpHeaders();
                 headers.setContentType(MediaType.APPLICATION_JSON);
                headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
                //get the token for communication
                 AuthRequest entitycred=new AuthRequest();

                 entitycred.setUsername(adminClientId);
                 entitycred.setPassword(adminClientSecret);

//                MultiValueMap<String, String> body = new LinkedMultiValueMap<String,String>();
//
//                body.add("username", adminClientId);
//                body.add("password", adminClientSecret);
                HttpEntity<?> request_token = new HttpEntity<>(entitycred, headers);
                System.out.println(request_token.getBody());

                String Token= restTemplate_cms.postForObject(cms,request_token, String.class,"");
                System.out.println(Token);
                //Getting the medical records
                String pid=entityResponse.getPid();
                headers.set("Authorization", "Bearer "+Token);

                List<EntityRes> adminResponse =entityResponse.getEntityRegs();
                cms="/his/get-medical-records/"+pid;
                List<EntityRes> entity =  adminResponse;
                HttpEntity<?> request_records = new HttpEntity<>(entity, headers);
                finalRecordResponseEntity= restTemplate_cms.postForEntity(cms,request_records, String.class,pid);



                 return  finalRecordResponseEntity;

 }


}
