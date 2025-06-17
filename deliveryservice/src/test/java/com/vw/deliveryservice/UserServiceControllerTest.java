package com.vw.deliveryservice;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vw.deliveryservice.controller.UserServiceController;
import com.vw.deliveryservice.model.User;
import com.vw.deliveryservice.securityConfig.JwtUtil;
import com.vw.deliveryservice.service.CustomUserDetailsService;
import com.vw.deliveryservice.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceControllerTest {

	private MockMvc mockMvc;

	@Mock
	private UserService userService;
	@Mock
	private JwtUtil jwtUtil;
	@Mock
	private PasswordEncoder passwordEncoder;
	@Mock
	private CustomUserDetailsService CustUserDetailsService;
	@Mock
	private AuthenticationManager authenticationManager;
	@Mock
	private Authentication authentication;
	@Mock
	private SecurityContext securityContext;

	@InjectMocks
	private UserServiceController userServiceController;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(userServiceController).build();
	}

	@Test
	public void testLogin() throws Exception {
		User user = new User();
		user.setName("testUser");
		user.setPasswordhash("password");
		when(authenticationManager.authenticate(any())).thenReturn(null);
		when(CustUserDetailsService.loadUserByUsername(any()))
				.thenReturn(mock(org.springframework.security.core.userdetails.User.class));
		when(jwtUtil.generateToken(any(), any())).thenReturn("mockedToken");
		String requestBody = new ObjectMapper().writeValueAsString(user);
		mockMvc.perform(post("/user/login").contentType(MediaType.APPLICATION_JSON).content(requestBody))
				.andExpect(status().isOk()).andExpect(content().string("mockedToken"));
	}

	// ✅ Test Save User @Test void testSaveUser() throws Exception { User user = new
	// User(); user.setPasswordhash("plainpassword");
	// when(passwordEncoder.encode(any())).thenReturn("hashedpassword"); String
	// requestBody = new ObjectMapper().writeValueAsString(user);
	// mockMvc.perform(post("/user/save") .contentType(MediaType.APPLICATION_JSON)
	// .content(requestBody)) .andExpect(status().isOk())
	// .andExpect(jsonPath("$.status").value("Success"))
	// .andExpect(jsonPath("$.message").value("User has been created
	// successfully")); verify(userService, times(1)).saveUser(any(User.class)); }

	@Test
	public void testSaveUser() throws Exception {
		User user = new User();
		user.setName("testUser");
		user.setPasswordhash("password");
		when(passwordEncoder.encode(any())).thenReturn("hashedpassword");
		String requestBody = new ObjectMapper().writeValueAsString(user);
		mockMvc.perform(post("user/saveUser").contentType(MediaType.APPLICATION_JSON).content(requestBody))
				.andExpect(status().isOk()).andExpect(jsonPath("$.status").value("Success"))
				.andExpect(jsonPath("$.message").value("User has been created successfully"));
		verify(userService, times(1)).saveUser(any(User.class));
	}
	
	@Test
	public void testGetAllUsers() throws Exception
	{
		when(securityContext.getAuthentication()).thenReturn(authentication);
		when(authentication.getName()).thenReturn("AdminUser");
		SecurityContextHolder.setContext(securityContext);
		
		List<User> mockUser = List.of(new User(), new User() , new User());
		when(userService.getAllUsers()).thenReturn(mockUser);
		
		mockMvc.perform(get("getAllUsers"))
		       .andExpect(status().isAccepted())
		       .andExpect(jsonPath("$.length()")
		       .value(mockUser.size()));
	}
	
	

}
