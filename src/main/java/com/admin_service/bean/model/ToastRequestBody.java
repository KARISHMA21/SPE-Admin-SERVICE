package com.admin_service.bean.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ToastRequestBody {
    private String message;
    private String pid;
}
