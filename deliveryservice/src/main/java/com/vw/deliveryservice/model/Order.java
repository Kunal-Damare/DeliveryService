
package com.vw.deliveryservice.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private User customer; // Links to User entity
	
	
	@ManyToOne
	@JoinColumn(name = "delivery_agent_id")
	private User deliveryAgent; // Assigned delivery agent
	
	
	@ManyToOne // Changed from @Column to @ManyToOne
	@JoinColumn(name = "order_status")
	private OrderStatus status; // PLACED,IN_TRANSIT,DELIVERED,CANCELLED

	@Column
	private BigDecimal totalPrice;

	@CreationTimestamp
	private LocalDateTime createdAt;

	@Column
	private LocalDateTime deliveredAt;

	public Order(Long id, User customer, User deliveryAgent, OrderStatus status, BigDecimal totalPrice,
			LocalDateTime createdAt, LocalDateTime deliveredAt) {
		super();
		this.id = id;
		this.customer = customer;
		this.deliveryAgent = deliveryAgent;
		this.status = status;
		this.totalPrice = totalPrice;
		this.createdAt = createdAt;
		this.deliveredAt = deliveredAt;
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public User getCustomer() {
		return customer;
	}

	public User getDeliveryAgent() {
		return deliveryAgent;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getDeliveredAt() {
		return deliveredAt;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public void setDeliveryAgent(User deliveryAgent) {
		this.deliveryAgent = deliveryAgent;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public void setDeliveredAt(LocalDateTime deliveredAt) {
		this.deliveredAt = deliveredAt;
	}
	
	
}
