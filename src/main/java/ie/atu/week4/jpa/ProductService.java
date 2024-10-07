package ie.atu.week4.jpa;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductRespository productRespository;

    public ProductService(ProductRespository productRespository){
        this.productRespository = productRespository;
    }

    public List<Product> add(Product product)
    {
        productRespository.save(product);

        return productRespository.findAll();
    }

}
