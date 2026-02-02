package model;

import operations.Executable;
import other.FilesHandler;
import view.ViewData;

/** Назначение класса - сервис статистического анализа, сравнивает наиболее часто встречающиеся символы
 * в зашифрованном файле и файле с предпологаемой похожей стилистикой. На основе сравнения вычисляет ключ смещения.
 */

public class AnalyzeService implements Executable {
    private final FilesHandler handler;
    private final Encoder encoder;
    private final static int SIZE = Alphabet.alphabetAsMap.size();

    public AnalyzeService(FilesHandler handler, Encoder encoder) {
        this.handler = handler;
        this.encoder = encoder;
    }

    @Override
    public void execute(ViewData data) {
        char mostOfSample = handler.getStatistic(data.getSample()).getFirst().getKey();
        int indexMostOfSample = Alphabet.alphabetAsMap.get(mostOfSample);
        char mostOfCrypted = handler.getStatistic(data.getSrc()).getFirst().getKey();
        int indexMostOfCrypted = Alphabet.alphabetAsMap.get(mostOfCrypted);
        int key = ((indexMostOfSample - indexMostOfCrypted + SIZE) % SIZE);
        encoder.setKey(key);
        handler.process(data,encoder);
    }
}
