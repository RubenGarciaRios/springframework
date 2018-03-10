package es.rubengarciarios.web.persistence.services.dev;

import es.rubengarciarios.web.persistence.entities.dev.Enterprise;
import es.rubengarciarios.web.persistence.repositories.dev.EnterpriseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class EnterpriseServiceImp implements EnterpriseService {

    @PersistenceContext
    private EntityManager em;

    @Resource
    private EnterpriseRepository repository;

    @Override
    @Transactional
    public Enterprise save( Enterprise enterprise) {
        return repository.save( enterprise );
    }
}
