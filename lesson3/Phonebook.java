package homework3;

import java.util.*;
import java.util.regex.Pattern;

public class Phonebook {
    private final HashMap<String, ArrayList<String>> phonebook;

    public Phonebook() {
        this.phonebook = new HashMap<>();
    }

    public Phonebook(HashMap<String, ArrayList<String>> phonebook) {
        this.phonebook = new HashMap<>(phonebook);
    }

    public void add(String surname, String phoneNumber) {
        if(!isPhoneNumber(phoneNumber)) throw new IllegalArgumentException("Invalid phone number format");

        ArrayList<String> phones = phonebook.get(surname);
        if (phones == null) phones = new ArrayList<>(1);

        phones.add(phoneNumber);

        phonebook.put(surname, phones);
    }

    public void add(String surname, String[] phoneNumbers) {
        for (int i = 0; i < phoneNumbers.length; i++)
            if(!isPhoneNumber(phoneNumbers[i]))
                throw new IllegalArgumentException("Array contains a phone number in the invalid format at position " + i);

        ArrayList<String> phones = phonebook.get(surname);
        if (phones == null) phones = new ArrayList<>(phoneNumbers.length);

        phones.addAll(Arrays.asList(phoneNumbers));

        phonebook.put(surname, phones);
    }

    public ArrayList<String> get(String surname) {
        return phonebook.get(surname);
    }

    public void info() {
        List<String> surnameSorted = new ArrayList<>(phonebook.keySet());
        surnameSorted.sort(String::compareTo);
        surnameSorted.forEach((surname) -> System.out.println("Surname: " + surname + ",\tPhones: " +
                Arrays.toString(phonebook.get(surname).toArray()).replaceAll("^\\[|]$", "")));
    }

    private boolean isPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.length() != 15) return false;

        return Pattern.compile("^\\+7-\\d{3}-\\d{3}-\\d{4}$").matcher(phoneNumber).find();
    }
}
