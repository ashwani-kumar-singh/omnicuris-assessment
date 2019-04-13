package com.omnicuris.medstore.service.impl;

import com.omnicuris.medstore.entity.Inventory;
import com.omnicuris.medstore.entity.Product;
import com.omnicuris.medstore.model.dto.ProductDTO;
import com.omnicuris.medstore.model.response.MedstoreResponse;
import com.omnicuris.medstore.repository.InventoryRepository;
import com.omnicuris.medstore.repository.OrderRepository;
import com.omnicuris.medstore.repository.ProductRepository;
import com.omnicuris.medstore.service.api.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Objects;

import static org.springframework.beans.BeanUtils.copyProperties;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private InventoryRepository inventoryRepository;

  @Override
  public MedstoreResponse<Boolean> createProduct(ProductDTO productDTO) {
    MedstoreResponse<Boolean> createProductResponse = new MedstoreResponse<>(Boolean.FALSE);
    log.debug("In createProduct:  create product for productDTO: {}", productDTO);
    try {
      Product productWithExistingName = productRepository.findByName(productDTO.getName());
      if (Objects.nonNull(productWithExistingName)) {
        log.error("Product created with  duplicate Name : {}", productDTO.getName());
        createProductResponse.setResponseObject(Boolean.FALSE);
      } else {
        Product product = new Product();
        copyProperties(productDTO, product);
        Product savedProduct = productRepository.save(product);
        Inventory inventory = new Inventory();
        inventory.setQuantity(productDTO.getQuantity());
        inventory.setProductId(savedProduct.getId());
        inventoryRepository.save(inventory);
        createProductResponse.setResponseObject(Boolean.TRUE);
      }
    } catch (Exception e) {
      createProductResponse.setResponseObject(Boolean.FALSE);
      log.error("product creation failed for productDTO: {}", productDTO, e);
    }
    return createProductResponse;
  }

  @Override
  public MedstoreResponse<Page<Product>> getAllProducts(PageRequest pageRequest) {
    log.info("Going to get all products for - signedInCustomerId:{}, pageRequest:{}", pageRequest);
    MedstoreResponse<Page<Product>> getAllProducts = new MedstoreResponse<>();
    Page<Product> productPage = new PageImpl(new ArrayList(), pageRequest, 0);
    try {
      Page<Product> orders = productRepository.findAll(pageRequest);
      productPage = new PageImpl<>(orders.getContent(), pageRequest, orders.getTotalElements());
      getAllProducts.setResponseObject(productPage);
    } catch (Exception e) {
      getAllProducts.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
      log.error("Error in getting products for signedInCustomerId : {}", e);
    }
    return getAllProducts;
  }

  @Override
  public MedstoreResponse<Boolean> updateProduct(ProductDTO productDTO) {
    MedstoreResponse<Boolean> createProductResponse = new MedstoreResponse<>(Boolean.FALSE);
    log.debug("In updateProduct:  update product for productDTO: {}", productDTO);
    try {
      Product product = new Product();
      copyProperties(productDTO, product);
      Product savedProduct = productRepository.save(product);
      Inventory inventory = new Inventory();
      inventory.setQuantity(productDTO.getQuantity());
      inventory.setProductId(savedProduct.getId());
      inventoryRepository.save(inventory);
      createProductResponse.setResponseObject(Boolean.TRUE);
    } catch (Exception e) {
      createProductResponse.setResponseObject(Boolean.FALSE);
      log.error("product update failed for productDTO: {}", productDTO, e);
    }
    return createProductResponse;
  }
}
