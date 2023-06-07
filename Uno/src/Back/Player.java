package Back;
import Front.UI;

import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class Player {
    public static String[][] player;
    public static String[][] temp;
    public static Scanner scan = new Scanner(System.in);
    public static int[] playerNumber;
    public static int totalPlayer;
    static int prevPlayerCounter = 0;
    static int tempInt = 0;

    //check for winning condition
    public static void winCheck(int i)
    {
        //reset counter
        tempInt = 0;

        //check number of card is the same amount as the size of the hand capacity
        for (int j = 0; j < Player.player[i].length-1; j++)
        {
            if (Objects.equals(Player.player[i][j], "  ") || Objects.equals(Player.player[i][j], null))
            {
                tempInt++;
            }
        }

        if (tempInt == Player.player[i].length-1 && i < Player.player.length-1)
        {
            System.out.println("Game over! Player " + (i + 1) + " is the winner");
            //get winner name
            System.out.print("Enter the username of the winner: ");
            Scanner Winner = new Scanner(System.in);
            String winnerName = Winner.nextLine();

            try {

                File winnerFile = new File("C:\\dev\\2023_2SE1_Project1_Group1\\Uno\\src\\Data\\win.txt");


                //if the file is not exist create new file and add the winner name into the newly created file, while preserving the previous data
                if (!winnerFile.exists()) {

                    winnerFile.createNewFile();
                    PrintWriter writer = new PrintWriter(new FileWriter(winnerFile));
                    writer.println(winnerName + ":" + 1);
                    writer.close();
                    System.out.println("Winner name and number of wins saved");
                } else {
                    //if the file is exist and the winner name is found it create a buffered reader to read the file and update the win number of the winner
                    BufferedReader reader = new BufferedReader(new FileReader(winnerFile));
                    String line;
                    boolean found = false;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(":");
                        String name = parts[0].trim();
                        int wins = Integer.parseInt(parts[1].trim());
                        if (name.equals(winnerName)) {
                            found = true;
                            wins++;
                            PrintWriter writer = new PrintWriter(new FileWriter(winnerFile));
                            reader = new BufferedReader(new FileReader(winnerFile));
                            while ((line = reader.readLine()) != null) {
                                if (line.startsWith(name)) {
                                    writer.println(name + ":" + wins);
                                } else {
                                    writer.println(line);
                                }
                            }
                            writer.close();
                            System.out.println("Winner number of wins Increased");
                            Game.gameOn = false;
                        }
                    }
                    reader.close();
                    //if the winner name not found, create and append new data
                    if (!found) {
                        PrintWriter writer = new PrintWriter(new FileWriter(winnerFile, true));
                        writer.println(winnerName + ":"+1);
                        writer.close();
                        System.out.println("Winner name and number of wins saved");
                    }
                }
                //catch the input output error
            } catch (IOException e) {
                System.out.println("error while saving" + e.getMessage());
            }
            Game.gameOn = false;
            //if the current player is the same player as the previous player 3 times in a row, the player is considered as the last player standing
            //and the execute the similar command as the previous if condition
        } else if (prevPlayerCounter == 3){
            System.out.println("Game over! Player " + (i + 1) + " is the last player standing");
            Game.gameOn = false;
            System.out.print("Enter the name of the winner: ");
            Scanner scan = new Scanner(System.in);
            String winnerName = scan.nextLine();
            try {

                File winnerFile = new File("C:\\dev\\2023_2SE1_Project1_Group1\\Uno\\src\\Data\\win.txt");


                if (!winnerFile.exists())
                {
                    winnerFile.createNewFile();
                    PrintWriter writer = new PrintWriter(new FileWriter(winnerFile));
                    writer.println(winnerName + ":" + 1);
                    writer.close();
                    System.out.println("Winner name and number of wins saved");
                } else
                {
                    BufferedReader reader = new BufferedReader(new FileReader(winnerFile));
                    String line;
                    boolean found = false;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(":");
                        String name = parts[0].trim();
                        int wins = Integer.parseInt(parts[1].trim());
                        if (name.equals(winnerName)) {
                            found = true;
                            wins++;
                            PrintWriter writer = new PrintWriter(new FileWriter(winnerFile, true)); // menggunakan mode append
                            BufferedReader reader2 = new BufferedReader(new FileReader(winnerFile));
                            while ((line = reader2.readLine()) != null) {
                                if (line.startsWith(name)) {
                                    writer.println(name + ":" + wins);
                                    System.out.println("Winner number of wins increased");
                                } else {
                                    writer.println(line);
                                }
                            }
                            writer.close();
                            reader2.close();
                            break;
                        }
                    }
                    reader.close();

                    if (!found) {
                        PrintWriter writer = new PrintWriter(new FileWriter(winnerFile, true));
                        writer.println(winnerName + ":1");
                        writer.close();
                        System.out.println("New winner added to leaderboard");
                    }
                }
            } catch (IOException e) {
                System.out.println("error while saving" + e.getMessage());
            }
            Game.gameOn = false;
        }
        tempInt = 0;
    }

    //remove player method,
    public static void removePlayer(int index)
    {
        //this method supposed to be using an array swapping method but then changed into modify the String value into "  " instead
        /*
        String[][] tempArr = player;
        player = new String[tempArr.length-1][tempArr[tempArr.length-1].length];
        for (int j = 0; j < index; j++) {
            player[j] = tempArr[j];
        }
        for (int j = index+1; j < tempArr.length; j++) {
            player[j-1] = tempArr[j];
        }

         */
        tempInt = 0;
        for (int i = 0; i < player[index].length; i++)
        {
            player[index][i] = "  ";
            if (Player.player[index][i] == "  " || Player.player[index][i] == null)
            {
                tempInt++;
            }
        }
        //if the player hand is empty, reduce the total player counter
        if (tempInt == Player.player[index].length)
        {
            totalPlayer -= 1;
        }
        tempInt = 0;
    }



    //initil method of the card class which will be executed the first time the program runs
    //it assign 7 randomly generated card from the file "Card.txt" data into each player hand
    //and save the player.length value into "totalPlayer" variable(because the array length is still the same)
    public static void init()
    {
        System.out.print("Number of player: ");
        player = new String[scan.nextInt()][7];
        totalPlayer = player.length;
        Front.UI.clearConsole();
    }
}

//initial swapping idea
//tempArr = arr
//arr = new arr[i][tempArr.length+1]
//arr = tempArr

//tempArr = arr
//arr = new arr[tempArr.length-1]
//arr = tempArr