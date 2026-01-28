package view;

import controller.Controller;
import exceptions.PathIsInvalidException;
import operations.Operation;
import other.Validator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class CryptWindow extends JDialog {
    private final Controller controller;

    private JButton buttonConfirm = new JButton("Шифровать");
    private JButton buttonClose = new JButton("Отмена");

    private JTextField inputSrc = new JTextField("C:\\test\\src.txt", 15);
    private JTextField inputOut = new JTextField("C:\\test\\out.txt", 15);
    private JTextField inputKey = new JTextField("1", 8);


    private JLabel labelSrc = new JLabel("Путь к файлу-источнику: ");
    private JLabel labelOut = new JLabel("Путь к выходному файлу: ");
    private JLabel labelKey = new JLabel("На сколько символов сместить шифр? ");


    public CryptWindow(MainWindow owner, Controller controller) {
        super(owner, "Шифрование", true);
        this.controller = controller;
        setBounds(500, 650, 600, 300);
        Container container = this.getContentPane();
        container.setLayout(new BorderLayout());


        JPanel panelField1 = new JPanel();
        panelField1.add(labelSrc);
        panelField1.add(inputSrc);


        JPanel panelField2 = new JPanel();
        panelField2.add(labelOut);
        panelField2.add(inputOut);


        JPanel panelField3 = new JPanel();
        panelField3.add(labelKey);
        panelField3.add(inputKey);

        JPanel allField = new JPanel(new BorderLayout());
        allField.add(panelField1, BorderLayout.NORTH);
        allField.add(panelField2, BorderLayout.CENTER);
        allField.add(panelField3, BorderLayout.SOUTH);


        JPanel buttonPanel = new JPanel();
        buttonPanel.add(buttonConfirm);
        buttonPanel.add(buttonClose);

        buttonClose.addActionListener(new CloseListener());
        buttonConfirm.addActionListener(new CryptListener());

        container.add(allField, BorderLayout.NORTH);
        container.add(buttonPanel, BorderLayout.SOUTH);

    }

    class CloseListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }

    class CryptListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ViewData data = new ViewData();
                data.setSrc(inputSrc.getText());
                data.setOut(inputOut.getText());
                data.setOperation(Operation.CRYPT);

                int key = Integer.parseInt(inputKey.getText());
                data.setKey(key);

                if (Validator.inIsValid(data) && Validator.OutIsValid(data)) {
                    controller.start(data);
                } else {
                    throw new PathIsInvalidException("Проверьте пути к файлам!");
                }

                new ExceptionWindow(CryptWindow.this, "Done!", "Crypt is Done!");
                CryptWindow.this.dispose();


            } catch (NumberFormatException NFEerr) {
                String errorMessage = "Ошибка! " + "Для поля смещения введите целое число";
                new ExceptionWindow(CryptWindow.this, "Error! ", errorMessage);
            } catch (PathIsInvalidException PIIerr) {
                String errorMessage = "Ошибка! " + PIIerr.getMessage();
                new ExceptionWindow(CryptWindow.this, "Error! ", errorMessage);
            } catch (RuntimeException err) {
                String errorMessage = "Ошибка! " + err.getMessage();
                new ExceptionWindow(CryptWindow.this, "Error! ", errorMessage);
            }
        }
    }
}
