package model;

import exceptions.PathIsInvalidException;
import operations.Actions;
import operations.Operation;
import other.FilesHandler;
import other.Validator;
import view.ViewData;

import java.io.IOException;
/*
 Класс для файловой обработки с методом шифрования/дешифрования
 */

public class Crypter implements Actions {
    private final FilesHandler handler;
    private final Chipher chipher;

    public Crypter(FilesHandler handler, Chipher chipher) {
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

        chipher.setKey(data.getKey());
        handler.process(data, chipher);
    }
}