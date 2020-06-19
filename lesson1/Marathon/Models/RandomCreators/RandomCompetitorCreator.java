package homework1.Marathon.Models.RandomCreators;

import homework1.Marathon.Interfaces.Competitor;
import homework1.Marathon.Models.Animal.AnimalFactory;
import homework1.Marathon.Models.Animal.AnimalType;
import homework1.Marathon.Utils.Utils;

public class RandomCompetitorCreator {
    public static final float MIN_RUN_DISTANCE = 0;
    public static final float MAX_RUN_DISTANCE = 5000;
    public static final float MIN_JUMP_HEIGHT = 0;
    public static final float MAX_JUMP_HEIGHT = 10;
    public static final float MIN_SWIM_DISTANCE = 0;
    public static final float MAX_SWIM_DISTANCE = 50;

    public Competitor getRandomCompetitor(AnimalType type, String name) {
        float maxRunDistance = Utils.getRandomFloatInclusive(MIN_RUN_DISTANCE, MAX_RUN_DISTANCE);
        float maxJumpHeight = Utils.getRandomFloatInclusive(MIN_JUMP_HEIGHT, MAX_JUMP_HEIGHT);
        float maxSwimDistance = Utils.getRandomFloatInclusive(MIN_SWIM_DISTANCE, MAX_SWIM_DISTANCE);

        return new AnimalFactory().getAnimal(type, name, maxRunDistance, maxJumpHeight, maxSwimDistance);
    }
}
