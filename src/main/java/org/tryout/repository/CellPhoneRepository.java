package org.tryout.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.tryout.model.EmployeeCellPhone;

@Repository
public interface CellPhoneRepository extends CrudRepository<EmployeeCellPhone, Long> {
}