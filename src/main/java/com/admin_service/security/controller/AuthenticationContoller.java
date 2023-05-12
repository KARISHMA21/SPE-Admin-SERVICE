package com.admin_service.security.controller;


import com.admin_service.security.model.AuthRequest;
import com.admin_service.security.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationContoller {

    @Value("${patient.client.id}")
    private String patientClientId;

    @Value("${patient.client.secret}")
    private String patientClientSecret;

    @Value("${his.client.id}")
    private String hisClientId;

    @Value("${his.client.secret}")
    private String hisClientSecret;

    @Value("${cms.client.id}")
    private String cmsClientId;

    @Value("${cms.client.secret}")
    private String cmsClientSecret;

    @Autowired
    private JwtService jwtService;
    @PostMapping(value = "/authenticate-patient")
    public ResponseEntity<?> patientAuthenticate(@RequestBody AuthRequest authRequest){

        if(patientClientId.equals(authRequest.getUsername()) && patientClientSecret.equals(authRequest.getPassword())){
            String token=jwtService.createToken(patientClientId);
            System.out.println("Authenticating Patient");
            System.out.println(token);
            return ResponseEntity.ok(token);
        }

        return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping(value = "/authenticate-his")
    public ResponseEntity<?> hisAuthenticate(@RequestBody AuthRequest authRequest){

        if(hisClientId.equals(authRequest.getUsername()) && hisClientSecret.equals(authRequest.getPassword())){
            String token=jwtService.createToken(hisClientId);
            System.out.print("Trying HIS authentication \n");
            System.out.println(token);
            return ResponseEntity.ok(token);
        }

        return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping(value = "/authenticate-cms")
    public ResponseEntity<?> cmsAuthenticate(@RequestBody AuthRequest authRequest){

        if(cmsClientId.equals(authRequest.getUsername()) && cmsClientSecret.equals(authRequest.getPassword())){
            String token=jwtService.createToken(cmsClientId);
            System.out.print(token);
            return ResponseEntity.ok(token);
        }

        return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
    }
}
