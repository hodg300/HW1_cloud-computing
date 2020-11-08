package acs.logic.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;

import org.springframework.stereotype.Component;
import acs.boundary.UserBoundary;
import acs.data.UserEntity;
import acs.utils.UserFullName;

@Component
public class UserConverter {

	public UserBoundary fromEntity(UserEntity entity) {
		UserBoundary rv = new UserBoundary();
		UserFullName name = new UserFullName(entity.getFirstName(), entity.getLastName());
		rv.setName(name);
		rv.setBirthdate(entity.getBirthdate().toString());
		rv.setEmail(entity.getEmail());
		rv.setRoles(entity.getRoles().toArray(new String[0]));

		return rv;
	}

	public UserEntity toEntity(UserBoundary boundary) {
		UserEntity rv = new UserEntity();
		rv.setFirstName(boundary.getName().getFirst());
		rv.setLastName(boundary.getName().getLast());
		//rv.setBirthdate(LocalDate.parse(boundary.getBirthdate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
		try {
			rv.setBirthdate(new SimpleDateFormat("dd/MM/yyyy").parse(boundary.getBirthdate()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rv.setEmail(boundary.getEmail());
		rv.setPassword(boundary.getPassword());
		rv.setRoles(Arrays.asList(boundary.getRoles()));

		return rv;
	}

}
