package ro.msg.learning.shop.strategies;

import org.springframework.beans.factory.annotation.Autowired;
import ro.msg.learning.shop.entities.Order;
import ro.msg.learning.shop.entities.Stock;
import ro.msg.learning.shop.exceptions.OutOfStockException;
import ro.msg.learning.shop.repositories.StockRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SingleLocationStrategy implements StrategyInterface {
    @Autowired
    private StockRepository stockRepository;

    @Override
    public List<Stock> findLocation(Order order) {
        Map<Integer, List<Stock>> locations = new HashMap<>();
        order.getOrderDetails().forEach(orderDetail -> {
                    List<Stock> stocks = stockRepository
                            .findLocationByProductAndQuantity(orderDetail.getProduct().getId(), orderDetail.getQuantity());
                    if (stocks.isEmpty()) {
                        throw new OutOfStockException(orderDetail.getId());
                    }

                    stocks.forEach(stock -> {
                        List<Stock> locationList = locations.get(stock.getLocation().getId());
                        if (locationList == null) {
                            locationList = new ArrayList<>();
                        }
                        locationList.add(Stock.builder()
                                .location(stock.getLocation())
                                .product(stock.getProduct())
                                .quantity(stock.getQuantity())
                                .build());
                        locations.put(stock.getLocation().getId(), locationList);
                    });
                }
        );
        for (Map.Entry<Integer, List<Stock>> entry : locations.entrySet()) {
            if (entry.getValue().size() == order.getOrderDetails().size()) {
                return entry.getValue();
            }
        }
        return new ArrayList<>();
    }
}
