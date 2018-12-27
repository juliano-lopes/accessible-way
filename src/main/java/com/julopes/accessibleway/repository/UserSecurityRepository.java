package com.julopes.accessibleway.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.julopes.accessibleway.domain.UserSecurity;

@Transactional
public interface UserSecurityRepository extends CrudRepository<UserSecurity, Long> {
	UserSecurity findByLogin(String login);
}
