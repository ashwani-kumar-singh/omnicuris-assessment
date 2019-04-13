package com.omnicuris.medstore.controller;

import com.omnicuris.medstore.model.dto.CustomerDTO;
import com.omnicuris.medstore.model.response.MedstoreResponse;
import com.omnicuris.medstore.service.api.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/med-api/customer")
public class CustomerController {

  @Autowired
  private CustomerService customerService;

  @PostMapping("/create")
  public ResponseEntity<MedstoreResponse<Boolean>> createCustomer(
      @RequestBody CustomerDTO customerDTO) {
    log.info("Inside createCustomer controller for customerDTO:{}", customerDTO);
    ResponseEntity<MedstoreResponse<Boolean>> responseEntity =
        new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    MedstoreResponse<Boolean> customerResponse = customerService.createCustomer(customerDTO);
    if (customerResponse.getResponseObject()) {
      responseEntity = new ResponseEntity<>(customerResponse, HttpStatus.OK);
    }
    return responseEntity;
  }
}
