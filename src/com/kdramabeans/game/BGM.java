package com.kdramabeans.game;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class BGM {
    /*
        FIELDS
     */
    public Clip clip;// what allows us to actually play music

    public BGM() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        Thread musicThread = new Thread(() -> {
            try {
                createClip("https://kathyle.dev/songs/goblin.wav");
//                URL url = BGM.class.getClass().getResource("/resources/music/goblin.wav");
//                AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
//                clip = AudioSystem.getClip();
//                clip.open(audioStream);
            }catch(Exception e){
                System.out.println(e);
            }
        });
        musicThread.start();

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
