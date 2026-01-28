package model;

import exceptions.PathIsInvalidException;
import operations.Actions;
import other.FilesHandler;
import other.Validator;
import view.ViewData;
import java.io.FileReader;
import java.io.IOException;


public class Brootforce implements Actions {
    private final FilesHandler handler;
    private final Chipher chipher;
    private final String DEFAULT_SAMPLE = ", а";

    public Brootforce(FilesHandler handler, Chipher chipher) {
        this.handler = handler;
        this.chipher = chipher;
    }

    @Override
    public void execute(ViewData data) throws IOException {

        // Выполнение валидации путей перед выполнением файловой операции
        if (!Validator.isSrcValid(data)) {
            throw new PathIsInvalidException("Введен неверный путь к файлу - источнику!");
        } else if (!Validator.isOutValid(data)) {
            throw new PathIsInvalidException("Выходной файл не может быть создан! \nУбедитесь что формат .txt!");
        }

        int key = 0;
        boolean isFind = false;

        while (key < Alphabet.alphabetAsMap.size()) {
            chipher.setKey(key);
            handler.process(data, chipher);

            try (FileReader fr = new FileReader(data.getOut())) {
                char[] buffer = new char[2048];
                String sample = data.getSample();

                if (sample == null || sample.isEmpty()) {
                    sample = DEFAULT_SAMPLE;
                }

                int len = fr.read(buffer);
                while (len != -1) {
                    isFind = new String(buffer).contains(sample);
                    if (isFind) {
                        return;
                    }
                    len = fr.read(buffer);
                }

            }
            key++;
        }
    }
}
