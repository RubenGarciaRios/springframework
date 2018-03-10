package es.rubengarciarios.web.persistence;

import es.rubengarciarios.web.config.persistence.Config;
import es.rubengarciarios.web.persistence.config.persistence.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = { Config.class, TestConfig.class } )
public class EnterpriseIntegrationTest {
    @Autowired
    EnterpriseServiceIntegrationTest enterpriseServiceIntegrationTest;

    @Test
    public void testSave( ) {
        enterpriseServiceIntegrationTest.save( );
    }
}
