package ro.msg.learning.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.entities.OrderDetail;

import java.util.Optional;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer>{
    Optional<OrderDetail> findByOrderId(Integer orderId);
    Optional<OrderDetail> findByProductId(Integer productID);
}
