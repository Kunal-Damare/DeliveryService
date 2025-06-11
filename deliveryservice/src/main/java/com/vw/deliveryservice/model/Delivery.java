
package com.vw.deliveryservice.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity

@Table(name = "deliveries")
public class Delivery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "order_id")
	private Order order;

	@ManyToOne
	@JoinColumn(name = "delivery_agent_id")
	private User deliveryAgent;

	@ManyToOne // Changed from @Column to @ManyToOne
	@JoinColumn(name = "delivary_status")
	private DeliveryStatus status; // ASSIGNED,OUT_FOR_DELIVERY, DELIVERED

	@Column
	private LocalDateTime estimatedDeliveryTime;

	@Column
	private LocalDateTime actualDeliveryTime;

	public Long getId() {
		return id;
	}

	public Order getOrder() {
		return order;
	}

	public User getDeliveryAgent() {
		return deliveryAgent;
	}

	public DeliveryStatus getStatus() {
		return status;
	}

	public LocalDateTime getEstimatedDeliveryTime() {
		return estimatedDeliveryTime;
	}

	public LocalDateTime getActualDeliveryTime() {
		return actualDeliveryTime;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public void setDeliveryAgent(User deliveryAgent) {
		this.deliveryAgent = deliveryAgent;
	}

	public void setStatus(DeliveryStatus status) {
		this.status = status;
	}

	public void setEstimatedDeliveryTime(LocalDateTime estimatedDeliveryTime) {
		this.estimatedDeliveryTime = estimatedDeliveryTime;
	}

	public void setActualDeliveryTime(LocalDateTime actualDeliveryTime) {
		this.actualDeliveryTime = actualDeliveryTime;
	}

	public Delivery(Long id, Order order, User deliveryAgent, DeliveryStatus status,
			LocalDateTime estimatedDeliveryTime, LocalDateTime actualDeliveryTime) {
		super();
		this.id = id;
		this.order = order;
		this.deliveryAgent = deliveryAgent;
		this.status = status;
		this.estimatedDeliveryTime = estimatedDeliveryTime;
		this.actualDeliveryTime = actualDeliveryTime;
	}

	public Delivery() {
		super();
		// TODO Auto-generated constructor stub
	}

}
