package com.way.controller;

import com.way.model.User;
import com.way.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:tests-context.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class})
public class UserControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private UserController userController;

	@Autowired
	private UserService userService;

	@Before
	public void setup() {
		prepareUserServiceMock();

		mockMvc = standaloneSetup(userController)
				.alwaysExpect(status().isOk())
				.alwaysExpect(content().contentType("application/json;charset=UTF-8"))
				.build();
	}

	private void prepareUserServiceMock() {
		String email = "ww@ww.ww";
		String password = "wwwwwww";

		User user = new User();
		user.setEmail(email);
		user.setPassword(password);

		Mockito.when(userService.findOneByEmail(email)).thenReturn(user);
		Mockito.when(userService.findOneByEmailAndPassword(email, password)).thenReturn(user);
	}

	@Test
	public void registerWithEmptyCredentials() throws Exception {
		mockMvc.perform(post("/registration.json"))
			.andExpect(content().string("{\"errors\":{\"email\":[\"may not be empty\"],\"password\":[\"may not be empty\"]},\"success\":false}"));
	}

	@Test
	public void registerWithInvalidCredentials() throws Exception {
		mockMvc.perform(post("/registration.json").param("email", "eeee").param("password", "qqq"))
				.andExpect(content().string("{\"errors\":{\"email\":[\"not a well-formed email address\"],\"password\":[\"size must be between 4 and 20\"]},\"success\":false}"));
	}

	@Test
	public void registerWithValidCredentials() throws Exception {
		mockMvc.perform(post("/registration.json").param("email", "username@example.com").param("password", "qwerty"))
				.andExpect(content().string("{\"success\":true}"));
	}

	@Test
	public void registerWithUsedCredentials() throws Exception {
		mockMvc.perform(post("/registration.json").param("email", "ww@ww.ww").param("password", "wwwwwww"))
				.andExpect(content().string("{\"errors\":{\"email\":[\"User with this email already exists\"]},\"success\":false}"));
	}



	@Test
	public void loginWithEmptyCredentials() throws Exception {
		mockMvc.perform(post("/login.json"))
				.andExpect(content().string("{\"success\":false}"));
	}

	@Test
	public void loginWithNotUsedCredentials() throws Exception {
		mockMvc.perform(post("/login.json").param("email", "username@example.com").param("password", "qwerty"))
				.andExpect(content().string("{\"success\":false}"));
	}

	@Test
	public void loginWithUsedCredentials() throws Exception {
		mockMvc.perform(post("/login.json").param("email", "ww@ww.ww").param("password", "wwwwwww"))
				.andExpect(content().string("{\"success\":true}"));
	}
}