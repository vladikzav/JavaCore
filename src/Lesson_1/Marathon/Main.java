package Lesson_1.Marathon;

import Lesson_1.Marathon.Competitors.Cat;
import Lesson_1.Marathon.Competitors.Dog;
import Lesson_1.Marathon.Competitors.Human;
import Lesson_1.Marathon.Obstacles.Cross;
import Lesson_1.Marathon.Obstacles.Wall;

public class Main {
    public static void main(String[] args) {
        /*
        Competitor[] competitors = {new Human("Боб"), new Cat("Барсик"), new Dog("Бобик")};
        Obstacle[] course = {new Cross(80), new Wall(10), new Wall(1), new Cross(220)};
        for (Competitor c : competitors) {
            for (Obstacle o : course) {
                o.doIt(c);
                if (!c.isOnDistance()) break;
            }
        }
        for (Competitor c : competitors) {
            c.info();
        }

        */

        Course c = new Course(new Cross(80), new Wall(10), new Wall(1), new Cross(220)); // Создаем полосу препятствий
        Team team = new Team(new Human("Боб"), new Cat("Барсик"), new Dog("Бобик")); // Создаем команду
        c.doIt(team); // Просим команду пройти полосу
        team.showResults(); // Показываем результаты

    }

}