package com.br.fiap.customer.controller;

import com.br.fiap.customer.record.customer.CustomerCreateDTO;
import com.br.fiap.customer.record.customer.CustomerDTO;
import com.br.fiap.customer.services.customer.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@Tag(name = "Customer", description = "Customer related endpoint")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    @Operation(summary = "list all customers", description = "Returns a list of customers")
    public ResponseEntity<Page<CustomerDTO>> getAll(Pageable pageable) {
        Page<CustomerDTO> getAllCustomer = this.customerService.getAllCustomer(pageable);
        return ResponseEntity.ok(getAllCustomer);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Search a customer", description = "Returns a customer based on id")
    public ResponseEntity<CustomerDTO> getCategory(@PathVariable Long id) {
        CustomerDTO getCustomer = this.customerService.getCustomerById(id);
        return ResponseEntity.ok(getCustomer);
    }

    @PostMapping
    @Operation(summary = "Create customer", description = "Creating a customer")
    public ResponseEntity<CustomerCreateDTO> postCategory(@RequestBody @Valid CustomerCreateDTO customerCreateDTO) {
        CustomerCreateDTO createCustomer  = this.customerService.createCustomer(customerCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createCustomer);
    }
}
