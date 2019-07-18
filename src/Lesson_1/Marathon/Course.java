package Lesson_1.Marathon;



import Lesson_1.Marathon.Competitors.Competitor;
import Lesson_1.Marathon.Obstacles.Obstacle;

public class Course {
    private Obstacle[] obstacles;
    public Course(Obstacle... obstacles) {
       this.obstacles = obstacles;
    }

    public void doIt(Team team) {
       Competitor[] competitors = team.getCompetitors();
        for (Competitor c : competitors) {
            for (Obstacle o : obstacles) {
                o.doIt(c);
                if (!c.isOnDistance()) break;
            }
        }

    }
}
