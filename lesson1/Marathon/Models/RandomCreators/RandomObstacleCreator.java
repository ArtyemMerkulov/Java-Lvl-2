package homework1.Marathon.Models.RandomCreators;

import homework1.Marathon.Models.Obstacle.Obstacle;
import homework1.Marathon.Models.Obstacle.ObstacleFactory;
import homework1.Marathon.Models.Obstacle.ObstacleType;
import homework1.Marathon.Utils.Utils;

public class RandomObstacleCreator {
    public static final float MIN_CROSS_DISTANCE = 0;
    public static final float MAX_CROSS_DISTANCE = 5000;
    public static final float MIN_WALL_HEIGHT = 0;
    public static final float MAX_WALL_HEIGHT = 10;
    public static final float MIN_SWIM_DISTANCE = 0;
    public static final float MAX_SWIM_DISTANCE = 50;

    public Obstacle getRandomObstacle(ObstacleType type) {
        float distance = 0;

        switch (type) {
            case CROSS:
                distance = Utils.getRandomFloatInclusive(MIN_CROSS_DISTANCE, MAX_CROSS_DISTANCE);
                break;
            case WALL:
                distance = Utils.getRandomFloatInclusive(MIN_WALL_HEIGHT, MAX_WALL_HEIGHT);
                break;
            case WATTER:
                distance = Utils.getRandomFloatInclusive(MIN_SWIM_DISTANCE, MAX_SWIM_DISTANCE);
                break;
        }

        return new ObstacleFactory().getObstacle(type, distance);
    }
}
