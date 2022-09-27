package ro.msg.learning.shop.unit;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import ro.msg.learning.shop.TestBase;
import ro.msg.learning.shop.entities.Address;
import ro.msg.learning.shop.entities.Order;
import ro.msg.learning.shop.entities.OrderDetail;
import ro.msg.learning.shop.entities.Stock;
import ro.msg.learning.shop.exceptions.OutOfStockException;
import ro.msg.learning.shop.repositories.StockRepository;
import ro.msg.learning.shop.strategies.SingleLocationStrategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class SingleLocationTest extends TestBase {
    @InjectMocks
    private SingleLocationStrategy strategy;
    @Mock
    private StockRepository stockRepository = Mockito.mock(StockRepository.class);

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        when(stockRepository.findLocationByProductAndQuantity(1, 10)).thenReturn(stocks);
    }

    @Test
    void singleLocationStrategySuccess() {
        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProduct(product);
        orderDetail.setQuantity(10);
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
        Assertions.assertFalse(stocks.isEmpty());
    }

    @Test()
    void singleLocationStrategyFailDueToOutOfStock() {


        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        OrderDetail orderDetail2 = new OrderDetail();

        orderDetail.setProduct(product);
        orderDetail2.setProduct(product2);
        orderDetail.setQuantity(10);
        orderDetail2.setQuantity(800);

        orderDetails.add(orderDetail);
        orderDetails.add(orderDetail2);
        Order order = new Order();

        order.setOrderDetails(orderDetails);
        order.setCreatedAt(LocalDateTime.now());

        Address address = new Address();
        address.setCountry("someCounty");
        address.setCity("someCity");
        address.setStreet("someStreet");

        order.setAddress(address);

        Throwable exception = Assert.assertThrows(OutOfStockException.class, () -> strategy.implementStrategy(order));
        Assertions.assertEquals("The product " + order.getOrderDetails().get(1).getId() +
                " is not in our stock right now!", exception.getMessage());


    }

}
