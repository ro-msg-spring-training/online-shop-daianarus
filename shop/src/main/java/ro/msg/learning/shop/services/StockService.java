package ro.msg.learning.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entities.Stock;
import ro.msg.learning.shop.repositories.StockRepository;
import ro.msg.learning.shop.utils.IDs;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {
    private final StockRepository stockRepository;
    private final EntityManager entityManager;

    public void updateStock(Stock stockToUpdate, Integer quantityTaken){
        Integer newQuantity= stockToUpdate.getQuantity()-quantityTaken;
        stockToUpdate.setQuantity(newQuantity);
        stockRepository.save(stockToUpdate);
    }
    public List<Stock> findStocksByLocationAndProductIDs(List<IDs> locationAndProductIDsList) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Stock> query = cb.createQuery(Stock.class);
        Root<Stock> stock = query.from(Stock.class);
        Path<Integer> locationIdPath = stock.get("location").get("id");
        Path<Integer> productIdPath = stock.get("product").get("id");
        List<Predicate> predicates = new ArrayList<>();

        locationAndProductIDsList.forEach(locationProductPair ->
                predicates.add(
                        cb.and(
                                cb.equal(locationIdPath, locationProductPair.getLocationID()),
                                cb.equal(productIdPath, locationProductPair.getProductID())
                        )
                ));

        query.select(stock)
                .where(cb.or(predicates.toArray(new Predicate[0])));
        return entityManager.createQuery(query)
                .getResultList();
    }
}
