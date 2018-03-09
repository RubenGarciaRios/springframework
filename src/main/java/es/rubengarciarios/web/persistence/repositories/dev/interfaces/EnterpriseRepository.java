package es.rubengarciarios.web.persistence.repositories.dev.interfaces;

import es.rubengarciarios.web.persistence.entities.dev.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnterpriseRepository extends JpaRepository< Enterprise, Long > {

}
