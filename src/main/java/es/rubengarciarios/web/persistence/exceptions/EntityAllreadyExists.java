package es.rubengarciarios.web.persistence.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.CONFLICT )
public class EntityAllreadyExists extends PersistenceException {
    public EntityAllreadyExists( String message ) { super( message ); }
}
