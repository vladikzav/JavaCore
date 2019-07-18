package Lesson_2.Enum;

public class DayOfWeekMain {

    public static void main(final String[] args) {
        System.out.println(getWorkingHours(DayOfWeek.WEDNESDAY));
    }

    static int getWorkingHours(Enum e){
        int hours=0;
        if(e.ordinal()<5){
            hours=40-(e.ordinal()*8);
        }else System.out.println("Выходной!");
        return hours;
    }

}

enum DayOfWeek{

    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;

}