package model;

import java.util.HashMap;
import java.util.Map;

/** Алфавит-константа */
public class Alphabet {
    public static final char[] ALPHABET =
            {'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К','Л',
                    'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц','Ч',
                    'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я', 'а', 'б', 'в', 'г',
                    'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к','л', 'м', 'н', 'о',
                    'п', 'р','с', 'т', 'у', 'ф', 'ч', 'ц','ш', 'щ', 'ъ', 'ы',
                    'ь', 'э', 'ю', 'я','.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};


    public static Map<Character,Integer> alphabetAsMap = new HashMap<>();// <---в дальнейшем используется хэшмап этого алфавита ввиду быстроты доступа по ключу (символ-индекс)
    static  {
        for (int i = 0; i < ALPHABET.length; i++) {
            alphabetAsMap.put(Alphabet.ALPHABET[i], i );
        }
    }
}
