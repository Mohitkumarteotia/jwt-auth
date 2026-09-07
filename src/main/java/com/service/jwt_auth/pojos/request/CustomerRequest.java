package com.service.jwt_auth.pojos.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerRequest {
    private String customerName;
    private String address;
    private String city;
    private Integer postalCode;
    private String country;
}
