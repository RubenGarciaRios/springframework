package es.rubengarciarios.web.persistence.repositories.dev;

import es.rubengarciarios.web.persistence.entities.dev.Enterprises;
import es.rubengarciarios.web.persistence.repositories.dev.custom.EnterprisesRepositoryCustomized;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnterprisesRepository extends JpaRepository< Enterprises, Integer >, EnterprisesRepositoryCustomized { }
