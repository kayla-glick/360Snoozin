/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

import java.io.IOException;

public interface Radio {

  /**
   * Method to start playing the radio.
   * @throws java.io.IOException - exception thrown if input could not be loaded.
   */
  public void playRadio() throws IOException;

  /**
   * Method to stop playing the Radio.
   */
  public void stopRadio();

  /**
   * Return the name of the current station.
   * @return String
   */
  public String getStation();

  /**
   * Returns whether or not the radio is currently playing.
   * @return boolean - true if the radio is playing
   */
  public boolean isPlaying();

  /**
   * Toggles to AM radio input if using FM radio input,
   * and toggles to FM radio input if using AM radio input.
   * @throws java.io.IOException - exception thrown if input could not be loaded.
   */
  public void toggleAMFM() throws IOException;

  /**
   * Changes the current station.
   * @param direction - an integer to determine where to tune to.
   * @throws java.io.IOException - exception thrown if input could not be loaded.
   */
  public void tune(int direction) throws IOException;
}
