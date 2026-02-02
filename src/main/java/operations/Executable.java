package operations;

import exceptions.CustomIOException;
import view.ViewData;
import java.io.IOException;

/** Интерфейс для классов реализующих шифрование, брутфорс, анализ и т.д. */

public interface Executable {
     void execute(ViewData data);
}
