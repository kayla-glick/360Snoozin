/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;


/**
 *
 * @author Giff
 */

import java.time.temporal.ChronoUnit;
import java.util.TimerTask;
import java.time.Instant;

public class ClockTimeTask extends TimerTask{
    Clock clock;
    
    public ClockTimeTask(Clock clock){
        this.clock = clock;
    }
    
    
    @Override
    public void run(){
        clock.setTime(clock.getTime().plus(1, ChronoUnit.MINUTES));
    }
    
}
