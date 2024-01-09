package com.itemManagement.pos.repository.item;
import com.itemManagement.pos.entity.item.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
}
