package com.br.fiap.customer.record.customer;

import com.br.fiap.customer.module.Customer;
import com.br.fiap.customer.record.address.AddressCreateDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CustomerCreateDTO(
        @NotBlank(message = "Nome não pode estar em branco") String firstName,
        @NotBlank(message = "Sobrenome não pode estar em branco") String lastName,
        @Valid AddressCreateDTO address) {

    public static CustomerCreateDTO toDto(Customer customer) {
        return new CustomerCreateDTO(
                customer.getFirstName(),
                customer.getLastName(),
                AddressCreateDTO.toDto(customer.getAddress()));
    }

    public static Customer toEntity(CustomerCreateDTO customerDTO) {
        return new Customer(
                customerDTO.firstName,
                customerDTO.lastName,
                AddressCreateDTO.toEntity(customerDTO.address));
    }
}
