package Front;
import Back.Player;

import java.io.IOException;


public class UI
{
    //clear the terminal console by excuting the "cls" command into the console
    public static void clearConsole()
    {
        final String os = System.getProperty("os.name");

        try {
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    //display the guide for the uno game
    public static void guide()
    {
        Front.UI.clearConsole();
        System.out.println("=== UNO Guide ===");
        System.out.println("\n*UNO is a card game that is played with a specially printed deck.");
        System.out.println("*The deck consists of 108 cards, of which there are 25 of each \n color (red, green, blue, and yellow), each color having two of each rank except zero.");
        System.out.println("*The ranks in each color are 0 through 9, \"Skip\", \"Draw Two\", \n and \"Reverse\".");
        System.out.println("*In addition, the deck contains four each of \"Wild\" and \"Wild \n Draw Four\" cards.");
        System.out.println("*To win the game, you must be the first player to get rid of all \n your cards.");
        System.out.println("*On your turn, you must match the top card on the discard pile \n either by color, number, or action.");
        System.out.println("*If you cannot match the top card, you must draw a card from the \n draw pile.");
        System.out.println("*If the card you drew can be played, you may play it immediately, \n if not, your turn ends.");
        System.out.println("*If you play a \"Wild\" card, you must declare the color you want \n the next player to match.");
        System.out.println("*If you play a \"Wild Draw Four\" card, you must declare the color you want \n the next player to match and the next player must draw four cards from the draw pile.");
        System.out.println("*If you play a \"Draw Two\" card, the next player must draw two \n cards from the draw pile and skip their turn.");
        System.out.println("*If you play a \"Skip\" card, the next player is skipped and their \n turn is forfeited.");
        System.out.println("*If you play a \"Reverse\" card, the direction of play is reversed.");
        System.out.println("*If you have only one card left, you must say \"UNO\" to indicate \n that you are about to win.");
        System.out.println("*If you fail to say \"UNO\" and are caught by another player, you must \n draw two cards from the draw pile.");
        System.out.println("Good luck and have fun!");
        System.out.println("\nEnter any key to continue...");
        Player.scan.next();
        Front.UI.clearConsole();
    }
}
