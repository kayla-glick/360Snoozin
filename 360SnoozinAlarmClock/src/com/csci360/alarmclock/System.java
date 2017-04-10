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
    
    /**
     * Method to get the System's clock's time
     * 
     * @return The clock's time
     */
    public LocalTime getClockTime() {
        return this.clock.getTime();
    }
    
    /**
     * Method to add an hour to the specified Alarm's time
     * 
     * @param n An integer representing the nth Alarm (1 or 2)
     */
    public void addHourToAlarm(int n) {
        this.clock.addHourToAlarm(n);
    }
    
    /**
     * Method to add a minute to the specified Alarm's time
     * 
     * @param n An integer representing the nth Alarm (1 or 2)
     */
    public void addMinuteToAlarm(int n) {
        this.clock.addMinuteToAlarm(n);
    }
    
    /**
     * Method to get the specified Alarm's time as a LocalDate
     * 
     * @param n An integer representing the nth Alarm (1 or 2)
     * @return The Alarm's time
     */
    public LocalTime getAlarmTime(int n) {
        return this.clock.getAlarmTime(n);
    }
    
    /**
     * Method to enable the specified Alarm
     * 
     * @param n An integer representing the nth Alarm (1 or 2)
     */
    public void enableAlarm(int n) {
        this.clock.enableAlarm(n);
    }
    
    /**
     * Method to disable the specified Alarm
     * 
     * @param n An integer representing the nth Alarm (1 or 2)
     */
    public void disableAlarm(int n) {
        this.clock.disableAlarm(n);
    }
    
    /**
     * Method to snooze the specified Alarm
     * 
     * @param n An integer representing the nth Alarm (1 or 2)
     */
    public void snoozeAlarm(int n) {
        this.clock.snoozeAlarm(n);
    }
    
    /**
     * Method to check whether or not the specified Alarm is sounding
     * 
     * @param n An integer representing the nth Alarm (1 or 2)
     * @return Whether the alarm is sounding or not
     */
    public boolean isAlarmSounding(int n) {
        return this.clock.isAlarmSounding(n);
    }
    
    /**
     * Method to check whether or not any Alarms are sounding
     * 
     * @return Whether or not any alarms are sounding
     */
    public boolean anyAlarmsSounding() {
        return this.clock.anyAlarmsSounding();
    }
    
    /**
     * Method to determine whether to sound alarms. If alarms are sounded, the radio is turned off
     * 
     * @return An array of integers containing the integers n representing the nth Alarm (1 or 2) or defaulting to 0
     */
    public int[] attemptToSoundAlarms() {
        int[] alarmNumbersToPlay = this.clock.attemptToSoundAlarms();
        
        if ( this.clock.anyAlarmsSounding() ) {
            this.turnOffRadio();
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
