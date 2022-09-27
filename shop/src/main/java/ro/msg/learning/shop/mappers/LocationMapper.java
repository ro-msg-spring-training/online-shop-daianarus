package ro.msg.learning.shop.mappers;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.LocationDTO;
import ro.msg.learning.shop.entities.Location;

@Component
@NoArgsConstructor
public class LocationMapper {
    public static LocationDTO locationToLocationDTO(Location location) {
        return LocationDTO.builder()
                .address(location.getAddress())
                .name(location.getName())
                .build();

    }

    public static Location locationDTOToLocation(LocationDTO locationDTO) {
        return Location.builder()
                .address(locationDTO.getAddress())
                .name(locationDTO.getName())
                .build();
    }
}
