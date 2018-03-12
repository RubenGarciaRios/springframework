package es.rubengarciarios.web.persistence.services.dev;

import es.rubengarciarios.web.persistence.entities.dev.Enterprises;
import es.rubengarciarios.web.exceptions.NullArgumentException;
import es.rubengarciarios.web.persistence.exceptions.EnterpriseNotFoundException;

import java.util.Collection;

public interface EnterprisesService {
    boolean exists( String name );
    Enterprises findOne( Integer id ) throws RuntimeException;
    Enterprises findOne( String name ) throws RuntimeException;
    Collection< Enterprises > findAll( );
    Enterprises save( Enterprises enterprise ) throws NullArgumentException;
    Enterprises update( Enterprises enterprise ) throws RuntimeException;
    void delete( Integer id ) throws NullArgumentException;
    void delete( String name ) throws NullArgumentException;
    void delete( Enterprises enterprise ) throws NullArgumentException;
    void delete( Iterable<Enterprises> enterprises );
    void deleteAll( );
}
