package com.itemManagement.pos.controller.item;

import com.itemManagement.pos.dto.item.ItemDto;
import com.itemManagement.pos.service.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*") //allowing cross origin to all
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/create_item")
    public ResponseEntity<Object> createItem(@RequestBody ItemDto itemDto){
        try {
            return  ResponseEntity.status(201).body(itemService.createItem(itemDto));
        }catch (Exception e){
            return ResponseEntity.status(207).body("Failed to create the item");
        }
    }

    @GetMapping("/get_all_item")
    public ResponseEntity<Object> getAllItems(){
        return itemService.getItemList();
    }

    @PutMapping("/update_item/{id}")
    public ResponseEntity<Object> updateItem(@PathVariable Long id, @RequestBody ItemDto itemDto){
        return itemService.updateItem(id, itemDto);
    }

    @DeleteMapping("/delete_item/{id}")
    public ResponseEntity<Object> deleteItem(@PathVariable Long id){
        return itemService.deleteItem(id);
    }

}
