package acs.boundary;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.hibernate.validator.internal.constraintvalidators.EmailValidator;

import acs.utils.UserFullName;

public class UserBoundary {

	private String email;
	private UserFullName name;
	private String password;
	private String birthdate;
	private String[] roles;

	public UserBoundary() {
		// TODO Auto-generated constructor stub
	}

	public UserBoundary(String email, UserFullName name, String password, String birthdate, String[] roles) {
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

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
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
	
	private void validateRoles() {
		for(String role : roles) {
			if(role == null) {
				throw new RuntimeException("Role was not instantiate");
			}
			if(role.isEmpty()) {
				throw new RuntimeException("Role can not be empty");
			}
		}
	}
	
	public void validate(){
		
		if (this.email == null) {
			throw new RuntimeException("UserId email was not instantiate");
		}
		
		if (!new EmailValidator().isValid(this.email, null) || email.isEmpty() || this.email.contains("#")) {
            throw new RuntimeException("The email is not in a valid format");
        }
		
		name.validate();
		
		if(password == null) {
			throw new RuntimeException("Password was not instantiate");
		}
		
		if(password.length() < 5 || !password.matches(".*[0-9].*")) {
			throw new RuntimeException("Password is not illegal");
		}
			
		System.out.println(birthdate);
		
		final String NEW_FORMAT = "dd/MM/yyyy";
		final String OLD_FORMAT = "yyyy/MM/dd";

	
	
//		String newDateString;
//
//		SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
//		Date d = sdf.parse(birthdate);
//		sdf.applyPattern(NEW_FORMAT);
//		newDateString = sdf.format(d);
//		
//		
//		//Validate the date
//	    Date.valueOf(new SimpleDateFormat("dd/MM/yyyy").parse(birthdate));
		
		
	}
		
		

}
