package com.example.demo.entity;


import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name="tbl_order_item")
public class OrderItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name="order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double amount;
}
