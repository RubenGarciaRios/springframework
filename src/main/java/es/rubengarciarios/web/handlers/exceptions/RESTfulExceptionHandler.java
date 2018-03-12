package es.rubengarciarios.web.handlers.exceptions;

import es.rubengarciarios.web.exceptions.NullArgumentException;
import es.rubengarciarios.web.persistence.exceptions.EntityAllreadyExistsException;
import es.rubengarciarios.web.persistence.exceptions.EntityNotFoundException;
import es.rubengarciarios.web.persistence.exceptions.InvalidEntityException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RESTfulExceptionHandler {
     private Logger logger = LogManager.getLogger( this.getClass( ) );

     @ExceptionHandler( value = { EntityNotFoundException.class } )
     @ResponseStatus( HttpStatus.NOT_FOUND )
     public void entityNotFoundException( EntityNotFoundException e ) {
          logger.error( e.getStackTrace( ) );
     }

     @ExceptionHandler( value = { EntityAllreadyExistsException.class } )
     @ResponseStatus( HttpStatus.CONFLICT )
     public void entityAllreadyExistsException( EntityAllreadyExistsException e ) {
          logger.error( e.getStackTrace( ) );
     }

     @ExceptionHandler( value = { InvalidEntityException.class, NullArgumentException.class } )
     @ResponseStatus( HttpStatus.UNPROCESSABLE_ENTITY )
     public void invalidEntityException( InvalidEntityException e ) {
          logger.error( e.getStackTrace( ) );
     }
}
