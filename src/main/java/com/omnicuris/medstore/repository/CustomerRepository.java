package com.omnicuris.medstore.repository;


import com.omnicuris.medstore.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

  /**
   *
   * @param email
   * @return
   */
  Customer findByEmail(String email);
}
