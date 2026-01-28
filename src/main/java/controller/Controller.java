package controller;

import operations.Actions;
import operations.Operation;
import view.ViewData;

import java.io.IOException;
import java.util.HashMap;

/*
 * Контроллер запускает необходимую бизнес логику в зависимости от данных переданных в него
 * по DataTransferObject - ViewData()
 */

public class Controller {
    private final HashMap<Operation, Actions> mapActions;

    public Controller(HashMap<Operation, Actions> mapActions) {
        this.mapActions = mapActions;
    }

    public void start(ViewData data) {
        Actions action = mapActions.get(data.getOperation());
        try {
            action.execute(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
