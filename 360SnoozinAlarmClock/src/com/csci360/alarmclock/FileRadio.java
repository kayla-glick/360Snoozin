/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class FileRadio extends Thread implements Radio {

  private static final Radio INSTANCE = new FileRadio();
  private static final String FILE_DIRECTORY = "src/com/csci360/alarmclock/AudioFiles/";
  private static final File[] AM_FILES = new File(FILE_DIRECTORY + "AM/").listFiles();
  private static final File[] FM_FILES = new File(FILE_DIRECTORY + "FM/").listFiles();

  private int currentAMFile, currentFMFile;
  private boolean useAM;
  private boolean unstarted;
  private boolean interrupted;
  private Player player;

  /**
   * Private constructor for FileRadio objects following the singleton pattern.s
   */
  private FileRadio() {
    super();
    
    interrupted = false;
    this.unstarted = true;
    this.useAM = false;
    currentAMFile = 0; currentFMFile = 0;
  }

  /**
   * Returns an instance of FileRadio as a Radio object following the Singleton pattern.
   * This also ensures that the FileRadio cannot be terminated or destroyed
   * during the duration of an application.
   * @return Radio - the only FileRadio object
   */
  public static Radio getInstance() {
    return INSTANCE;
  }

  /**
   * Plays the current music file. Unlike java.lang.Thread, start()
   * may be called multiple times. If called more than once, only the current audio file
   * will be played.
   */
  @Override
  public void start() {
    if(this.unstarted) {
      this.unstarted = false;
      interrupted = false;
      super.start();
    } else if(interrupted) {
      this.playCurrentAudioFile();
    }
  }

  /**
   * This is called by the superclass when it is started.
   */
  @Override
  public void run() {
    this.playCurrentAudioFile();
  }

  /**
   * Closes the Player if it has been instantiated. If the Player is playing
   * any audio, it is immediately stopped.
   * Audio can be turned on again again by calling start() without creating a
   * new FileRadio object, unlike java.lang.Thread.
   */
  @Override
  public void interrupt() {
    if(this.player != null) {
      this.interrupted = true;
      this.player.close();
    }
  }

  /**
   * Returns the name of the current InputStream (the current "station").
   * In this case, it is the name of the file associated with the current InputStream.
   * @return String
   */
  @Override
  public String getStation() {
    return useAM ? AM_FILES[currentAMFile].getName() : FM_FILES[currentFMFile].getName();
  }

  /**
   * Toggles to AM radio input if using FM radio input,
   * and toggles to FM radio input if using AM radio input.
   * This method changes which file directory to index.
   */
  @Override
  public void toggleAMFM() {
    this.useAM = !this.useAM;
  }

  /**
   * Tunes the radio up or down (simply goes to the next file in the directory
   * or the previous one to simulate tuning a radio). If there are no more files in the
   * directory, stay on the current file.
   * If a Player object exists, close it to change files if it is playing.
   * @param direction - positive integer to tune up, zero or negative to tune down.
   */
  @Override
  public void tune(int direction) {
    int n = (direction > 0) ? 1 : -1;
    int newFrequency = useAM ? currentAMFile + n : currentFMFile + n;
    File[] currentFileSystem = useAM ? AM_FILES : FM_FILES;
    if( (newFrequency < currentFileSystem.length) && (newFrequency >= 0) ) {
      if(useAM) {
        this.currentAMFile = newFrequency;
      } else {
        this.currentFMFile = newFrequency;
      }
      if(this.player != null)
        this.player.close();
    }
  }

  /**
   * Plays the current Audio File based on the current File[] index
   * (currentAMFile or currentFMFile).
   * The file plays on a loop unless this thread is interrupted.
   */
  private void playCurrentAudioFile() {
    try {
      while(!this.interrupted) {
        this.player = new Player(this.getCurrentStream());
        this.player.play();
      }
    }
    catch(IOException e) {
      java.lang.System.out.println("There was a problem in loading the current station.");
    }
    catch(JavaLayerException e) {
      java.lang.System.out.println("There was a problem in playing the current station.");
    }
  }

  /**
   * Returns the InputStream for the current AM/FM file to play.
   * This method can throw an IOException if there is some problem loading a file.
   * @return InputStream
   * @throws java.io.IOException
   */
  private InputStream getCurrentStream() throws IOException {
    return useAM ? new FileInputStream(AM_FILES[currentAMFile]) : new FileInputStream(FM_FILES[currentFMFile]);
  }

}