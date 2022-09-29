package ro.msg.learning.shop.strategies;

import org.springframework.beans.factory.annotation.Autowired;
import ro.msg.learning.shop.entities.Order;
import ro.msg.learning.shop.entities.Stock;
import ro.msg.learning.shop.exceptions.OutOfStockException;
import ro.msg.learning.shop.repositories.StockRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class MostAbundantStrategy implements StrategyInterface {
    @Autowired
    private StockRepository stockRepository;

    @Override
    public List<Stock> implementStrategy(Order orders) {

        List<Stock> stockLocation = new ArrayList<>();
        orders.getOrderDetails().forEach(orderDetail -> {
            try {
                List<Stock> stocks = stockRepository.findLocationByProductAndQuantity(orderDetail.getProduct().getId(), orderDetail.getQuantity());
                if (stocks.isEmpty()) {
                    throw new OutOfStockException(orderDetail.getProduct().getId());
                }
                //because we query the products ordered by quantity(desc)we can now select the first stock to be the most abundant
                Stock stock = stocks.get(0);
                stockLocation.add(Stock.builder()
                        .location(stock.getLocation())
                        .product(stock.getProduct())
                        .quantity(stock.getQuantity())
                        .build());
            } catch (NoSuchElementException e) {
                throw new EntityNotFoundException("Product" + orderDetail.getProduct().getId() + "not found!");
            }

        });
        return stockLocation;
    }
}
