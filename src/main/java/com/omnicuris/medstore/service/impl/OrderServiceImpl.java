package com.omnicuris.medstore.service.impl;

import com.omnicuris.medstore.entity.Customer;
import com.omnicuris.medstore.entity.Inventory;
import com.omnicuris.medstore.entity.Order;
import com.omnicuris.medstore.model.request.BulkOrder;
import com.omnicuris.medstore.model.response.MedstoreResponse;
import com.omnicuris.medstore.repository.CustomerRepository;
import com.omnicuris.medstore.repository.InventoryRepository;
import com.omnicuris.medstore.repository.OrderRepository;
import com.omnicuris.medstore.service.api.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private InventoryRepository inventoryRepository;

  @Autowired
  private CustomerRepository customerRepository;

  @Override
  public MedstoreResponse<Page<Order>> getAllOrders(Long signedInCustomerId,
      PageRequest pageRequest) {
    log.info("Going to get all orders for - signedInCustomerId:{}, pageRequest:{}",
        signedInCustomerId, pageRequest);
    MedstoreResponse<Page<Order>> getAllOrders = new MedstoreResponse<>();
    Page<Order> orderPage = new PageImpl(new ArrayList(), pageRequest, 0);
    try {
      Page<Order> orders = orderRepository.findAll(pageRequest);
      orderPage = new PageImpl<>(orders.getContent(), pageRequest, orders.getTotalElements());
      getAllOrders.setResponseObject(orderPage);
    } catch (Exception e) {
      getAllOrders.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
      log.error("Error in getting orders for signedInCustomerId : {}", signedInCustomerId, e);
    }
    return getAllOrders;
  }

  @Override
  public MedstoreResponse<Boolean> createOrder(Integer productId, Integer quantity, String email) {
    MedstoreResponse<Boolean> createOrderResponse = new MedstoreResponse<>();
    try {
      Integer cid;
      Inventory inventory = inventoryRepository.findOne(productId);
      if (Objects.nonNull(inventory) && quantity > inventory.getQuantity()) {
        log.error("Quantity not available for item : {}", productId);
        createOrderResponse.setResponseObject(Boolean.FALSE);
      } else {
        Customer customer = customerRepository.findByEmail(email);
        cid = customer.getId();
        Order order = Order.builder().customerId(cid).productId(productId).quantity(quantity).
            build();
        orderRepository.save(order);
        createOrderResponse.setResponseObject(Boolean.TRUE);
        inventory.setQuantity(inventory.getQuantity() - quantity);
        inventoryRepository.save(inventory);
      }
    } catch (Exception e) {
      createOrderResponse.setResponseObject(Boolean.FALSE);
      log.error("order creation failed for item: {}, quantity:{}, email:{}", productId, quantity,
          email, e);
    }
    return createOrderResponse;
  }

  @Override
  public MedstoreResponse<Boolean> bulkOrderCreate(ArrayList<BulkOrder> orderList) {
    MedstoreResponse<Boolean> createOrderResponse = new MedstoreResponse<>();
    for (BulkOrder bulkOrder : orderList) {
      Inventory inventory = inventoryRepository.findByProductId(bulkOrder.getProductId());
      if (inventory.getQuantity() <= bulkOrder.getQuantity()) {
        log.error("Quantity not available for item : {}", bulkOrder.getProductId());
        createOrderResponse.setResponseObject(Boolean.FALSE);
      } else {
        createOrderResponse =  insertOrder(bulkOrder, inventory);
      }
    }
    return createOrderResponse;
  }

  private MedstoreResponse<Boolean> insertOrder(BulkOrder bulkOrder, Inventory inventory) {
    MedstoreResponse<Boolean> createOrderResponse = new MedstoreResponse<>();
    Integer cid;
    Customer customer = customerRepository.findByEmail(bulkOrder.getEmail());
    cid = customer.getId();
    Order order =
        Order.builder().customerId(cid).productId(bulkOrder.getProductId()).quantity(bulkOrder.getQuantity()).
            build();
    orderRepository.save(order);
    createOrderResponse.setResponseObject(Boolean.TRUE);
    inventory.setQuantity(inventory.getQuantity() - bulkOrder.getQuantity());
    inventoryRepository.save(inventory);
    return createOrderResponse;
  }

  @Override
  public MedstoreResponse<Order> getOrderById(Integer orderId) {
    log.info("get order for - orderId:{}", orderId);
    MedstoreResponse<Order> getInventoryResponse = new MedstoreResponse<>();
    try {
      Order order = orderRepository.findOne(orderId);
      getInventoryResponse.setResponseObject(order);
    } catch (Exception e) {
      getInventoryResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
      log.error("Error in getting  order for orderId:{}", orderId, e);
    }
    return getInventoryResponse;
  }
}
