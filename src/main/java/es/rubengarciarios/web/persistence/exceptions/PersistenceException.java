package es.rubengarciarios.web.persistence.exceptions;

public class PersistenceException extends RuntimeException {
    public PersistenceException( ){ }
    public PersistenceException( String message ){ super( message ); }
}
