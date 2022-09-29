package ro.msg.learning.shop.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.entities.Address;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LocationDTO {
    private Address address;
    private String name;
}
