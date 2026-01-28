package model;

import exceptions.PathIsInvalidException;
import operations.Actions;
import other.FilesHandler;
import other.Validator;
import view.ViewData;

import java.io.IOException;

public class Analyze implements Actions {
    private final FilesHandler handler;
    private final Chipher chipher;
    private final static int SIZE = Alphabet.alphabetAsMap.size();

    public Analyze(FilesHandler handler, Chipher chipher) {
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
        } else if (!Validator.isSampleValid(data)) {
            throw new PathIsInvalidException("Введен неверный путь к файлу - образцу!");
        }

        String sample = data.getSample();
        char mostOfSample = handler.getStatistic(sample).getFirst().getKey();
        int indexMostOfSample = Alphabet.alphabetAsMap.get(mostOfSample);

        String src = data.getSrc();
        char mostOfCrypted = handler.getStatistic(src).getFirst().getKey();

        int indexMostOfCrypted = Alphabet.alphabetAsMap.get(mostOfCrypted);

        int key = ((indexMostOfSample - indexMostOfCrypted + SIZE) % SIZE);

        chipher.setKey(key);
        handler.process(data,chipher);
    }
}
