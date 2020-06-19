package homework1.Marathon.Models.Animal;

public class Cat extends Animal {

    public Cat(String name, float maxRunDistance, float maxJumpHeight, float maxSwimDistance) {
        super(AnimalType.CAT, name, maxRunDistance, maxJumpHeight, maxSwimDistance);
    }
}