package model;

import operations.Executable;
import other.FilesHandler;
import view.ViewData;
import java.io.IOException;

/** Сервис дешифрования по заданному ключу */
public class DecrypterService implements Executable {
    private final FilesHandler handler;
    private final Encoder encoder;

    public DecrypterService(FilesHandler handler, Encoder encoder) {
        this.handler = handler;
        this.encoder = encoder;
    }

    @Override
    public void execute(ViewData data) throws IOException {
        encoder.setKey(data.getKey());
        handler.process(data, encoder);
    }
}