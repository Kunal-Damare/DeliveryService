package com.vw.deliveryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vw.deliveryservice.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
	
	
// please add something 
}
