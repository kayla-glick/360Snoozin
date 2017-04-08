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
    
    private final static long ALARM_DELAY = 500;
    private final static long ALARM_INTERVAL = 500;
    
    private Timer alarmTimer;
    private PlayAlarmTask alarmTask;
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
        this.alarmTimer = new Timer();
        this.alarmTask = new PlayAlarmTask(this);
        
        this.alarmTimer.scheduleAtFixedRate(this.alarmTask, Alarm.ALARM_DELAY, Alarm.ALARM_INTERVAL);
    }
    
    /**
     * Method that returns Alarm's time attribute
     * 
     * @return Alarm's time attribute
     */
    public LocalTime getTime() {
        return this.time;
    }
    
    /**
     * Method that assigns Alarm's time and snoozeTime attributes
     * 
     * @param time The time to be set
     */
    public void setTime(LocalTime time) {
        this.time = time;
        this.setSnoozeTime(time);
    }
    
    /**
     * Method that returns Alarm's snoozeTime attribute
     * 
     * @return Alarm's snoozeTime attribute
     */
    public LocalTime getSnoozeTime() {
        return this.snoozeTime;
    }
    
    /**
     * Method that assigns Alarm's snoozeTime attribute
     * 
     * @param snoozeTime The time to be set
     */
    private void setSnoozeTime(LocalTime snoozeTime) {
        this.snoozeTime = snoozeTime;
    }
    
    /**
     * Method that returns Alarm's isActive attribute
     * 
     * @return Alarm's isActive attribute
     */
    public boolean getIsActive() {
        return this.isActive;
    }
    
    /**
     * Method that handles "enabling" and "disabling" the Alarm
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
     * Method that assigns Alarm's isSounding attribute
     * 
     * @return Alarm's isSounding attribute
     */
    public boolean getIsSounding() {
        return this.isSounding;
    }
    
    /**
     * Method that handles "playing" and "turning off" the Alarm's sound
     * 
     * @param isSounding The value to be set
     */
    public void setIsSounding(boolean isSounding) {
        this.isSounding = isSounding;
    }
    
    /**
     * Method that handles snoozing the Alarm
     */
    public void snooze(LocalTime currentTime) {
        this.setSnoozeTime(currentTime.plus(Alarm.SNOOZE_INTERVAL, ChronoUnit.MINUTES));
        this.setIsSounding(false);
    }
    
    /**
     * Method to add an hour to the time
     */
    public void addHour() {
        LocalTime newTime = this.time.plusHours(1);
        
        this.setTime(newTime);
    }
    
    /**
     * Method to add a minute to the time
     */
    public void addMinute() {
        LocalTime newTime = this.time.plusMinutes(1);
        
        this.setTime(newTime);
    }
}
