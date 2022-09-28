package ro.msg.learning.shop;

import ro.msg.learning.shop.entities.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestBase {
    Customer customer = Customer.builder().id(12).firstName("Boyce").lastName("Tschersich").username("test").emailAddress("btschersich0@microsoft.com").password("test").build();
    Supplier supplier = Supplier.builder().id(47).name("Alphazap").build();
    ProductCategory productCategory = ProductCategory.builder().id(33).name("Namfix").description("Assisted pool exercise").build();
    public Product product = Product.builder().id(4).name("Ralph Lauren Corporation").description("Hypertroph osteoarthrop").price(BigDecimal.valueOf(56.87)).weight(77.22).imageUrl("http://dummyimage.com/116x125.jpg/ff4444/ffffff").productCategory(productCategory).supplier(supplier).build();
    public Product product2 = Product.builder().id(5).name("Nuveen Select Maturities Municipal Fund").description("No medical serv in home").price(BigDecimal.valueOf(45.63)).weight(33.29).imageUrl("http://dummyimage.com/125x104.jpg/cc0000/ffffff").productCategory(productCategory).supplier(supplier).build();
    Address address1 = Address.builder().country("HR").city("Piskorevci").street("506 Mitchell Court").build();
    Address address2 = Address.builder().country("IR").city("Firuzabad").street("9 Shasta Park").build();
    Location location = Location.builder().id(31).name("Skinte").address(address1).build();
    Location location2 = Location.builder().id(29).name("Wordify").address(address2).build();
    Stock stock = Stock.builder().id(95).location(location).product(product).quantity(95).build();
    Stock stock2 = Stock.builder().id(5).location(location2).product(product2).quantity(83).build();

    protected List<Stock> stocks = Stream.of(stock, stock2).collect(Collectors.toList());
}
