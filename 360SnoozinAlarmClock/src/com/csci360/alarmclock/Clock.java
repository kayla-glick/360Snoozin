/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

import java.time.LocalTime;

public class Clock {
    
    private LocalTime time;
    private boolean use24HourFormat;
    private Alarm[] alarms = new Alarm[2];
    
    /**
     * Constructor
     */
    public Clock() {
        this.time = this.time = LocalTime.now().withHour(0).withMinute(0);
        this.use24HourFormat = false;
        this.alarms[0] = new Alarm();
        this.alarms[1] = new Alarm();
    }
    
    /**
     * Method that returns Clock's time attribute
     * 
     * @return Clock's time attribute
     */
    public LocalTime getTime(){
        return this.time;
    }
    
    /**
     * Method that assigns Clock's time attribute
     * 
     * @param time The time to be set
     */
    public void setTime(LocalTime time) {
        this.time = time;
    }
    
    /**
     * Method to add an hour to the time
     */
    public void addHour() {
        this.setTime(this.time.plusHours(1));
    }
    
    /**
     * Method to add a minute to the time
     */
    public void addMinute() {
        this.setTime(this.time.plusMinutes(1));
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
    
    /**
     * Method to add a minute to the specified Alarm's time
     * 
     * @param n The alarm number
     */
    public void addHourToAlarm(int n) {
        this.alarms[n-1].addHour();
    }
    
    /**
     * Method to add a minute to the specified Alarm's time
     * 
     * @param n The alarm number
     */
    public void addMinuteToAlarm(int n) {
        this.alarms[n-1].addMinute();
    }
    
    /**
     * Method to return the specified Alarm's time
     * 
     * @param n The alarm number
     * @return The alarm's time as LocalDateTime
     */
    public LocalTime getAlarmTime(int n) {
        return this.alarms[n-1].getTime();
    }
    
    public void enableAlarm(int n) {
        this.alarms[n-1].setIsActive(true);
    }
    
    public void disableAlarm(int n) {
        this.alarms[n-1].setIsActive(false);
    }
    
    public void snoozeAlarm(int n) {
        if ( alarms[n-1].getIsSounding() ) {
            alarms[n-1].snooze(this.getTime());
        }
    }
    
    public int[] attemptToSoundAlarms() {
        int[] alarmNumbersToPlay = new int[this.alarms.length];
        
        for ( int i=0; i<this.alarms.length; i++ ) {
            if ( alarms[i].getIsActive() && alarms[i].getSnoozeTime() != null && this.getTime().getHour() == alarms[i].getSnoozeTime().getHour() && this.getTime().getMinute() == alarms[i].getSnoozeTime().getMinute() ) {
                alarms[i].setIsSounding(true);
                alarmNumbersToPlay[i] = i+1;
            }
        }
        
        return alarmNumbersToPlay;
    }
}
