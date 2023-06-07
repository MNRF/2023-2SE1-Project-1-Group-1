package Front;

import javax.sound.sampled.*;
import java.io.*;

public class Music {
    public static void start() {
        //start the music
        try {
            File file = new File("C:\\dev\\2023_2SE1_Project1_Group1\\Uno\\src\\Front\\1.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            //loop
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

    }
}
