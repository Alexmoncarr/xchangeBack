package com.xchange.xchange.models;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "orders") // Cambio realizado aquí
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long orderId;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "description", nullable = false, length = 200)
    private String description;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "deliveryDate", nullable = false)
    private LocalDate deliveryDate;

    @Column(name = "expirationDate", nullable = false)
    private LocalDate expirationDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
