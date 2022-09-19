package ro.msg.learning.shop.strategies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.OrderDetailDTO;
import ro.msg.learning.shop.dtos.StockDTO;
import ro.msg.learning.shop.entities.Stock;
import ro.msg.learning.shop.exceptions.ProductsCantBeShipped;
import ro.msg.learning.shop.mappers.StockMapper;
import ro.msg.learning.shop.repositories.LocationRepository;
import ro.msg.learning.shop.repositories.StockRepository;
import ro.msg.learning.shop.services.LocationService;
import ro.msg.learning.shop.services.StockService;
import ro.msg.learning.shop.utils.IDs;
import ro.msg.learning.shop.utils.LocationFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class SingleLocationStrategy implements StrategyInterface {
    private final StockRepository stockRepository;
    private final StockService stockService;
    private final LocationService locationService;

    @Override
    public List<StockDTO> implementStrategy(List<OrderDetailDTO> orderDetailDTOList, LocationFormat deliveryAddress) {
        Map<Integer, Integer> productIDsAndQuantities = getOrderedProductsIDsAndQuantitiesMap(orderDetailDTOList);
        List<Integer> locationsIDsWithAvailableStocks = locationService.findLocationWithProductAndQuantity(productIDsAndQuantities);
        if (!locationsIDsWithAvailableStocks.isEmpty()) {
            List<Stock> stocks = stockService.findStocksByLocationAndProductIDs(createLocationAndProductIDsList(locationsIDsWithAvailableStocks.get(0), getOrderedProductsIDsList(orderDetailDTOList)));
            return getAndUpdateAvailableStocks(stocks, orderDetailDTOList, stockRepository, stockService);
        }
        throw new ProductsCantBeShipped("Demanded products can't be taken from single location!");
    }

    public List<IDs> createLocationAndProductIDsList(Integer locationID, List<Integer> orderedProductsIDs) {
        List<IDs> result = new ArrayList<>();
        for (Integer productID : orderedProductsIDs) {
            result.add(new IDs(locationID, productID));
        }
        return result;
    }
}
