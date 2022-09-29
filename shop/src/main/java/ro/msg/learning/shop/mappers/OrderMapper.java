package ro.msg.learning.shop.mappers;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.OrderDTO;
import ro.msg.learning.shop.entities.Order;

@Component
@NoArgsConstructor
public class OrderMapper {

    public static OrderDTO orderToOrderDTO(Order order) {
        return OrderDTO.builder()
                .orderID(order.getId())
                .userID(order.getCustomer().getId())
                .address(order.getAddress())
                .orderedProducts(OrderDetailMapper.orderDetailListToOrderDetailDTOList(order.getOrderDetails()))
                .build();
    }

    public static Order orderDTOToOrder(OrderDTO orderDTO) {
        return Order.builder()
                .id(orderDTO.getOrderID())
                .address(orderDTO.getAddress())
                .orderDetails(OrderDetailMapper.orderDetailDTOListToOrderDetailList(orderDTO.getOrderedProducts()))
                .build();
    }
}
