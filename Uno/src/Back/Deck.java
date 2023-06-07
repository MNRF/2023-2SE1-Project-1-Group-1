package Back;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
//abstract class deck to be inherit by card class
public abstract class Deck {
    //encapsulation (private), excapsulate the "card.txt" file and its file scanner
    private static File file = new File("C:\\dev\\2023_2SE1_Project1_Group1\\Uno\\src\\Data\\Card.txt");
    private static Scanner fileScanner;


    //encapsulation(protected), encapsulate the file scanning process.
    protected static String deck;
    static {
        try {
            fileScanner = new Scanner(file);
            deck = fileScanner.nextLine();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //draw method
    public static String draw() {
        String a;
        int tempInt;
        double tempDouble;
        //generated random number and assign two index of the string(beacuse each card has 2 character)
        //based on the generated number, into String a
        while (true) {
            tempDouble = Math.random() * (deck.length() - 2);
            tempInt = (int) tempDouble;
            if (tempInt % 2 == 0) {
                a = deck.substring(tempInt, tempInt + 2);
                break;
            }
        }
        return a;
    }
}