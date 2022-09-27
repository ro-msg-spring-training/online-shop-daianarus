package ro.msg.learning.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.entities.Location;
import ro.msg.learning.shop.repositories.LocationRepository;


@Service
@RequiredArgsConstructor
@Transactional
public class LocationService {
    @Autowired
    private final LocationRepository locationRepository;

    public void createLocation(Location location) {
        locationRepository.save(location);
    }

    public void deleteAllLocation() {
        locationRepository.deleteAll();
    }
}
