/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

import java.awt.Toolkit;
import java.util.TimerTask;

public class PlayAlarmTask extends TimerTask {

    @Override
    public void run() {
        Toolkit.getDefaultToolkit().beep();
    }
}
