package acs.logic;

import java.util.List;

import acs.boundary.UserBoundary;

public interface UserService {

	UserBoundary createUser(UserBoundary userBoundary);

	UserBoundary getUser(String email);
	
	UserBoundary login(String email, String password);

	void updateUser(String email, UserBoundary update);
	
	void deleteAllUsers();

//	List<UserBoundary> getAllUsers(int size, int page, String sortBy, String sortOrder);

	List<UserBoundary> getAllUsers(String criteriaType, String criteriaValue, int size, int page,
			String sortBy, String sortOrder);
	
	

	
}
