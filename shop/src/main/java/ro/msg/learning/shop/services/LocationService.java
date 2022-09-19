package ro.msg.learning.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dtos.LocationDTO;
import ro.msg.learning.shop.entities.Location;
import ro.msg.learning.shop.entities.Stock;
import ro.msg.learning.shop.mappers.LocationMapper;
import ro.msg.learning.shop.repositories.LocationRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;
    private final EntityManager entityManager;

    public List<LocationDTO> getAllLocations() {
        List<LocationDTO> allLocationToDTO = new ArrayList<>();
        List<Location> locations = locationRepository.findAll();
        for (Location l : locations) {
            allLocationToDTO.add(locationMapper.locationToLocationDTO(l));
        }
        return allLocationToDTO;
    }

    public List<Integer> findLocationWithProductAndQuantity(Map<Integer, Integer> productWithQuantity) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Integer> query = criteriaBuilder.createQuery(Integer.class);
        Root<Stock> stock = query.from(Stock.class);
        Path<Integer> productID = stock.get("product").get("id");
        Path<Integer> quantity = stock.get("quantity");
        Path<Integer> locationID = stock.get("location").get("id");
        List<Predicate> predicates = new ArrayList<>();

        productWithQuantity.forEach((prodID, prodQuantity) ->
                predicates.add(
                        criteriaBuilder.and(
                                criteriaBuilder.equal(productID, prodID),
                                criteriaBuilder.greaterThanOrEqualTo(quantity, prodQuantity)
                        )
                ));

        query.select(locationID)
                .where(criteriaBuilder.or(predicates.toArray(new Predicate[0])))
                .groupBy(locationID)
                .having(criteriaBuilder.equal(criteriaBuilder.count(productID), productWithQuantity.size()));

        return entityManager.createQuery(query)
                .getResultList();

    }
}
