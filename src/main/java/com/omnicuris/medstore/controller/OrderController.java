package com.omnicuris.medstore.controller;

import com.omnicuris.medstore.entity.Order;
import com.omnicuris.medstore.model.request.BulkOrder;
import com.omnicuris.medstore.model.response.MedstoreResponse;
import com.omnicuris.medstore.service.api.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping("/med-api/order")
public class OrderController {

  @Autowired
  private OrderService orderService;


  @GetMapping("/all-orders")
  public ResponseEntity<MedstoreResponse<Page<Order>>> getAllOrders(
      @RequestParam("customerId") Long signedInCustomerId, @RequestParam("page") int page,
      @RequestParam("size") int size) {
    log.info("In getAllOrders controller:- get all orders for "
        + "signedInCustomerId:{}, page :{}, size:{}", signedInCustomerId, page, size);
    ResponseEntity<MedstoreResponse<Page<Order>>> responseEntity;
    MedstoreResponse<Page<Order>> engatiBotResponse =
        orderService.getAllOrders(signedInCustomerId, new PageRequest(page, size));
    responseEntity = new ResponseEntity<>(engatiBotResponse, HttpStatus.OK);
    return responseEntity;
  }

  @PostMapping("/place-order")
  public ResponseEntity<MedstoreResponse<Boolean>> createOrder(@RequestParam Integer productId,
      @RequestParam Integer quantity, @RequestParam String email) {
    log.info("In createOrder controller:- for item:{}, quantity:{}, email:{}", productId, quantity,
        email);
    ResponseEntity<MedstoreResponse<Boolean>> responseEntity =
        new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    MedstoreResponse<Boolean> customerResponse = orderService.createOrder(productId, quantity, email);
    if (customerResponse.getResponseObject()) {
      responseEntity = new ResponseEntity<>(customerResponse, HttpStatus.OK);
    }
    return responseEntity;
  }

  @PostMapping("/bulk-order")
  public ResponseEntity<MedstoreResponse<Boolean>> bulkOrderCreate(@RequestBody
      ArrayList<BulkOrder> orderList) {
    log.info("In createOrder controller:- Bulk Order list", orderList);
    if (orderList.size() != 0) {
      MedstoreResponse<Boolean> customerResponse = orderService.bulkOrderCreate(orderList);
      if (customerResponse.getResponseObject()) {
        return new ResponseEntity<>(customerResponse, HttpStatus.OK);
      }
    }
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @GetMapping("/{id}")
  public ResponseEntity<MedstoreResponse<Order>> getOrderById(@PathVariable("id") Integer id) {
    log.info("In getOrderById controller:- get order for id:{}", id);
    ResponseEntity<MedstoreResponse<Order>> responseEntity;
    MedstoreResponse<Order> productResponse = orderService.getOrderById(id);
    responseEntity = new ResponseEntity<>(productResponse, HttpStatus.OK);
    return responseEntity;
  }

}
