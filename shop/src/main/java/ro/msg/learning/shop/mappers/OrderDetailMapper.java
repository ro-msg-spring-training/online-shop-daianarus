package ro.msg.learning.shop.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.OrderDetailDTO;
import ro.msg.learning.shop.entities.OrderDetail;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderDetailMapper {
    public static OrderDetailDTO orderDetailToOrderDetailDTO(OrderDetail orderDetail) {
        return OrderDetailDTO.builder()
                .productId(orderDetail.getProduct().getId())
                .quantity(orderDetail.getQuantity())
                .build();
    }

    public static OrderDetail orderDetailDTOToOrderDetail(OrderDetailDTO orderDetailDTO) {
        return OrderDetail.builder()
                .quantity(orderDetailDTO.getQuantity())
                .build();
    }

    public static List<OrderDetailDTO> orderDetailListToOrderDetailDTOList(List<OrderDetail> orderDetailList) {
        List<OrderDetailDTO> orderDetailDTO = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetailList) {
            OrderDetailDTO crtOrderDetailDTO = orderDetailToOrderDetailDTO(orderDetail);
            crtOrderDetailDTO.setProductId(orderDetail.getProduct().getId());
            orderDetailDTO.add(crtOrderDetailDTO);
        }
        return orderDetailDTO;
    }

    public static List<OrderDetail> orderDetailDTOListToOrderDetailList(List<OrderDetailDTO> orderDetailListDTO) {
        List<OrderDetail> orderDetail = new ArrayList<>();
        for (OrderDetailDTO crtOrderDetailDTO : orderDetailListDTO) {
            orderDetail.add(orderDetailDTOToOrderDetail(crtOrderDetailDTO));
        }
        return orderDetail;
    }
}
