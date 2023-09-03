package com.xxx.pojo;

import io.cucumber.messages.internal.com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Search {

    @JsonProperty("content")
    private List<Spartan> content;
    private int totalElement;


}
