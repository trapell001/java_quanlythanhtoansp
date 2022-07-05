package com.example.demo.exeption;

import lombok.Data;

@Data
public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(){
        
    }
}
