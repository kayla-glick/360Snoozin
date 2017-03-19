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
    private final static int SNOOZE_INTERVAL = 10;
    private final static ChronoUnit SNOOZE_UNIT = ChronoUnit.MINUTES;
    private final static long ALARM_DELAY = 0;
    private final static long ALARM_INTERVAL = 500;
    private final static PlayAlarmTask ALARM_TASK = new PlayAlarmTask();
    
    private Timer alarmTimer;
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
        
        if ( isSounding ) {
            this.alarmTimer = new Timer();
            this.alarmTimer.scheduleAtFixedRate(Alarm.ALARM_TASK, Alarm.ALARM_DELAY, Alarm.ALARM_INTERVAL);
        }
        else {
            this.alarmTimer.cancel();
        }
    }
    
    public void snooze() {
        this.setSnoozeTime(this.snoozeTime.plus(Alarm.SNOOZE_INTERVAL, Alarm.SNOOZE_UNIT));
        this.isSounding = false;
        this.alarmTimer.cancel();
    }
}
