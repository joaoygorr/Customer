package com.br.fiap.customer.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@Tag(name = "Customer", description = "Customer related endpoint")
public class CustomerController {
}
