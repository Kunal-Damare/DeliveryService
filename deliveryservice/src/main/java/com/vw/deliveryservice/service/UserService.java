package com.vw.deliveryservice.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vw.deliveryservice.model.Role;
import com.vw.deliveryservice.model.User;
import com.vw.deliveryservice.repository.RoleRepository;
import com.vw.deliveryservice.repository.UserRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

	@Autowired
	UserRepository repository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Transactional
	public User saveUser(User user) {
		
		
		  String roleName = user.getRoleName();
		  
		  Role role = roleRepository.findByR_name(roleName);
		  
		  if (role == null) { 
			  role = new Role();
			  role.setR_name(roleName);
			  role = roleRepository.save(role); 
			  }
		  
		  user.setRoleName(roleName);
		 
       
		return repository.save(user);
	}

	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	public void updateUser(int id, User user) {
		Optional<User> optionalUser = repository.findById(id);

		if (optionalUser.isPresent()) {
			User existingUser = optionalUser.get();
			boolean isUpdated = false;

			if (user.getName() != null && !user.getName().equals(existingUser.getName())) {
				existingUser.setName(user.getName());
				isUpdated = true;
			}

			if (user.getEmail() != null && !user.getEmail().equals(existingUser.getEmail())) {
				existingUser.setEmail(user.getEmail());
				isUpdated = true;
			}

			if (user.getPasswordhash() != null && !user.getPasswordhash().equals(existingUser.getPasswordhash())) {
				//existingUser.setPasswordhash(user.getPasswordhash());
				existingUser.setPasswordhash(passwordEncoder.encode(user.getPasswordhash()));
				isUpdated = true;
			}

			if (user.getPhonenumber() != null && !user.getPhonenumber().equals(existingUser.getPhonenumber())) {
				existingUser.setPhonenumber(user.getPhonenumber());
				isUpdated = true;
			}

			if (user.getAddress() != null && !user.getAddress().equals(existingUser.getAddress())) {
				existingUser.setAddress(user.getAddress());
				isUpdated = true;
			}

			if (user.getRoleName() != null && !user.getRoleName().equals(existingUser.getRoleName())) {
				existingUser.setRoleName(user.getRoleName());
				isUpdated = true;
			}

			if (isUpdated) {
				repository.save(existingUser);
			}
		} else {
			throw new NoSuchElementException("User  not found with ID: " + id);
		}
	}

	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		Optional<User> user = repository.findById(id);
		
		if(!user.isEmpty())
		{
			repository.deleteById(id);
		}else {
			throw new NoSuchElementException("User is not present in database with id : " + id);
		}
	}
}
