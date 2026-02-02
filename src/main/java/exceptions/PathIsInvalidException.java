package exceptions;

/** Исключение выбрасывает Validator после проверки введеных пользовательских данных,
 * обрабатывается в ServiceDialog */

public class PathIsInvalidException extends RuntimeException {
    public PathIsInvalidException(String message) {
        super(message);
    }
}
