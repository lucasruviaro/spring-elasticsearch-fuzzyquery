package com.lucas.springbootelasticsearch.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.lucas.springbootelasticsearch.entity.Product;
import com.lucas.springbootelasticsearch.utils.ElasticsearchUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.function.Supplier;

@Service
public class ElasticsearchService {

    private final ElasticsearchClient elasticsearchClient;

    public ElasticsearchService(ElasticsearchClient elasticsearchClient) {
        this.elasticsearchClient = elasticsearchClient;
    }

    public SearchResponse<Product> fuzzySearch(String approximateProductName) throws IOException {
        Supplier<Query> supplier = ElasticsearchUtils.createSupplier(approximateProductName);

        SearchResponse<Product> response = elasticsearchClient.search(s->s.index("products").query(supplier.get()), Product.class);

        return response;
    }
}
