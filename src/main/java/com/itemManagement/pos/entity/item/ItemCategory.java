package com.itemManagement.pos.entity.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class ItemCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String status;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "itemCategory", cascade = CascadeType.ALL)
    private List<Item> items;
}
