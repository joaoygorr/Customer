package com.br.fiap.customer.services.customer;

import com.br.fiap.customer.record.customer.CustomerCreateDTO;
import com.br.fiap.customer.record.customer.CustomerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    Page<CustomerDTO> getAllCustomer(Pageable pageable);

    CustomerDTO getCustomerById(Long customerId);

    CustomerCreateDTO createCustomer(CustomerCreateDTO customerCreateDTO);

    void deleteCustomerById(Long id);
}
