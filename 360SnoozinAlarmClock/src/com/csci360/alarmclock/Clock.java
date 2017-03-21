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
    //private String defaultTime = "12:00 pm";
    private Instant time;
    public final static ChronoUnit MINUTES = ChronoUnit.MINUTES;
    //method called plus, specify time units.
    private boolean use24HourFormat;
    private Alarm[] alarms = new Alarm[2];
    private Timer timer;
    
    public Clock(){
        
        time = Instant.now();
        use24HourFormat = false;
        alarms[0] = new Alarm();
        alarms[1] = new Alarm();
        timer = new Timer();
        setTime(time, timer); 

    }
    
    public Instant getTime(){
        return this.time;
    }
    
    public void setTime(Instant inTime, Timer timer){
        this.timer.cancel();
        long minute = 60*1000;
        this.time = inTime;
        TimerTask clockTimeTask = new ClockTimeTask(this);
        timer.scheduleAtFixedRate(clockTimeTask, 0, minute);
    }
        
    public boolean getUse24HourFormat(){
        return this.use24HourFormat;
    
    }
    public void setUse24HourFormat(boolean inUse24HourFormat){
        this.use24HourFormat = inUse24HourFormat;
    }
    
    public Alarm[] getAlarms(){
        return alarms;
    }

  
     
}
