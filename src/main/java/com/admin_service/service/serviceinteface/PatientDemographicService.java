package com.admin_service.service.serviceinteface;

import com.admin_service.bean.entity.PatientEntity;
import com.admin_service.bean.model.UpdatedProfile;
import com.admin_service.bean.response.PatientDemographicsResponse;

public interface PatientDemographicService {

    public PatientDemographicsResponse getPatientDemographics(String pid);
    public UpdatedProfile processProfileUpdate(UpdatedProfile patientprofile);

}
