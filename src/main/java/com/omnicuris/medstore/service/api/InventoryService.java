package com.omnicuris.medstore.service.api;

import com.omnicuris.medstore.entity.Inventory;
import com.omnicuris.medstore.model.response.MedstoreResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


public interface InventoryService {

  MedstoreResponse<Page<Inventory>> getAllInventory(Integer signedInCustomerId,
      PageRequest pageRequest);

  /**
   * @param inventoryId
   *
   * @return
   */
  MedstoreResponse<Inventory> getInventoryById(Integer inventoryId);

  /**
   * @param inventoryId
   *
   * @return
   */
  MedstoreResponse<Boolean> deleteInventoryById(Integer inventoryId);
}
