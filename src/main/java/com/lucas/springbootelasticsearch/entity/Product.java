package com.lucas.springbootelasticsearch.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(indexName = "product")
public class Product {

    private String id;
    @Field(type = FieldType.Text, name ="name")
    private String name;
    @Field(type = FieldType.Text, name ="description")
    private String description;
    @Field(type = FieldType.Integer, name ="quantity")
    private int quantity;
    @Field(type = FieldType.Double, name ="price")
    private Double price;


}
