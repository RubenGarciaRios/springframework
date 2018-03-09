package es.rubengarciarios.web.persistence.services.dev.implementations;

import es.rubengarciarios.web.persistence.entities.dev.Enterprise;
import es.rubengarciarios.web.persistence.repositories.dev.interfaces.EnterpriseRepository;
import es.rubengarciarios.web.persistence.services.dev.interfaces.EnterpriseService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class EnterpriseServiceImp implements EnterpriseService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private EnterpriseRepository repository;

    @Override
    @Transactional
    public Enterprise save( Enterprise enterprise) {
        return repository.save( enterprise );
    }
}
