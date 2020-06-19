package homework1.Marathon;

import homework1.Marathon.Models.Competition;
import homework1.Marathon.Models.Course;
import homework1.Marathon.Models.RandomCreators.RandomCourseCreator;
import homework1.Marathon.Models.RandomCreators.RandomTeamsCreator;
import homework1.Marathon.Models.Team;

public class Main {
    private static final int N_TEAMS = 3;
    private static final int N_COMPETITORS = 3;
    private static final int N_OBSTACLES = 4;

    public static void main(String[] args) throws CloneNotSupportedException {
        Team[] teams = new RandomTeamsCreator(N_TEAMS, N_COMPETITORS).getTeams();
        Course course = new RandomCourseCreator(N_OBSTACLES).getCourse();

        new Competition(teams, course).begin();
    }
}