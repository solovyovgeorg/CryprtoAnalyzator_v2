package model;

import other.FilesHandler;
import view.ViewData;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class BrootforceService implements Actions {
    private final FilesHandler handler;
    private final BufferHandler encoder;
    private final String DEFAULT_SAMPLE = ", ";

    public BrootforceService(FilesHandler handler, BufferHandler encoder) {
        this.handler = handler;
        this.encoder = encoder;
    }

    @Override
    public void execute(ViewData data) throws IOException {
        // В случае переданного пустого поля образца орфографии использовать дефолтную
        if (data.getTextSample() == null || data.getTextSample().isEmpty()) {
            data.setTextSample(DEFAULT_SAMPLE);
        }
        int keyCounter = 0;
        HashMap<Integer, Integer> scores = new HashMap<>();
        String sample = data.getTextSample();
        int score = 0;
        StringBuilder builder = new StringBuilder();
        while (keyCounter < Alphabet.alphabetAsMap.size()) {
            encoder.setKey(keyCounter);
            handler.process(data, encoder);
            try (FileReader fr = new FileReader(data.getOut())) {
                char[] buffer = new char[2048];
                int len = fr.read(buffer);
                while (len != -1) {
                    builder.append(buffer);
                    int indexSample = builder.indexOf(sample);
                    while (indexSample != -1) {
                        score++;
                        scores.put(keyCounter, score);
                        builder.delete(0, indexSample + sample.length());
                        indexSample = builder.indexOf(sample);
                    }
                    builder.setLength(0);
                    len = fr.read(buffer);
                }
            }
            keyCounter++;
        }
        encoder.setKey(getBestOf(scores));
        handler.process(data, encoder);
    }

    private int getBestOf(HashMap<Integer, Integer> scores) {
        int max = 0;
        int bestKey = 0;
        for (var value : scores.entrySet()) {
            if (value.getValue() >= max) {
                max = value.getValue();
                bestKey = value.getKey();
            }
        }
        return bestKey;
    }
}
