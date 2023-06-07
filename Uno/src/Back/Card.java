package Back;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

import static Front.Color.White;


//inheritance from deck
public class Card extends Deck {
    static boolean initDraw = true;

    public static void showCard(String[][] player, int i) {
        System.out.print("Player " + (i + 1) + " cards: ");
        for (int j = 0; j < player[i].length; j++) {
            if (Player.player[i][j] == null) {
                Player.player[i][j] = "  ";
            }
            System.out.print(Front.Color.color(player[i][j]) + player[i][j] + White + " ");
        }
        System.out.print("\n");
    }

    public static void topCard(String Card) {
        System.out.print("The top card at the pile is: " + Front.Color.color(Card) + Card + White + "\n");
    }

    public static void draw(int i) {
        int tempInt2 = 0;
        for (int j = 0; j < Player.player[i].length; j++) {
            if (Objects.equals(Player.player[i][j], "  ") || Objects.equals(Player.player[i][j], null)) {
                tempInt2++;
            }
        }

        if (tempInt2 == 0) {
            String[][] tempArr = Player.player;
            Player.player = new String[tempArr.length][tempArr[0].length + 1];
            for (int k = 0; k < tempArr.length; k++) {
                System.arraycopy(tempArr[k], 0, Player.player[k], 0, tempArr[k].length);
            }
        }

        for (int j = 0; j < Player.player[i].length; j++) {
            if ((Objects.equals(Player.player[i][j], "  ") || (Objects.equals(Player.player[i][j], null)))) {
                while (true) {
                    double tempDouble = Math.random() * (deck.length() - 2);
                    int tempInt = (int) tempDouble;
                    if (tempInt % 2 == 0) {
                        Player.player[i][j] = deck.substring(tempInt, tempInt + 2);
                        break;
                    }
                }
                break;
            }
        }
    }

    public static void init() {
        for (int i = 0; i < Player.player.length; i++) {
            for (int j = 0; j < Player.player[i].length; j++) {
                draw(i);
            }
        }
        initDraw = false;
    }
}
