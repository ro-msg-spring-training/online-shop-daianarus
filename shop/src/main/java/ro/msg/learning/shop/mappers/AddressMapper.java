package ro.msg.learning.shop.mappers;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.AddressDTO;
import ro.msg.learning.shop.entities.Address;

@Component
@NoArgsConstructor
public class AddressMapper {
    public static AddressDTO fromAddressToAddressDTO(Address address) {
        return AddressDTO.builder()
                .country(address.getCountry())
                .city(address.getCity())
                .street(address.getStreet())
                .build();
    }

    public static Address fromAddressDTOToAddress(AddressDTO addressDTO) {
        return Address.builder()
                .country(addressDTO.getCountry())
                .city(addressDTO.getCity())
                .street(addressDTO.getStreet())
                .build();
    }
}
