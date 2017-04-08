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
    
    public void add(String identifier, Element timeDisplay, Element amPMDisplay) {
        this.displays.put(identifier, new Element[]{timeDisplay, amPMDisplay});
    }
    
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
