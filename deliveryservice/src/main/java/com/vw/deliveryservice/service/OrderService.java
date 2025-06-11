package com.vw.deliveryservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vw.deliveryservice.model.Order;
import com.vw.deliveryservice.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	OrderRepository orderRepository;

	public Order createOrder(Order order) {
		// TODO Auto-generated method stub
		
		return orderRepository.save(order);
	}

}
