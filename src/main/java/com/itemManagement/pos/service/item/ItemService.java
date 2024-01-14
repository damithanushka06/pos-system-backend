package com.itemManagement.pos.service.item;

import com.itemManagement.pos.dto.item.ItemDto;
import com.itemManagement.pos.entity.item.Item;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ItemService {
    Item createItem(ItemDto itemDto);

    ResponseEntity<Object> updateItem(Long id, ItemDto itemDto);

    ResponseEntity<Object> deleteItem(Long id);

    ResponseEntity<Object> getItemList();
}
