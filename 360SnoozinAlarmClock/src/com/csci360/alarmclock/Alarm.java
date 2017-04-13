/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Timer;

public class Alarm {
    public final static int SNOOZE_INTERVAL = 10;
    
    private LocalTime time;
    private LocalTime snoozeTime;
    private boolean isActive;
    private boolean isSounding;
    
    /**
     * Constructor
     * 
     */
    public Alarm() {
        this.time = LocalTime.now().withHour(0).withMinute(0);
        this.snoozeTime = this.time;
        this.isActive = false;
        this.isSounding = false;
    }
    
    /**
     * Method to add 1 hour to the Alarm's time
     */
    public void addHour() {
        LocalTime newTime = this.time.plusHours(1);
        
        this.setTime(newTime);
    }
    
    /**
     * Method to add 1 minute to the Alarm's time
     */
    public void addMinute() {
        LocalTime newTime = this.time.plusMinutes(1);
        
        this.setTime(newTime);
    }
    
    /**
     * Method that returns Alarm's time
     * 
     * @return The Alarm's time attribute
     */
    public LocalTime getTime() {
        return this.time;
    }
    
    /**
     * Method that assigns the Alarm's time and snooze time
     * 
     * @param time The time to be set
     */
    public void setTime(LocalTime time) {
        this.time = time;
        this.setSnoozeTime(time);
    }
    
    /**
     * Method that returns the Alarm's snooze time
     * 
     * @return The Alarm's snoozeTime attribute
     */
    public LocalTime getSnoozeTime() {
        return this.snoozeTime;
    }
    
    /**
     * Method that assigns the Alarm's snooze time
     * 
     * @param snoozeTime The time to be set
     */
    private void setSnoozeTime(LocalTime snoozeTime) {
        this.snoozeTime = snoozeTime;
    }
    
    /**
     * Method that returns whether or not the Alarm is active
     * 
     * @return The Alarm's isActive attribute
     */
    public boolean getIsActive() {
        return this.isActive;
    }
    
    /**
     * Method that enables or disables the alarm. If true, the alarm is enabled. If false, the alarm is disabled
     * 
     * @param isActive The value to be set
     */
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
        
        if ( isActive ) {
            this.setSnoozeTime(this.time);
        }
        else {
            this.setIsSounding(isActive);
        }
    }
    
    /**
     * Method that returns whether or not the Alarm is sounding
     * 
     * @return The Alarm's isSounding attribute
     */
    public boolean getIsSounding() {
        return this.isSounding;
    }
    
    /**
     * Method that sounds or turns off the Alarm. If true, the Alarm sounds. If false, the Alarm is turned off.
     * 
     * @param isSounding The value to be set
     */
    public void setIsSounding(boolean isSounding) {
        this.isSounding = isSounding;
    }
    
    /**
     * Method that snoozes the Alarm, adding 10 minutes to the time provided.
     * 
     * @param currentTime The current time, provided by a Clock
     */
    public void snooze(LocalTime currentTime) {
        this.setSnoozeTime(currentTime.plus(Alarm.SNOOZE_INTERVAL, ChronoUnit.MINUTES));
        this.setIsSounding(false);
    }
}
