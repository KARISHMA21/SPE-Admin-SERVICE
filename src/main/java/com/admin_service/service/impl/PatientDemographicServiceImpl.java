package com.admin_service.service.impl;

import com.admin_service.bean.entity.PatientEntity;
import com.admin_service.bean.model.UpdatedProfile;
import com.admin_service.bean.response.PatientDemographicsResponse;

import com.admin_service.repository.PatientRepository;
import com.admin_service.service.serviceinteface.PatientDemographicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Transactional
@Service
public class PatientDemographicServiceImpl implements PatientDemographicService {
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public PatientDemographicsResponse getPatientDemographics(String pid){
//        PatientDemographicsResponse patientDemographics=null;
        PatientEntity pe=null;
//        System.out.println("========================== PID : "+pid+"==========================");
//        patientDemographics= patientRepository.getPatientDemographicsById(pid);
        pe= patientRepository.getPatientDemographicsById(pid);
        System.out.println(pe);
        System.out.println("========================== PID : "+pid+"==========================");

        PatientDemographicsResponse finalRecords = new PatientDemographicsResponse();
        finalRecords.setPid(pe.getPid());
        finalRecords.setName(pe.getName());
        finalRecords.setGender(pe.getGender());
        finalRecords.setPhone(pe.getPhone());
        finalRecords.setAge(pe.getAge());
        finalRecords.setDob(pe.getDob());

        finalRecords.setEmail(pe.getEmail());
        finalRecords.setAddress(pe.getAddress());
        finalRecords.setUniqueid(pe.getUniqueid());
        if(pe.getDisabled()==true)
        finalRecords.setDisabled("Yes");
        else
            finalRecords.setDisabled("No");
        if(pe.getMinor_incapacitated()==true)
            finalRecords.setMinor_incapacitated("Yes");
        else
            finalRecords.setMinor_incapacitated("No");
        if(pe.getHaswebappaccess()==true)
            finalRecords.setHaswebappaccess("Yes");
        else
            finalRecords.setHaswebappaccess("No");
        if(pe.getGuardian()!=null)
            finalRecords.setGuardian_id(pe.getGuardian().getPid());
        else
            finalRecords.setGuardian_id(null);
        return finalRecords;


    }
    @Override
    public UpdatedProfile processProfileUpdate(UpdatedProfile patientprofile)
    {
        UpdatedProfile afterupdate=new UpdatedProfile();
        patientRepository.updateProfile(
                patientprofile.getPid(),
                patientprofile.getName(),
                patientprofile.getGender(),
                patientprofile.getPhone(),
                patientprofile.getEmail(),
                patientprofile.getAddress());

        return afterupdate;
    }



}
