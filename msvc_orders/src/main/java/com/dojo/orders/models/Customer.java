package com.dojo.orders.models;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class Customer{
    private Long id;
    private String name;
    private String lastName;
    private String username;
    private String cellPhone;
}
