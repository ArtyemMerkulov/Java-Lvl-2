package homework1.Marathon.Interfaces;

public interface Competitor {
    void jump(float height);

    void run(float dist);

    void swim(float dist);

    boolean isOnDistance();

    void info();
}