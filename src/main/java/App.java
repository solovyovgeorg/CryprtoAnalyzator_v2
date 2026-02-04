import controller.Controller;
import model.*;
import operations.Executable;
import operations.Operation;
import other.FilesHandler;
import view.MainFrame;
import javax.swing.*;
import java.io.IOException;
import java.util.HashMap;

/** Сборка основных объектов и запуск оболочки
 */

public class App {
    public static void main(String[] args) throws IOException {
        /** Создание классов */
        Encoder encoder = new Encoder();
        FilesHandler handler = new FilesHandler();
        Executable crypter = new CrypterService(handler, encoder);
        Executable decrypter = new CrypterService(handler, encoder);
        Executable brootforce = new BrootforceService(handler, encoder);
        Executable analyze = new AnalyzeService(handler, encoder);
        /** Список Executable сервисов по enum Operations */
        HashMap<Operation, Executable> services = new HashMap<>();
        services.put(Operation.CRYPT, crypter);
        services.put(Operation.DECRYPT, decrypter);
        services.put(Operation.BROOTFORCE, brootforce);
        services.put(Operation.ANALYZE, analyze);
        Controller controller = new Controller(services);
        /** Запуск GUI, внутри которого будет вызываться контроллер с переданными данными пользовательского ввода */
            MainFrame mainFrame = new MainFrame(controller);
            mainFrame.setVisible(true);
    }
}
