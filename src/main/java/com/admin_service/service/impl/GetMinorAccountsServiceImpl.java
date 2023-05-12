package com.admin_service.service.impl;

import com.admin_service.bean.entity.PatientEntity;
import com.admin_service.bean.model.MinorAccounts;
import com.admin_service.bean.response.PatientDemographicsResponse;
import com.admin_service.repository.PatientRepository;
import com.admin_service.service.serviceinteface.GetMinorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetMinorAccountsServiceImpl implements GetMinorsService {
    @Autowired
    private PatientRepository patientRepository;
    @Override
    public ResponseEntity<List<MinorAccounts>> getMinorAccounts(String pid){
//        PatientDemographicsResponse patientDemographics=null;
        List<MinorAccounts>minorAccounts= new ArrayList<>();
        List<PatientEntity> accounts=patientRepository.getMinors(pid);

//        System.out.println(accounts.get(0).getPid());

        for(PatientEntity p:accounts){

            MinorAccounts m=new MinorAccounts();
            m.setPid(p.getPid());
            m.setName(p.getName());

            minorAccounts.add(m);

        }

        return ResponseEntity.ok(minorAccounts);



    }


}
