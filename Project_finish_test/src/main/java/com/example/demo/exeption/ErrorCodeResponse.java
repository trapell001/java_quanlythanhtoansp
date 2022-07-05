package com.example.demo.exeption;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Data
public class ErrorCodeResponse {
    private String code;
    private String message;
    private HttpStatus status;
}
