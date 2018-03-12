package es.rubengarciarios.web.persistence.exceptions;

public class EntityNotFoundException extends PersistenceException {
   public EntityNotFoundException( String message ) { super( message ); }
}