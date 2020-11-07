package acs.logic.mockups;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acs.boundary.UserBoundary;
import acs.data.UserEntity;
import acs.logic.EnhancedUserService;
//import org.springframework.stereotype.Service;
import acs.logic.utils.UserConverter;



@Service
public class UserServiceMockup implements EnhancedUserService {
	private List<UserEntity> allUsers;
	private UserConverter userConverter;

	@Autowired
	public UserServiceMockup(UserConverter userConverter) {
		super();
		this.userConverter = userConverter;
	}
	

	@PostConstruct
	public void init() {
		// synchronized Java collection
		this.allUsers = Collections.synchronizedList(new ArrayList<>());
	}

	@Override
	public UserBoundary createUser(UserBoundary userBoundary) {
		System.out.println("im hereee");
		userBoundary.validate();

		try {
			findUser(userBoundary.getEmail()); //if it wont find it will throw an exception
		}catch(RuntimeException re) {
			UserEntity newUser = userConverter.toEntity(userBoundary);
			this.allUsers.add(newUser);
			return userBoundary;
		}
		throw new RuntimeException("user is already exists in the system");
	}

	@Override
	public UserBoundary getUser(String email) {
		UserEntity user = findUser(email);
		return this.userConverter.fromEntity(user);
	}

	@Override
	public UserBoundary login(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUser(String email, UserBoundary update) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllUsers() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserBoundary getAllUsers(int size, int page, String sortBy, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserBoundary[] getAllUsersByCriteriaType(int criteriaType, int criteriaValue, int size, int page,
			String sortBy, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}


	// check if user exist in the system
	private UserEntity findUser(String userEmail) {
		return this.allUsers.stream()
				.filter(userEntity -> userEntity.getEmail().equals(userEmail))
				.findFirst().orElseThrow(() -> new RuntimeException("User not found"));
		
	}
	
//
//	@Override
//	public UserBoundary createUser(UserBoundary user) {
//		
//		user.validation();
//		user.getUserId().setDomain(projectName);
//		try {
//			findUser(user.getUserId().getDomain(), user.getUserId().getEmail()); //if it wont find it will throw an exception
//		}catch(RuntimeException re) {
//			UserEntity newUser = userConverter.toEntity(user);
//			this.allUsers.add(newUser);
//			return user;
//		}
//		throw new RuntimeException("user is already exists in the system");
//	}
//
//	@Override
//	public UserBoundary login(String userDomain, String userEmail) {
//		// mockup reads data from list
//		UserEntity user = findUser(userDomain, userEmail);
//		return this.userConverter.fromEntity(user);
//	}
//
//	@Override
//	public UserBoundary updateUser(String userDomain, String userEmail, UserBoundary update) {
//		UserEntity updateUser = findUser(userDomain, userEmail);
//		
//		//---Inside the setters there are null checks---
//		updateUser.setAvatar(update.getAvatar());
//		updateUser.setRole(update.getRole());
//		updateUser.setUsername(update.getUsername());
//		
//		return this.userConverter.fromEntity(updateUser);
//	}
//
//	@Override
//	public List<UserBoundary> getAllUsers(String adminDomain, String adminEmail) {
//		checkAdmin(adminDomain, adminEmail);
//		return this.allUsers.stream().map(this.userConverter::fromEntity).collect(Collectors.toList());
//		
//	}
//
//	@Override
//	public void deleteAllUsers(String adminDomain, String adminEmail) {
//		checkAdmin(adminDomain, adminEmail);
//		this.allUsers.clear();		
//	}
//
//	// check if user exist in the system
//	public UserEntity findUser(String userDomain, String userEmail) {
//		return this.allUsers.stream()
//				.filter(userEntity -> userEntity.getUserId().getEmail().equals(userEmail)
//						&& userEntity.getUserId().getDomain().equals(userDomain))
//				.findFirst().orElseThrow(() -> new RuntimeException("Could not find user"));
//		
//	}
//
//	// check if user is admin and exists 
//	public void checkAdmin(String adminDomain, String adminEmail) {
//		this.allUsers.stream()
//				.filter(userEntity -> userEntity.getUserId().getEmail().equals(adminEmail)
//						&& userEntity.getUserId().getDomain().equals(adminDomain)
//						&& userEntity.getRole() == UserRole.ADMIN)
//				.findFirst().orElseThrow(() -> new RuntimeException("User is not admin"));
//	}
}
