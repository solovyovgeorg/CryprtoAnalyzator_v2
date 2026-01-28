package operations;

/*
* Перечисление возможных операций производимых над файлами
*/

public enum Operation {
    CRYPT("Шифрование"),
    DECRYPT("Дешифрование"),
    BROOTFORCE("Брутфорс"),
    ANALYZE("Анализ");

    private final String nameRus;



    Operation(String nameRus) {
        this.nameRus = nameRus;
    }
    public String getNameRus(){
        return this.nameRus;
    }
}

