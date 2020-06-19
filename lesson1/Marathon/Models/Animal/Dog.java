package homework1.Marathon.Models.Animal;

public class Dog extends Animal {

    public Dog(String name, float maxRunDistance, float maxJumpHeight, float maxSwimDistance) {
        super(AnimalType.DOG, name, maxRunDistance, maxJumpHeight, maxSwimDistance);
    }
}