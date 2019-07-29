package Lesson_5.ThreadHomework;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreadsMain {
    static final int SIZE = 10000000;                   // Размер обрабатываемого массива
    static final int THREADSQUANTITY = 2;               // Количество потоков (я не учитывал главный поток в расчётах)
    static final int PARTS = SIZE / THREADSQUANTITY;    // Количество частей на которые будет разделён массив

    public static void main(String[] args) {
        Float[] arr = new Float[SIZE];



        oneThreadMethod(arr);
        multipleThreadsMethod(arr);

        /*


        // блок кода для проверки разделения листа (рекомендую ставить значение SIZE в пределах 20-30 и закоментировать вызов методов "oneThreadMethod(arr)" и "multipleThreadsMethod(arr)")
        List<Float[]> listOfParts = splitArray(arr, PARTS);     // делим список на части

        for(int i=0; i<listOfParts.size(); i++) {

            System.out.println("Array " + i);

            for (int j = 0; j < listOfParts.get(i).length; j++) {
                System.out.println("  " + listOfParts.get(i)[j]);
            }
        }
        */




    }





    static void oneThreadMethod(Float[] arr){

        for(int i=0; i<arr.length; i++){
            arr[i]=1f;
        }

        long a = System.currentTimeMillis();

        for(int i=0; i<arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }


        System.out.println(System.currentTimeMillis()-a);
    }

    static void multipleThreadsMethod(Float[] arr) {
        for(int i=0; i<arr.length; i++){
            arr[i]=1f;
        }

        long a = System.currentTimeMillis();

        List<Float[]> listOfParts = splitArray(arr, PARTS);     // делим список на части


        Thread myThreads[] = new Thread[THREADSQUANTITY];       //Массив потоков
        for (int j = 0; j < THREADSQUANTITY; j++) {
            myThreads[j] = new Thread(new aThreads(j));         //Инициализация потоков
            myThreads[j].run();{                                //Запуск функционала
                for(int i = 0; i < listOfParts.get(j).length; i++){
                    listOfParts.get(j)[i]= (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
            myThreads[j].start();
        }
        for (int j = 0; j < THREADSQUANTITY; j++) {
            try {
                myThreads[j].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(System.currentTimeMillis()-a);
    }


    public static <T extends Object> List<T[]> splitArray(T[] array, int max){

        int x = array.length / max;
        int r = (array.length % max); // remainder

        int lower = 0;
        int upper = 0;

        List<T[]> list = new ArrayList<T[]>();

        int i=0;

        for(i=0; i<x; i++){

            upper += max;

            list.add(Arrays.copyOfRange(array, lower, upper));

            lower = upper;
        }

        if(r > 0){

            list.add(Arrays.copyOfRange(array, lower, (lower + r)));

        }

        return list;
    }

}



class aThreads implements Runnable {        //Класс для упрощения работы с потоками

    private final int i;

    public aThreads(int i) {
        this.i = i;
    }


    @Override
    public void run() {

    }




}


