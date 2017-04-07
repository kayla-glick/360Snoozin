/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

import java.io.IOException;
import java.time.Instant;

public class System {
    protected Clock clock;
    protected FileRadio radio;
    
    /**
     * Constructor
     */
    public System() {
        this.clock = new Clock();
        this.radio = new FileRadio();
    }
    
    /**
     * Method to set the Clock's time attribute
     * 
     * @param time The time to be set
     */
    public void setClockTime(String time) {  
        this.clock.setTime(Instant.parse(time));
    }
    
    /**
     * Method to toggle Clock's time format
     */
    public void toggleTimeFormat() {
        this.clock.setUse24HourFormat(!this.clock.getUse24HourFormat());
    }
    
    /**
     * Method to set the Alarm's time
     * 
     * @param time The time to set
     * @param n  An integer representing the nth Alarm (1 or 2)
     */
    public void setAlarmTime(String time, int n) {
        this.clock.getAlarms()[n-1].setTime(Instant.parse(time));
    }
    
    /**
     * Method to enable an alarm
     * 
     * @param n An integer representing the nth Alarm (1 or 2)
     */
    public void enableAlarm(int n) {
        this.clock.getAlarms()[n-1].setIsActive(true);
    }
    
    /**
     * Method to disable an alarm
     * 
     * @param n An integer representing the nth Alarm (1 or 2)
     */
    public void disableAlarm(int n) {
        this.clock.getAlarms()[n-1].setIsActive(false);
    }
    
    /**
     * Method to snooze any alarms which are currently playing
     */
    public void snoozeAlarms() {
        Alarm[] alarms = this.clock.getAlarms();
        
        for ( Alarm alarm : alarms ) {
            if ( alarm.getIsSounding() ) {
                alarm.snooze();
            }
        }
    }
    
    /**
     * Method to play the radio
     */
    public void playRadio() {
        try {
            this.radio.playRadio();
        }
        catch ( IOException e ) {
            java.lang.System.out.println("Placeholder for return to GUI indicating error");
        }
    }
    
    /**
     * Method to turn off the radio
     */
    public void turnOffRadio() {
       this.radio.stopRadio();
    }

    /**
     * Returns whether or not the radio is playing.
     * @return boolean - true if the radio is currently playing
     */
    public boolean getIsRadioPlaying() {
      return this.radio.isPlaying();
    }
    
    /**
     * Method to toggle whether the radio uses AM or FM signal
     */
    public void toggleAMFM() {
        try {
            this.radio.toggleAMFM();
        }
        catch ( IOException e ) {
            java.lang.System.out.println("Placeholder for return to GUI indicating error");
        }
    }
    
    /**
     * Method to return whether the radio is using AM or FM frequencies.
     * @return boolean - true if using AM
     */
    public boolean getFrequency() {
      return this.radio.getUseAM();
    }
    
    /**
     * Method to change the radio's current station
     * 
     * @param direction The direction to change the station (0 or 1)
     */
    public void changeStation(int direction) {
        if (this.radio.isPlaying()) {
            try {
                this.radio.tune(direction);
            }
            catch ( IOException e ) {
                java.lang.System.out.println("Placeholder for return to GUI indicating error");
            }
        }
    }

    /**
     * Method to get the current station name.
     * @return String - the name of the current station
     */
    public String getStation() {
      return this.radio.getStation();
    }
//    // Return and param types may need to be changed
//    public void adjustRadioVolume(int direction) {
//        
//    }
}
