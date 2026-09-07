package com.service.jwt_auth.controller;

import com.service.jwt_auth.pojos.request.CustomerRequest;
import com.service.jwt_auth.pojos.response.CustomerResponse;
import com.service.jwt_auth.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CustomerResponse> create(@RequestBody CustomerRequest customerRequest) {
        CustomerResponse customerResponse = customerService.create(customerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerResponse);
    }

    @PutMapping("/update-completely/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CustomerResponse> updateCompletely(@PathVariable Long id, @RequestBody CustomerRequest customerRequest) {
        CustomerResponse customerResponse = customerService.updateCompletely(id, customerRequest);
        return ResponseEntity.status(HttpStatus.OK).body(customerResponse);
    }

    @PatchMapping("/update-partially/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CustomerResponse> updatePartially(@PathVariable Long id, @RequestBody CustomerRequest customerRequest) {
        CustomerResponse customerResponse = customerService.updatePartially(id, customerRequest);
        return ResponseEntity.status(HttpStatus.OK).body(customerResponse);
    }

    @GetMapping("/get-all")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<CustomerResponse>> getAll(@RequestParam int page, @RequestParam int size) {
        List<CustomerResponse> allCustomers = customerService.getAllCustomers(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(allCustomers);
    }

    @PostMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Customer is deleted successfully");
    }

}
