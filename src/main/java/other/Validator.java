package other;

import view.ViewData;
import java.nio.file.Files;
import java.nio.file.Path;

public class Validator {
    public static boolean inIsValid (ViewData data) {
        Path src = Path.of(data.getSrc());
        Path out = Path.of(data.getOut());
        return Files.isRegularFile(src) && src.toString().endsWith(".txt") && Files.isExecutable(src);
    }
    public static boolean OutIsValid (ViewData data) {
        Path src = Path.of(data.getSrc());
        Path out = Path.of(data.getOut());
        return src.toString().endsWith(".txt") && Files.isWritable(out);
    }
    public static boolean SampleIsValid (ViewData data) {
        Path src = Path.of(data.getSample());
        Path out = Path.of(data.getOut());
        return src.toString().endsWith(".txt") && Files.isWritable(out);
    }
    public static boolean IsValid (ViewData data) {
        return inIsValid(data) && OutIsValid(data);
    }
}
