package acs.logic.utils;

import java.sql.Date;

import org.springframework.stereotype.Component;

import acs.boundary.UserBoundary;
import acs.data.UserEntity;

@Component
public class UserConverter {

	public UserBoundary fromEntity(UserEntity entity) {
		UserBoundary rv = new UserBoundary();
		rv.setName(entity.getName());
		rv.setBirthdate(entity.getBirthdate().toString());
		rv.setEmail(entity.getEmail());
		rv.setRoles(entity.getRoles());
		

		return rv;
	}

	public UserEntity toEntity(UserBoundary boundary) {
		UserEntity rv = new UserEntity();
		rv.setName(boundary.getName());
		rv.setBirthdate(Date.valueOf(boundary.getBirthdate()));
		rv.setEmail(boundary.getEmail());
		rv.setPassword(boundary.getPassword());
		rv.setRoles(boundary.getRoles());

		return rv;
	}

}
