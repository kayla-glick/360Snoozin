/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Timer;

public class Alarm {
    public final static int SNOOZE_INTERVAL = 10;
    public final static ChronoUnit SNOOZE_UNIT = ChronoUnit.MINUTES;
    private final static long ALARM_DELAY = 0;
    private final static long ALARM_INTERVAL = 500;
    private final static PlayAlarmTask ALARM_TASK = new PlayAlarmTask();
    
    private Timer alarmTimer;
    private Instant time;
    private Instant snoozeTime;
    private boolean isActive;
    private boolean isSounding;
    
    /**
     * Constructor
     * 
     */
    public Alarm() {
        this.time = null;
        this.snoozeTime = null;
        this.isActive = false;
        this.isSounding = false;
    }
    
    /**
     * Method that returns Alarm's time attribute
     * 
     * @return Alarm's time attribute
     */
    public Instant getTime() {
        return this.time;
    }
    
    /**
     * Method that assigns Alarm's time and snoozeTime attributes
     * 
     * @param time The time to be set
     */
    public void setTime(Instant time) {
        this.time = time;
        this.setSnoozeTime(time);
    }
    
    /**
     * Method that returns Alarm's snoozeTime attribute
     * 
     * @return Alarm's snoozeTime attribute
     */
    public Instant getSnoozeTime() {
        return this.snoozeTime;
    }
    
    /**
     * Method that assigns Alarm's snoozeTime attribute
     * 
     * @param snoozeTime The time to be set
     */
    private void setSnoozeTime(Instant snoozeTime) {
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
        
        if ( isSounding ) {
            this.alarmTimer = new Timer();
            this.alarmTimer.scheduleAtFixedRate(Alarm.ALARM_TASK, Alarm.ALARM_DELAY, Alarm.ALARM_INTERVAL);
        }
        else {
            this.alarmTimer.cancel();
        }
    }
    
    /**
     * Method that handles snoozing the Alarm
     */
    public void snooze() {
        this.setSnoozeTime(this.snoozeTime.plus(Alarm.SNOOZE_INTERVAL, Alarm.SNOOZE_UNIT));
        this.setIsSounding(false);
    }
}
