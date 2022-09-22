package ro.msg.learning.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dtos.ProductDTO;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.mappers.ProductMapper;
import ro.msg.learning.shop.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    private final ProductService productService;

    @PostMapping(value = "/add", produces = {"application/json"})
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        Product product = productService.createProduct(ProductMapper.productDTOToProduct(productDTO));
        ProductDTO productToAdd = ProductMapper.productToProductDTO(product);
        return new ResponseEntity<>(productToAdd, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}", produces = {"application/json"})
    public void deleteProductById(@PathVariable Integer id) {
        productService.deleteProductById(id);
    }

    @GetMapping(produces = {"application/json"})
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductDTO> productDTOS = products.stream().map(ProductMapper::productToProductDTO).collect(Collectors.toList());
        return new ResponseEntity<>(productDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "/update/{id}", produces = {"application/json"})
    public ResponseEntity<ProductDTO> updateProductById(@PathVariable Integer id, @RequestBody ProductDTO productToUpdate) {
        Product product = productService.updateProduct(id, ProductMapper.productDTOToProduct(productToUpdate));
        ProductDTO productDTO = ProductMapper.productToProductDTO(product);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer id) {
        Product product = productService.getProductById(id);
        ProductDTO productToFind = ProductMapper.productToProductDTO(product);
        return new ResponseEntity<>(productToFind, HttpStatus.OK);
    }
}
