package com.omnicuris.medstore.service.impl;

import com.omnicuris.medstore.entity.Inventory;
import com.omnicuris.medstore.model.dto.InventoryDTO;
import com.omnicuris.medstore.model.response.MedstoreResponse;
import com.omnicuris.medstore.repository.InventoryRepository;
import com.omnicuris.medstore.service.api.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;

import static org.springframework.beans.BeanUtils.copyProperties;

@Slf4j
@Service
public class InventoryServiceImpl implements InventoryService {

  @Autowired
  private InventoryRepository inventoryRepository;

  @Override
  public MedstoreResponse<Page<Inventory>> getAllInventory(Integer signedInCustomerId,
      PageRequest pageRequest) {
    log.info("Going to get all Inventory for - signedInCustomerId:{}, pageRequest:{}",
        signedInCustomerId, pageRequest);
    MedstoreResponse<Page<Inventory>> getAllInventory = new MedstoreResponse<>();
    Page<Inventory> inventoryPage = new PageImpl(new ArrayList(), pageRequest, 0);
    try {
      Page<Inventory> orders = inventoryRepository.findAll(pageRequest);
      inventoryPage = new PageImpl<>(orders.getContent(), pageRequest, orders.getTotalElements());
      getAllInventory.setResponseObject(inventoryPage);
    } catch (Exception e) {
      getAllInventory.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
      log.error("Error in getting inventories for signedInCustomerId : {}", signedInCustomerId, e);
    }
    return getAllInventory;
  }

  @Override
  public MedstoreResponse<Inventory> getInventoryById(Integer inventoryId) {
    log.info("get inventory for - inventoryId:{}", inventoryId);
    MedstoreResponse<Inventory> getInventoryResponse = new MedstoreResponse<>();
    try {
      Inventory inventory = inventoryRepository.findOne(inventoryId);
      getInventoryResponse.setResponseObject(inventory);
    } catch (Exception e) {
      getInventoryResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
      log.error("Error in getting  inventory for inventoryId:{}", inventoryId, e);
    }
    return getInventoryResponse;
  }

  @Override
  public MedstoreResponse<Boolean> deleteInventoryById(Integer inventoryId) {
    log.info("Going to delete inventory for - inventoryId:{}", inventoryId);
    MedstoreResponse<Boolean> deleteInventoryResponse = new MedstoreResponse<>();
    try {
      inventoryRepository.delete(inventoryId);
      deleteInventoryResponse.setResponseObject(true);
    } catch (Exception e) {
      deleteInventoryResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
      log.error("Error in deleting  inventory for inventoryId:{}", inventoryId, e);
    }
    return deleteInventoryResponse;
  }
}
