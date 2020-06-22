package homework1.Marathon.Models.Animal;

import homework1.Marathon.Utils.Utils;

public enum AnimalType {
    HUMAN,
    CAT,
    DOG;

    private static final AnimalType[] VALUES = values();

    public static AnimalType getRandomAnimalType() {
        return VALUES[Utils.getRandomIntInclusive(0, VALUES.length - 1)];
    }
}
