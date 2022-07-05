package com.example.demo.entity;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;

@Data
@Entity
@Table(name="tbl_customer")
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(unique = true,nullable = false,length = 10)
    private String mobile;

    @Column
    private String address;
    @OneToMany(mappedBy = "customer")
    private List<Order> orderList;
}
