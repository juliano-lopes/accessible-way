package com.julopes.accessibleway.repository;

import org.springframework.data.repository.CrudRepository;

import com.julopes.accessibleway.domain.UserSecurity;

public interface UserSecurityRepository extends CrudRepository<UserSecurity, Long> {
	UserSecurity findByLogin(String login);
}
