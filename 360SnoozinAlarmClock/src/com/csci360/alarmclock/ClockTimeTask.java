/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

import java.time.temporal.ChronoUnit;
import java.util.TimerTask;

public class ClockTimeTask extends TimerTask{
    
    private Clock clock;
    
    /**
     * Constructor
     * 
     * @param clock The clock to increment
     */
    public ClockTimeTask(Clock clock){
        this.clock = clock;
    }

    /**
     * Method to update Clock's time
     */
    @Override
    public void run(){
        clock.setTime(clock.getTime().plus(1, ChronoUnit.MINUTES));
        
        for ( Alarm alarm : clock.getAlarms() ) {
            if ( alarm.getTime() != null && clock.getTime().equals(alarm.getTime()) ) {
                alarm.setIsSounding(true);
            }
        }
    }
}
