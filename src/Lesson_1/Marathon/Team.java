package Lesson_1.Marathon;

import Lesson_1.Marathon.Competitors.Competitor;

public class Team {
    private Competitor[] competitors;
    public Team(Competitor... competitors) {
        this.competitors = competitors;
    }

    Competitor[] getCompetitors(){
        return competitors;
    }

    public void showResults() {
        for (Competitor c : competitors) {
            c.info();
        }
    }
}
