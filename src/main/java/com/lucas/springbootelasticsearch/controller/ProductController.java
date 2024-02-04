package com.lucas.springbootelasticsearch.controller;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.lucas.springbootelasticsearch.entity.Product;
import com.lucas.springbootelasticsearch.service.ElasticsearchService;
import com.lucas.springbootelasticsearch.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    private final ElasticsearchService elasticsearchService;

    public ProductController(ProductService productService, ElasticsearchService elasticsearchService) {
        this.productService = productService;
        this.elasticsearchService = elasticsearchService;
    }

    @GetMapping("/findAll")
    public Iterable<Product> findAll(){
        return productService.getProducts();
    }

    @GetMapping("/fuzzySearch/{approximateProductName}")
    public List<Product> fuzzySearch(@PathVariable String approximateProductName) throws IOException {
        SearchResponse<Product> searchResponse = elasticsearchService.fuzzySearch(approximateProductName);
        List<Hit<Product>> hitList = searchResponse.hits().hits();

        List<Product> productList = new ArrayList<>();
        for(Hit<Product> hit : hitList){
            productList.add(hit.source());
        }

        return productList;
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
