package com.itemManagement.pos.service_impl.item;

import com.itemManagement.pos.dto.item.ItemDto;
import com.itemManagement.pos.entity.item.Item;
import com.itemManagement.pos.entity.item.ItemCategory;
import com.itemManagement.pos.repository.itemCategory.ItemCategoryRepository;
import com.itemManagement.pos.repository.item.ItemRepository;
import com.itemManagement.pos.service.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemCategoryRepository itemCategoryRepository;

    @Override
    public Item createItem(ItemDto itemDto) {
        ItemCategory itemCategory = itemCategoryRepository.findById(itemDto.getCategoryId()).orElse(null);;
        if(itemCategory != null){
            Item item = new Item();
            item.setStatus("A");
            item.setName(itemDto.getName());
            item.setPrice(itemDto.getPrice());
            item.setQty(itemDto.getQty());
            item.setItemCategory(itemCategory);
            return itemRepository.save(item);
        } else {
            return null;
        }
    }

    @Override
    public ResponseEntity<Object> updateItem(Long id, ItemDto itemDto) {
        Item exsistingItem = itemRepository.findById(id).orElse(null);
        if(exsistingItem != null){
            ItemCategory itemCategory = itemCategoryRepository.findById(id).orElse(null);
            exsistingItem.setName(itemDto.getName());
            exsistingItem.setItemCategory(itemCategory);
            exsistingItem.setPrice(itemDto.getPrice());
            exsistingItem.setQty(itemDto.getQty());
            itemRepository.save(exsistingItem);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deleteItem(Long id) {
        Optional<Item> item = itemRepository.findById(id);
        if(item.isPresent()){
            Item updateItem = item.get();
            updateItem.setStatus("D");
            itemRepository.save(updateItem);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getItemList() {
        return new ResponseEntity<>(itemRepository.findItemByStatusNotDeleted(),HttpStatus.OK);
    }
}
