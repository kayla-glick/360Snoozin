package com.csci360.alarmclock;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class RadioThread extends Thread {
  
  private File station;
  private Player player;
  private boolean isPlaying;
  
  /**
   * Constructor for RadioThread objects.
   * @param station - InputStream object to play
   */
  public RadioThread(File station) {
    this.isPlaying = false;
    this.station = station;
  }
  
  /**
   * Same as java.lang.Thread start() method, except isPlaying is assigned to true
   */
  @Override
  public void start() {
    super.start();
    this.isPlaying = true;
  }
  
  /**
   * Plays the inputted InputStream file.
   * If Player throws a JavaLayerException, print a note to the console.
   * This is called by java.lang.Thread start() method.
   */
  @Override
  public void run() {
    try {
      while(this.isPlaying) {
        InputStream stationStream = new FileInputStream(this.station);
        this.player = new Player(stationStream);
        this.player.play();
      }
    } catch (IOException | JavaLayerException ex) {
      java.lang.System.out.println("Unable to play file. " + ex);
    }
  }

  /**
   * Calls java.lang.Thread interrupt() method and closes the player.
   * In combination with the start() method, the player cannot be played again
   * once interrupt() has been called once.
   */
  @Override
  public void interrupt() {
    super.interrupt();
    this.isPlaying = false;
    if(this.player != null)
      this.player.close();
  }
  
  /**
   * Returns whether or not the player is playing. Will return true if start()
   * has been called but interrupt() has not.
   * @return boolean - true if the player is playing.
   */
  public boolean getIsPlaying() {
    return this.isPlaying;
  }
}
