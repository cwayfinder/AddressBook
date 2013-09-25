package com.way.viewmodel;


import com.way.model.User;

public class UserDto {

	private Long id;

	private String email;

	public UserDto() {
	}

	public UserDto(User user) {
		if (user != null) {
			id = user.getId();
			email = user.getEmail();
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
