package es.rubengarciarios.web.persistence.exceptions;

public class EntityAllreadyExistsException extends PersistenceException {
    public EntityAllreadyExistsException( String message ) { super( message ); }
}
