package es.rubengarciarios.web.persistence;

import es.rubengarciarios.web.persistence.entities.dev.Enterprise;
import es.rubengarciarios.web.persistence.services.dev.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnterpriseServiceIntegrationTest {
    @Autowired
    EnterpriseService enterpriseService;

    public void save( ) {
        Enterprise enterprise = new Enterprise( );
        enterprise.setName( "SOAINT" );
        enterpriseService.save( enterprise );
    }
}
