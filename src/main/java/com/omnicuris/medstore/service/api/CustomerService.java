package com.omnicuris.medstore.service.api;

import com.omnicuris.medstore.model.dto.CustomerDTO;
import com.omnicuris.medstore.model.response.MedstoreResponse;

public interface CustomerService {

  /**
   * @param customerDTO
   *
   * @return
   */
  MedstoreResponse<Boolean> createCustomer(CustomerDTO customerDTO);
}
