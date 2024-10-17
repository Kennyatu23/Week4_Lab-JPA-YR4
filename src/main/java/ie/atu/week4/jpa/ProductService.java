package ie.atu.week4.jpa;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> add(Product product) {
        productRepository.save(product);

        return productRepository.findAll();
    }

    public List<Product> updateProduct(Long id, Product updatedProduct) {
        // Fetch the list of products from the repository
        List<Product> products = productRepository.findAll();

        // Find the product by its ID and update it
        for (Product existingProduct : products) {
            if (existingProduct.getId().equals(id)) {
                // Update the existing product fields
                existingProduct.setProductName(updatedProduct.getProductName());
                existingProduct.setProductDescription(updatedProduct.getProductDescription());
                existingProduct.setProductPrice(updatedProduct.getProductPrice());
                existingProduct.setProductCode(updatedProduct.getProductCode());
                // break;  // Exit loop once the product is found and updated
            }
        }

        // Save the updated product list back to the repository
        productRepository.saveAll(products);

        // Return the updated product list
        return products;
    }


    public List<Product> deleteProduct(Long id) {
        // Checking if product exists by the id
        if (productRepository.existsById(id)) {
            //Delete product using id
            productRepository.deleteById(id);
        }

        return productRepository.findAll();

    }
}


