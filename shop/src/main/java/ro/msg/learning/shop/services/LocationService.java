package ro.msg.learning.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entities.Location;
import ro.msg.learning.shop.repositories.LocationRepository;


@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;

    public void createLocation(Location location) {
        locationRepository.save(location);
    }

    public void deleteAllLocation() {
        locationRepository.deleteAll();
    }
}
