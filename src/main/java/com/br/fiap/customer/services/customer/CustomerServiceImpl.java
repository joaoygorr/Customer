package com.br.fiap.customer.services.customer;

import com.br.fiap.customer.exceptions.Exception404;
import com.br.fiap.customer.module.Customer;
import com.br.fiap.customer.record.address.AddressCreateDTO;
import com.br.fiap.customer.record.customer.CustomerCreateDTO;
import com.br.fiap.customer.record.customer.CustomerDTO;
import com.br.fiap.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Page<CustomerDTO> getAllCustomer(Pageable pageable) {
        Page<Customer> customers = this.customerRepository.findAll(pageable);
        return customers.map(CustomerDTO::toDto);
    }

    @Override
    public CustomerDTO getCustomerById(Long categoryId) {
        Customer customer = this.customerRepository.findById(categoryId)
                .orElseThrow(() -> new Exception404("Cliente com o id " + categoryId + " não encontrado!"));
        return CustomerDTO.toDto(customer);
    }

    @Override
    public CustomerCreateDTO createCustomer(CustomerCreateDTO customerCreateDTO) {
        Customer customer = this.customerRepository.save(CustomerCreateDTO.toEntity(customerCreateDTO));
        return CustomerCreateDTO.toDto(customer);
    }

    @Override
    public void deleteCustomerById(Long id) {
        if (customerRepository.existsById(id)) {
            this.customerRepository.deleteById(id);
        } else {
            throw new Exception404("Cliente com o id " + id + " não encontrado!");
        }
    }

    @Override
    public CustomerCreateDTO editCustomer(Long id, CustomerCreateDTO customerCreateDTO) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new Exception404("Cliente não encontrado com ID: " + id));

        existingCustomer.setFirstName(customerCreateDTO.firstName());
        existingCustomer.setLastName(customerCreateDTO.lastName());
        existingCustomer.setAddress(AddressCreateDTO.toEntity(customerCreateDTO.address()));

        Customer updatedCustomer = customerRepository.save(existingCustomer);

        return CustomerCreateDTO.toDto(updatedCustomer);
    }
}
