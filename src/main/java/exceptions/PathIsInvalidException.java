package exceptions;

public class PathIsInvalidException extends RuntimeException {
    public PathIsInvalidException(String message) {
        super(message);
    }
}
