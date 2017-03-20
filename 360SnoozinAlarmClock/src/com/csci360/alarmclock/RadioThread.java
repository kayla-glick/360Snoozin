/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

import java.io.InputStream;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class RadioThread extends Thread {
  
  private InputStream station;
  private Player player;
  private boolean isPlaying;
  
  /**
   * Constructor for RadioThread objects.
   * @param station - InputStream object to play
   */
  public RadioThread(InputStream station) {
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
        this.player = new Player(this.station);
        this.player.play();
      }
    } catch (JavaLayerException ex) {
      java.lang.System.out.println("Unable to play file.");
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
