package ro.msg.learning.shop.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.exceptions.ProductsCantBeShippedException;
import ro.msg.learning.shop.strategies.MostAbundantStrategy;
import ro.msg.learning.shop.strategies.SingleLocationStrategy;
import ro.msg.learning.shop.strategies.StrategyEnum;
import ro.msg.learning.shop.strategies.StrategyInterface;

@Configuration
public class StrategyConfiguration {
    @Value("${strategy}")
    private StrategyEnum strategy;

    @Bean
    public StrategyInterface decideStrategy() {
        switch (strategy) {
            case SINGLE_LOCATION:
                return new SingleLocationStrategy();
            case MOST_ABUNDANT:
                return new MostAbundantStrategy();
            default:
                throw new ProductsCantBeShippedException("Can't get place your order. We are sorry!");
        }
    }
}
