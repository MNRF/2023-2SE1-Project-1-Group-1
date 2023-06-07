public class Main
{
    public static void main(String[] args)
    {
        Uno.game();
    }
}

/*
else if(reversed)
                {
                    pickable = true;
                    if(i != 0)
                    {
                        i--;
                    }else
                    {
                        i = (short)(Player.player.length-1);
                    }
                    clearConsole();
                    while(true) {
                        topCard(Card);
                        if(wildColor != ' ')
                        {
                            System.out.print("Color = " + color(wildColor) + wildColor + White + "\n");
                        }
                        System.out.print("Player " + (i + 1) + " turn\n");
                        System.out.print("1. Show card\n");
                        System.out.print("2. Pick card\n");
                        System.out.print("3. Draw card\n");
                        System.out.print("4. Next turn\n");
                        System.out.print("9. Withdraw\n");
                        System.out.print("0. Guide\n");
                        System.out.print("Option: ");
                        input = Player.scan.next();

                        if (Objects.equals(input, "1"))
                        {
                            clearConsole();
                            showCard(Player.player, i);
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

                                if (pickable && ((firstTurn && input.charAt(0) != 'W') || (input.charAt(0) == Card.charAt(0) || input.charAt(0) == Card.charAt(1) || (Card.charAt(0) == 'W' && input.charAt(0) == wildColor)))) {
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
                                        reversed = false;
                                    }

                                    if (pickable) {
                                        pickable = false;
                                    }
                                    clearConsole();
                                } else if (pickable && input.charAt(0) == 'W') {
                                    for (int j = 0; j < Player.player[i].length; j++) {
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
                                                clearConsole();
                                                System.out.print("Only input either Red,Green,Blue,or Yellow!\n");
                                        }
                                    }
                                    if (input.charAt(1) == 'R')
                                    {
                                        reversed = false;
                                    }

                                    if (pickable) {
                                        pickable = false;
                                    }
                                } else if (!pickable) {
                                    clearConsole();
                                    System.out.print("Only input the card on your hand!\n");
                                } else {
                                    clearConsole();
                                    System.out.print("Only input card of the same color, number, or action!\n");
                                }
                            }
                            else
                            {
                                clearConsole();
                                System.out.print("You can only pick once per turn!\n");
                            }
                        }
                        else if (Objects.equals(input, "3"))
                        {

                        }
                        else if (Objects.equals(input, "4"))
                        {
                            break;
                        }
                        else if (Objects.equals(input, "9"))
                        {

                        }
                        else if (Objects.equals(input, "0"))
                        {
                            guide();
                        }
                        else
                        {
                            clearConsole();
                            System.out.print("Only input either 1,2,3,4, or 9!\n");
                        }
                    }
                }
 */