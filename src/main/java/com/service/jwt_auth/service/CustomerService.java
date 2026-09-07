package com.service.jwt_auth.service;

import com.service.jwt_auth.pojos.request.CustomerRequest;
import com.service.jwt_auth.pojos.response.CustomerResponse;

import java.util.List;

public interface CustomerService {
    CustomerResponse create(CustomerRequest customerRequest);

    CustomerResponse updateCompletely(Long id, CustomerRequest customerRequest);

    CustomerResponse updatePartially(Long id, CustomerRequest customerRequest);

    List<CustomerResponse> getAllCustomers(int page, int size);

    void delete(Long id);
}
