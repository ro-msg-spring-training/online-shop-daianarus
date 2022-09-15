package ro.msg.learning.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.entities.Stock;

import java.util.List;

@Component
public interface StockRepository extends JpaRepository<Stock, Integer> {
    List<Stock> findAllByLocationId(Integer locationID);

    List<Stock> findAllByProductId(Integer productID);
}
