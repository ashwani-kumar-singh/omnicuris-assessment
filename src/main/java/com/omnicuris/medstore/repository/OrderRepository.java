package com.omnicuris.medstore.repository;

import com.omnicuris.medstore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
