package acs.logic;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnhancedUserService extends UserService, CrudRepository<T, ID>{

}
