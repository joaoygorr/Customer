package com.br.fiap.customer.record.address;

import com.br.fiap.customer.module.Address;
import jakarta.validation.constraints.NotBlank;

public record AddressCreateDTO(
                               @NotBlank(message = "Cidade não pode estar em branco") String city,
                               @NotBlank(message = "CEP não pode estar em branco") String postalCode) {

    public static AddressCreateDTO toDto(Address address) {
        return new AddressCreateDTO(
                address.getCity(),
                address.getPostalCode());
    }

    public static Address toEntity(AddressCreateDTO addressCreateDTO) {
        return new Address(
                addressCreateDTO.city,
                addressCreateDTO.postalCode);
    }
}
