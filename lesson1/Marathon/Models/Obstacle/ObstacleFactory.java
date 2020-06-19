package homework1.Marathon.Models.Obstacle;

public class ObstacleFactory {

    public Obstacle getObstacle(ObstacleType type, float distance) {
        Obstacle toReturn = null;

        switch (type) {
            case CROSS:
                toReturn = new Cross(distance);
                break;
            case WATTER:
                toReturn = new Water(distance);
                break;
            case WALL:
                toReturn = new Wall(distance);
                break;
        }

        return toReturn;
    }
}
