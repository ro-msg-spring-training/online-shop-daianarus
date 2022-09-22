package ro.msg.learning.shop.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Builder
public class Address {
    private String country;
    private String city;
    private String street;

    @Override
    public String toString() {
        return "Country: " + country + "\n" +
                "City: " + city + "\n" +
                "Street: " + street + "\n";
    }
}
