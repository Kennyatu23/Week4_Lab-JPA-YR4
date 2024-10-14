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

    public ResponseEntity <List<Product>> updateProduct(Long id, Product updatedProduct) {
        // Fetch the existing product from the repository// Fetch the existing product from the repository
        Product existingProduct = productRepository.findById(id).orElse(null);

        // Check if the product exists
        if (existingProduct != null) {
            // Update existing product fields
            existingProduct.setProductName(updatedProduct.getProductName());
            existingProduct.setProductDescription(updatedProduct.getProductDescription());
            existingProduct.setProductPrice(updatedProduct.getProductPrice());
            existingProduct.setProductPrice(updatedProduct.getProductCode());

            // Save the updated product back to the repository
            productRepository.save(existingProduct);

            // Fetch the updated list of products
            List<Product> allProducts = productRepository.findAll();

            // Return the updated product with a 200 OK status
            return ResponseEntity.ok(allProducts);
        } else {
            // Return a 404 Not Found if the product doesn't exist
            return ResponseEntity.notFound().build();
        }
    }

}

/*
        if (productRepository.findById(id)) {

            existingProduct.setProductName(updatedProduct.getProductName());
            existingProduct.setProductDescription(updatedProduct.getProductDescription());
            existingProduct.setProductPrice(updatedProduct.getProductPrice());

            // Save the updated product back to the repository
            productRepository.save(existingProduct);

           // return productRepository.findProductById(id) product;

            // Return the updated product with a 200 OK status
            return ResponseEntity.ok(existingProduct);
        } else {
            // Return a 404 Not Found if the product doesn't exist
            return ResponseEntity.notFound().build();
        }
    }



}
*/

/*
Finding the Existing Product: Added a line to fetch the existing product from the repository before checking for null.

ResponseEntity: Properly returned a ResponseEntity object for both successful updates and the case where the product isn’t found.

Save Method: Included a call to productRepository.save(existingProduct) to save the updated product.

Make sure that productRepository is properly defined and injected in your class.
 */


