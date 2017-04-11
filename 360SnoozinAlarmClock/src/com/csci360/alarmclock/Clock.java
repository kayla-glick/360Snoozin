/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

import java.time.LocalTime;

public class Clock {
    
    private final Alarm[] alarms = new Alarm[2];
    
    private LocalTime time;
    
    /**
     * Constructor
     */
    public Clock() {
        this.time = this.time = LocalTime.now().withHour(0).withMinute(0);
        this.alarms[0] = new Alarm();
        this.alarms[1] = new Alarm();
    }
    
    /**
     * Method to add an hour to the clock's time
     */
    public void addHour() {
        this.setTime(this.time.plusHours(1));
    }
    
    /**
     * Method to add a minute to the clock's time
     */
    public void addMinute() {
        this.setTime(this.time.plusMinutes(1));
    }
    
    /**
     * Method that returns the Clock's time
     * 
     * @return The Clock's time attribute
     */
    public LocalTime getTime(){
        return this.time;
    }
    
    /**
     * Method that assigns the Clock's time
     * 
     * @param time The time to be set
     */
    public void setTime(LocalTime time) {
        this.time = time;
    }
    
    /**
     * Method to add 1 minute to the specified Alarm's time
     * 
     * @param n An integer representing the nth Alarm (1 or 2)
     */
    public void addHourToAlarm(int n) {
        this.alarms[n-1].addHour();
    }
    
    /**
     * Method to add 1 minute to the specified Alarm's time
     * 
     * @param n An integer representing the nth Alarm (1 or 2)
     */
    public void addMinuteToAlarm(int n) {
        this.alarms[n-1].addMinute();
    }
    
    /**
     * Method to return the specified Alarm's time
     * 
     * @param n An integer representing the nth Alarm (1 or 2)
     * @return The alarm's time attribute
     */
    public LocalTime getAlarmTime(int n) {
        return this.alarms[n-1].getTime();
    }
    
    /**
     * Method to enable the specified Alarm
     * 
     * @param n An integer representing the nth Alarm (1 or 2)
     */
    public void enableAlarm(int n) {
        this.alarms[n-1].setIsActive(true);
    }
    
    /**
     * Method to disable the specified Alarm
     * 
     * @param n An integer representing the nth Alarm (1 or 2)
     */
    public void disableAlarm(int n) {
        this.alarms[n-1].setIsActive(false);
    }
    
    /**
     * Method to snooze the specified Alarm.
     * 
     * @param n An integer representing the nth Alarm (1 or 2)
     */
    public void snoozeAlarm(int n) {
        if ( alarms[n-1].getIsSounding() ) {
            alarms[n-1].snooze(this.getTime());
        }
    }
    
    /**
     * Method to check whether or not the specified Alarm is sounding
     * 
     * @param n An integer representing the nth Alarm (1 or 2)
     * @return Whether the alarm is sounding or not
     */
    public boolean isAlarmSounding(int n) {
        return this.alarms[n-1].getIsSounding();
    }
    
    /**
     * Method to check whether or not any Alarms are sounding
     * 
     * @return Whether or not any alarms are sounding
     */
    public boolean anyAlarmsSounding() {
        boolean rtnval = false;
        
        for ( Alarm alarm : this.alarms ) {
            if ( alarm.getIsSounding() ) {
                rtnval = true;
            }
        }
        
        return rtnval;
    }
    
    /**
     * Method to determine whether to sound Alarms.
     * 
     * @return An array of integers containing the integers n representing the nth Alarm (1 or 2) or defaulting to 0
     */
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

