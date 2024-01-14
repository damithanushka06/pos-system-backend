package com.itemManagement.pos.service.itemCategory;

import com.itemManagement.pos.entity.item.ItemCategory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ItemCategoryService {
    ResponseEntity<Object> createCategory(ItemCategory itemCategory);

    ResponseEntity<Object> updateCategory(Long id, ItemCategory itemCategory);

    ResponseEntity<Object> deleteCategory(Long id);

    ResponseEntity<Object> getAllCategories();
}
