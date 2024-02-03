package com.lucas.springbootelasticsearch.controller;

import com.lucas.springbootelasticsearch.entity.Product;
import com.lucas.springbootelasticsearch.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/findAll")
    public Iterable<Product> findAll(){
        return productService.getProducts();
    }

    @PostMapping("/insert")
    public Product insert(@RequestBody Product product){
        return productService.insertProduct(product);
    }

    @PutMapping("/update/{id}")
    public Product update(@RequestBody Product product, @PathVariable String id){
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id){
        productService.deleteProduct(id);
    }

}
