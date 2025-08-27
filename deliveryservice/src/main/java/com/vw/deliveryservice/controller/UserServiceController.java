package com.vw.deliveryservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vw.deliveryservice.model.ApiResponse;
import com.vw.deliveryservice.model.User;
import com.vw.deliveryservice.securityConfig.JwtUtil;
import com.vw.deliveryservice.service.CustomUserDetailsService;
import com.vw.deliveryservice.service.UserService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/user")
public class UserServiceController {

	@Autowired
	UserService userservice;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private CustomUserDetailsService CustUserDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	private static final Logger log = LoggerFactory.getLogger(UserServiceController.class);

	/*
	 * @PostMapping("/login") public ResponseEntity<String> login(@RequestBody User
	 * user) { try { // Authenticate using name and passwordhash
	 * authenticationManager .authenticate(new
	 * UsernamePasswordAuthenticationToken(user.getName(), user.getPasswordhash()));
	 * UserDetails userDetails =
	 * CustUserDetailsService.loadUserByUsername(user.getName()); String jwt =
	 * jwtUtil.generateToken(userDetails.getUsername()); return new
	 * ResponseEntity<String>(jwt, HttpStatus.OK); } catch (Exception e) {
	 * log.error("Exception occurred while creating Authentication Token", e);
	 * return new ResponseEntity<String>("Incorrect Username or Password",
	 * HttpStatus.BAD_REQUEST); } }
	 */

	// Used for generating token for authantication
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody User user) {
		try {

			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getName(), user.getPasswordhash()));

			UserDetails userDetails = CustUserDetailsService.loadUserByUsername(user.getName());

			String role = user.getRoleName() != null ? user.getRoleName() : "CUSTOMER";
			String jwt = jwtUtil.generateToken(userDetails.getUsername(), role);

			return ResponseEntity.ok(jwt);
		} catch (UsernameNotFoundException e) {
			log.error("User not found: {}", user.getName(), e);
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
		} catch (Exception e) {
			log.error("Authentication failed for user: {}", user.getName(), e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect username or password");
		}
	}

	// Used for saving user
	@PostMapping("/save")
	public ResponseEntity<ApiResponse> saveUser(@RequestBody User user) {
		user.setPasswordhash(passwordEncoder.encode(user.getPasswordhash()));
		userservice.saveUser(user);
		ApiResponse ar = new ApiResponse("Success", "User has been created successfully");
		return ResponseEntity.ok(ar);

	}

	// Used for getting information of all user - Only ADMIN
	@GetMapping("/getAllUsers")
	public ResponseEntity<?> getUsers() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		List<User> ul = userservice.getAllUsers();
		return new ResponseEntity<>(ul, HttpStatus.ACCEPTED);
	}

	// Used for updating user details
	@PutMapping("/updateUser/{id}")
	public ResponseEntity<ApiResponse> updateUser(@PathVariable int id, @RequestBody User user) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		userservice.updateUser(id, user);
		ApiResponse ar = new ApiResponse("Success", "User has been updated successfully");
		return ResponseEntity.ok(ar);
	}

	// Delete user from system
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable int id) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			userservice.deleteUser(id);
		} catch (Exception e) {
			ApiResponse ar = new ApiResponse("Failed", "Provided User ID " + id + " is not present in our data.");
			return new ResponseEntity<>(ar,HttpStatus.BAD_REQUEST);
		}
		ApiResponse ar = new ApiResponse("Success", "User has been deleted successfully");
		return ResponseEntity.ok(ar);
	}
}
