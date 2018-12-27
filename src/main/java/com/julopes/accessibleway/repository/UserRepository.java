package com.julopes.accessibleway.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.julopes.accessibleway.domain.User;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Long> {
	Iterable<User> findByName(String nameUser);

}
