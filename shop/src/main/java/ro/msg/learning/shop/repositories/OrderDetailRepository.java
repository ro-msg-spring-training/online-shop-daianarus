package ro.msg.learning.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.entities.OrderDetail;

import java.util.List;

@Component
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer>{
    List<OrderDetail> findAllByOrderId(Integer orderId);
}
