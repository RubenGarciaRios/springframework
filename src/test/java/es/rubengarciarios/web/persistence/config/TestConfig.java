package es.rubengarciarios.web.persistence.config;

import es.rubengarciarios.web.config.PackageList;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan( basePackages = PackageList.JPA_REPOSITORIES )
public class TestConfig { }
