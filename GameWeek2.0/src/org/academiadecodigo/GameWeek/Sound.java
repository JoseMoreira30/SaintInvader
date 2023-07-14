package org.academiadecodigo.GameWeek;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Sound {

    private Clip clip;

    public Sound(String soundFilePath) {
        loadSound(soundFilePath);
    }

    private void loadSound(String soundFilePath) {
        try {
            /*File soundFile = new File(soundFilePath);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            DataLine.Info info = new DataLine.Info(Clip.class, audioIn.getFormat());*/

            URL audio = getClass().getResource(soundFilePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audio);
            clip = AudioSystem.getClip();
            //clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip != null) {
            clip.stop();
            clip.setFramePosition(0);
            clip.start();
        }
    }
}
