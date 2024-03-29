package com.itemManagement.pos.service_impl.itemCategory;
import com.itemManagement.pos.entity.item.ItemCategory;
import com.itemManagement.pos.repository.itemCategory.ItemCategoryRepository;
import com.itemManagement.pos.service.itemCategory.ItemCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {
    @Autowired
    private ItemCategoryRepository itemCategoryRepository;

    @Override
    public ResponseEntity<Object> createCategory(ItemCategory itemCategory) {
        if(itemCategory.getName().isEmpty()){
            return new ResponseEntity<>("Category Name is Required.", HttpStatus.MULTI_STATUS);
        }
        itemCategory.setStatus("A");
        itemCategoryRepository.save(itemCategory);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> updateCategory(Long id, ItemCategory itemCategory) {
        if(id != null){
            ItemCategory exsistItemCategory = itemCategoryRepository.findById(id).orElse(null);
            assert exsistItemCategory != null;
            exsistItemCategory.setName(itemCategory.getName());
            itemCategoryRepository.save(exsistItemCategory);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deleteCategory(Long id) {
        if(id != null){
            ItemCategory exsistItemCategory = itemCategoryRepository.findById(id).orElse(null);
            assert exsistItemCategory != null;
            exsistItemCategory.setStatus("D");
            itemCategoryRepository.save(exsistItemCategory);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getAllCategories() {
        return new ResponseEntity<>(itemCategoryRepository.findItemCategoryNotDeleted(), HttpStatus.OK);
    }


}
