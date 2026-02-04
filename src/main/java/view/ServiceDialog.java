package view;

import controller.Controller;
import exceptions.CustomIOException;
import exceptions.PathIsInvalidException;
import operations.Operation;
import other.Validator;
import javax.swing.*;
import java.awt.*;

/** Класс для отрисовки окон сервисов шифрования, также здесь происходит обработка исключений */

public class ServiceDialog extends JDialog {
    private final Controller controller;
    private final Operation operation;
    private ViewData data;
    private final int X_POS = 500;
    private final int Y_POS = 650;
    private final int WIDTH = 600;
    private final int HEIGHT = 300;
    private JButton buttonConfirm = new JButton("Выполнить");
    private JButton buttonClose = new JButton("Отмена");
    private JTextField inputSrc = new JTextField("C:\\test\\src.txt", 15);
    private JTextField inputOut = new JTextField("C:\\test\\out.txt", 15);
    private JTextField inputSample = new JTextField("C:\\test\\sample.txt", 15);
    private JTextField inputTextSample = new JTextField(", а", 8);
    private JTextField inputKey = new JTextField("1", 8);
    private JLabel labelSrc = new JLabel("Путь к файлу-источнику: ");
    private JLabel labelOut = new JLabel("Путь к выходному файлу: ");
    private JLabel labelSample = new JLabel("Путь к файлу - образцу: ");
    private JLabel labelTextSample = new JLabel("Образец орфографии: ");
    private JLabel labelKey = new JLabel("На сколько символов сместить шифр? ");

    public ServiceDialog(Frame owner,
                         Controller controller,
                         Operation operation) {
        super(owner, operation.getNameRus(), true);
        this.controller = controller;
        this.operation = operation;
        draw();
    }
    /** Отрисовка окон с заданными параметрами */
    public void draw() {
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
                new EventDialog(this, "Выполнено!", operation.getNameRus() + " выполнено!");
                this.dispose();
            } catch (PathIsInvalidException a) {
                new EventDialog(this, "Ошибка! ", a.getMessage());
            } catch (NumberFormatException b) {
                String errorMessage = "Ошибка! " + "Для поля смещения введите целое число!";
                new EventDialog(this, "Ошибка! ", errorMessage);
            } catch (CustomIOException c) {
                new EventDialog(this, "Ошибка! ", c.getMessage());
            } catch (RuntimeException d) {
                new EventDialog(this, "Ошибка! ", d.getMessage());
            }
        });
        container.add(allField, BorderLayout.NORTH);
        container.add(buttonPanel, BorderLayout.SOUTH);
    }

    /** Метод получения информации с полей окна ввода, присвоение знака ключа для шифрования/дешифрования */
    public void collectData () throws NumberFormatException {
        data = new ViewData();
        data.setSrc(inputSrc.getText());
        data.setOut(inputOut.getText());
        data.setTextSample(inputTextSample.getText());
        data.setSample(inputSample.getText());
        data.setOperation(operation);

        if (operation == Operation.CRYPT) {
            int key = Integer.parseInt(inputKey.getText());
            data.setKey(key);
        } else if (operation == Operation.DECRYPT) {
            int key = Integer.parseInt(inputKey.getText());
            data.setKey(-key);
        }
    }
}
