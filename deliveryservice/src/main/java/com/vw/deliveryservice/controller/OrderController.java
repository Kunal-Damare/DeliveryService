package com.vw.deliveryservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vw.deliveryservice.model.ApiResponse;
import com.vw.deliveryservice.model.Order;
import com.vw.deliveryservice.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@PostMapping("/CreateOrder")
	public ResponseEntity<?> createOrder(@RequestBody Order order) {
		//TODO: process POST request
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		orderService.createOrder(order);
		ApiResponse ar = new ApiResponse("Success", "Order created successfully");
		return ResponseEntity.ok(ar);
	}
	

}
