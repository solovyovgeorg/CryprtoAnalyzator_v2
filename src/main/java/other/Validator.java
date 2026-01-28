package other;

import view.ViewData;
import java.nio.file.Files;
import java.nio.file.Path;

public class Validator {
    public static boolean isSrcValid (ViewData data) {
        Path src = Path.of(data.getSrc());
        return Files.exists(src)
                && Files.isRegularFile(src)
                && src.toString().endsWith(".txt");
    }
    public static boolean isOutValid (ViewData data) {
        Path src = Path.of(data.getSrc());
        Path out = Path.of(data.getOut());
        return !out.equals(src) && out.toString().endsWith(".txt");
    }
    public static boolean isSampleValid (ViewData data) {
        Path src = Path.of(data.getSrc());
        Path out = Path.of(data.getOut());
        Path sample = Path.of(data.getSample());
        return  Files.exists(sample)
                && Files.isRegularFile(sample)
                && sample.toString().endsWith(".txt")
                && !sample.equals(src)
                && !sample.equals(out);
    }
    public static boolean isValid (ViewData data) {
        return isSrcValid(data) && isOutValid(data);
    }
}
