package ro.msg.learning.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.entities.Product;

@Component
public interface ProductRepository extends JpaRepository<Product, Integer> {
    void deleteById(Integer id);
}
