/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

import java.util.HashMap;
import org.w3c.dom.Element;

public class AlarmButtonUpdater {
    
    private HashMap<String, Element[][]> buttonGroups = new HashMap<String, Element[][]>();
    
    /**
     * Method to add an alarm's hour, minute, and snooze button GUI elements to a HashMap storage
     * to be updated.
     * 
     * @param identifier The identifier to be stored as a Key in the HashMap
     * @param hourButton The alarm hour button
     * @param minuteButton The alarm minute button
     * @param snoozeButton The alarm snooze button
     */
    public void addAlarm(String identifier, Element hourButton, Element minuteButton, Element snoozeButton) {
        Element snoozeContainer = (Element) snoozeButton.getParentNode();
        Element hourContainer = (Element) hourButton.getParentNode();
        Element minuteContainer = (Element) minuteButton.getParentNode();
        
        // Because Elements are not thread safe, the attributes do not seem to persist when updating from the ClockTimeTask.
        // Instead, we can remove the snooze button from the DOM and remove the class before storage.
        snoozeContainer.removeChild(snoozeButton);
        snoozeButton.setAttribute("class", snoozeButton.getAttribute("class").replace(" hidden", ""));
        
        this.buttonGroups.put(identifier, new Element[][]{
            new Element[]{hourButton, hourContainer},
            new Element[]{minuteButton, minuteContainer},
            new Element[]{snoozeButton, snoozeContainer}
                
        });
    }
    
    public void updateAlarmButtonsOnSounding(int[] alarmNumbersToPlay) {
        for ( int i=0; i<alarmNumbersToPlay.length; i++ ) { 
            if ( alarmNumbersToPlay[i] != 0 ) {
                Element[][] elements = this.buttonGroups.get("alarm ".concat(Integer.toString(alarmNumbersToPlay[i])));
                Element hourButton = elements[0][0];
                Element minuteButton = elements[1][0];
                Element snoozeButton = elements[2][0];
                Element snoozeContainer = elements[2][1];
                
                hourButton.getParentNode().removeChild(hourButton);
                minuteButton.getParentNode().removeChild(minuteButton);
                snoozeContainer.appendChild(snoozeButton);
            }
        }
    }
    
    /**
     * Method to update the Alarm buttons on snooze, showing the hour and minute buttons, and hiding the snooze button
     * 
     * @param n An integer representing the nth Alarm (1 or 2)
     */
    public void updateAlarmButtonsOnSnooze(int n) {
        Element[][] elements = this.buttonGroups.get("alarm ".concat(Integer.toString(n)));
        Element hourButton = elements[0][0];
        Element hourContainer = elements[0][1];
        Element minuteButton = elements[1][0];
        Element minuteContainer = elements[1][1];
        Element snoozeButton = elements[2][0];
        Element snoozeContainer = elements[2][1];
        
        hourContainer.appendChild(hourButton);
        minuteContainer.appendChild(minuteButton);
        snoozeButton.getParentNode().removeChild(snoozeButton);
    }
    
    /**
     * Method to alias updateAlarmButtonsOnSnooze
     * When an Alarm is disabled, the GUI functionality is the same.
     * 
     * @param n An integer representing the nth Alarm (1 or 2)
     */
    public void updateAlarmButtonsOnDisable(int n) {
        updateAlarmButtonsOnSnooze(n);
    }
}
