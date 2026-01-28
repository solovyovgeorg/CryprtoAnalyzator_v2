package view;

import controller.Controller;
import operations.Operation;

import java.awt.*;

public class WindowChild  extends WindowService {

    public WindowChild(Frame owner, String title, Controller controller, Operation operation) {
        super(owner, title, controller, operation);
    }
}
