package Front;

public class Color
{
    public static String Red = "\033[31m";
    public static String Green = "\033[32m";
    public static String Blue = "\033[34m";
    public static String Yellow = "\033[33m";
    public static String White = "\033[97m";
    public static String Color = "\033[97m";


    //polymorphism (Overloading for String and char data type)
    //check what the color of the card are based on the first index of the string

    public static String color(String Card)
    {
        if(Card != null && Card.charAt(0) == 'R')
        {
            Color = Red;
        }
        else if (Card != null && Card.charAt(0) == 'G')
        {
            Color = Green;
        }
        else if (Card != null && Card.charAt(0) == 'B')
        {
            Color = Blue;
        }
        else if (Card != null && Card.charAt(0) == 'Y')
        {
            Color = Yellow;
        }
        else
        {
            Color = White;
        }
        return Color;
    }

    public static String color(char wildColor)
    {
        if(wildColor == 'R')
        {
            Color = Red;
        }
        else if (wildColor == 'G')
        {
            Color = Green;
        }
        else if (wildColor == 'B')
        {
            Color = Blue;
        }
        else if (wildColor == 'Y')
        {
            Color = Yellow;
        }
        else
        {
            Color = White;
        }
        return Color;
    }
}
