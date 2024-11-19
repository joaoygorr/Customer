package com.br.fiap.customer.services.customer;

import com.br.fiap.customer.exceptions.Exception404;
import com.br.fiap.customer.module.Customer;
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
    public Page<Customer> getAllCustomer(Pageable pageable) {
        return this.customerRepository.findAll(pageable);
    }

    @Override
    public Customer getCustomerById(Long id) {
        return this.customerRepository.findById(id)
                .orElseThrow(() -> new Exception404("Cliente com o id " + id + " não encontrado!"));
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return this.customerRepository.save(customer);
    }

    @Override
    public void deleteCustomerById(Long id) {
        if (this.getCustomerById(id) != null) {
            this.customerRepository.deleteById(id);
        }
    }

    @Override
    public Customer editCustomer(Long id, Customer customer) {
        Customer existingCustomer = this.getCustomerById(id);

        existingCustomer.setFirstName(customer.getFirstName());
        existingCustomer.setLastName(customer.getLastName());
        existingCustomer.setAddress(customer.getAddress());

        return customerRepository.save(existingCustomer);
    }
}
