package com.omnicuris.medstore.repository;

import com.omnicuris.medstore.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
  /**
   *
   * @param productId
   * @return
   */

  Inventory  findByProductId(Integer productId);

}
