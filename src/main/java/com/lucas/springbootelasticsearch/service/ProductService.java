package com.lucas.springbootelasticsearch.service;

import com.lucas.springbootelasticsearch.entity.Product;
import com.lucas.springbootelasticsearch.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;


@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Autowired
    private ElasticsearchOperations operations;


    public Iterable<Product> getProducts(){
       return productRepository.findAll();
    }

    public Product insertProduct(Product product){
        return productRepository.save(product);
    }

    public Product updateProduct(String id, Product product){
        Product product1 = productRepository.findById(id).get();

        product1.setName(product.getName());
        product1.setDescription(product.getDescription());
        product1.setQuantity(product.getQuantity());
        product1.setPrice(product.getPrice());

        return productRepository.save(product1);
    }

    public void deleteProduct(String id){

        productRepository.deleteById(id);
    }


}
