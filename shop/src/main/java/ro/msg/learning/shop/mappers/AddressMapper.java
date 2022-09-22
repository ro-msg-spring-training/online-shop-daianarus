package ro.msg.learning.shop.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.AddressDTO;
import ro.msg.learning.shop.entities.Address;

@Component
@RequiredArgsConstructor
public class AddressMapper {
    public AddressDTO fromAddressToAddressDTO(Address address) {
        return AddressDTO.builder()
                .country(address.getCountry())
                .city(address.getCity())
                .street(address.getStreet())
                .build();
    }

    public Address fromAddressDTOToAddress(AddressDTO addressDTO) {
        return Address.builder()
                .country(addressDTO.getCountry())
                .city(addressDTO.getCity())
                .street(addressDTO.getStreet())
                .build();
    }
}
