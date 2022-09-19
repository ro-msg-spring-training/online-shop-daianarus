package ro.msg.learning.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import ro.msg.learning.shop.dtos.ProductCategoryDTO;
import ro.msg.learning.shop.dtos.ProductDTO;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.entities.ProductCategory;
import ro.msg.learning.shop.exceptions.ProductNotFoundException;
import ro.msg.learning.shop.mappers.ProductMapper;
import ro.msg.learning.shop.repositories.ProductCategoryRepository;
import ro.msg.learning.shop.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductCategoryRepository productCategoryRepository;

    public ProductDTO createProduct(ProductDTO productDto) {
        Product product = Product.builder()
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .weight(productDto.getWeight())
                .imageUrl(productDto.getImageUrl())
                .productCategory(checkCategoryPresence(productDto.getProductCategory()))
                .build();
        productRepository.save(product);
        return productMapper.productToProductDTO(product);
    }

    public ProductCategory checkCategoryPresence(ProductCategoryDTO productCategoryDTO) {
        Optional<ProductCategory> searchedCategory = productCategoryRepository.findByName(productCategoryDTO.getName());
        ProductCategory productCategory;
        if (searchedCategory.isPresent()) {
            productCategory = searchedCategory.get();
        } else {
            productCategory = new ProductCategory();
            productCategory.setName(productCategoryDTO.getName());
            productCategory.setDescription(productCategoryDTO.getDescription());
            productCategoryRepository.save(productCategory);
        }
        return productCategory;

    }

    public ProductDTO updateProduct(Integer id, ProductDTO updatedProduct) {
        ProductDTO resultedProduct;
        Optional<Product> productToUpdate = productRepository.findById(id);
        if (productToUpdate.isPresent()) {
            Product updated = productToUpdate.get();
            updated.setName(updatedProduct.getName());
            updated.setPrice(updatedProduct.getPrice());
            updated.setDescription(updatedProduct.getDescription());
            updated.setWeight(updatedProduct.getWeight());
            updated.setImageUrl(updatedProduct.getImageUrl());
            updated.setProductCategory(checkCategoryPresence(updatedProduct.getProductCategory()));
            productRepository.save(updated);
            resultedProduct = productMapper.productToProductDTO(updated);
        } else throw new ProductNotFoundException("Product not found!");
        return resultedProduct;
    }

    public void deleteProductById(Integer id) {
        productRepository.deleteById(id);
    }

    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> existingProducts = new ArrayList<>();
        try {
            List<Product> products = productRepository.findAll();
            if (products.isEmpty()) {
                throw new ProductNotFoundException("Products not found!");
            } else {
                for (Product p : products) {
                    existingProducts.add(productMapper.productToProductDTO(p));
                }
            }
        } catch (ProductNotFoundException ex) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return existingProducts;
    }

    public ProductDTO getProductById(Integer id) {
        Optional<Product> searchedProduct = productRepository.findById(id);
        if (searchedProduct.isPresent()) {
            return productMapper.productToProductDTO(searchedProduct.get());
        } else {
            throw new ProductNotFoundException("Product not found!");
        }
    }
}
