package es.rubengarciarios.web.persistence.services.dev;

import es.rubengarciarios.web.persistence.entities.dev.Enterprises;
import es.rubengarciarios.web.exceptions.NullArgumentException;
import es.rubengarciarios.web.persistence.exceptions.EnterpriseNotFoundException;
import es.rubengarciarios.web.persistence.exceptions.EntityAllreadyExistsException;
import es.rubengarciarios.web.persistence.exceptions.InvalidEntityException;
import es.rubengarciarios.web.persistence.repositories.dev.EnterprisesRepository;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Service
public class EnterprisesServiceImpl implements EnterprisesService {

    @PersistenceContext
    private EntityManager em;

    @Resource
    private Environment environment;

    @Resource
    private EnterprisesRepository repository;

    @Override
    public boolean exists( String name ) {
        return  repository.existByName( name ); }

    @Override
    public Enterprises findOne( Integer id ) throws RuntimeException {
        if ( id == null )
            throw new NullArgumentException( environment.getProperty( "global.exceptions.null.argument" ) );
        Enterprises enterprise = repository.findOne( id );
        if ( enterprise == null )
            throw new EnterpriseNotFoundException( environment.getProperty( "persistence.exceptions.entity.notfound" ) );
        return enterprise;
    }

    @Override
    public Enterprises findOne( String name ) throws RuntimeException {
        if ( name == null )
            throw new NullArgumentException( environment.getProperty( "global.exceptions.null.argument" ) );
        Enterprises enterprise = repository.findOneByName( name );
        if ( enterprise == null )
            throw new EnterpriseNotFoundException( environment.getProperty( "persistence.exceptions.entity.notfound" ) );
        return enterprise;
    }

    @Override
    public Collection< Enterprises > findAll( ) {
        return repository.findAll( ); }

    @Override
    @Transactional
    public Enterprises save( Enterprises enterprise ) throws NullArgumentException {
        if ( enterprise == null )
            throw new NullArgumentException( environment.getProperty( "global.exceptions.null.argument" ) );
        if ( enterprise.getName( ) == null )
            throw new InvalidEntityException( "persistence.exceptions.entity.invalid" );
        if ( exists( enterprise.getName( ) ) )
            throw new EntityAllreadyExistsException( "persistence.exceptions.entity.allreadyexists" );
        Enterprises newEnterprise = repository.save( enterprise );
        repository.flush( );
        return newEnterprise;
    }

    @Override
    @Transactional
    public Enterprises update( Enterprises enterprise ) throws RuntimeException {
        if ( enterprise == null )
            throw new NullArgumentException( environment.getProperty( "global.exceptions.null.argument" ) );
        if ( enterprise.getId( ) == 0 )
            throw new InvalidEntityException( "persistence.exceptions.entity.invalid" );
        Enterprises outDatedEnterprise = this.findOne( enterprise.getId( ) );
        outDatedEnterprise.setName( enterprise.getName( ) );
        Enterprises updatedEnterprise = repository.save( outDatedEnterprise );
        repository.flush( );
        return updatedEnterprise;
    }

    @Override
    @Transactional
    public void delete( Integer id ) throws NullArgumentException {
        if ( id == null )
            throw new NullArgumentException( environment.getProperty( "global.exceptions.null.argument" ) );
        repository.delete( id );
        repository.flush( );
    }

    @Override
    @Transactional
    public void delete( String name ) throws NullArgumentException {
        if ( name == null )
            throw new NullArgumentException( environment.getProperty( "global.exceptions.null.argument" ) );
        try {
            repository.delete( this.findOne( name ).getId( ) );
            repository.flush( );
        } catch ( Exception e ) { }
    }

    @Override
    @Transactional
    public void delete( Enterprises enterprise ) throws NullArgumentException {
        if ( enterprise == null )
            throw new NullArgumentException( environment.getProperty( "global.exceptions.null.argument" ) );
        repository.delete( enterprise );
        repository.flush( );
    }

    @Override
    @Transactional
    public void delete( Iterable< Enterprises > enterprises ) {
        if ( enterprises == null )
            throw new NullArgumentException( environment.getProperty( "global.exceptions.null.argument" ) );
        repository.delete( enterprises );
        repository.flush( );
    }

    @Override
    @Transactional
    public void deleteAll( ) {
        repository.deleteAllInBatch( );
        repository.flush( );
    }
}
