package com.anusikh.elasticsearchdemo.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.anusikh.elasticsearchdemo.model.Product;
import com.anusikh.elasticsearchdemo.query.ElasticSearchQuery;

@RestController
public class ElasticSearchController {

    @Autowired
    private ElasticSearchQuery elasticSearchQuery;

    @PostMapping("/createProduct")
    public ResponseEntity<Object> createProduct(@RequestBody Product product) throws IOException {
        String response = elasticSearchQuery.createOrUpdateDocument(product);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getProduct/{productId}")
    public ResponseEntity<Object> getProduct(@PathVariable(value = "productId") String productId) throws IOException {
        Product response = elasticSearchQuery.getDocumentById(productId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/deleteProduct/{productId}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "productId") String productId)
            throws IOException {
        String response = elasticSearchQuery.deleteDocumentById(productId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/searchProduct")
    public ResponseEntity<Object> searchProduct() throws IOException {
        List<Product> response = elasticSearchQuery.searchAllDocuments();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
