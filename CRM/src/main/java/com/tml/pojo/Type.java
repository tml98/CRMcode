package com.tml.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Type {
    private String code;
    private String name;
    private String description;

    private List<Value> values;
}
