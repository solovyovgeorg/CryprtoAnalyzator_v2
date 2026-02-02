package model;


import operations.Actions;
import other.FilesHandler;
import view.ViewData;
import java.io.IOException;

/** Назначение класса - сервис статистического анализа, сравнивает наиболее часто встречающиеся символы
 * в зашифрованном файле и файле с предпологаемой похожей стилистикой. На основе сравнения вычисляет ключ смещения.
 *
 */
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
