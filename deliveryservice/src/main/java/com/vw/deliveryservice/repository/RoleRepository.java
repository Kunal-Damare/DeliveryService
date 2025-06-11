package com.vw.deliveryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vw.deliveryservice.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
	
	@Query("SELECT r FROM Role r WHERE r.r_name = :r_name")
	Role findByR_name(String r_name);

}
