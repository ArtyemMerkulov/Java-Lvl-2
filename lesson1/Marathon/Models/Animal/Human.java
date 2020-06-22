package homework1.Marathon.Models.Animal;

public class Human extends Animal {

    public Human(String name, float maxRunDistance, float maxJumpHeight, float maxSwimDistance) {
        super(AnimalType.HUMAN, name, maxRunDistance, maxJumpHeight, maxSwimDistance);
    }
}