package es.rubengarciarios.web.persistence.repositories.dev;

import es.rubengarciarios.web.persistence.entities.dev.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeesRepository extends JpaRepository< Employees, Integer > { }
