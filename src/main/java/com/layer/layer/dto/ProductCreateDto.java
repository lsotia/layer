package com.layer.layer.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCreateDto {
    private String name;
    private String brand;
    private Integer price;
    private String image;
    private String description;

}
