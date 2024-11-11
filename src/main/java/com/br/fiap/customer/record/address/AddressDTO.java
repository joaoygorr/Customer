package com.br.fiap.customer.record.address;

import com.br.fiap.customer.module.Address;

public record AddressDTO(Long id,
                         String city,
                         String postalCode) {

    public static AddressDTO toDto(Address address) {
        return new AddressDTO(address.getId(),
                address.getCity(),
                address.getPostalCode());
    }
}
