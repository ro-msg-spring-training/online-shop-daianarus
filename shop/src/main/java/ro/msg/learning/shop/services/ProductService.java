package ro.msg.learning.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.repositories.ProductRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Integer id, Product updatedProduct) {
        Optional<Product> productToUpdate = productRepository.findById(id);
        Product result = null;
        if (productToUpdate.isPresent()) {
            Product toUpdate = productToUpdate.get();
            toUpdate.setName(updatedProduct.getName());
            toUpdate.setDescription(updatedProduct.getDescription());
            toUpdate.setPrice(updatedProduct.getPrice());
            toUpdate.setWeight(updatedProduct.getWeight());
            toUpdate.setImageUrl(updatedProduct.getImageUrl());
            toUpdate.setProductCategory(updatedProduct.getProductCategory());
            toUpdate.setSupplier(updatedProduct.getSupplier());
            result = productRepository.save(toUpdate);

        }
        return result;
    }

    public void deleteProductById(Integer id) {
        productRepository.deleteById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Integer id) {
        Optional<Product> searchedProduct = productRepository.findById(id);
        if (searchedProduct.isPresent()) {
            return searchedProduct.get();
        } else {
            throw new EntityNotFoundException("Product" + id + "not found!");
        }
    }
}
