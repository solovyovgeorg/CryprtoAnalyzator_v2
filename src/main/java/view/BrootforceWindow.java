package view;



import controller.Controller;
import exceptions.PathIsInvalidException;
import model.Brootforce;
import operations.Operation;
import other.Validator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class BrootforceWindow extends JDialog {

    private final Controller controller;

    private JButton buttonConfirm = new JButton("Брутфорс");
    private JButton buttonClose = new JButton("Отмена");

    private JTextField inputSrc = new JTextField("C:\\test\\src.txt", 15);
    private JTextField inputOut = new JTextField("C:\\test\\out.txt", 15);
    private JTextField inputSample = new JTextField(", а", 15);



    private JLabel label1 = new JLabel("Путь к файлу-источнику: ");
    private JLabel label2 = new JLabel("Путь к выходному файлу: ");
    private JLabel label3 = new JLabel("Проверочное слово/орфография: ");



    public BrootforceWindow(MainWindow owner, Controller controller) {
        super(owner, "Брутфорс>", true);
        this.controller = controller;
        setBounds(500, 650, 600, 300);
        Container container = this.getContentPane();
        container.setLayout(new BorderLayout());


        JPanel panelField1 = new JPanel();
        panelField1.add(label1);
        panelField1.add(inputSrc);


        JPanel panelField2 = new JPanel();
        panelField2.add(label2);
        panelField2.add(inputOut);

        JPanel panelField3 = new JPanel();
        panelField3.add(label3);
        panelField3.add(inputSample);


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
                data.setOperation(Operation.BROOTFORCE);
                if (Validator.inIsValid(data) && Validator.OutIsValid(data)) {
                    controller.start(data);
                } else {
                    throw new PathIsInvalidException("Проверьте пути к файлам!");
                }
                new ExceptionWindow(BrootforceWindow.this, "Done!", "Crypt is Done!");
                BrootforceWindow.this.dispose();

            } catch (PathIsInvalidException PIIerr) {
                String errorMessage = "Ошибка! " + PIIerr.getMessage();
                new ExceptionWindow(BrootforceWindow.this, "Error! ", errorMessage);
            } catch (RuntimeException err) {
                String errorMessage = "Ошибка! " + err.getMessage();
                new ExceptionWindow(BrootforceWindow.this, "Error! ", errorMessage);
            }
        }
    }
}