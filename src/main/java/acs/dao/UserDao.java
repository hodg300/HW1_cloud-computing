package acs.dao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;

import acs.data.UserEntity;

public interface UserDao  extends PagingAndSortingRepository<UserEntity, String> {	
    public List<UserEntity> findAllByLastNameLike(@Param("lastName") String lastName, Pageable pageable);
    
    public List<UserEntity> findAllByAgeGreaterThan(@Param("age") int age, Pageable pageable);
    
    @Query("select user from UserEntity user WHERE :role in elements(user.roles)")
    public List<UserEntity> findAllByRole(@Param("role") String role, Pageable pageable);
}