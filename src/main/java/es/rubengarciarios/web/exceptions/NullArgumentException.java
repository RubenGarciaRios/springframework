package es.rubengarciarios.web.exceptions;

public class NullArgumentException extends RuntimeException {
    public NullArgumentException( ) { super( "Null argument exception." ); }
    public NullArgumentException( String message ) { super( message ); }
}
