package com.blog.userservice.domain;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Address {
    private String state;
    private String city;
    private String zip;
    private String street;
}
