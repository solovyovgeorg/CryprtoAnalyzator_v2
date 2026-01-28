import controller.Controller;
import model.*;
import operations.Actions;
import operations.Operation;
import other.FilesHandler;
import view.MainWindow;
import java.util.HashMap;

 /*
 * Сборка основных объектов и запуск оболочки
 */

public class App {
    public static void main(String[] args) {
        // Создание классов
        Chipher chipher = new Chipher();
        FilesHandler handler = new FilesHandler();

        Actions crypter = new Crypter(handler, chipher);
        Actions decrypter = new Crypter(handler, chipher);
        Actions brootforce = new Brootforce(handler, chipher);
        Actions analyze = new Analyze(handler, chipher);

        //Список операций по enum Operations
        HashMap<Operation, Actions> actions = new HashMap<>();

        actions.put(Operation.CRYPT, crypter);
        actions.put(Operation.DECRYPT, decrypter);
        actions.put(Operation.BROOTFORCE, brootforce);
        actions.put(Operation.ANALYZE, analyze);

        Controller controller = new Controller(actions);

        // Запуск GUI, внутри которого будет вызываться контроллер
        MainWindow mainWindow = new MainWindow(controller);
        mainWindow.setVisible(true);
    }
}
