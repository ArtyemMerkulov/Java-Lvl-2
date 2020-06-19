package homework1.Marathon.Models.Obstacle;

import homework1.Marathon.Interfaces.Competitor;

public final class Wall extends Obstacle {
    private float height;

    public Wall(float height) {
        this.height = height;
    }

    @Override
    public void doIt(Competitor competitor) {
        competitor.jump(height);
    }
}