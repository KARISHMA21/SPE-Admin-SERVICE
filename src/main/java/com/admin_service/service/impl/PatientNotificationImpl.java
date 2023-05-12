package com.admin_service.service.impl;

import com.admin_service.bean.entity.PatientEntity;
import com.admin_service.repository.PatientRepository;
import com.admin_service.service.serviceinteface.PatientNotificationService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Properties;
@Service
public class PatientNotificationImpl implements PatientNotificationService {

    @Value("${cms.email.id}")
    private String myEmail;
    @Value("${cms.email.password}")
    private String myPassword;
    @Value("${cms.twilio.username}")
    private String twilioUsername;
    @Value("${cms.twilio.password}")
    private String twilioPassword;
    private JavaMailSenderImpl javaMailSender=new JavaMailSenderImpl();
    @Autowired
    private PatientRepository patientRepository;
    @Override
    public Integer notifyConsentAction(String message, String email, String patient_name, BigInteger phone) {
        try{//sending mail
            javaMailSender.setPort(587);
            javaMailSender.setHost("smtp.gmail.com");
            javaMailSender.setUsername(myEmail);
            javaMailSender.setPassword(myPassword);
            Properties properties = javaMailSender.getJavaMailProperties();
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.auth", "true");
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(email);
            mailMessage.setSubject("Hello " + patient_name + ". You have a mail from Consent Management System");

            mailMessage.setText(message);
            mailMessage.setFrom(myEmail);
            javaMailSender.send(mailMessage);

            //sending SMS
            Twilio.init(twilioUsername, twilioPassword);
            String phoneno = "+91" + phone;
            Message.creator(new PhoneNumber(phoneno),
                    new PhoneNumber("+16204551401"), "Hello "
                            + patient_name
                            + " You have the following message from Consent Management System:\n\n"
                            + message
            ).create();
        }
        catch(Exception e){
            System.out.println(e);
            return 0;
        }
        return 1;
    }

    @Override
    public PatientEntity fetchPatientToNotify(String pid) {
        return patientRepository.getPatientDemographicsById(pid);
    }
}
