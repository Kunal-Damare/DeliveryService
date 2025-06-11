
package com.vw.deliveryservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Order_Status")
public class OrderStatus {

	@Id
	@Column(name = "order_status")
	private String orderStatus; /* PLACED, IN_TRANSIT, DELIVERED, CANCELLED */

	public OrderStatus() {
		// TODO Auto-generated constructor stub
	}

	public OrderStatus(String orderStatus) {
		super();
		this.orderStatus = orderStatus;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

}
