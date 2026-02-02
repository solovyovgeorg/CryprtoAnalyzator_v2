package exceptions;

/** Обертка IOException для FileHandler, BrootforceService */

public class CustomIOException extends RuntimeException {
    public CustomIOException(String message) {
        super(message);
    }
}
