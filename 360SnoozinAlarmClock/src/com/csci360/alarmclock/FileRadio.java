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

import java.util.concurrent.TimeUnit; //only used in main

public class FileRadio implements Radio {

  private static final String FILE_DIRECTORY = "src"+ File.separator +"AudioFiles" + File.separator;
  private static final File[] AM_FILES = new File(FILE_DIRECTORY + "AM" + File.separator).listFiles();
  private static final File[] FM_FILES = new File(FILE_DIRECTORY + "FM" + File.separator).listFiles();

  private int currentAMFile, currentFMFile;
  private boolean useAM;
  private RadioThread radio;

  /**
   * Constructor for FileRadio objects.
   * AM_FILES and FM_FILES are indexed at 0 on instantiation and begins on FM
   * radio files.
   */
  public FileRadio() {
    this.useAM = false;
    currentAMFile = 0;
    currentFMFile = 0;
  }

  /**
   * Plays the current music file. To do this, radio is assigned a new RadioThread
   * with the current AM/FM InputStream, and plays the InputStream in the RadioThread.
   * @throws java.io.IOException - There was a problem loading the InputStream from a File.
   */
  @Override
  public void playRadio() throws IOException {
    this.resetRadio(true);
  }

  /**
   * Interrupts the current RadioThread, and sets the attribute to null to
   * delete the RadioThread.
   */
  @Override
  public void stopRadio() {
    if(this.radio != null) {
      this.radio.interrupt();
      this.radio = null;
    }
  }

  /**
   * Returns the name of the current InputStream (the current "station").
   * In this case, it is the name of the file associated with the current InputStream
   * without its extention. If the radio is not playing, there is no current station,
   * so an empty string is returned.
   * @return String
   */
  @Override
  public String getStation() {
    String station = "";
    if(this.isPlaying()) {
      String nameWithExtention = useAM ? AM_FILES[currentAMFile].getName() : FM_FILES[currentFMFile].getName();
      station = nameWithExtention.substring(0, nameWithExtention.lastIndexOf("."));
    }
    return station;
  }

  /**
   * Toggles to AM radio input if using FM radio input,
   * and toggles to FM radio input if using AM radio input.
   * This method changes which file directory to index, and resets the RadioThread.
   * @throws java.io.IOException - There was a problem loading the InputStream from a File.
   */
  @Override
  public void toggleAMFM() throws IOException {
    this.useAM = !this.useAM;
    this.resetRadio(false);
  }

  /**
   * Tunes the radio up or down (simply goes to the next file in the directory
   * or the previous one to simulate tuning a radio). If there are no more files in the
   * directory, it remains on the current file and does not interfere with
   * the current RadioThread.
   * If the new index is valid, it resets the RadioThread.
   * @param direction - positive integer to tune up, zero or negative to tune down.
   * @throws java.io.IOException - There was a problem loading the InputStream from a File.
   */
  @Override
  public void tune(int direction) throws IOException {
    int n = (direction > 0) ? 1 : -1;
    int newFrequency = useAM ? currentAMFile + n : currentFMFile + n;
    File[] currentFileSystem = useAM ? AM_FILES : FM_FILES;
    if( (newFrequency < currentFileSystem.length) && (newFrequency >= 0) ) {
      if(useAM) {
        this.currentAMFile = newFrequency;
      } else {
        this.currentFMFile = newFrequency;
      }
      this.resetRadio(false);
    }
  }

  /**
   * Getter for useAM. Returns whether or not this FileRadio is using AM or FM
   * radio files.
   * @return boolean - true is using AM files, false for FM files.
   */
  public boolean getUseAM() {
    return this.useAM;
  }
  
  /**
   * Returns whether or not the RadioThread is playing or not.
   * @return boolean - true if the RadioThread is playing
   */
  @Override
  public boolean isPlaying() {
    return (this.radio == null) ? false : this.radio.getIsPlaying();
  }

  /**
   * Returns whether or not the current RadioThread is null.
   * Useful for testing/debugging without allowing access to the RadioThread.
   * @return boolean - true if the current RadioThread is null
   */
  public boolean isRadioThreadNull() {
    return this.radio == null;
  }

  /**
   * Resets the current RadioThread by interrupting it, and creating a new one.
   * To force the new RadioThread to play, set forceStart to true (useful in
   * reducing duplicate code).
   * @throws java.io.IOException - There was a problem loading the InputStream from a File.
   */
  private void resetRadio(boolean forceStart) throws IOException{
    boolean radioIsPlaying;
    radioIsPlaying = false;
    if(this.radio != null) {
      radioIsPlaying = this.radio.getIsPlaying();
      this.stopRadio();
    }
    this.radio = new RadioThread(this.getCurrentStream());
    if(radioIsPlaying || forceStart) this.radio.start();
  }

  /**
   * Returns the InputStream based on useAM and currentAMFile or currentFMFile.
   * @return InputStream
   * @throws java.io.IOException - There was a problem loading the InputStream from a File.
   */
  private InputStream getCurrentStream() throws IOException {
    return useAM ? new FileInputStream(AM_FILES[currentAMFile]) : new FileInputStream(FM_FILES[currentFMFile]);
  }

  /**
   * Main method to demonstrate FileRadio objects typed as Radio objects.
   * @param args
   */
  public static void main(String[] args) {
    try {
      Radio radio = new FileRadio();
      radio.playRadio();
        java.lang.System.out.println("Playing " + radio.getStation()); //FM0
      TimeUnit.SECONDS.sleep(3);

      radio.tune(0);
        java.lang.System.out.println("Still playing " + radio.getStation()); //FM0
      TimeUnit.SECONDS.sleep(3);

      radio.toggleAMFM();
        java.lang.System.out.println("Now playing " + radio.getStation()); //AM0
      TimeUnit.SECONDS.sleep(3);

      radio.tune(1);
      radio.tune(10);
        java.lang.System.out.println("Now playing " + radio.getStation()); //AM2
      TimeUnit.SECONDS.sleep(3);

      radio.stopRadio();
        java.lang.System.out.print("Turned off the radio for a bit."); //nothing
      TimeUnit.SECONDS.sleep(2);

      radio.toggleAMFM();
        java.lang.System.out.print("."); //nothing
      TimeUnit.SECONDS.sleep(2);

      radio.tune(1);
        java.lang.System.out.println("."); //nothing
      TimeUnit.SECONDS.sleep(2);

      radio.playRadio();
        java.lang.System.out.println("Playing " + radio.getStation()); //FM1
      TimeUnit.SECONDS.sleep(3);

      radio.tune(0);
        java.lang.System.out.println("Now playing " + radio.getStation()); //FM0
      TimeUnit.SECONDS.sleep(5);

      radio.stopRadio();
        java.lang.System.out.println("Done!");
    }
    catch(IOException e) {
      java.lang.System.out.println(e + ": There was an error loading the input.");
    }
    catch(InterruptedException e) {
      java.lang.System.out.println(e + ": There was probably an error with sleep.");
    }
  }
}
