package com.itemManagement.pos.entity.item;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itemManagement.pos.entity.order.Order;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) //name cannot be blank at any case
    private String name;

    private Integer qty;

    private Double price;

    private String status;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ItemCategory itemCategory;

    @JsonIgnore
    @ManyToMany(mappedBy = "items")
    private Set<Order> orders = new HashSet<>();

}
