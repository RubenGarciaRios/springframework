package es.rubengarciarios.web.persistence.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.NOT_FOUND )
public class EntityNotFound extends PersistenceException {
   public EntityNotFound( String message ) { super( message ); }
}