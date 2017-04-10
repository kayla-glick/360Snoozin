/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

import org.w3c.dom.Element;

public class RadioButtonUpdater {
    
    private Element playButton;
    private Element stopButton;
    private Element buttonContainer;
    private Element stationDisplay;
    
    /**
     * Constructor
     * 
     * @param playButton The radio play button
     * @param stopButton The radio stop button
     * @param stationDisplay The container with the radio station name
     */
    public RadioButtonUpdater(Element playButton, Element stopButton, Element stationDisplay) {
        this.playButton = playButton;
        this.stopButton = stopButton;
        this.buttonContainer = (Element) stopButton.getParentNode();
        this.stationDisplay = stationDisplay;
        
        this.stopButton.setAttribute("class", this.stopButton.getAttribute("class").replace(" hidden", ""));
        this.buttonContainer.removeChild(stopButton);
    }
    
    /**
     * Method to update the radio content to show when the radio is playing
     * 
     * @param station The station name
     */
    public void updateRadioButtonsOnPlay(String station) {
        this.stationDisplay.setTextContent(station);
        this.buttonContainer.removeChild(playButton);
        this.buttonContainer.appendChild(stopButton);
    }
    
    /**
     * Method to update the radio content to show when the radio is stopped
     * 
     * @param station The station name
     */
    public void updateRadioButtonsOnStop(String station) {
        this.stationDisplay.setTextContent(station);
        this.buttonContainer.appendChild(playButton);
        this.buttonContainer.removeChild(stopButton);
    }
}
