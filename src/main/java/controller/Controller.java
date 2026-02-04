package controller;


import operations.Executable;
import operations.Operation;
import other.Validator;
import view.ViewData;
import java.util.HashMap;

/**
 * Контроллер запускает необходимую бизнес логику в зависимости от данных переданных в него
 * по DataTransferObject - ViewData()
 */

public class Controller {
    private final HashMap<Operation, Executable> mapServices;

    public Controller(HashMap<Operation, Executable> mapServices) {
        this.mapServices = mapServices;
    }
    
    public void start(ViewData data) {
        Validator.checkUserInput(data);
        Executable action = mapServices.get(data.getOperation());
            action.execute(data);
    }

}
