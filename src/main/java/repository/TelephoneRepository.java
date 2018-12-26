package repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import domain.Telephone;

public interface TelephoneRepository extends CrudRepository<Telephone, Long> {
	List<Telephone> findTelephoneByUserId(long userId);
}
