package com.br.fiap.customer.services.customer;

import com.br.fiap.customer.module.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    Page<Customer> getAllCustomer(Pageable pageable);

    Customer getCustomerById(Long customerId);

    Customer createCustomer(Customer customer);

    void deleteCustomerById(Long id);

    Customer editCustomer(Long id, Customer customer);
}
