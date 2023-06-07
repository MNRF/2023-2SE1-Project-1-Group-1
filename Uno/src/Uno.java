import Back.Card;
import Back.Game;
import Back.Player;
import Front.Music;
import Front.UI;

public class Uno {
    public static void game()
    {
        UI.clearConsole();
        Music.start();
        Player.init();
        Card.init();
        Game.start();
    }
}