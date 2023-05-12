package com.admin_service.service.serviceinteface;

import com.admin_service.bean.response.PatientDemographicsResponse;
import com.admin_service.bean.response.PatientRegResponse;

public interface PatientRegService {
    public PatientRegResponse processPatientReg(PatientDemographicsResponse patientDemo,String registering_entityId);

    void createCredentials(PatientRegResponse patientreg);
}
