package view;

import controller.Controller;
import operations.Operation;

import javax.swing.*;
import java.awt.*;

public class WindowService extends JDialog {
    protected final Controller controller;
    protected final Operation operation;
    protected ViewData data;
    protected final int X_POS = 500;
    protected final int Y_POS = 650;
    protected final int WIDTH = 600;
    protected final int HEIGHT = 300;

    protected JButton buttonConfirm = new JButton("Выполнить");
    protected JButton buttonClose = new JButton("Отмена");

    protected JTextField inputSrc = new JTextField("C:\\test\\src.txt", 15);
    protected JTextField inputOut = new JTextField("C:\\test\\out.txt", 15);
    protected JTextField inputSample = new JTextField("C:\\test\\sample.txt", 15);
    protected JTextField inputTextSample = new JTextField(", a", 8);
    protected JTextField inputKey = new JTextField("1", 8);


    protected JLabel labelSrc = new JLabel("Путь к файлу-источнику: ");
    protected JLabel labelOut = new JLabel("Путь к выходному файлу: ");
    protected JLabel labelSample = new JLabel("Путь к файлу - образцу: ");
    protected JLabel labelTextSample = new JLabel("Образец орфографии: ");
    protected JLabel labelKey = new JLabel("На сколько символов сместить шифр? ");

    public WindowService(Frame owner,
                         Controller controller,
                         Operation operation) {

        super(owner, operation.getNameRus(), true);
        this.controller = controller;
        this.operation = operation;
        init();
    }

    // Инициализация и отрисовка окон
    public void init() {

        setBounds(500, 650, 600, 300);
        Container container = this.getContentPane();
        container.setLayout(new BorderLayout());


        JPanel panelSrc = new JPanel();
        panelSrc.add(labelSrc);
        panelSrc.add(inputSrc);


        JPanel panelOut = new JPanel();
        panelOut.add(labelOut);
        panelOut.add(inputOut);


        JPanel panelKey = new JPanel();
        panelKey.add(labelKey);
        panelKey.add(inputKey);


        JPanel panelSample = new JPanel();
        panelSample.add(labelSample);
        panelSample.add(inputSample);

        JPanel panelTextSample = new JPanel();
        panelTextSample.add(labelTextSample);
        panelTextSample.add(inputTextSample);

        JPanel allField = new JPanel(new BorderLayout());

        // Дальнейшая отрисовка элементов в зависимости от операции. Понимаю что изучаем java 8 - поэтому обойдусь без
        // использования продвинутого свитча - без мультиперечисления CRYPT и DECRYPT (Хоть IDEA и подчеркнула на пол
        // экрана что лучше совместить)
        switch (operation) {
            case CRYPT:
                allField.add(panelSrc, BorderLayout.NORTH);
                allField.add(panelOut, BorderLayout.CENTER);
                allField.add(panelKey, BorderLayout.SOUTH);
                break;

            case DECRYPT:
                allField.add(panelSrc, BorderLayout.NORTH);
                allField.add(panelOut, BorderLayout.CENTER);
                allField.add(panelKey, BorderLayout.SOUTH);
                break;

            case BROOTFORCE:
                allField.add(panelSrc, BorderLayout.NORTH);
                allField.add(panelOut, BorderLayout.CENTER);
                allField.add(panelTextSample, BorderLayout.SOUTH);
                break;

            case ANALYZE:
                allField.add(panelSrc, BorderLayout.NORTH);
                allField.add(panelOut, BorderLayout.CENTER);
                allField.add(panelSample, BorderLayout.SOUTH);
                break;

            default:
                allField.add(panelSrc, BorderLayout.NORTH);
                allField.add(panelOut, BorderLayout.CENTER);
                allField.add(panelKey, BorderLayout.SOUTH);


        }

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(buttonConfirm);
        buttonPanel.add(buttonClose);

        buttonClose.addActionListener(e -> dispose());

        buttonConfirm.addActionListener(e -> {
            try {
                collectData();
                controller.start(data);

            } catch (NumberFormatException ex) {
                String errorMessage = "Ошибка! " + "Для поля смещения введите целое число";
                new ExceptionWindow(this, "Error! ", errorMessage);
            } catch (RuntimeException ex) {
                new ExceptionWindow(this, "Error! ", ex.getMessage());
            }

            new ExceptionWindow(this, "Выполнено!", operation.getNameRus() + " выполнено!");
            this.dispose();

        });

        container.add(allField, BorderLayout.NORTH);
        container.add(buttonPanel, BorderLayout.SOUTH);
    }


    // Метод получения информации с полей окна ввода
    public void collectData () throws NumberFormatException {
        data = new ViewData();
        data.setSrc(inputSrc.getText());
        data.setOut(inputOut.getText());
        data.setTextSample(inputTextSample.getText());
        data.setSample(inputSample.getText());
        data.setOperation(operation);

        if (operation == Operation.CRYPT || operation == Operation.DECRYPT) {
            int key = Integer.parseInt(inputKey.getText());
            data.setKey(key);
        }
    }
}
