package model;

import exceptions.PathIsInvalidException;
import operations.Actions;
import operations.Operation;
import other.FilesHandler;
import other.Validator;
import view.ViewData;

import java.io.IOException;
/**
 Класс для файловой обработки с методом шифрования/дешифрования */

public class Crypter implements Actions {
    private final FilesHandler handler;
    private final Chipher chipher;

    public Crypter(FilesHandler handler, Chipher chipher) {
        this.handler = handler;
        this.chipher = chipher;
    }

    @Override
    public void execute(ViewData data) throws IOException {
        chipher.setKey(data.getKey());
        handler.process(data, chipher);
    }
}