package com.omnicuris.medstore.repository;

import com.omnicuris.medstore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("com.omnicuris.medstore.repository.ProductRepository")
public interface ProductRepository extends JpaRepository<Product, Integer> {

  /**
   * @param name
   *
   * @return
   */
  Product findByName(String name);
}
