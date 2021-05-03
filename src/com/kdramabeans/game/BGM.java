package com.kdramabeans.game;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;


public class BGM {
    /*
        FIELDS
     */
    private Clip clip;// what allows us to actually play music

    public BGM() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        createClip("https://kathyle.dev/songs/goblin.wav");
    }

    public void playSong() {
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void pauseSong(){
        clip.stop();
    }

    public boolean isPlaying(){
        return clip.isRunning();
    }

    public void changeSong(String url){
        pauseSong();
        try{
            createClip(url);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void createClip(String url) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        clip = AudioSystem.getClip();
        clip.open(createAudioStream(url));
    }

    //Helper Methods
    private AudioInputStream createAudioStream(String url) throws IOException, UnsupportedAudioFileException {
        URL songURL = new URL(url);
        //AudioSystem.getAudioInputStream(this.getClass().getResource("NameOfFile.wav"));
        return AudioSystem.getAudioInputStream(songURL);
    }

}
