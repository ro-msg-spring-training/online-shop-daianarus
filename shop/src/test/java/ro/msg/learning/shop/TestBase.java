package ro.msg.learning.shop;

import ro.msg.learning.shop.entities.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestBase {
    Customer customer = Customer.builder().id(1).firstName("test").lastName("test").username("test").emailAddress("email@gmail.com").password("test").build();
    Supplier supplier = Supplier.builder().id(1).name("Charter Supply").build();
    ProductCategory productCategory = ProductCategory.builder().id(1).name("Jewelry").description("For more than a century, Swarovski has put the sparkle in gowns, tiaras, jewelry, sculptures, and even luxury cars.").build();
    public Product product = Product.builder().id(1).name("BIO Carrots").description("Swarovski has put the sparkle in gowns, tiaras, jewelry, sculptures, and even luxury cars.)").price(BigDecimal.valueOf(91.97)).weight(8.22).imageUrl("https://picsum.photos/200/300").productCategory(productCategory).supplier(supplier).build();
    public Product product2 = Product.builder().id(2).name("Swarovski").description("Swarovski has put the sparkle in gowns, tiaras, jewelry, sculptures, and even luxury cars.)").price(BigDecimal.valueOf(91.97)).weight(8.22).imageUrl("https://picsum.photos/200/300").productCategory(productCategory).supplier(supplier).build();
    Address address1 = Address.builder().country("HR").city("Piskorevci").street("506 Mitchell Court").build();
    Address address2 = Address.builder().country("MK").city("Mapnho").street("578 Spaight Trail").build();
    Location location = Location.builder().id(1).name("MAggs").address(address1).build();
    Location location2 = Location.builder().id(2).name("Jaxspan").address(address2).build();
    Stock stock = Stock.builder().id(1).location(location).product(product).quantity(10).build();
    Stock stock2 = Stock.builder().id(2).location(location2).product(product2).quantity(100).build();
    Stock stock3 = Stock.builder().location(location).product(product2).quantity(10).build();

    protected List<Stock> stocks = Stream.of(stock, stock2).collect(Collectors.toList());
}
