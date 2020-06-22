package homework1.Marathon.Models.Obstacle;

import homework1.Marathon.Interfaces.Competitor;

public final class Water extends Obstacle {
    private float length;

    public Water(float length) {
        this.length = length;
    }

    @Override
    public void doIt(Competitor competitor) {
        competitor.swim(length);
    }
}
