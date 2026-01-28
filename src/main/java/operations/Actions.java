package operations;
import view.ViewData;

import java.io.IOException;

/*
* Интерфейс для классов реализующих шифрование, брутфорс, анализ и т.д.
*/

public interface Actions {
     void execute(ViewData data) throws IOException;
}
