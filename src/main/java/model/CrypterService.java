package model;

import operations.Executable;
import other.FilesHandler;
import view.ViewData;

/** Сервис шифрования по заданному ключу */
public class CrypterService implements Executable {
    private final FilesHandler handler;
    private final Encoder encoder;

    public CrypterService(FilesHandler handler, Encoder encoder) {
        this.handler = handler;
        this.encoder = encoder;
    }

    @Override
    public void execute(ViewData data){
        encoder.setKey(data.getKey());
        handler.process(data, encoder);
    }
}