package com.epam.smvc.form;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class UserForm {
	
	@NotEmpty(message="Please enter a username")
	@Size(min=4, max=50, message="Username must be at least 4 character and maximum 50 character")
	private String username;
	
	@NotEmpty(message="Please enter your first name")
	@Size(min=0, max=50, message="The first name must between 0 and 50 characters")
	private String firstName;
	
	@NotEmpty(message="Please enter your last name")
	@Size(min=0, max=50, message="The last name must between 0 and 50 characters")
	private String lastName;
	
	@NotEmpty(message="Please enter your password")
	@Size(min=0, max=50, message="The password must between 0 and 50 characters")
	private String pwd;
	
	private boolean enabled;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
