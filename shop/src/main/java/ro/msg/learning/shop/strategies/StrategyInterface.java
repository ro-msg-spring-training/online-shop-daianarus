package ro.msg.learning.shop.strategies;

import ro.msg.learning.shop.entities.Order;
import ro.msg.learning.shop.entities.Stock;

import java.util.*;

public interface StrategyInterface {
    List<Stock> implementStrategy(Order order);
}
