package com.lucas.springbootelasticsearch.repository;

import com.lucas.springbootelasticsearch.entity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface ProductRepository extends ElasticsearchRepository<Product, String> {
}
