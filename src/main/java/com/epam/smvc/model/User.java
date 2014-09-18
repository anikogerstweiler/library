package com.epam.smvc.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class User implements UserDetails {

	private static final long serialVersionUID = -464364469214612590L;

	@Id
	@Column(name = "username")
	@NotEmpty(message="Please enter a username")
	@Size(min=4, max=50, message="Username must be at least 4 character and maximum 50 character")
	private String username;

	@Column(name = "firstname")
	@NotEmpty(message="Please enter your first name")
	@Size(min=0, max=50, message="The first name must between 0 and 50 characters")
	private String firstname;

	@Column(name = "lastname")
	@NotEmpty(message="Please enter your last name")
	@Size(min=0, max=50, message="The last name must between 0 and 50 characters")
	private String lastname;

	@Column(name = "password")
	@NotEmpty(message="Please enter your password")
	@Size(min=0, max=100, message="The password must between 0 and 50 characters")
	private String password;

	@Column(name = "enabled")
	private Boolean enabled;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private List<Authority> entityAuthorities = new ArrayList<Authority>();

	@Transient
	private Set<GrantedAuthority> authorities;
	
	@Transient
	private boolean accountNonExpired;
	
	@Transient
	private boolean accountNonLocked;
	
	@Transient
	private boolean credentialsNonExpired;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	public List<Authority> getEntityAuthorities() {
		return entityAuthorities;
	}

	public void setEntityAuthorities(List<Authority> entityAuthorities) {
		this.entityAuthorities = entityAuthorities;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public void setAuthorities(Set<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
}
