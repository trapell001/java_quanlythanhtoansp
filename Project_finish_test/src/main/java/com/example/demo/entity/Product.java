package com.example.demo.entity;


import lombok.Data;


import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name="tbl_product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 100)
    private String name;

    @Column(nullable = false)
    private Double price;
    @Transient
    private Long avaiable;
}
