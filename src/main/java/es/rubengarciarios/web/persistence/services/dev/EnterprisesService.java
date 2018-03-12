package es.rubengarciarios.web.persistence.services.dev;

import es.rubengarciarios.web.persistence.entities.dev.Enterprises;
import es.rubengarciarios.web.exceptions.NullArgumentException;
import es.rubengarciarios.web.persistence.exceptions.EnterpriseNotFound;

import java.util.Collection;

public interface EnterprisesService {
    Collection< Enterprises > findAll( );
    boolean existsByName( String name );
    Enterprises findOne( Integer id ) throws EnterpriseNotFound;
    Enterprises findOneByName( String name ) throws EnterpriseNotFound;
    Enterprises save( Enterprises enterprise ) throws NullArgumentException;
    void delete( Integer id ) throws NullArgumentException;
    void delete( Enterprises enterprise ) throws NullArgumentException;
    void delete( Iterable<Enterprises> enterprises );
    void deleteAll( );
}
