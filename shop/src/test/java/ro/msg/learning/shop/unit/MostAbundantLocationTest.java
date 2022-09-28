package ro.msg.learning.shop.unit;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ro.msg.learning.shop.TestBase;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

import ro.msg.learning.shop.entities.Address;
import ro.msg.learning.shop.entities.Order;
import ro.msg.learning.shop.entities.OrderDetail;
import ro.msg.learning.shop.entities.Stock;
import ro.msg.learning.shop.exceptions.OutOfStockException;
import ro.msg.learning.shop.repositories.StockRepository;
import ro.msg.learning.shop.strategies.MostAbundantStrategy;

class MostAbundantLocationTest extends TestBase {
    @InjectMocks
    private MostAbundantStrategy strategy;
    @Mock
    private StockRepository stockRepository = Mockito.mock(StockRepository.class);

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        when(stockRepository.findLocationByProductAndQuantity(4, 5)).thenReturn(stocks);
    }

    @Test
    void mostAbundantStrategySuccess() {
        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProduct(product);
        orderDetail.setQuantity(5);
        orderDetails.add(orderDetail);
        Order order = new Order();

        order.setOrderDetails(orderDetails);
        order.setCreatedAt(LocalDateTime.now());

        Address address = new Address();
        address.setCountry("someCounty");
        address.setCity("someCity");
        address.setStreet("someStreet");

        order.setAddress(address);

        List<Stock> stocks = strategy.implementStrategy(order);
        Assertions.assertEquals("Skinte", stocks.get(0).getLocation().getName());

    }

    @Test
    void mostAbundantStrategyTestFailDueToOutOfStock() {
        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setQuantity(100);
        orderDetail.setProduct(product2);
        orderDetails.add(orderDetail);
        Order order = new Order();
        order.setCreatedAt(LocalDateTime.now());
        order.setOrderDetails(orderDetails);

        Address address = new Address();
        address.setCountry("someCounty");
        address.setCity("someCity");
        address.setStreet("someStreet");

        order.setAddress(address);

        Throwable exception = Assert.assertThrows(OutOfStockException.class, () -> strategy.implementStrategy(order));
        Assertions.assertEquals("The product " + orderDetail.getProduct().getId() +
                " is not in our stock right now!", exception.getMessage());
    }
}
