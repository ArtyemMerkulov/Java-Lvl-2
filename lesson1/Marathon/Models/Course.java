package homework1.Marathon.Models;

import homework1.Marathon.Interfaces.Competitor;
import homework1.Marathon.Models.Obstacle.Obstacle;

public final class Course implements Cloneable {
    private Obstacle[] obstacles;

    public Course(Obstacle[] obstacles) throws CloneNotSupportedException {
        this.obstacles = new Obstacle[obstacles.length];
        for (int i = 0; i < obstacles.length; i++) {
            this.obstacles[i] = (Obstacle) obstacles[i].clone();
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void doIt(Team team) {
        for (Obstacle obstacle : obstacles) {
            Competitor[] competitors = team.getCompetitors();

            for (Competitor competitor : competitors) {
                if (competitor.isOnDistance()) {
                    obstacle.doIt(competitor);
                }
            }

            if (!team.isOnDistance()) {
                return;
            }
        }
    }
}
