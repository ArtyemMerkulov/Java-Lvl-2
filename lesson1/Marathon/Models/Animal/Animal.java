package homework1.Marathon.Models.Animal;

import homework1.Marathon.Interfaces.Competitor;

public class Animal implements Competitor, Cloneable {
    protected AnimalType type;
    protected String name;

    protected float maxRunDistance;
    protected float maxJumpHeight;
    protected float maxSwimDistance;

    protected boolean onDistance;

    public Animal(AnimalType type, String name, float maxRunDistance, float maxJumpHeight, float maxSwimDistance) {
        this.type = type;
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxJumpHeight = maxJumpHeight;
        this.maxSwimDistance = maxSwimDistance;
        this.onDistance = true;
    }

    private boolean isAvailableAction(float required, float expected) {
        return required <= expected;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public void jump(float height) {
        if (isAvailableAction(height, maxJumpHeight)) {
            System.out.println(type.toString() + " " + name + " удачно перепрыгнул через стену");
        } else {
            System.out.println(type.toString() + " " + name + " не смог перепрыгнуть стену");
            onDistance = false;
        }
    }

    @Override
    public void run(float dist) {
        if (isAvailableAction(dist, maxRunDistance)) {
            System.out.println(type.toString() + " " + name + " хорошо справился с кроссом");
        } else {
            System.out.println(type.toString() + " " + name + " не справился с кроссом");
            onDistance = false;
        }
    }

    @Override
    public void swim(float dist) {
        if (maxSwimDistance == 0) {
            System.out.println(type.toString() + " " + name + " не умеет плавать");
            onDistance = false;
            return;
        }
        if (isAvailableAction(dist, maxSwimDistance)) {
            System.out.println(type.toString() + " " + name + " отлично проплыл");
        } else {
            System.out.println(type.toString() + " " + name + " не смог проплыть");
            onDistance = false;
        }
    }

    @Override
    public boolean isOnDistance() {
        return onDistance;
    }

    @Override
    public void info() {
        System.out.println(type.toString() + " " + name + " - " + (onDistance ? " на дистанции" : " сошел с дистанции"));
    }
}
