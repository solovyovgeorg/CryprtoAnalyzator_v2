package other;

import model.Chipher;
import view.ViewData;

import java.io.*;
import java.util.List;
import java.util.Map;


public class FilesHandler {


    public void process(ViewData data, Chipher chipher) throws IOException {
        try (Reader fr = new FileReader(data.getSrc());
             Writer fw = new FileWriter(data.getOut())) {
            char[] buffer = new char[2048];
            int len = fr.read(buffer);
            while (len != -1) {
                fw.write(chipher.crypt(buffer, len));
                len = fr.read(buffer);
            }
        }
    }

    public List<Map.Entry<Character, Integer>> getStatistic(String path) throws IOException {
        Statistic statistic = new Statistic();
        try (Reader fr = new FileReader(path)) {
            char[] buffer = new char[2048];

            int len = fr.read(buffer);
            while (len != -1) {
                statistic.count(buffer);
                len = fr.read(buffer);
            }
        }
        return statistic.getResult();
    }
}