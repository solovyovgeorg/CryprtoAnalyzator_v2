package other;

import exceptions.CustomIOException;
import model.Encoder;
import view.ViewData;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/** Обработчик файлов с шифрованием и сбором статистики для AnalyzeService*/

public class FilesHandler {
    public void process(ViewData data, Encoder encoder) throws CustomIOException {
        try (FileReader fr = new FileReader(data.getSrc());
             FileWriter fw = new FileWriter(data.getOut())) {
            char[] buffer = new char[2048];
            int len = fr.read(buffer);
            while (len != -1) {
                fw.write(encoder.process(buffer, len));
                len = fr.read(buffer);
            }
        } catch (IOException e) {
            throw new CustomIOException(e.getMessage());
        }
    }

    public List<Map.Entry<Character, Integer>> getStatistic(String path) throws CustomIOException {
        Statistic statistic = new Statistic();
        try (FileReader fr = new FileReader(path)) {
            char[] buffer = new char[2048];
            int len = fr.read(buffer);
            while (len != -1) {
                statistic.count(buffer);
                len = fr.read(buffer);
            }
        } catch (IOException e) {
            throw new CustomIOException(e.getMessage());
        }
        return statistic.getResult();
    }
}