package com.itemManagement.pos.controller.category;

import com.itemManagement.pos.entity.item.ItemCategory;
import com.itemManagement.pos.service.itemCategory.ItemCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ItemCategoryController {
    @Autowired
    private ItemCategoryService itemCategoryService;

    @PostMapping("/create_category")
    public ResponseEntity<Object> createCategory(@RequestBody ItemCategory itemCategory){
         return itemCategoryService.createCategory(itemCategory);
    }

    @PutMapping("/update_category")
    public ResponseEntity<Object> updateCategory(@RequestBody ItemCategory itemCategory){
        return itemCategoryService.updateCategory(itemCategory);
    }

    @DeleteMapping("/delete_category")
    public ResponseEntity<Object> deleteItemCategory(@RequestParam Long id){
        return itemCategoryService.deleteCategory(id);
    }
}
