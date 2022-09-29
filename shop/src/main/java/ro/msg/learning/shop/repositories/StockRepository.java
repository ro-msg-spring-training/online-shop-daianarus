package ro.msg.learning.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.entities.Location;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.entities.Stock;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
    /**
     * The query would return a list of stocks with available products ordered by quantity(desc).
     *
     * @param productId searched product in stock
     * @param quantity  minimum searched quantity
     * @return list of stocks
     */
    @Query(value = "SELECT s FROM Stock s WHERE s.product.id = :productId AND s.quantity >= :quantity ORDER BY s.quantity DESC")
    List<Stock> findLocationByProductAndQuantity(@Param("productId") Integer productId, @Param("quantity") Integer quantity);

    Stock findByProductAndLocation(Product product, Location location);

    List<Stock> findByLocationId(Integer locationId);
}
