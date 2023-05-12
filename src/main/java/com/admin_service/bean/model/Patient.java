package com.admin_service.bean.model;

import java.math.BigInteger;

public class Patient implements java.io.Serializable {
    private String pid;
    private String name;
    private BigInteger adhaar;
    private String dob;
    private int age;
    private String gender;
    private BigInteger phone;
    private String address;

    private String email;
    private int minor;
    private int disabled;
    private int iscapable;
//    private Patient patient;
    private String gaurdian_id;
    private String username;
    private String password;


}
