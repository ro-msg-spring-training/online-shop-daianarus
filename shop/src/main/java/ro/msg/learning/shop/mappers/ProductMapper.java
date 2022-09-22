package ro.msg.learning.shop.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.ProductDTO;
import ro.msg.learning.shop.entities.Product;

@Component
@RequiredArgsConstructor
public class ProductMapper {
    public static ProductDTO productToProductDTO(Product product) {
        return ProductDTO.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .imageUrl(product.getImageUrl())
                .productCategory(ProductCategoryMapper.categoryToCategoryDTO(product.getProductCategory()))
                .weight(product.getWeight())
                .build();
    }

    public static Product productDTOToProduct(ProductDTO productDTO) {
        return Product.builder()
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .imageUrl(productDTO.getImageUrl())
                .productCategory(ProductCategoryMapper.categoryDTOtoCategory(productDTO.getProductCategory()))
                .weight(productDTO.getWeight())
                .build();
    }
}
