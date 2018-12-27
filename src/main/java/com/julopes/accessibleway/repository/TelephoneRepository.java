package com.julopes.accessibleway.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.julopes.accessibleway.domain.Telephone;

public interface TelephoneRepository extends CrudRepository<Telephone, Long> {
	List<Telephone> findTelephoneByUserId(long userId);
}
