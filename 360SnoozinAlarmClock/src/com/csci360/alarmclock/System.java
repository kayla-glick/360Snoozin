/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

import java.io.IOException;
import java.time.LocalTime;

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
    
    public void addHourToClock() {
        this.clock.addHour();
    }
    
    public void addMinuteToClock() {
        this.clock.addMinute();
    }
    
    public LocalTime getClockTime() {
        return this.clock.getTime();
    }
    
    /**
     * Method to add an hour to the specified Alarm's time
     * 
     * @param n The alarm number
     */
    public void addHourToAlarm(int n) {
        this.clock.addHourToAlarm(n);
    }
    
    /**
     * Method to add a minute to the specified Alarm's time
     * 
     * @param n The alarm number
     */
    public void addMinuteToAlarm(int n) {
        this.clock.addMinuteToAlarm(n);
    }
    
    /**
     * Method to get the specified Alarm's time as a LocalDate
     * 
     * @param n The alarm number
     * @return The Alarm's time as a LocalDateTime object
     */
    public LocalTime getAlarmTime(int n) {
        return this.clock.getAlarmTime(n);
    }
    
    /**
     * Method to enable an alarm
     * 
     * @param n An integer representing the nth Alarm (1 or 2)
     */
    public void enableAlarm(int n) {
        this.clock.enableAlarm(n);
    }
    
    /**
     * Method to disable an alarm
     * 
     * @param n An integer representing the nth Alarm (1 or 2)
     */
    public void disableAlarm(int n) {
        this.clock.disableAlarm(n);
    }
    
    public void snoozeAlarm(int n) {
        this.clock.snoozeAlarm(n);
    }
    
    public int[] attemptToSoundAlarms() {
        int[] alarmNumbersToPlay = this.clock.attemptToSoundAlarms();
        
        for ( int i : alarmNumbersToPlay ) {
            if ( i != 0 ) {
                this.turnOffRadio();
            }
        }
        
        return alarmNumbersToPlay;
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
    public boolean getUseAM() {
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
}
