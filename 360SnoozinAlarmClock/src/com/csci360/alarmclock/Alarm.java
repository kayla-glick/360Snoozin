/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class Alarm {
    public final static int SNOOZE_INTERVAL = 10;
    public final static ChronoUnit SNOOZE_UNIT = ChronoUnit.MINUTES;

    private Instant time;
    private Instant snoozeTime;
    private boolean isActive;
    private boolean isSounding;
    
    public Alarm() {
        this.time = null;
        this.snoozeTime = null;
        this.isActive = false;
        this.isSounding = false;
    }
    
    public Instant getTime() {
        return time;
    }
    
    public void setTime(Instant time) {
        this.time = time;
        this.setSnoozeTime(time);
    }
    
    public Instant getSnoozeTime() {
        return snoozeTime;
    }
    
    private void setSnoozeTime(Instant snoozeTime) {
        this.snoozeTime = snoozeTime;
    }
    
    public boolean getIsActive() {
        return isActive;
    }
    
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
        
        if ( isActive ) {
            this.setSnoozeTime(this.time);
        }
    }
    
    public boolean getIsSounding() {
        return isSounding;
    }
    
    public void setIsSounding(boolean isSounding) {
        this.isSounding = isSounding;
    }
    
    public void snooze() {
        Instant newSnoozeTime = this.snoozeTime.plus(SNOOZE_INTERVAL, SNOOZE_UNIT);
        
        this.setSnoozeTime(newSnoozeTime);
        this.isSounding = false;
    }
}
