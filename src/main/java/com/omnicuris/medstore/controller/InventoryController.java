package com.omnicuris.medstore.controller;

import com.omnicuris.medstore.entity.Inventory;
import com.omnicuris.medstore.model.response.MedstoreResponse;
import com.omnicuris.medstore.service.api.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/med-api/inventory")
public class InventoryController {

  @Autowired
  private InventoryService inventoryService;

  @GetMapping("/all-inventory")
  public ResponseEntity<MedstoreResponse<Page<Inventory>>> getAllInventory(
      @RequestParam("customerId") Integer signedInCustomerId, @RequestParam("page") int page,
      @RequestParam("size") int size) {
    log.info("In getAllInventory controller:- get all inventories for "
        + "signedInCustomerId:{}, page :{}, size:{}", signedInCustomerId, page, size);
    ResponseEntity<MedstoreResponse<Page<Inventory>>> responseEntity;
    MedstoreResponse<Page<Inventory>> productResponse =
        inventoryService.getAllInventory(signedInCustomerId, new PageRequest(page, size));
    responseEntity = new ResponseEntity<>(productResponse, HttpStatus.OK);
    return responseEntity;
  }

  @GetMapping("/{id}")
  public ResponseEntity<MedstoreResponse<Inventory>> getInventoryById(
      @PathVariable("id") Integer id) {
    log.info("In getInventoryById controller:- get inventory for id:{}", id);
    ResponseEntity<MedstoreResponse<Inventory>> responseEntity;
    MedstoreResponse<Inventory> productResponse = inventoryService.getInventoryById(id);
    responseEntity = new ResponseEntity<>(productResponse, HttpStatus.OK);
    return responseEntity;
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<MedstoreResponse<Boolean>> deleteInventoryById(
      @PathVariable("id") Integer id) {
    log.info("In deleteInventoryById controller:- delete inventory for id:{}", id);
    ResponseEntity<MedstoreResponse<Boolean>> responseEntity;
    MedstoreResponse<Boolean> productResponse = inventoryService.deleteInventoryById(id);
    responseEntity = new ResponseEntity<>(productResponse, HttpStatus.OK);
    return responseEntity;
  }
}
