package com.omnicuris.medstore.service.impl;

import com.omnicuris.medstore.entity.Customer;
import com.omnicuris.medstore.model.dto.CustomerDTO;
import com.omnicuris.medstore.model.response.MedstoreResponse;
import com.omnicuris.medstore.repository.CustomerRepository;
import com.omnicuris.medstore.service.api.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Objects;

import static org.springframework.beans.BeanUtils.copyProperties;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  @Override
  public MedstoreResponse<Boolean> createCustomer(CustomerDTO customerDTO) {
    MedstoreResponse<Boolean> createCustomerResponse = new MedstoreResponse<>(Boolean.FALSE);
    log.debug("In createCustomer:  create customer for customerDTO: {}, signedInCustomerId",
        customerDTO);
    try {
      Customer customerWithExistingEmail = customerRepository.findByEmail(customerDTO.getEmail());
      if (Objects.nonNull(customerWithExistingEmail)) {
        log.error("Customer created with  duplicate email : {}", customerDTO.getEmail());
        createCustomerResponse.setResponseObject(Boolean.FALSE);
      } else {
        Customer customer = new Customer();
        copyProperties(customerDTO, customer);
        customer.setCreatedOn(new Timestamp(System.currentTimeMillis()));
        customerRepository.save(customer);
        createCustomerResponse.setResponseObject(Boolean.TRUE);
      }
    } catch (Exception e) {
      createCustomerResponse.setResponseObject(Boolean.FALSE);
      log.error("customer creation failed for customerDTO: {}", customerDTO, e);
    }
    return createCustomerResponse;
  }
}
