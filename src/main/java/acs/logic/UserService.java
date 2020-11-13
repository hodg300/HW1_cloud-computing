package acs.logic;

import java.util.List;

import acs.boundary.UserBoundary;

public interface UserService {

	public UserBoundary createUser(UserBoundary userBoundary);

	public UserBoundary getUser(String email);
	
	public UserBoundary login(String email, String password);

	public void updateUser(String email, UserBoundary update);
	
	public void deleteAllUsers();

	public List<UserBoundary> getAllUsers(int size, int page, String sortBy, String sortOrder);

	public List<UserBoundary> getAllUsersByCriteriaType(String criteriaType, String criteriaValue, int size, int page,
			String sortBy, String sortOrder);
	
	

	
}
