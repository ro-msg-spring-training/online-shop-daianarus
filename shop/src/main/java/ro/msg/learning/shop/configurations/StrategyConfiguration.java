package ro.msg.learning.shop.configurations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.exceptions.ProductsCantBeShipped;
import ro.msg.learning.shop.strategies.MostAbundantStrategy;
import ro.msg.learning.shop.strategies.SingleLocationStrategy;
import ro.msg.learning.shop.strategies.StrategyEnum;
import ro.msg.learning.shop.strategies.StrategyInterface;

@Configuration
@RequiredArgsConstructor
public class StrategyConfiguration {
    @Value("${strategy}")
    private StrategyEnum strategy;

    private final SingleLocationStrategy singleLocation;
    private final MostAbundantStrategy mostAbundantLocation;

    @Bean
    public StrategyInterface decideStrategy() {
        switch (strategy) {
            case SINGLE_LOCATION:
                return singleLocation;
            case MOST_ABUNDANT:
                return mostAbundantLocation;
            default:
                throw new ProductsCantBeShipped("Can't get place your order. We are sorry!");
        }
    }
}
