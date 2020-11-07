package acs.data;

import java.util.Date;


import acs.utils.UserFullName;

public class UserEntity {

	private String email;// EMAIL PK VARCHAR(255)
	private UserFullName name; // FIRST VARCHAR(255)
								// LAST VARCHAR(255)
	private String password;// PASSWORD VARCHAR(255)
	private Date birthdate;// CREATED_TIME_STAMP TIMESTAMP
	private String[] roles;

	public UserEntity() {
		// TODO Auto-generated constructor stub
	}

	public UserEntity(String email, UserFullName name, String password, Date birthdate, String[] roles) {
		super();
		this.email = email;
		this.name = name;
		this.password = password;
		this.birthdate = birthdate;
		this.roles = roles;
	}

	public UserFullName getName() {
		return name;
	}

	public void setName(UserFullName name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
