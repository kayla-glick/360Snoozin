/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import org.w3c.dom.Element;

public class TimeDisplayUpdater {
    
    private static final DateTimeFormatter FORMATTER_12_HR = DateTimeFormatter.ofPattern("hh:mm a");
    private static final DateTimeFormatter FORMATTER_24_HR = DateTimeFormatter.ofPattern("HH:mm");
    
    private HashMap<String, Element[]> displays = new HashMap<String, Element[]>();
            
    private boolean use24HourFormat = false;
    
    /**
     * Method to add a time display with a key identifier
     * 
     * @param identifier The key to be used in the HashMap
     * @param timeDisplay The time display element
     * @param amPMDisplay The ampm display element
     */
    public void add(String identifier, Element timeDisplay, Element amPMDisplay) {
        this.displays.put(identifier, new Element[]{timeDisplay, amPMDisplay});
    }
    
    /**
     * Method to get the current time format as a string
     * 
     * @return A string representing the current time format
     */
    public String getTimeFormat() {
        String rtnval;
        
        if ( this.use24HourFormat ) {
            rtnval = "24 Hour Format";
        }
        else {
            rtnval = "12 Hour Format";
        }
        
        return rtnval;
    }
    
    /**
     * Method to toggle the time format between 12 and 24 hours
     */
    public void toggleUse24HourFormat() {
        this.use24HourFormat = !this.use24HourFormat;
        
        if ( this.use24HourFormat ) {
            for ( String key : this.displays.keySet() ) {
                Element[] elements = this.displays.get(key);
                Element timeDisplay = elements[0];
                Element amPMDisplay = elements[1];
                String time = timeDisplay.getTextContent().trim().concat(" ").concat(amPMDisplay.getTextContent().trim());
                
                timeDisplay.setTextContent(LocalTime.parse(time, FORMATTER_12_HR).format(FORMATTER_24_HR));
                amPMDisplay.setAttribute("class", amPMDisplay.getAttribute("class").concat(" hidden"));
            }
        }
        else {
            for ( String key : this.displays.keySet() ) {
                Element[] elements = this.displays.get(key);
                Element timeDisplay = elements[0];
                Element amPMDisplay = elements[1];
                String time = timeDisplay.getTextContent();
                
                String[] splitAMPMTime = LocalTime.parse(time, FORMATTER_24_HR).format(FORMATTER_12_HR).split("\\s");
                
                timeDisplay.setTextContent(splitAMPMTime[0]);
                amPMDisplay.setTextContent(splitAMPMTime[1]);
                
                amPMDisplay.setAttribute("class", amPMDisplay.getAttribute("class").replace(" hidden", ""));
            }
        }
    }
    
    /**
     * Method to update the specified time display to the specified time
     * 
     * @param identifier The key to get the elements from the hashmap
     * @param time The time to set
     */
    public void updateTimeDisplay(String identifier, LocalTime time) {
        Element[] elements = this.displays.get(identifier);
        Element timeDisplay = elements[0];
        Element amPMDisplay = elements[1];
        
        if ( this.use24HourFormat ) {
            timeDisplay.setTextContent(time.format(FORMATTER_24_HR));
        }
        else {
            String[] splitAMPMTime = time.format(FORMATTER_12_HR).split("\\s");
            
            timeDisplay.setTextContent(splitAMPMTime[0]);
            amPMDisplay.setTextContent(splitAMPMTime[1]);
        }
    }
}
