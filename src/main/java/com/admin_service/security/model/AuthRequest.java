package com.admin_service.security.model;


    import lombok.Data;

    @Data
    public class AuthRequest {

        private String username;

        private String password;
    }

