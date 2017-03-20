/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

import java.time.Instant;

public class Clock {
    
    private Alarm[] alarms;
    
    public Clock() {
        alarms = new Alarm[2];
        alarms[0] = new Alarm();
        alarms[1] = new Alarm();
    }
    
    public Instant getTime() {
        return null;
    }
    
    public void setTime(Instant time) {
        
    }
    
    public boolean getUse24HourFormat() {
        return false;
    }
    
    public void setUse24HourFormat(boolean use24HourFormat) {
        
    }
    
    public Alarm[] getAlarms() {
        return alarms;
    }
}
