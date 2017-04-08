/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

import java.util.HashMap;
import org.w3c.dom.Element;

public class AlarmButtonUpdater {
    
    private HashMap<String, Element[]> buttonGroups = new HashMap<String, Element[]>();
    
    public void addAlarm(String identifier, Element hourButton, Element minuteButton, Element snoozeButton) {
        Element snoozeContainer = (Element) snoozeButton.getParentNode();
        snoozeContainer.removeChild(snoozeButton);
        snoozeButton.setAttribute("class", snoozeButton.getAttribute("class").replace(" hidden", ""));
        
        this.buttonGroups.put(identifier, new Element[]{hourButton, minuteButton, snoozeButton, snoozeContainer});
    }
    
    public void updateAlarmButtons(int[] alarmNumbersToPlay) {
        for ( int i=0; i<alarmNumbersToPlay.length; i++ ) { 
            if ( alarmNumbersToPlay[i] != 0 ) {
                Element[] buttons = this.buttonGroups.get("alarm ".concat(Integer.toString(alarmNumbersToPlay[i])));
                Element hourButton = buttons[0];
                Element minuteButton = buttons[1];
                Element snoozeButton = buttons[2];
                Element snoozeContainer = buttons[3];
                
                hourButton.getParentNode().removeChild(hourButton);
                minuteButton.getParentNode().removeChild(minuteButton);
                snoozeContainer.appendChild(snoozeButton);
            }
        }
    }
}
