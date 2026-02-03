package view;

import javax.swing.*;
import java.awt.*;

/** Окно событий, вызывается из ServiceDialog, отображает информацию об успешном/неуспешном выполнении операции*/

public class EventDialog extends JDialog {

    public EventDialog (JDialog owner, String title,String message) {
        super(owner, title, true);
        setBounds(550, 710, 500, 200);
        Container container = this.getContentPane();
        JPanel panelText = new JPanel();
        JTextArea labelMessage = new JTextArea(message);
        labelMessage.setBackground(owner.getBackground());
        labelMessage.setEditable(false);
        panelText.add(labelMessage);
        JPanel panelButton = new JPanel();
        JButton buttonOk = new JButton("OK!");
        buttonOk.addActionListener(e -> dispose());
        panelButton.add(buttonOk);
        container.add(panelText, BorderLayout.CENTER);
        container.add(panelButton, BorderLayout.SOUTH);
        this.setVisible(true);
    }
}
