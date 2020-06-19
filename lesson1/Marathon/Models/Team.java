package homework1.Marathon.Models;

import homework1.Marathon.Interfaces.Competitor;
import homework1.Marathon.Models.Animal.Animal;

public final class Team implements Cloneable {
    private String teamName;

    private boolean onDistance;

    private Competitor[] competitors;

    public Team(String teamName, Competitor[] competitors) throws CloneNotSupportedException {
        this.teamName = teamName;

        this.competitors = new Competitor[competitors.length];
        for (int i = 0; i < competitors.length; i++) {
            this.competitors[i] = (Competitor) ((Animal) competitors[i]).clone();
        }

        this.onDistance = true;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Competitor[] getCompetitors() {
        return competitors;
    }

    public boolean isOnDistance() {
        boolean onDistance = false;

        for (Competitor competitor : competitors) {
            onDistance = onDistance || competitor.isOnDistance();
        }

        this.onDistance = onDistance;

        return this.onDistance;
    }

    public void showResults() {
        System.out.println("Информация о участниках команды " + teamName + ", прошедших полосу препятствий:");

        if (!this.onDistance) {
            System.out.println("Ни один из участников команды " + teamName + " не прошел полосу препятствий");
            return;
        }

        for (Competitor competitor : competitors) {
            if (competitor.isOnDistance()) {
                competitor.info();
            }
        }
    }

    public void info() {
        System.out.println("Информация о участниках команды " + teamName);

        for (Competitor competitor : competitors) {
            competitor.info();
        }
    }

    public String getName() {
        return teamName;
    }
}
