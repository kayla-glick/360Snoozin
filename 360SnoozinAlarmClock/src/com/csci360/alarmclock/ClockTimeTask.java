/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

import java.util.TimerTask;

public class ClockTimeTask extends TimerTask{
    
    private final System system;
    private final TimeDisplayUpdater timeDisplayUpdater;
    private final AlarmButtonUpdater alarmButtonUpdater;
    private final RadioButtonUpdater radioButtonUpdater;
    
    /**
     * Constructor
     * 
     * @param system The system object to be used to update the Clock time
     * @param timeDisplayUpdater The time display updater to be used to update the clock time display
     * @param alarmButtonUpdater The alarm button updater to be used to update an Alarm's hour, minute, and snooze buttons when sounding
     * @param radioButtonUpdater The radio button updater to be used to update the radio stop/play buttons and station display when an alarm sounds
     */
    public ClockTimeTask(System system, TimeDisplayUpdater timeDisplayUpdater, AlarmButtonUpdater alarmButtonUpdater, RadioButtonUpdater radioButtonUpdater) {
        this.system = system;
        this.timeDisplayUpdater = timeDisplayUpdater;
        this.alarmButtonUpdater = alarmButtonUpdater;
        this.radioButtonUpdater = radioButtonUpdater;
    }
    
    @Override
    /**
     * Method to run the task. On each cycle:
     * 1 minute is added to the clock,
     * The clock time display is updated, and 
     * The system attempts to sound alarms, updating corresponding
     */
    public void run() {
        this.system.addMinuteToClock();
        this.timeDisplayUpdater.updateTimeDisplay("clock", system.getClockTime());
        
        int[] alarmNumbersToPlay = this.system.attemptToSoundAlarms();
        this.alarmButtonUpdater.updateAlarmButtonsOnSounding(alarmNumbersToPlay);
        
        if ( this.system.anyAlarmsSounding() && this.system.getIsRadioPlaying() ) {
            this.radioButtonUpdater.updateRadioButtonsOnStop(this.system.getStation());
        }
    }
}
