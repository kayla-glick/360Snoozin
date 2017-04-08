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
    
    public ClockTimeTask(System system, TimeDisplayUpdater timeDisplayUpdater, AlarmButtonUpdater alarmButtonUpdater) {
        this.system = system;
        this.timeDisplayUpdater = timeDisplayUpdater;
        this.alarmButtonUpdater = alarmButtonUpdater;
    }

    @Override
    public void run() {
        this.system.addMinuteToClock();
        this.timeDisplayUpdater.updateTimeDisplay("clock", system.getClockTime());
        this.alarmButtonUpdater.updateAlarmButtons(this.system.attemptToSoundAlarms());
    }
}
