package com.omnicuris.medstore.service.api;

import com.omnicuris.medstore.entity.Order;
import com.omnicuris.medstore.model.request.BulkOrder;
import com.omnicuris.medstore.model.response.MedstoreResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;

public interface OrderService {
  /**
   *
   * @param signedInCustomerId
   * @param pageRequest
   * @return
   */
  MedstoreResponse<Page<Order>> getAllOrders(Long signedInCustomerId, PageRequest pageRequest);

  /**
   *
   * @param productId
   * @param quantity
   * @param email
   * @return
   */
  MedstoreResponse<Boolean> createOrder(Integer productId, Integer quantity, String email);

  /**
   *
   * @param orderList
   * @return
   */
  MedstoreResponse<Boolean> bulkOrderCreate(ArrayList<BulkOrder> orderList);

  /**
   *
   * @param orderId
   * @return
   */
  MedstoreResponse<Order> getOrderById(Integer orderId);

}
