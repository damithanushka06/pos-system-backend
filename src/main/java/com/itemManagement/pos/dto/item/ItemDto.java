package com.itemManagement.pos.dto.item;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {
    private Long id;
    private String name;
    private Double price;
    private Integer qty;
    private Long categoryId;
}
