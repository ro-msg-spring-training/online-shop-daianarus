package ro.msg.learning.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.entities.Location;
import ro.msg.learning.shop.entities.Stock;
import ro.msg.learning.shop.repositories.LocationRepository;
import ro.msg.learning.shop.repositories.StockRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockService {
    @Autowired
    private final StockRepository stockRepository;
    @Autowired
    private final LocationRepository locationRepository;

    @Transactional
    public void createStock(Stock stock) {
        stockRepository.save(stock);
    }

    @Transactional
    public void deleteAllStocks() {
        stockRepository.deleteAll();
    }

    @Transactional
    public void updateStock(Stock stockToUpdate, Integer quantityTaken) {
        Integer newQuantity = stockToUpdate.getQuantity() - quantityTaken;
        stockToUpdate.setQuantity(newQuantity);
        stockRepository.save(stockToUpdate);
    }

    public List<Stock> exportStocks(Integer locationId) {
        List<Stock> stocks;
        Optional<Location> location = locationRepository.findById(locationId);
        if (location.isPresent()) {
            stocks = stockRepository.findByLocationId(locationId);
            return stocks;
        } else throw new EntityNotFoundException("Location " + locationId + "not found!");
    }
}
