
package com.vw.deliveryservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {

	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int r_id;
    
	private String r_name;

	public Role() {
		// TODO Auto-generated constructor stub }
	}

	public Role(int r_id, String r_name) {
		super();
		this.r_id = r_id;
		this.r_name = r_name;
	}

	public Role(String roleName) { 
		// TODO Auto-generated constructor stub }
	}

	public int getR_id() {
		return r_id;
	}

	public String getR_name() {
		return r_name;
	}

	public void setR_id(int r_id) {
		this.r_id = r_id;
	}

	public void setR_name(String r_name) {
		this.r_name = r_name;
	}

}
