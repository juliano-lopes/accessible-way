package repository;

import org.springframework.data.repository.CrudRepository;

import domain.Telephone;

public interface TelephoneRepository extends CrudRepository<Telephone, Long> {

}
