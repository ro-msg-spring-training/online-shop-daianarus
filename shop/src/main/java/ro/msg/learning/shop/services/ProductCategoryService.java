package ro.msg.learning.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.entities.ProductCategory;
import ro.msg.learning.shop.repositories.ProductCategoryRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductCategoryService {
    @Autowired
    private final ProductCategoryRepository productCategoryRepository;

    public void createProductCategory(ProductCategory productCategory) {
        productCategoryRepository.save(productCategory);
    }

    public void deleteProductCategory() {
        productCategoryRepository.deleteAll();
    }
}
