package ro.msg.learning.shop.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;
import ro.msg.learning.shop.ShopApplication;
import ro.msg.learning.shop.TestBase;
import ro.msg.learning.shop.dtos.AddressDTO;
import ro.msg.learning.shop.dtos.OrderDTO;
import ro.msg.learning.shop.dtos.OrderDetailDTO;
import ro.msg.learning.shop.mappers.AddressMapper;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ShopApplication.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class OrderCreationIntegrationTest extends TestBase {
    @Autowired
    public MockMvc mcv;

    @BeforeAll
    public void init() throws Exception {
        mcv.perform(get("/test/populate")).andExpect(status().isOk());

    }

    @AfterAll
    public void after() throws Exception {
        mcv.perform(get("/test/clear")).andExpect(status().isOk());

    }

    @Test
    public void createOrderTestSuccessfully() throws Exception {
        OrderDetailDTO order = new OrderDetailDTO();
        order.setProductId(product.getId());
        order.setQuantity(5);
        OrderDetailDTO order2 = new OrderDetailDTO();
        order2.setProductId(product2.getId());
        order2.setQuantity(5);
        List<OrderDetailDTO> orderDetails = Arrays.asList(
                order, order2
        );

        AddressDTO address = new AddressDTO();
        address.setCity("someCity");
        address.setStreet("someStreet");
        address.setCountry("someCountry");

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderedProducts(orderDetails);
        orderDTO.setAddress(AddressMapper.fromAddressDTOToAddress(address));

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        mapper.registerModule(new JavaTimeModule());
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(orderDTO);
        mcv.perform(post("/orders/create").contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk());
    }

    @Test(expected = NestedServletException.class)
    public void createOrderTestFailDueToOutOfStock() throws Exception {
        OrderDetailDTO order = new OrderDetailDTO();
        order.setProductId(product.getId());
        order.setQuantity(10);
        OrderDetailDTO order2 = new OrderDetailDTO();
        order2.setProductId(product2.getId());
        order2.setQuantity(1500);
        List<OrderDetailDTO> orderDetails = Arrays.asList(
                order, order2
        );

        AddressDTO address = new AddressDTO();
        address.setCity("someCity");
        address.setStreet("someStreet");
        address.setCountry("someCountry");

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderedProducts(orderDetails);
        orderDTO.setAddress(AddressMapper.fromAddressDTOToAddress(address));
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        mapper.registerModule(new JavaTimeModule());
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(orderDTO);

        mcv.perform(post("/orders/create").contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk());
    }
}
