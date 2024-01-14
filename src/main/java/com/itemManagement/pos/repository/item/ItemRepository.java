package com.itemManagement.pos.repository.item;
import com.itemManagement.pos.entity.item.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {

    @Query("select item from Item item where item.status <> 'D'")
    public List<Item> findItemByStatusNotDeleted();
}
