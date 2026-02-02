package model;

import java.util.Map;

/*
 * Логика шифрования, принимает на вход символьный буфер, шифрует ключем и отдает зашифрованный буфер
 */

public class Chipher implements BufferHandler{
    private int key;
    private static final int SIZE = Alphabet.alphabetAsMap.size();
    private final Map<Character, Integer> alphabetIndex = Map.copyOf(Alphabet.alphabetAsMap);


    public void setKey(int key) {
        this.key = key;
    }

    public char[] process(char[] in, int len) {
        char[] out = new char[len];
        for (int i = 0; i < len; i++) {
            if (alphabetIndex.containsKey(in[i])) {
                int index = alphabetIndex.get(in[i]);
                int newIndex = (index + key) % SIZE;

                if (newIndex < 0) {
                    newIndex += SIZE;
                }

                out[i] = Alphabet.ALPHABET[newIndex];
            } else {
                out[i] = in[i];
            }
        }

        return out;
    }
}
