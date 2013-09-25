package com.way.controller;

import com.way.model.User;
import com.way.service.UserService;
import com.way.viewmodel.SingleResponse;
import com.way.viewmodel.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

	@Autowired
	private UserService service;

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> registration(@Valid User user, BindingResult result, HttpSession session) throws IOException {
		System.out.printf("Attempt to register user with email \"%s\"", user.getEmail());

		Map<String, Object> response = new HashMap<String, Object>();

		boolean valid = !result.hasErrors();
		if (!valid) {
			Map<String, List<String>> errors = new HashMap<String, List<String>>();
			for (ObjectError e : result.getAllErrors()) {
				FieldError error = (FieldError) e;

				String field = error.getField();
				if (!errors.containsKey(field)) {
					errors.put(field, new ArrayList<String>());
				}

				errors.get(field).add(error.getDefaultMessage());
			}

			response.put("errors", errors);
		} else {
			if (valid = service.findOneByEmail(user.getEmail()) == null) {
				service.save(user);
			} else {
				Map<String, List<String>> errors = new HashMap<String, List<String>>();
				errors.put("email", new ArrayList<String>());
				errors.get("email").add("User with this email already exists. Click \"Use existing account\" button");
				response.put("errors", errors);
			}
		}

		response.put("success", valid);

		if (valid) {
			session.setAttribute("user", user);

			Map<String, Object> userData = new HashMap<String, Object>();
			userData.put("id", user.getId());
			userData.put("email", user.getEmail());
			response.put("user", userData);
		}

		return response;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(@Valid User user, BindingResult result, HttpSession session) throws IOException {
		System.out.printf("Attempt to login user with email \"%s\"", user.getEmail());

		Map<String, Object> response = new HashMap<String, Object>();

		user = service.findOneByEmailAndPassword(user.getEmail(), user.getPassword());

		if (user != null) {
			session.setAttribute("user", user);

			Map<String, Object> userData = new HashMap<String, Object>();
			userData.put("id", user.getId());
			userData.put("email", user.getEmail());
			response.put("user", userData);
		} else {
			Map<String, List<String>> errors = new HashMap<String, List<String>>();
			errors.put("email", new ArrayList<String>());
			errors.get("email").add("User with such credentials not found. Click \"Create new account\" button.");
			response.put("errors", errors);
		}
		response.put("success", user != null);

		return response;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> logout(HttpSession session) throws IOException {
		session.removeAttribute("user");

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("success", true);
		return response;
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public SingleResponse<UserDto> getAuthenticatedUser(HttpSession session) throws IOException {
		User user = (User) session.getAttribute("user");
		return new SingleResponse<UserDto>(user != null, new UserDto(user));
	}
}
