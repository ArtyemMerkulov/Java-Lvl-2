package homework1.Marathon.Models.Obstacle;

import homework1.Marathon.Interfaces.Competitor;

public abstract class Obstacle implements Cloneable {

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public abstract void doIt(Competitor competitor);
}
