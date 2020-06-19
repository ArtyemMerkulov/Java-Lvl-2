package homework1.Marathon.Models.RandomCreators;

import homework1.Marathon.Models.Course;
import homework1.Marathon.Models.Obstacle.Obstacle;
import homework1.Marathon.Models.Obstacle.ObstacleType;

public class RandomCourseCreator {
    private Course course;

    public RandomCourseCreator(int nObstacles) throws CloneNotSupportedException {
        RandomObstacleCreator randomObstacleCreator = new RandomObstacleCreator();
        Obstacle[] obstacles = new Obstacle[nObstacles];

        for (int i = 0; i < nObstacles; i++) {
            ObstacleType type = ObstacleType.getRandomObstacleType();

            obstacles[i] = randomObstacleCreator.getRandomObstacle(type);
        }

        course = new Course(obstacles);
    }

    public Course getCourse() throws CloneNotSupportedException {
        return (Course) course.clone();
    }
}
