package com.br.fiap.customer.record.customer;

import com.br.fiap.customer.module.Customer;
import com.br.fiap.customer.record.address.AddressDTO;

public record CustomerDTO(Long id,
                          String firstName,
                          String lastName,
                          AddressDTO address) {

    public static CustomerDTO toDto(Customer customer) {
        return new CustomerDTO(customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                AddressDTO.toDto(customer.getAddress()));
    }
}
