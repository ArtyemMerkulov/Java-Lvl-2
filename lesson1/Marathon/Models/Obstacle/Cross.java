package homework1.Marathon.Models.Obstacle;

import homework1.Marathon.Interfaces.Competitor;

public final class Cross extends Obstacle {
    private float length;

    public Cross(float length) {
        this.length = length;
    }

    @Override
    public void doIt(Competitor competitor) {
        competitor.run(length);
    }
}
