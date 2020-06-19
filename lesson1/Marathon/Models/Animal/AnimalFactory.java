package homework1.Marathon.Models.Animal;

public class AnimalFactory {

    public Animal getAnimal(AnimalType type, String name, float maxRunDistance,
                            float maxJumpHeight, float maxSwimDistance) {
        Animal toReturn = null;

        switch (type) {
            case HUMAN:
                toReturn = new Human(name, maxRunDistance, maxJumpHeight, maxSwimDistance);
                break;
            case CAT:
                toReturn = new Cat(name, maxRunDistance, maxJumpHeight, maxSwimDistance);
                break;
            case DOG:
                toReturn = new Dog(name, maxRunDistance, maxJumpHeight, maxSwimDistance);
                break;
        }

        return toReturn;
    }
}
