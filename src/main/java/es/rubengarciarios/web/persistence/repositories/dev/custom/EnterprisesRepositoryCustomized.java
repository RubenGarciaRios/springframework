package es.rubengarciarios.web.persistence.repositories.dev.custom;

import es.rubengarciarios.web.persistence.entities.dev.Enterprises;
import org.springframework.data.jpa.repository.Query;

public interface EnterprisesRepositoryCustomized {
    @Query( "SELECT e FROM Enterprises e WHERE e.name = ?1" )
    Enterprises findOneByName( String name );

    @Query( "SELECT  COUNT(e)>0 FROM Enterprises e WHERE e.name = ?1" )
    boolean existByName( String name );
}
