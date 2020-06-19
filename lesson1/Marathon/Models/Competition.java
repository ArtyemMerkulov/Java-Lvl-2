package homework1.Marathon.Models;

public class Competition {
    private Team[] teams;
    private Course course;

    public Competition(Team[] teams, Course course) throws CloneNotSupportedException {
        this.course = (Course) course.clone();

        this.teams = new Team[teams.length];
        for (int i = 0; i < teams.length; i++) {
            this.teams[i] = (Team) teams[i].clone();
        }
    }

    public void begin() {
        for (Team team : teams) {
            System.out.println(team.getName() + " начинает участие в соревновании!");

            course.doIt(team);

            System.out.println();
            team.showResults();

            System.out.println();
            team.info();

            System.out.println();
        }
    }

}
