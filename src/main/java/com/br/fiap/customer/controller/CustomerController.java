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
        Page<CustomerDTO> getAllCustomer = this.customerService.getAllCustomer(pageable).map(CustomerDTO::toDto);
        return ResponseEntity.ok(getAllCustomer);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Search a customer", description = "Returns a customer based on id")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Long id) {
        final var response = this.customerService.getCustomerById(id);
        return ResponseEntity.ok(CustomerDTO.toDto(response));
    }

    @PostMapping
    @Operation(summary = "Create customer", description = "Creating a customer")
    public ResponseEntity<CustomerCreateDTO> postCustomer(@RequestBody @Valid CustomerCreateDTO customerCreateDTO) {
        final var response = this.customerService.createCustomer(CustomerCreateDTO.toEntity(customerCreateDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(CustomerCreateDTO.toDto(response));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete customer", description = "Delete a customer by id")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        this.customerService.deleteCustomerById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Edit customer", description = "Edit a customer by id")
    public ResponseEntity<CustomerCreateDTO> editCustomer(@PathVariable Long id,
            @RequestBody @Valid CustomerCreateDTO customerCreateDTO) {
        final var response = this.customerService.editCustomer(id, CustomerCreateDTO.toEntity(customerCreateDTO));
        return ResponseEntity.ok(CustomerCreateDTO.toDto(response));
    }
}
