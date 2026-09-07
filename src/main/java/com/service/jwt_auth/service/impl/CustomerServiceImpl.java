package com.service.jwt_auth.service.impl;

import com.service.jwt_auth.entity.Customer;
import com.service.jwt_auth.pojos.request.CustomerRequest;
import com.service.jwt_auth.pojos.response.CustomerResponse;
import com.service.jwt_auth.repository.CustomerRepository;
import com.service.jwt_auth.service.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public CustomerResponse create(CustomerRequest customerRequest) {
        Customer customer = Customer.builder()
                .customerName(customerRequest.getCustomerName())
                .address(customerRequest.getAddress())
                .city(customerRequest.getCity())
                .postalCode(customerRequest.getPostalCode())
                .country(customerRequest.getCountry())
                .build();
        customer = customerRepository.save(customer);
        return CustomerResponse.builder()
                .CustomerID(customer.getCustomerID())
                .customerName(customer.getCustomerName())
                .address(customer.getAddress())
                .city(customer.getCity())
                .postalCode(customer.getPostalCode())
                .country(customer.getCountry())
                .build();
    }

    @Override
    @Transactional
    public CustomerResponse updateCompletely(Long id, CustomerRequest customerRequest) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer is not exists"));
        customer.setCustomerName(customerRequest.getCustomerName());
        customer.setAddress(customerRequest.getAddress());
        customer.setCity(customerRequest.getCity());
        customer.setPostalCode(customerRequest.getPostalCode());
        customer.setCountry(customerRequest.getCountry());

        Customer save = customerRepository.save(customer);
        return CustomerResponse.builder()
                .CustomerID(save.getCustomerID())
                .customerName(save.getCustomerName())
                .address(save.getAddress())
                .city(save.getCity())
                .postalCode(save.getPostalCode())
                .country(save.getCountry())
                .build();
    }

    @Override
    @Transactional
    public CustomerResponse updatePartially(Long id, CustomerRequest customerRequest) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer is not exists"));
        customer.setAddress(customerRequest.getAddress());
        Customer save = customerRepository.save(customer);
        return CustomerResponse.builder()
                .CustomerID(save.getCustomerID())
                .customerName(save.getCustomerName())
                .address(save.getAddress())
                .city(save.getCity())
                .postalCode(save.getPostalCode())
                .country(save.getCountry())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerResponse> getAllCustomers(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Customer> customers = customerRepository.findAll(pageRequest);
        return customers.stream().map(customer -> CustomerResponse.builder()
                .CustomerID(customer.getCustomerID())
                .customerName(customer.getCustomerName())
                .address(customer.getAddress())
                .city(customer.getCity())
                .postalCode(customer.getPostalCode())
                .country(customer.getCountry())
                .build()).toList();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer is not exists"));
        customerRepository.delete(customer);
    }
}
