package ro.msg.learning.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entities.*;
import ro.msg.learning.shop.repositories.*;
import ro.msg.learning.shop.strategies.StrategyInterface;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderService {
    @Autowired
    private OrderRepository ordersRepository;

    @Autowired
    private StrategyInterface strategyInterface;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public Order createOrder(Order order) {

        List<Stock> stocks = strategyInterface.findLocation(order);
        try {
            order.setShippedFrom(stocks.get(0).getLocation());
            order.setCreatedAt(LocalDateTime.now());
            order.setCustomer(customerRepository.findAll().get(0));
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("We don't have all products in stock right now");
        }

        ordersRepository.save(order);

        List<OrderDetail> orderDetailsProducts = order.getOrderDetails();

        stocks.forEach(stock -> {
            for (OrderDetail orderDetails : orderDetailsProducts) {

                if (Objects.equals(orderDetails.getId(), stock.getProduct().getId())) {
                    int quantity = stock.getQuantity() - orderDetails.getQuantity();

                    Stock stockToUpdate = stockRepository.findByProductAndLocation(stock.getProduct(), stock.getLocation());
                    stockToUpdate.setQuantity(quantity);
                    stockRepository.save(stockToUpdate);
                    orderDetailRepository.save(orderDetails);

                }

            }
        });
        return order;
    }
}
