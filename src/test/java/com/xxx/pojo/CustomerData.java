package com.xxx.pojo;

import java.util.List;

import io.cucumber.messages.types.Meta;
import lombok.Data;

@Data

public class CustomerData {

    private Meta meta;
    private List<Customer> customers;
}
