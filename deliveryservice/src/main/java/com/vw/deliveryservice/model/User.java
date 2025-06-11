package com.vw.deliveryservice.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String name;

	@Column
	private String email;

	@Column
	private String passwordhash;

	/*
	 * @Enumerated(EnumType.STRING) private Role role; // CUSTOMER, DELIVERY_AGENT,
	 */
	
    
    @Column(name = "role_name")
    private String  roleName;
    
	private String phonenumber;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column
	private String address;

	@CreationTimestamp
	private LocalDateTime createdat;

	public User(Long id, String name, String email, String passwordhash, String phonenumber, String address,
			LocalDateTime createdat, String  roleName) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.passwordhash = passwordhash;
		this.phonenumber = phonenumber;
		this.address = address;
		this.createdat = createdat;
		this.roleName = roleName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordhash() {
		return passwordhash;
	}

	public void setPasswordhash(String passwordhash) {
		this.passwordhash = passwordhash;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDateTime getCreatedat() {
		return createdat;
	}

	public void setCreatedat(LocalDateTime createdat) {
		this.createdat = createdat;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", passwordhash=" + passwordhash
				+ ", phonenumber=" + phonenumber + ", address=" + address + ", createdat=" + createdat + "]";
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String email, String passwordhash, String  roleName) {
		super();

		this.email = email;
		this.passwordhash = passwordhash;

		this.roleName = roleName;
	}
}
