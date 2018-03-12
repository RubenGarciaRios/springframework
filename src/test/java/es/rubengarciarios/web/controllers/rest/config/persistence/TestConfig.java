package es.rubengarciarios.web.controllers.rest.config.persistence;

import es.rubengarciarios.web.config.PackageList;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan( basePackages = { PackageList.PERSISTENCE_REPOSITORIES_DEV, PackageList.PERSISTENCE_REPOSITORIES_DEV + ".custom" } )
public class TestConfig { }
