package com.itemManagement.pos.controller.category;

import com.itemManagement.pos.entity.item.ItemCategory;
import com.itemManagement.pos.service.itemCategory.ItemCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class ItemCategoryController {
    @Autowired
    private ItemCategoryService itemCategoryService;

    @PostMapping("/create_category")
    public ResponseEntity<Object> createCategory(@RequestBody ItemCategory itemCategory){
         return itemCategoryService.createCategory(itemCategory);
    }

    @GetMapping("/get_category_list")
    public ResponseEntity<Object> getAllCategories(){
        return itemCategoryService.getAllCategories();
    }

    @PutMapping("/update_category/{id}")
    public ResponseEntity<Object> updateCategory(@PathVariable Long id, @RequestBody ItemCategory itemCategory){
        return itemCategoryService.updateCategory(id, itemCategory);
    }

    @DeleteMapping("/delete_category/{id}")
    public ResponseEntity<Object> deleteItemCategory(@PathVariable Long id){
        return itemCategoryService.deleteCategory(id);
    }
}
