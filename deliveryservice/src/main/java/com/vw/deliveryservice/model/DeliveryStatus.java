
package com.vw.deliveryservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Delivery_Status")
public class DeliveryStatus {

	/* ASSIGNED, OUT_FOR_DELIVERY, DELIVERED */
	
	@Id
	@Column(name = "delivery_status")
	private String deliveryStatus;

	public DeliveryStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DeliveryStatus(String deliveryStatus) {
		super();
		this.deliveryStatus = deliveryStatus;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
	
	

}
