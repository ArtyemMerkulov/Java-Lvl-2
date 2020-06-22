package homework1.Marathon.Models.Obstacle;

import homework1.Marathon.Utils.Utils;

public enum ObstacleType {
    WALL,
    WATTER,
    CROSS;

    private static final ObstacleType[] VALUES = values();

    public static ObstacleType getRandomObstacleType() {
        return VALUES[Utils.getRandomIntInclusive(0, VALUES.length - 1)];
    }
}
