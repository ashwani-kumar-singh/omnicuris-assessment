package com.omnicuris.medstore.controller;

import com.omnicuris.medstore.entity.Product;
import com.omnicuris.medstore.model.dto.ProductDTO;
import com.omnicuris.medstore.model.response.MedstoreResponse;
import com.omnicuris.medstore.service.api.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/med-api/product")
public class ProductController {

  @Autowired
  private ProductService productService;

  @PostMapping("/create")
  public ResponseEntity<MedstoreResponse<Boolean>> createProduct(
      @RequestBody ProductDTO productDTO) {
    log.info("Inside createProduct controller for productDTO:{}", productDTO);
    ResponseEntity<MedstoreResponse<Boolean>> responseEntity =
        new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    MedstoreResponse<Boolean> productResponse = productService.createProduct(productDTO);
    if (productResponse.getResponseObject()) {
      responseEntity = new ResponseEntity<>(productResponse, HttpStatus.OK);
    }
    return responseEntity;
  }

  @GetMapping("/all-products")
  public ResponseEntity<MedstoreResponse<Page<Product>>> getAllProducts(
      @RequestParam("page") int page, @RequestParam("size") int size) {
    log.info("In getAllProducts controller:- get all products for "
        + "signedInCustomerId:{}, page :{}, size:{}", page, size);
    ResponseEntity<MedstoreResponse<Page<Product>>> responseEntity;
    MedstoreResponse<Page<Product>> productResponse =
        productService.getAllProducts(new PageRequest(page, size));
    responseEntity = new ResponseEntity<>(productResponse, HttpStatus.OK);
    return responseEntity;
  }

  @PutMapping("/update")
  public ResponseEntity<MedstoreResponse<Boolean>> updateProduct(
      @RequestBody ProductDTO productDTO) {
    log.info("Inside updateProduct controller for productDTO:{}", productDTO);
    ResponseEntity<MedstoreResponse<Boolean>> responseEntity =
        new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    MedstoreResponse<Boolean> productResponse = productService.updateProduct(productDTO);
    if (productResponse.getResponseObject()) {
      responseEntity = new ResponseEntity<>(productResponse, HttpStatus.OK);
    }
    return responseEntity;
  }
}
