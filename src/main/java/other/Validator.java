package other;

import exceptions.PathIsInvalidException;
import view.ViewData;
import java.nio.file.Files;
import java.nio.file.Path;

/** Валидатор введеных пользовательских данных, последовательно проверяет ввод в зависимости от выбранной операции,
 * в случае несоответствия выбрасывает исключение PathIsInvalidException с сообщением о несоответствии*/
public class Validator {
    private static final String ERROR_SOURCE = "Введен неверный путь к файлу - источнику!";
    private static final String ERROR_TARGET = "Выходной файл не может быть создан! \nУбедитесь что формат .txt!";
    private static final String ERROR_SAMPLE = "Введен неверный путь к файлу - образцу!";

    public static boolean isSrcValid(ViewData data) {
        Path src = Path.of(data.getSrc());
        return Files.exists(src)
                && Files.isRegularFile(src)
                && src.toString().endsWith(".txt");
    }

    public static boolean isOutValid(ViewData data) {
        Path src = Path.of(data.getSrc());
        Path out = Path.of(data.getOut());
        return !out.equals(src) && out.toString().endsWith(".txt");
    }

    public static boolean isSampleValid(ViewData data) {
        Path src = Path.of(data.getSrc());
        Path out = Path.of(data.getOut());
        Path sample = Path.of(data.getSample());

        return Files.exists(sample)
                && Files.isRegularFile(sample)
                && sample.toString().endsWith(".txt")
                && !sample.equals(src)
                && !sample.equals(out);
    }

    public static boolean isValid(ViewData data) {
        switch (data.getOperation()) {
            case CRYPT:
                return isOutValid(data) && isSrcValid(data);
            case DECRYPT:
                return isSrcValid(data) && isOutValid(data);
            case BROOTFORCE:
                return isSrcValid(data) && isOutValid(data);
            case ANALYZE:
                return isSampleValid(data) && isSrcValid(data) && isOutValid(data);
            default:
                return false;
        }
    }

    public static void checkUserInput(ViewData data) {
        if (isValid(data)) {
            return;
        } else if (!isSrcValid(data)) {
            throw new PathIsInvalidException(ERROR_SOURCE);
        } else if (!isOutValid(data)) {
            throw new PathIsInvalidException(ERROR_TARGET);
        } else if (!isSampleValid(data)) {
            throw new PathIsInvalidException(ERROR_SAMPLE);
        }
    }
}
