package ro.msg.learning.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.entities.ProductCategory;

@Component
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
}
