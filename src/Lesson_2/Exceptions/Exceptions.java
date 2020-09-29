package Lesson_2.Exceptions;


public class Exceptions{


    public static void main(String[] args) throws MyArraySizeException, MyArrayDataException{
        String[][] alphaMass = new String[4][4];
        alphaMass[0][0] = "1";
        alphaMass[0][1] = "1";
        alphaMass[0][2] = "1";
        alphaMass[0][3] = "1";
        alphaMass[1][0] = "1";
        alphaMass[1][1] = "ф";
        alphaMass[1][2] = "1";
        alphaMass[1][3] = "1";
        alphaMass[2][0] = "1";
        alphaMass[2][1] = "1";
        alphaMass[2][2] = "1";
        alphaMass[2][3] = "1";
        alphaMass[3][0] = "1";
        alphaMass[3][1] = "1";
        alphaMass[3][2] = "1";
        alphaMass[3][3] = "1";
        System.out.println(mass(alphaMass));
    }

    static int mass(String[][] a) {
        if(a.length>4 || a[0].length>4) throw new MyArraySizeException("Массив больше чем 4 на 4");

        int[][] b = new int[4][4];
        int sum = 0;
        for(int i = 0; i < a.length;i++){
            for(int j = 0; j < a.length; j++) {
                if(a[i][j].matches("[-+]?\\d+")) throw  new MyArrayDataException("Массив содержит недопустимый символ");
                b[i][j] = Integer.valueOf(a[i][j]);
                sum += b[i][j];

            }
        }
        return sum;
    }

}