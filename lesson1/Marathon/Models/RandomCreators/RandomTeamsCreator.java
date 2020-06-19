package homework1.Marathon.Models.RandomCreators;

import homework1.Marathon.Interfaces.Competitor;
import homework1.Marathon.Models.Animal.AnimalType;
import homework1.Marathon.Models.Team;

public class RandomTeamsCreator {
    private Team[] teams;

    public RandomTeamsCreator(int nTeams, int nCompetitors) throws CloneNotSupportedException {
        teams = new Team[nTeams];

        for (int i = 0; i < nTeams; i++) {
            String teamName = "Команда №" + (i + 1);
            teams[i] = this.getRandomTeam(teamName, nCompetitors);
        }
    }

    public Team getRandomTeam(String teamName, int nCompetitors) throws CloneNotSupportedException {
        RandomCompetitorCreator randomCompetitorCreator = new RandomCompetitorCreator();
        Competitor[] competitors = new Competitor[nCompetitors];

        for (int i = 0; i < nCompetitors; i++) {
            AnimalType type = AnimalType.getRandomAnimalType();
            String name = type.toString() + " №" + (i + 1);

            competitors[i] = randomCompetitorCreator.getRandomCompetitor(type, name);
        }

        return new Team(teamName, competitors);
    }

    public Team[] getTeams() throws CloneNotSupportedException {
        Team[] teams = new Team[this.teams.length];
        for (int i = 0; i < this.teams.length; i++) {
            teams[i] = (Team) this.teams[i].clone();
        }

        return teams;
    }
}
