package other;

import model.Alphabet;

import java.util.*;

/* Назначение - вспомогательный класс подсчета статистики, производит подсчет из буфера (символьный массив)*/

public class Statistic {
    private Map<Character, Integer> result = new HashMap<>();

    public void count (char[] symbols){
        for (char key : symbols) {
            if (result.get(key) == null && Alphabet.alphabetAsMap.get(key) != null) {
                result.put(key, 0);
            } else if (result.get(key) != null && Alphabet.alphabetAsMap.get(key) != null) {
                Integer tempValue = result.get(key);
                result.put(key, tempValue + 1);
            }
        }
    }

    /*
    Для отображения статистики нам нужен сортированный список по количеству подсчитанных совпадений символов,
    выбрал превращение HashMap класса в List для возвращения сортированного списка
    */

    public List<Map.Entry<Character, Integer>> getResult(){
        List <Map.Entry<Character, Integer>> resultList = new ArrayList<>(result.entrySet());
        resultList.sort(new Comparator<>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        return resultList;
    }
}
