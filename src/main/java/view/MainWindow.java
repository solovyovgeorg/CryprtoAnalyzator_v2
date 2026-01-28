package view;

import controller.Controller;
import operations.Operation;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private final Controller controller;

    public MainWindow(Controller controller) {
        // Первичные установки главного окна, расположение, размеры, заголовок
        super("Криптоанализатор");
        this.controller = controller;
        this.setBounds(500, 500, 600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();

        // Выбор лэйаут менеджера для размещения компонентов
        container.setLayout(new BorderLayout());

        //Создание кнопок
        JButton buttonCrypt = new JButton("Шифрование");
        JButton buttonDecrypt = new JButton("Дешифрование");
        JButton buttonBrutforce = new JButton("Брутфорс");
        JButton buttonAnalyze = new JButton("Расшифровка на основе примера (статистический анализ)");
        JButton buttonClose = new JButton("Выход");

        // Установка листенеров на кнопки.
        buttonCrypt.addActionListener(e -> new WindowService(this, controller, Operation.CRYPT).setVisible(true));
        buttonDecrypt.addActionListener(e -> new WindowService(this, controller, Operation.DECRYPT).setVisible(true));
        buttonBrutforce.addActionListener(e -> new WindowService(this, controller, Operation.BROOTFORCE).setVisible(true));
        buttonAnalyze.addActionListener(e -> new WindowService(this, controller, Operation.ANALYZE).setVisible(true));
        buttonClose.addActionListener(e -> dispose());

        // Описание приложения текстом
        JTextArea description = new JTextArea(
                "\n\n  Это приложение предназначено для шифрования, расшифрования и анализа \n текстовых файлов, зашифрованных методом символьного сдвига (шифр Цезаря) \n на основе заданного алфавита.\n" +
                        "\n" +
                        "  Доступные возможности:\n" +
                        "\n" +
                        "\t - Шифрование файлов с указанием ключа смещения\n" +
                        "\n" +
                        "\t - Расшифрование файлов при известном ключе\n" +
                        "\n" +
                        "\t - Брутфорс-атака — автоматический подбор ключа на основе текстового образца\n" +
                        "\n" +
                        "\t - Статистический анализ — восстановление ключа по частотам символов \n\t и примеру исходного текста\n" +
                        "\n" +
                        "  Программа ориентирована на учебное изучение криптографии,\n принципов криптоанализа и работы с файловыми данными.\n" +
                        "\n  Все операции выполняются над .txt файлами.\n\n");

        // Чтобы этот текст пользователю нельзя было изменять к JtextArea применяется .setEditable(false)
        description.setEditable(false);
        description.setBackground(this.getBackground());

        // Разделяю кнопки на два блока
        JPanel buttonsBlock1 = new JPanel();
        JPanel buttonsBlock2 = new JPanel();

        // В первый блок добавляю Crypter, Decrypter, Brutforce кнопки
        buttonsBlock1.add(buttonCrypt);
        buttonsBlock1.add(buttonDecrypt);
        buttonsBlock1.add(buttonBrutforce);

        // Во второй блок добавляю Analyzer и кнопку выхода
        buttonsBlock2.add(buttonAnalyze);
        buttonsBlock2.add(buttonClose);

        // Чтобы отобразить описание сверху, а все кнопки снизу создаю еще одну панель для всех кнопок
        JPanel allButtons = new JPanel(new GridLayout(3, 2, 2, 2));
        allButtons.add(buttonsBlock1);
        allButtons.add(buttonsBlock2);

        // Добавляю текст описания сверху, кнопки по центру
        container.add(description, BorderLayout.NORTH);
        container.add(allButtons, BorderLayout.SOUTH);


    }
}
