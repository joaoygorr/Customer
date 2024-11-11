package com.br.fiap.customer.module;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @Column(name = "id_address")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;

    private String city;

    private String state;

    @Column(nullable = false)
    private String postalCode;

    private String country;

    public Address(Long id, String city, String postalCode) {
        this.id = id;
        this.city = city;
        this.postalCode = postalCode;
    }
}
