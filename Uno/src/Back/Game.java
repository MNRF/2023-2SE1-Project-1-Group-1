package Back;
import Front.UI;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import static Front.Color.*;

public class Game {
    public static boolean gameOn = true;
    public static int prevPlayer = 0;

    //dislay leader board
    public static void displayLeaderboard() {
        try {
            File winnerFile = new File("C:\\dev\\2023_2SE1_Project1_Group1\\Uno\\src\\Data\\win.txt");

            if (!winnerFile.exists()) {
                System.out.println("Leaderboard is empty");
            } else {
                BufferedReader reader = new BufferedReader(new FileReader(winnerFile));
                String line;
                List<String> leaderboard = new ArrayList<>();
                while ((line = reader.readLine()) != null) {
                    leaderboard.add(line);
                }
                reader.close();
                if (leaderboard.isEmpty()) {
                    System.out.println("Leaderboard is empty");
                } else {
                    leaderboard.sort((o1, o2) -> {
                        int wins1 = Integer.parseInt(o1.split(":")[1].trim());
                        int wins2 = Integer.parseInt(o2.split(":")[1].trim());
                        return Integer.compare(wins2, wins1);
                    });
                    System.out.println("Leaderboard:");
                    for (String s : leaderboard) {
                        System.out.println(s);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("error while reading" + e.getMessage());
        }
    }

    //reset leaderboard
    public static void resetLeaderboard()
    {
        try
        {
            File winnerFile = new File("C:\\dev\\2023_2SE1_Project1_Group1\\Uno\\src\\Data\\win.txt");
            FileWriter fw = new FileWriter(winnerFile);
            fw.write("");
        }
        catch (Exception e)
        {
            System.out.print(e);
        }

    }


    public static void start()
    {
        try
        {
            File file = new File("C:\\dev\\2023_2SE1_Project1_Group1\\Uno\\src\\Data\\Card.txt");
            Scanner fileScanner = new Scanner(file);
            String card = fileScanner.nextLine();
            int tempInt = 0;
            double tempDouble;
            boolean loop = true;

            for (int i = 0; i < Player.player.length; i++)
            {
                for (int j = 0; j < Player.player[i].length; j++)
                {
                    while(loop)
                    {
                        tempDouble = Math.random() * (card.length()-2);
                        tempInt = (int) tempDouble;
                        Player.player[i][j] = card.substring(tempInt, tempInt + 2);
                        if(tempInt%2==0)
                        {
                            loop = false;
                        }
                    }
                }
            }

            String Card = "0";
            loop = true;

            Card = Back.Card.draw();

            String input;
            char wildColor = ' ';
            boolean reversed = false, skipped = false, drawable = true, pickable = false, firstTurn = true;
            int i = Player.player.length-1;

            do
            {
                tempInt = 0;
                pickable = true;
                drawable = true;

                Player.winCheck(i);

                if(!reversed)
                {
                    if (prevPlayer == i)
                    {
                        Player.prevPlayerCounter++;
                    }else
                    {
                        Player.prevPlayerCounter = 0;
                    }
                    prevPlayer = i;
                    if(i != Player.player.length-1)
                    {
                        i++;
                    }else
                    {
                        i = 0;
                    }
                    Front.UI.clearConsole();
                    while(true) {
                        Back.Card.topCard(Card);
                        if(wildColor != ' ')
                        {
                            System.out.print("Color = " + color(wildColor) + wildColor + White + "\n");
                        }
                        tempInt = 0;
                        while(true)
                        {
                            for (int j = 0; j < Player.player[i].length-1; j++)
                            {
                                if (Objects.equals(Player.player[i][j], "  "))
                                {
                                    tempInt++;
                                }
                            }

                            if (tempInt == Player.player[i].length-1 && i < Player.player.length-1)
                            {
                                i++;
                            }
                            else if (tempInt == Player.player[i].length-1 && i == Player.player.length-1)
                            {
                                i = 0;
                            }
                            else
                            {
                                break;
                            }
                            tempInt = 0;
                        }
                        System.out.print("Total Player: " + Player.totalPlayer + "\n");
                        System.out.print("Player " + (i+1) + " turn\n");
                        System.out.print("1. Show card\n");
                        System.out.print("2. Pick card\n");
                        System.out.print("3. Draw card\n");
                        System.out.print("4. Next turn\n");
                        System.out.print("5. Display Leaderboard\n");
                        System.out.print("6. Reset Leaderboard\n");
                        System.out.print("9. Withdraw\n");
                        System.out.print("0. Guide\n");
                        System.out.print("Option: ");
                        input = Player.scan.next();

                        if (Objects.equals(input, "1"))
                        {
                            Front.UI.clearConsole();
                            Back.Card.showCard(Player.player, i);
                        }
                        else if (Objects.equals(input, "2"))
                        {
                            if(pickable) {
                                System.out.print("Pick a card: ");
                                input = Player.scan.next();

                                for (int j = 0; j < Player.player[i].length; j++) {
                                    if (Objects.equals(input, Player.player[i][j])) {
                                        pickable = true;
                                        break;
                                    } else {
                                        pickable = false;
                                    }
                                }

                                if (pickable && (/*(firstTurn && */input.charAt(0) != 'W'/*)*/ || (input.charAt(0) == Card.charAt(0) || input.charAt(0) == Card.charAt(1) || (Card.charAt(0) == 'W' && input.charAt(0) == wildColor)))) {
                                    for (int j = 0; j < Player.player[i].length; j++) {
                                        if (Objects.equals(input, Player.player[i][j])) {
                                            Card = Player.player[i][j];
                                            Player.player[i][j] = "  ";
                                            j = Player.player[i].length;
                                        }
                                    }

                                    if (wildColor != ' ') {
                                        wildColor = ' ';
                                    }

                                    if (firstTurn)
                                    {
                                        firstTurn = false;
                                    }

                                    if (input.charAt(1) == 'R')
                                    {
                                        reversed = true;
                                    }else if (input.charAt(1) == 'S')
                                    {
                                        if(i+2 < Player.player.length)
                                        {
                                            i+=2;
                                        }
                                        else if (i+2 >= Player.player.length)
                                        {
                                            i = 1;
                                        }
                                        pickable = true;
                                        Player.winCheck(i);
                                    }else if (input.charAt(1) == 'D')
                                    {
                                        if(i+1 <= Player.player.length-1)
                                        {
                                            for (int k = 0; k < 2; k++)
                                            {
                                                drawable = true;
                                                Back.Card.draw(i+1);
                                                drawable = false;
                                            }
                                        }
                                        else// if (i+1 > Player.player.length-1)
                                        {
                                            for (int k = 0; k < 2; k++)
                                            {
                                                drawable = true;
                                                Back.Card.draw(0);
                                                drawable = false;
                                            }
                                        }
                                    }

                                    if (pickable) {
                                        pickable = false;
                                    }

                                    Front.UI.clearConsole();
                                }
                                else if (pickable && input.charAt(0) == 'W')
                                {
                                    for (int j = 0; j < Player.player[i].length; j++)
                                    {
                                        if (Objects.equals(input, Player.player[i][j])) {
                                            Card = Player.player[i][j];
                                            Player.player[i][j] = "  ";
                                            j = Player.player[i].length;
                                        }
                                    }

                                    System.out.print("Pick a color: ");
                                    input = Player.scan.next();
                                    wildColor = input.charAt(0);

                                    loop = true;
                                    while (loop) {
                                        switch (input.charAt(0)) {
                                            case 'r':
                                            case 'R':
                                                wildColor = 'R';
                                                loop = false;
                                                break;
                                            case 'g':
                                            case 'G':
                                                wildColor = 'G';
                                                loop = false;
                                                break;
                                            case 'b':
                                            case 'B':
                                                wildColor = 'B';
                                                loop = false;
                                                break;
                                            case 'y':
                                            case 'Y':
                                                wildColor = 'Y';
                                                loop = false;
                                                break;
                                            default:
                                                Front.UI.clearConsole();
                                                System.out.print("Only input either Red,Green,Blue,or Yellow!\n");
                                        }
                                        Front.UI.clearConsole();
                                    }

                                    if (pickable) {
                                        pickable = false;
                                    }
                                    if (input.charAt(1) == 'R')
                                    {
                                        reversed = true;
                                    }
                                    if (input.charAt(1) == 'S')
                                    {
                                        if(i+2 < Player.player.length)
                                        {
                                            i+=2;
                                        }
                                        else if (i+2 >= Player.player.length)
                                        {
                                            i = 1;
                                        }
                                        pickable = true;
                                        Player.winCheck(i);
                                    }
                                    if (input.charAt(1) == 'D')
                                    {
                                        if(i+1 <= Player.player.length-1)
                                        {
                                            for (int k = 0; k < 4; k++)
                                            {
                                                drawable = true;
                                                Back.Card.draw(i+1);
                                                drawable = false;
                                            }
                                        }
                                        else// if (i+1 > Player.player.length-1)
                                        {
                                            for (int k = 0; k < 4; k++)
                                            {
                                                drawable = true;
                                                Back.Card.draw(0);
                                                drawable = false;
                                            }
                                        }
                                    }
                                } else if (!pickable) {
                                    Front.UI.clearConsole();
                                    System.out.print("Only input the card on your hand!\n");
                                    pickable = true;
                                } else {
                                    Front.UI.clearConsole();
                                    System.out.print("Only input card of the same color, number, or action!\n");
                                }
                            }
                            else
                            {
                                Front.UI.clearConsole();
                                System.out.print("You can only pick once per turn!\n");
                            }
                            Player.winCheck(i);
                        }
                        else if (Objects.equals(input, "3"))
                        {
                            if (drawable == true)
                            {
                                Back.Card.draw(i);
                                drawable = false;
                            }
                            else
                            {
                                Front.UI.clearConsole();
                                System.out.print("You can only draw once per turn!\n");
                            }
                        }
                        else if (Objects.equals(input, "4"))
                        {
                            break;
                        }
                        else if (Objects.equals(input, "5"))
                        {
                            Front.UI.clearConsole();
                            displayLeaderboard();
                        }
                        else if (Objects.equals(input, "6"))
                        {
                            Front.UI.clearConsole();
                            resetLeaderboard();
                            System.out.print("Leaderboard reset successful!\n");
                        }
                        else if (Objects.equals(input, "9"))
                        {
                            Front.UI.clearConsole();
                            System.out.print("Are you sure you want to withdraw?: ");
                            input = Player.scan.next();
                            if (Objects.equals(input.charAt(0), 'Y') || Objects.equals(input.charAt(0), 'y')) {
                                Player.removePlayer(i);
                                Front.UI.clearConsole();
                                System.out.println("Player " + (i+1) + " has withdrawn from the game.");
                            }
                        }
                        else if (Objects.equals(input, "0"))
                        {
                            Front.UI.guide();
                        }
                        else
                        {
                            Front.UI.clearConsole();
                            System.out.print("Only input either 1,2,3,4,5,6 or 9!\n");
                        }
                    }
                }

                else if(reversed)
                {
                    if (prevPlayer == i)
                    {
                        Player.prevPlayerCounter++;
                    }else
                    {
                        Player.prevPlayerCounter = 0;
                    }
                    if(i > 0)
                    {
                        i--;
                    }else
                    {
                        i = Player.player.length-1;
                    }
                    Front.UI.clearConsole();
                    while(true) {
                        Back.Card.topCard(Card);
                        if(wildColor != ' ')
                        {
                            System.out.print("Color = " + color(wildColor) + wildColor + White + "\n");
                        }
                        tempInt = 0;
                        while(true)
                        {
                            for (int j = 0; j < Player.player[i].length-1; j++)
                            {
                                if (Objects.equals(Player.player[i][j], "  "))
                                {
                                    tempInt++;
                                }
                            }

                            if (tempInt == Player.player[i].length-1 && i > 0)
                            {
                                i--;
                            }
                            else if (tempInt == Player.player[i].length-1 && i == 0)
                            {
                                i = Player.player.length-1;
                            }
                            else
                            {
                                break;
                            }
                            tempInt = 0;
                        }
                        System.out.print("Total Player: " + Player.totalPlayer + "\n");
                        System.out.print("Player " + (i+1) + " turn\n");
                        System.out.print("1. Show card\n");
                        System.out.print("2. Pick card\n");
                        System.out.print("3. Draw card\n");
                        System.out.print("4. Next turn\n");
                        System.out.print("5. Leaderboard\n");
                        System.out.print("9. Withdraw\n");
                        System.out.print("0. Guide\n");
                        System.out.print("Option: ");
                        input = Player.scan.next();

                        if (Objects.equals(input, "1"))
                        {
                            Front.UI.clearConsole();
                            Back.Card.showCard(Player.player, i);
                        }
                        else if (Objects.equals(input, "2"))
                        {
                            if(pickable) {
                                System.out.print("Pick a card: ");
                                input = Player.scan.next();

                                for (int j = 0; j < Player.player[i].length; j++) {
                                    if (Objects.equals(input, Player.player[i][j])) {
                                        pickable = true;
                                        break;
                                    } else {
                                        pickable = false;
                                    }
                                }

                                if (pickable && (/*(firstTurn && */input.charAt(0) != 'W'/*)*/ || (input.charAt(0) == Card.charAt(0) || input.charAt(0) == Card.charAt(1) || (Card.charAt(0) == 'W' && input.charAt(0) == wildColor)))) {
                                    for (int j = 0; j < Player.player[i].length; j++) {
                                        if (Objects.equals(input, Player.player[i][j])) {
                                            Card = Player.player[i][j];
                                            Player.player[i][j] = "  ";
                                            j = Player.player[i].length;
                                        }
                                    }

                                    if (wildColor != ' ') {
                                        wildColor = ' ';
                                    }

                                    if (firstTurn)
                                    {
                                        firstTurn = false;
                                    }

                                    if (input.charAt(1) == 'R')
                                    {
                                        reversed = true;
                                    }else if (input.charAt(1) == 'S')
                                    {
                                        if(i-2 >= 0)
                                        {
                                            i-=2;
                                        }
                                        else if (i-2 < 0)
                                        {
                                            i = Player.player.length-1;
                                        }
                                        pickable = true;
                                        Player.winCheck(i);
                                    }else if (input.charAt(1) == 'D')
                                    {
                                        if(i-1 >= 0)
                                        {
                                            for (int k = 0; k < 2; k++)
                                            {
                                                drawable = true;
                                                Back.Card.draw(i-1);
                                                drawable = false;
                                            }
                                        }
                                        else// if (i+1 < 0)
                                        {
                                            for (int k = 0; k < 2; k++)
                                            {
                                                drawable = true;
                                                Back.Card.draw(Player.player.length-1);
                                                drawable = false;
                                            }
                                        }
                                    }

                                    if (pickable) {
                                        pickable = false;
                                    }

                                    Front.UI.clearConsole();
                                }
                                else if (pickable && input.charAt(0) == 'W')
                                {
                                    for (int j = 0; j < Player.player[i].length; j++)
                                    {
                                        if (Objects.equals(input, Player.player[i][j])) {
                                            Card = Player.player[i][j];
                                            Player.player[i][j] = "  ";
                                            j = Player.player[i].length;
                                        }
                                    }

                                    System.out.print("Pick a color: ");
                                    input = Player.scan.next();
                                    wildColor = input.charAt(0);

                                    loop = true;
                                    while (loop) {
                                        switch (input.charAt(0)) {
                                            case 'r':
                                            case 'R':
                                                wildColor = 'R';
                                                loop = false;
                                                break;
                                            case 'g':
                                            case 'G':
                                                wildColor = 'G';
                                                loop = false;
                                                break;
                                            case 'b':
                                            case 'B':
                                                wildColor = 'B';
                                                loop = false;
                                                break;
                                            case 'y':
                                            case 'Y':
                                                wildColor = 'Y';
                                                loop = false;
                                                break;
                                            default:
                                                Front.UI.clearConsole();
                                                System.out.print("Only input either Red,Green,Blue,or Yellow!\n");
                                        }
                                        Front.UI.clearConsole();
                                    }

                                    if (pickable) {
                                        pickable = false;
                                    }
                                    if (input.charAt(1) == 'R')
                                    {
                                        reversed = true;
                                    }else if (input.charAt(1) == 'S')
                                    {
                                        if(i-2 >= 0)
                                        {
                                            i-=2;
                                        }
                                        else if (i-2 < 0)
                                        {
                                            i = Player.player.length-1;
                                        }
                                        pickable = true;
                                        Player.winCheck(i);
                                    }else if (input.charAt(1) == 'D')
                                    {
                                        if(i-1 >= 0)
                                        {
                                            for (int k = 0; k < 4; k++)
                                            {
                                                drawable = true;
                                                Back.Card.draw(i-1);
                                                drawable = false;
                                            }
                                        }
                                        else// if (i+1 < 0)
                                        {
                                            for (int k = 0; k < 4; k++)
                                            {
                                                drawable = true;
                                                Back.Card.draw(Player.player.length-1);
                                                drawable = false;
                                            }
                                        }
                                    }
                                } else if (!pickable) {
                                    Front.UI.clearConsole();
                                    System.out.print("Only input the card on your hand!\n");
                                    pickable = true;
                                } else {
                                    Front.UI.clearConsole();
                                    System.out.print("Only input card of the same color, number, or action!\n");
                                }
                            }
                            else
                            {
                                Front.UI.clearConsole();
                                System.out.print("You can only pick once per turn!\n");
                            }
                            Player.winCheck(i);
                        }
                        else if (Objects.equals(input, "3"))
                        {
                            if (drawable == true)
                            {
                                Back.Card.draw(i);
                                drawable = false;
                            }
                            else
                            {
                                Front.UI.clearConsole();
                                System.out.print("You can only drawn once per turn!\n");
                            }
                        }
                        else if (Objects.equals(input, "4"))
                        {
                            break;
                        }
                        else if (Objects.equals(input, "5"))
                        {
                            Front.UI.clearConsole();
                            displayLeaderboard();
                        }
                        else if (Objects.equals(input, "6"))
                        {
                            Front.UI.clearConsole();
                            resetLeaderboard();
                            System.out.print("Leaderboard reset successful!\n");
                        }
                        else if (Objects.equals(input, "9"))
                        {
                            Front.UI.clearConsole();
                            System.out.print("Are you sure you want to withdraw?: ");
                            input = Player.scan.next();
                            if (Objects.equals(input.charAt(0), 'Y') || Objects.equals(input.charAt(0), 'y')) {
                                Player.removePlayer(i);
                                Front.UI.clearConsole();
                                System.out.println("Player " + (i+1) + " has withdrawn from the game.");
                            }

                        }
                        else if (Objects.equals(input, "0"))
                        {
                            Front.UI.guide();
                        }
                        else
                        {
                            Front.UI.clearConsole();
                            System.out.print("Only input either 1,2,3,4, or 9!\n");
                        }
                    }
                }
            }while(gameOn);
        }
        catch (Exception e)
        {
            Front.UI.clearConsole();
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
