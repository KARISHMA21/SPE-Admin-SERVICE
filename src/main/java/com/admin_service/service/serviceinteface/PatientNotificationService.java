package com.admin_service.service.serviceinteface;

import com.admin_service.bean.entity.PatientEntity;

import java.math.BigInteger;

public interface PatientNotificationService {
    public Integer notifyConsentAction(String message, String email, String patient_name, BigInteger phone);

    public PatientEntity fetchPatientToNotify(String pid);
}
