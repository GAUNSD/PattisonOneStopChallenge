package ca.murcia.dev.pattison;

/**
 * Book Exception
 *
 * Custom Exception for this project.
 */
public class BookException extends RuntimeException {

    public BookException( String type, String message, Throwable cause ) {
        super(
                String.format( "%s :: %s", type, message ),
                cause
        );
    }
}
