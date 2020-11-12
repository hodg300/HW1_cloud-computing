package acs.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import acs.boundary.UserBoundary;
import acs.dao.UserDao;
import acs.data.UserEntity;
import acs.exceptions.BadRequestException;
import acs.exceptions.NotFoundException;
import acs.exceptions.UnauthorizedException;
import acs.logic.utils.UserConverter;
import acs.utils.CriteriaType;
import acs.utils.SortOrder;
import acs.utils.UserFullName;

import javax.annotation.PostConstruct;

@Service
public class UserDataAccess implements EnhancedUserService {
	private UserDao userDao; // Data access object
	private UserConverter converter;

	@Autowired
	public UserDataAccess(UserDao userDao, UserConverter converter) {
		this.userDao = userDao;
		this.converter = converter;
	}

	@Override
	@Transactional
	public UserBoundary createUser(UserBoundary userBoundary) {
		UserEntity userEntity = this.converter.toEntity(userBoundary);
		userEntity = this.userDao.save(userEntity);

		return this.converter.fromEntity(userEntity);
	}

	@Override
	@Transactional(readOnly = true)
	public UserBoundary getUser(String email) {
		UserEntity userEntity = this.userDao.findById(email)
				.orElseThrow(() -> new NotFoundException("no user found by email: " + email));

		return this.converter.fromEntity(userEntity);
	}

	@Override
	@Transactional(readOnly = true)
	public UserBoundary login(String email, String password) {
		UserEntity userEntity = this.userDao.findById(email)
				.orElseThrow(() -> new NotFoundException("no user found by email: " + email));

		if (password != null) {
			if (!userEntity.getPassword().equals(password)) {
				throw new UnauthorizedException("wrong password");
			}
		}

		return this.converter.fromEntity(userEntity);

	}

	@Override
	@Transactional
	public void updateUser(String email, UserBoundary update) {
		UserBoundary user = this.converter.fromEntity(this.userDao.findById(email)
				.orElseThrow(() -> new NotFoundException("no user found by email: " + email)));

		if (update.getName().getFirst() != null) {
			user.setName(new UserFullName(update.getName().getFirst(), user.getName().getLast()));
		}
		if (update.getName().getLast() != null) {
			user.setName(new UserFullName(user.getName().getFirst(), update.getName().getLast()));
		}
		if (update.getPassword() != null) {
			user.setPassword(update.getPassword());
		}
		if (update.getBirthdate() != null) {
			user.setBirthdate(update.getBirthdate());
		}
		if (update.getRoles() != null) {
			user.setRoles(update.getRoles());
		}

		this.userDao.save(this.converter.toEntity(user));
	}

	@Override
	@Transactional
	public void deleteAllUsers() {
		this.userDao.deleteAll();
	}

	@Override

	@Transactional(readOnly = true)
	public List<UserBoundary> getAllUsers(int size, int page, String sortBy, String sortOrder) {
		return this.userDao.findAll(PageRequest.of(page, size, Direction.valueOf(sortOrder), sortBy)).getContent()
				.stream().map(this.converter::fromEntity).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserBoundary> getAllUsersByCriteriaType(String criteriaType, String criteriaValue, int size, int page,
			String sortBy, String sortOrder) {
		if (criteriaType != null && criteriaValue != null) {
			if (criteriaType.equals(CriteriaType.BY_LAST_NAME.toString())) {
				return this.userDao
						.findAllByLastNameLike(criteriaValue,
								PageRequest.of(page, size, Direction.valueOf(sortOrder), sortBy))
						.stream().map(this.converter::fromEntity).collect(Collectors.toList());
			} else if (criteriaType.equals(CriteriaType.BY_MINIMUM_AGE.toString())) {
				return this.userDao
						.findAllByAgeGreaterThan(Integer.parseInt(criteriaValue),
								PageRequest.of(page, size, Direction.valueOf(sortOrder), sortBy))
						.stream().map(this.converter::fromEntity).collect(Collectors.toList());
			} else if (criteriaType.equals(CriteriaType.BY_ROLE.toString())) {
				return this.userDao
						.findAllByRole(criteriaValue, PageRequest.of(page, size, Direction.valueOf(sortOrder), sortBy))
						.stream().map(this.converter::fromEntity).collect(Collectors.toList());
			}
			return this.userDao
					.findAllByLastNameLike(criteriaValue,
							PageRequest.of(page, size, Direction.valueOf(sortOrder), sortBy))
					.stream().map(this.converter::fromEntity).collect(Collectors.toList());		}
		return this.userDao.findAll(PageRequest.of(page, size, Direction.valueOf(sortOrder), sortBy)).getContent()
		.stream().map(this.converter::fromEntity).collect(Collectors.toList());
		// throw new BadRequestException();
	}

}
