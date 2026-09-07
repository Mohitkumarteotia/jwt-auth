package com.service.jwt_auth.pojos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerResponse {
    private Long CustomerID;
    private String customerName;
    private String address;
    private String city;
    private Integer postalCode;
    private String country;
}
