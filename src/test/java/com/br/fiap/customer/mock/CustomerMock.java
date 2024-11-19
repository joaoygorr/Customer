package com.br.fiap.customer.mock;

import java.time.LocalDate;

import com.br.fiap.customer.module.Address;
import com.br.fiap.customer.module.Customer;

public class CustomerMock {

    public static Customer mock() {
        final var customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("firstName");
        customer.setLastName("lastName");
        customer.setGender("teste");
        customer.setBirthDate(LocalDate.now());
        customer.setEmail("email");
        customer.setPhoneNumber("phoneNumber");
        customer.setAddress(addressMock());
        return customer;
    }

    private static Address addressMock() {
        return new Address(
                1L,
                "street",
                "city",
                "state",
                "postalCode",
                "country");
    }

}
