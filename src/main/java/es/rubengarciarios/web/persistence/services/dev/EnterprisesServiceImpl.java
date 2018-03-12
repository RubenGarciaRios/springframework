package es.rubengarciarios.web.persistence.services.dev;

import es.rubengarciarios.web.persistence.entities.dev.Enterprises;
import es.rubengarciarios.web.exceptions.NullArgumentException;
import es.rubengarciarios.web.persistence.exceptions.EnterpriseNotFound;
import es.rubengarciarios.web.persistence.exceptions.EntityAllreadyExists;
import es.rubengarciarios.web.persistence.repositories.dev.EnterprisesRepository;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Example;
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
    public Collection< Enterprises > findAll( ) {
        return repository.findAll( ); }

    @Override
    public boolean existsByName( String name ) {
        return  repository.existByName( name ); }

    @Override
    public Enterprises findOne( Integer id ) throws EnterpriseNotFound {
        Enterprises enterprise = repository.findOne( id );
        if ( enterprise == null )
            throw new EnterpriseNotFound( environment.getProperty( "persistence.exceptions.entity.notfound" ) );
        return enterprise;
    }

    @Override
    public Enterprises findOneByName( String name ) throws EnterpriseNotFound {
        Enterprises enterprise = repository.findOneByName( name );
        if ( enterprise == null )
            throw new EnterpriseNotFound( environment.getProperty( "persistence.exceptions.entity.notfound" ) );
        return enterprise;
    }

    @Override
    @Transactional
    public Enterprises save( Enterprises enterprise ) throws NullArgumentException {
        if ( enterprise == null )
            throw new NullArgumentException( environment.getProperty( "global.exceptions.null.argument" ) );
        if ( existsByName( enterprise.getName( ) ) )
            throw new EntityAllreadyExists( "persistence.exceptions.entity.allreadyexists" );
        return repository.save( enterprise );
    }

    @Override
    @Transactional
    public void delete( Integer id ) throws NullArgumentException {
        if ( id == null )
            throw new NullArgumentException( environment.getProperty( "global.exceptions.null.argument" ) );
        repository.delete( id );
    }

    @Override
    @Transactional
    public void delete( Enterprises enterprise ) throws NullArgumentException {
        if ( enterprise == null )
            throw new NullArgumentException( environment.getProperty( "global.exceptions.null.argument" ) );
        repository.delete( enterprise );
    }

    @Override
    @Transactional
    public void delete( Iterable< Enterprises > enterprises ) {
        if ( enterprises == null )
            throw new NullArgumentException( environment.getProperty( "global.exceptions.null.argument" ) );
        repository.delete( enterprises );
    }

    @Override
    @Transactional
    public void deleteAll( ) { repository.deleteAll( ); }
}
