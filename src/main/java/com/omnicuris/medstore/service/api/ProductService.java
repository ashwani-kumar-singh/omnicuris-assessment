package com.omnicuris.medstore.service.api;

import com.omnicuris.medstore.entity.Product;
import com.omnicuris.medstore.model.dto.ProductDTO;
import com.omnicuris.medstore.model.response.MedstoreResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


public interface ProductService {

  /**
   *
   * @param productDTO
   * @return
   */
  MedstoreResponse<Boolean> createProduct(ProductDTO productDTO);

  /**
   *
   * @param pageRequest
   * @return
   */
  MedstoreResponse<Page<Product>> getAllProducts(
      PageRequest pageRequest);

  /**
   *
   * @param productDTO
   * @return
   */
  MedstoreResponse<Boolean> updateProduct(ProductDTO productDTO);
}
