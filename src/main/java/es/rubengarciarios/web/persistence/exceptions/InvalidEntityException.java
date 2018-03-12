package es.rubengarciarios.web.persistence.exceptions;

public class InvalidEntityException extends PersistenceException {
    public InvalidEntityException( String message ) { super( message ); }
}
