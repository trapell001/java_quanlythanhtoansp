package com.example.demo.entity;


import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name="tbl_order")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private LocalDateTime orderDateTime;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    @Column(name="total_amount")
    private Double totalAmount;

    @Column
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
