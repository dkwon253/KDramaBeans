package com.kdramabeans.game;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class BGM {
    /*
        FIELDS
     */
    private Clip clip;// what allows us to actually play music

    public BGM() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        createClip(new File("../KDramaBeans/songs/goblin.wav").toURI().toString());
    }

    public void playSong() {
        clip.setFramePosition(0);
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stopSong(){
        clip.stop();
        clip.close();
    }

    public void createClip(String url) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        clip = AudioSystem.getClip();
        clip.open(createAudioStream(url));
    }

    //Helper Methods
    private AudioInputStream createAudioStream(String url) throws IOException, UnsupportedAudioFileException {
        URL songURL = new URL(url);
        return AudioSystem.getAudioInputStream(songURL);
    }

}
