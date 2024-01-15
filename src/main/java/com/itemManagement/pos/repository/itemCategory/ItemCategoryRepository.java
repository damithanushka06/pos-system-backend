package com.itemManagement.pos.repository.itemCategory;

import com.itemManagement.pos.entity.item.ItemCategory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemCategoryRepository extends CrudRepository<ItemCategory, Long> {

    @Query("select itemCat from ItemCategory itemCat where itemCat.status <> 'D'")
    public List<ItemCategory> findItemCategoryNotDeleted();
}
