package ro.msg.learning.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.dtos.OrderDTO;
import ro.msg.learning.shop.entities.Order;
import ro.msg.learning.shop.mappers.OrderMapper;
import ro.msg.learning.shop.services.OrderService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/order", produces = {"application/json"})
public class OrderController {

    private final OrderService orderService;

    @PostMapping(value = "/create", produces = {"application/json"})
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        Order orderToCreate = orderService.createOrder(OrderMapper.orderDTOToOrder(orderDTO));
        OrderDTO orders = OrderMapper.orderToOrderDTO(orderToCreate);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}

