package es.rubengarciarios.web.config.persistence;

import es.rubengarciarios.web.config.PackageList;
import org.apache.logging.log4j.LogManager;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan( PackageList.COMPONENTS )
@PropertySource( PackageList.PROPERTIES_APPLICATION )
@EnableJpaRepositories( basePackages = PackageList.PERSISTENCE_REPOSITORIES_DEV, repositoryImplementationPostfix = "Impl" )
public class Config {
    @Resource
    private Environment environment;

    @Bean
    public DataSource dataSource( ) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource( );
        dataSource.setDriverClassName( environment.getProperty( "dataSource.driver" ) );
        dataSource.setUrl( environment.getProperty( "dataSource.url" ) );
        dataSource.setUsername( environment.getProperty( "dataSource.username" ) );
        dataSource.setPassword( environment.getProperty( "dataSource.password" ) );
        LogManager.getLogger( this.getClass( ) ).info( "Datasource: " + dataSource.getUrl( ) );
        return dataSource;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory( ) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean( );
        entityManagerFactoryBean.setPersistenceProviderClass( HibernatePersistenceProvider.class );
        // JPA - Hibernate.
        entityManagerFactoryBean.setJpaProperties( hibernateProperties( ) );
        // Entities Paclages.
        entityManagerFactoryBean.setPackagesToScan( PackageList.PERSISTENCE_ENTITIES_DEV );
        entityManagerFactoryBean.setDataSource( dataSource( ) );
        // Entity Manager FactoryBean Object
        entityManagerFactoryBean.afterPropertiesSet( );
        return entityManagerFactoryBean.getObject( );
    }

    private Properties hibernateProperties( ) {
        Properties properties = new Properties( );
        properties.put( "hibernate.connection.charSet", environment.getProperty( "hibernate.connection.charSet" ) );
        properties.put( "hibernate.show_sql", environment.getProperty( "hibernate.show_sql" ) );
        properties.put( "hibernate.format_sql", environment.getProperty( "hibernate.format_sql" ) );
        // create-drop, create, update, validate
        properties.put( "hibernate.hbm2ddl.auto", environment.getProperty( "hibernate.hbm2ddl.auto" ) );
        properties.put( "hibernate.dialect", environment.getProperty( "hibernate.dialect" ) );
        return properties;
    }

    @Bean
    public PlatformTransactionManager transactionManager( ) {
        JpaTransactionManager transactionManager = new JpaTransactionManager( );
        transactionManager.setEntityManagerFactory( entityManagerFactory( ) );
        return transactionManager;
    }
}
