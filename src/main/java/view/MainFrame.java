package view;

import controller.Controller;
import operations.Operation;

import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;


/**
 * Главное окно, здесь пользователь выбирает необходимую операцию
 */

public class MainFrame extends JFrame {
    private final Controller controller;

    public MainFrame(Controller controller) throws IOException {
        /** Первичные установки главного окна, расположение, размеры, заголовок */
        super("Криптоанализатор");
        this.controller = controller;
        this.setBounds(500, 500, 600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();

        /** Выбор лэйаут менеджера для размещения компонентов */
        container.setLayout(new BorderLayout());

        /** Создание кнопок */
        JButton buttonCrypt = new JButton("Шифрование");
        JButton buttonDecrypt = new JButton("Дешифрование");
        JButton buttonBrutforce = new JButton("Брутфорс");
        JButton buttonAnalyze = new JButton("Расшифровка на основе примера (статистический анализ)");
        JButton buttonClose = new JButton("Выход");
        /** Установка листенеров на кнопки. */
        buttonCrypt.addActionListener(e -> new ServiceDialog(this, controller, Operation.CRYPT).setVisible(true));
        buttonDecrypt.addActionListener(e -> new ServiceDialog(this, controller, Operation.DECRYPT).setVisible(true));
        buttonBrutforce.addActionListener(e -> new ServiceDialog(this, controller, Operation.BROOTFORCE).setVisible(true));
        buttonAnalyze.addActionListener(e -> new ServiceDialog(this, controller, Operation.ANALYZE).setVisible(true));
        buttonClose.addActionListener(e -> dispose());
        /** Описание приложения подгружается из файла description.html*/
        JEditorPane editorPane = new JEditorPane();
        editorPane.setContentType("text/html");
        editorPane.setEditable(false);
        URL url = getClass().getResource("/description.html");
        editorPane.setPage(url);
        /** Разделяю кнопки на два блока */
        JPanel buttonsBlock1 = new JPanel();
        JPanel buttonsBlock2 = new JPanel();
        /** В первый блок добавляю CrypterService, DecrypterService, BrutforceService кнопки */
        buttonsBlock1.add(buttonCrypt);
        buttonsBlock1.add(buttonDecrypt);
        buttonsBlock1.add(buttonBrutforce);
        /** Во второй блок добавляю Analyzer и кнопку выхода */
        buttonsBlock2.add(buttonAnalyze);
        buttonsBlock2.add(buttonClose);
        /** Чтобы отобразить описание сверху, а все кнопки снизу создаю еще одну панель для всех кнопок */
        JPanel allButtons = new JPanel(new GridLayout(3, 2, 2, 2));
        allButtons.add(buttonsBlock1);
        allButtons.add(buttonsBlock2);
        /** Добавляю текст описания сверху, кнопки по центру */
        container.add(editorPane, BorderLayout.NORTH);
        container.add(allButtons, BorderLayout.SOUTH);

    }
}
