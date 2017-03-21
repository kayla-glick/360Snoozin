/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;
import java.awt.Toolkit;
import java.util.TimerTask;

public class PlayAlarmTask extends TimerTask {
    
    private Alarm alarm;
    
    /**
     * Constructor
     * 
     * @param alarm The alarm being played
     */
    public PlayAlarmTask(Alarm alarm) {
        this.alarm = alarm;
    }
    
    /**
     * Method that overrides TimerTask's run method and plays the Alarm tone
     */
    @Override
    public void run() {
        if ( this.alarm.getIsSounding() ) {
            Toolkit.getDefaultToolkit().beep();
        }
    }
}
