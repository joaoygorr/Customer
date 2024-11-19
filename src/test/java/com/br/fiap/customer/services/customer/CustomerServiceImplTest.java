package com.br.fiap.customer.services.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.br.fiap.customer.exceptions.Exception404;
import com.br.fiap.customer.mock.CustomerMock;
import com.br.fiap.customer.module.Customer;
import com.br.fiap.customer.record.customer.CustomerCreateDTO;
import com.br.fiap.customer.repository.CustomerRepository;

class CustomerServiceImplTest {

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerService = new CustomerServiceImpl(customerRepository);
    }

    @Test
    void testCreateCustomer() {
        when(this.customerRepository.save(any(Customer.class))).thenReturn(CustomerMock.mock());

        final var response = this.customerService.createCustomer(CustomerCreateDTO.toDto(CustomerMock.mock()));

        assertNotNull(response);
    }

    @Test
    void testDeleteCustomerByIdSuccess() {
        when(this.customerRepository.existsById(anyLong())).thenReturn(true);

        this.customerService.deleteCustomerById(1L);

        verify(this.customerRepository).deleteById(anyLong());
    }

    @Test
    void testDeleteCustomerByIdFailed() {
        when(this.customerRepository.existsById(anyLong())).thenReturn(false);

        final var error = assertThrows(Exception404.class, () -> this.customerService.deleteCustomerById(1L));

        assertNotNull(error);
        assertEquals("Cliente com o id 1 não encontrado!", error.getMessage());
    }

    @Test
    void testEditCustomer() {
        when(this.customerRepository.findById(anyLong())).thenReturn(Optional.of(CustomerMock.mock()));
        when(this.customerRepository.save(any(Customer.class))).thenReturn(CustomerMock.mock());

        final var response = this.customerService.editCustomer(1l, CustomerCreateDTO.toDto(CustomerMock.mock()));

        assertNotNull(response);
    }

    @Test
    void testGetAllCustomer() {
        when(this.customerRepository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(CustomerMock.mock())));

        final var response = this.customerService.getAllCustomer(Pageable.ofSize(1));

        assertNotNull(response);
        assertFalse(response.isEmpty());
    }

    @Test
    void testGetCustomerByIdSuccess() {
        when(this.customerRepository.findById(anyLong())).thenReturn(Optional.of(CustomerMock.mock()));

        final var response = this.customerService.getCustomerById(1L);

        assertNotNull(response);
    }

    @Test
    void testGetCustomerByIdFailed() {
        when(this.customerRepository.findById(anyLong())).thenReturn(Optional.empty());

        final var error = assertThrows(Exception404.class, () -> this.customerService.getCustomerById(1L));

        assertNotNull(error);
        assertEquals("Cliente com o id 1 não encontrado!", error.getMessage());
    }
}
