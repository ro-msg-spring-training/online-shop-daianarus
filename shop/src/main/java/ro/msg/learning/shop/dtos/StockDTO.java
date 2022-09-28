package ro.msg.learning.shop.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonPropertyOrder({"locationId", "productId", "quantity"})
public class StockDTO {
    private Integer locationId;
    private Integer productId;
    private Integer quantity;
}
