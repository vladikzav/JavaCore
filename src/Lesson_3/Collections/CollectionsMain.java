package Lesson_3.Collections;

import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CollectionsMain {


    public static void main(String[] args) {

        HashMap<String, Integer> hashMass = new HashMap<>();
        String[] mass = new String[15];
        for(int i=0; i<mass.length; i++){
            if((i % 2) == 1){
                mass[i]=("word"+(i-1));
            }else
                mass[i]=("word"+i);
            System.out.println(mass[i]);
            Integer value = hashMass.get(mass[i]);
            hashMass.put(mass[i], value == null ? 1 : value+1);
        }

        System.out.println(hashMass);





    }

}

class Phonebook{

    public static void main(String[] args) {
        Phonebook book = new Phonebook("viktor","898700000000");
        book.addNumber("viktor", "898700000001");
        book.addNumber("viktor", "898700000002");
        book.addNumber("dmitriy", "898700000003");
        book.getNumber("viktor");
        book.getNumber("dmitriy");
    }

    private HashMap<String, HashSet<String>> phonebook = new HashMap<>();
    private Phonebook(String name, String number){
        phonebook.put(name, new HashSet<String>());
        addNumber(name, number);

    }

    private void getNumber(String name){
        System.out.println(name + " " + phonebook.get(name));
    }

    private void addNumber(String name,String number){
        if(phonebook.get(name)==null){
            phonebook.put(name, new HashSet<String>());
            phonebook.get(name).add(number);
        }else
            phonebook.get(name).add(number);
    }

}

class Password{

    public static void main(String[] args) throws PassException {
        String password = "1aasD1a$";
        passCheck(password);


    }

    private static void passCheck(String password) throws PassException{
        char[] passToArray = password.toCharArray();
        int minChar = 8;
        int maxChar = 20;
        boolean num = false;
        boolean charL = false;
        boolean charS = false;
        boolean special = false;

        Pattern pNum = Pattern.compile("[0-9]+?");
        Matcher mNum = pNum.matcher(password);

        Pattern pCharL = Pattern.compile("[A-Z]+?");
        Matcher mCharL = pCharL.matcher(password);

        Pattern pCharS = Pattern.compile("[a-z]+?");
        Matcher mCharS = pCharS.matcher(password);

        Pattern pSpec = Pattern.compile("[^a-zA-Z0-9]+?");
        Matcher mSpec = pSpec.matcher(password);


        if(mNum.find())
            num = true;

        if(mCharL.find())
            charL = true;

        if(mCharS.find())
            charS = true;

        if(mSpec.find())
            special = true;


        System.out.println(num + " " + charL + " " + charS + " " + special);







        if(passToArray.length<minChar || passToArray.length>maxChar
                || !num || !charL || !charS || !special) {
            throw new PassException("Пароль не соответствует требованиям");
        }


        System.out.println("Пароль принят");

    }
}

class PassException extends Exception{
    PassException(String msg){
        super(msg);
    }
}