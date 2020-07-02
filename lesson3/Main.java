package homework3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Main {
    private static final int N_STRINGS = 20;
    private static final int MIN_V = 0;
    private static final int MAX_V = 9;

    public static void main(String[] args) {
        /*
        1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
        Найти и вывести список уникальных слов, из которых состоит массив (дубликаты
        не считаем). Посчитать сколько раз встречается каждое слово.
         */
        String[] numStrings = getRandomNumStrings(N_STRINGS, MIN_V, MAX_V);
        HashMap<String, Integer> countedStrings = countStrings(numStrings);

        countedStrings.forEach((k, v) -> System.out.println("Key: " + k + ", Value: " + v));
        System.out.println();
        /*
        2. Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и
        телефонных номеров. В этот телефонный справочник с помощью метода add() можно добавлять
        записи. С помощью метода get() искать номер телефона по фамилии. Следует учесть, что под
        одной фамилией может быть несколько телефонов, тогда при запросе такой фамилии должны
        выводиться все телефоны.
         */
        Phonebook phonebook = new Phonebook();
        String[] surnames = new String[] {"Ivanov", "Petrov", "Sidorov", "Erohin", "Sichev", "Abramov"};

        phonebook.add(surnames[0], "+7-987-654-3210");
        phonebook.add(surnames[0], "+7-987-654-3211");
        phonebook.add(surnames[0], "+7-987-654-3212");
        phonebook.add(surnames[1], "+7-987-654-3213");
        phonebook.add(surnames[1], "+7-987-654-3214");
        phonebook.add(surnames[2], "+7-987-654-3215");
        phonebook.add(surnames[2], "+7-987-654-3216");
        phonebook.add(surnames[3], "+7-987-654-3217");
        phonebook.add(surnames[3], "+7-987-654-3218");
        phonebook.add(surnames[3], "+7-987-654-3219");
        phonebook.add(surnames[3], "+7-987-654-3220");
        phonebook.add(surnames[4], "+7-987-654-3221");
        phonebook.add(surnames[5], new String[] {"+7-987-654-3222", "+7-987-654-3223", "+7-987-654-3224"});

        for (String surname : surnames)
            System.out.println("Surname: " + surname + ",\tPhones: " +
                    Arrays.toString(phonebook.get(surname).toArray()).replaceAll("^\\[|]$", ""));

        System.out.println("\nSorted phonebook output:");
        phonebook.info();
    }

    private static HashMap<String, Integer> countStrings(String[] numStrings) {
        HashMap<String, Integer> countedStrings = new HashMap<>();

        for (String numString : numStrings) countedStrings.merge(numString, 1, Integer::sum);

        return countedStrings;
    }

    private static String[] getRandomNumStrings(int nStrings, int min, int max) {
        String[] numStrings = new String[nStrings];

        for (int i = 0; i < nStrings; i++) numStrings[i] = String.valueOf(getRandomIntInclusive(min, max));

        return numStrings;
    }

    private static int getRandomIntInclusive(int min, int max) { return min + new Random().nextInt(max + 1); }
}
