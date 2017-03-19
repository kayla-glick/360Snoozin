/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

import java.io.IOException;
import java.io.InputStream;

public interface Radio {

  /**
   * Method to start playing the radio.
   */
  public void start();

  /**
   * Method to stop playing the Radio.
   */
  public abstract void interrupt();

  /**
   * Method to return the name of the current station.
   * @return String
   */
  public abstract String getStation();

  /**
   * Toggles to AM radio input if using FM radio input,
   * and toggles to FM radio input if using AM radio input.
   */
  public abstract void toggleAMFM();

  /**
   * Changes the current station.
   * @param direction - an integer to determine where to tune to.
   */
  public abstract void tune(int direction);

}
