/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

import java.util.Timer;
import java.util.TimerTask;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class Clock {
    
    public final static ChronoUnit MINUTES = ChronoUnit.MINUTES;
    private final static long MINUTE = 60000;
    
    private Instant time;
    private boolean use24HourFormat;
    private Alarm[] alarms = new Alarm[2];
    private Timer timer;
    private TimerTask clockTimeTask;
    
    /**
     * Constructor
     */
    public Clock() {
        this.time = Instant.now();
        this.use24HourFormat = false;
        this.alarms[0] = new Alarm();
        this.alarms[1] = new Alarm();
        this.timer = new Timer();
        this.clockTimeTask = new ClockTimeTask(this);
        
        this.timer.scheduleAtFixedRate(clockTimeTask, 0, Clock.MINUTE);
    }
    
    /**
     * Method that returns Clock's time attribute
     * 
     * @return Clock's time attribute
     */
    public Instant getTime(){
        return this.time;
    }
    
    /**
     * Method that assigns Clock's time attribute
     * 
     * @param time The time to be set
     */
    public void setTime(Instant time) {
        this.time = time;
    }
    
    /**
     * Method that returns Clock's use24HourFormat attribute
     * 
     * @return Clock's use24HourFormat attibute
     */
    public boolean getUse24HourFormat(){
        return this.use24HourFormat;
    
    }
    
    /**
     * Method that assigns Clock's use24HourFormat attribute
     * 
     * @param use24HourFormat The value to be set
     */
    public void setUse24HourFormat(boolean use24HourFormat){
        this.use24HourFormat = use24HourFormat;
    }
    
    /**
     * Method that returns Clock's alarms
     * 
     * @return Clock's alarms
     */
    public Alarm[] getAlarms(){
        return alarms;
    }
}
