package com.vw.deliveryservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vw.deliveryservice.model.ApiResponse;
import com.vw.deliveryservice.model.Role;
import com.vw.deliveryservice.repository.RoleRepository;

@RestController
@RequestMapping("/role")
public class RoleServiceController {

	@Autowired
	RoleRepository roleRepository;

	// Retrieve all roles

	@GetMapping("/getAllRoles")
	public ResponseEntity<?> getAllRoles() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		List<Role> role = roleRepository.findAll();

		return new ResponseEntity<>(role, HttpStatus.ACCEPTED);
	}

	// Retrieve specific role details.

	@GetMapping("/getRoleByID/{id}")
	public ResponseEntity<?> getRoleById(@PathVariable int id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Optional<Role> role = roleRepository.findById(id);
		if(!role.isEmpty()) {
		return new ResponseEntity<>(role, HttpStatus.ACCEPTED);
		}else
		{
			ApiResponse ar = new ApiResponse("Failed", "The role with the provided ID " + id + " is not present in our data");
			return new ResponseEntity<>(ar,HttpStatus.BAD_REQUEST);
		}
	}
}
