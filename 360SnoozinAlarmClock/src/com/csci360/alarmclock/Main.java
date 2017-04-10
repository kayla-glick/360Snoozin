/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

import java.util.Timer;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.scene.Scene;
import javafx.scene.web.*;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

public class Main extends Application {
    
    private static final String APP_TITLE = "360Snoozin Dual-Alarm AM/FM Clock & Radio";
    
    private static final System system = new System();
    private static final Timer clockTimer = new Timer();
    
    private static TimeDisplayUpdater timeDisplayUpdater;
    private static AlarmButtonUpdater alarmButtonUpdater;
    private static RadioButtonUpdater radioButtonUpdater;
    
    // Clock DOM Elements
    private static Element clockTimeDisplay;
    private static Element clockAMPMDisplay;
    private static Element clockHourButton;
    private static Element toggleTimeFormatButton;
    private static Element clockMinuteButton;
    
    // Alarm1 DOM Elements
    private static Element alarm1StateButton;
    private static Element alarm1TimeDisplay;
    private static Element alarm1AMPMDisplay;
    private static Element alarm1HourButton;
    private static Element alarm1MinuteButton;
    private static Element alarm1SnoozeButton;
    
    // Alarm2 DOM Elements
    private static Element alarm2StateButton;
    private static Element alarm2TimeDisplay;
    private static Element alarm2AMPMDisplay;
    private static Element alarm2HourButton;
    private static Element alarm2MinuteButton;
    private static Element alarm2SnoozeButton;
    
    // Radio Elements
    private static Element radioStationDisplay;
    private static Element radioFrequencyButton;
    private static Element radioTuneDownButton;
    private static Element radioPlayButton;
    private static Element radioStopButton;
    private static Element radioTuneUpButton;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        getAndLoadEngine(primaryStage);
    }
    
    /**
     * Method to enable FirebugLite in the WebView for debugging
     * 
     * @param engine The current WebView engine
     */
    // Source: http://stackoverflow.com/questions/17387981/javafx-webview-webengine-firebuglite-or-some-other-debugger
    private static void enableFirebug(final WebEngine engine) {
        engine.documentProperty().addListener(new ChangeListener<Document>() {
          @Override public void changed(ObservableValue<? extends Document> prop, Document oldDoc, Document newDoc) {
            engine.executeScript("if (!document.getElementById('FirebugLite')){E = document['createElement' + 'NS'] && document.documentElement.namespaceURI;E = E ? document['createElement' + 'NS'](E, 'script') : document['createElement']('script');E['setAttribute']('id', 'FirebugLite');E['setAttribute']('src', 'https://getfirebug.com/' + 'firebug-lite.js' + '#startOpened');E['setAttribute']('FirebugLite', '4');(document['getElementsByTagName']('head')[0] || document['getElementsByTagName']('body')[0]).appendChild(E);E = new Image;E['setAttribute']('src', 'https://getfirebug.com/' + '#startOpened');}"); 
          }
        });
    }
    
    /**
     * Method to set up the view elements of the application, as
     * well as defining elements and initializing handlers.
     * 
     * @param primaryStage The window encapsulating the view
     */
    private static void getAndLoadEngine(Stage primaryStage) {
        WebView browser = new WebView();
        WebEngine engine = browser.getEngine();
        // Source: http://stackoverflow.com/questions/32678413/why-document-is-null-even-after-loadcontent-webview-javafx
        // Because the engine takes time to load the document, use a handler to wait until the document is loaded
        engine.getLoadWorker().stateProperty().addListener((observable, oldState, newState) -> {
            if (newState == State.SUCCEEDED) {
                setupDOMElements(engine.getDocument());
                setupActionListeners();
                
                timeDisplayUpdater = new TimeDisplayUpdater();
                timeDisplayUpdater.add("clock", clockTimeDisplay, clockAMPMDisplay);
                timeDisplayUpdater.add("alarm 1", alarm1TimeDisplay, alarm1AMPMDisplay);
                timeDisplayUpdater.add("alarm 2", alarm2TimeDisplay, alarm2AMPMDisplay);
                timeDisplayUpdater.updateTimeDisplay("clock", system.getClockTime());
                timeDisplayUpdater.updateTimeDisplay("alarm 1", system.getAlarmTime(1));
                timeDisplayUpdater.updateTimeDisplay("alarm 2", system.getAlarmTime(2));
                
                toggleTimeFormatButton.setTextContent(timeDisplayUpdater.getTimeFormat());
                
                alarmButtonUpdater = new AlarmButtonUpdater();
                alarmButtonUpdater.addAlarm("alarm 1", alarm1HourButton, alarm1MinuteButton, alarm1SnoozeButton);
                alarmButtonUpdater.addAlarm("alarm 2", alarm2HourButton, alarm2MinuteButton, alarm2SnoozeButton);
                
                radioButtonUpdater = new RadioButtonUpdater(radioPlayButton, radioStopButton, radioStationDisplay);
                
                ClockTimeTask clockTimeTask = new ClockTimeTask(system, timeDisplayUpdater, alarmButtonUpdater, radioButtonUpdater);
        
                clockTimer.scheduleAtFixedRate(clockTimeTask, 1000, 1000);
            }
        });
        engine.load(Main.class.getResource("main.html").toExternalForm());
//        enableFirebug(engine); // Comment out this line to remove the debugger from the view

        Scene scene = new Scene(browser, 1080, 720);
        primaryStage.setTitle(APP_TITLE);
        primaryStage.setMinHeight(720);
        primaryStage.setMinWidth(1080);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * Method to assign variables to dynamic elements on the page
     * 
     * @param dom The Document Object Model (DOM) within the WebView containing the HTML
     */
    private static void setupDOMElements(Document dom) {
        // Clock Elements
        clockTimeDisplay = dom.getElementById("clock-time-display");
        clockAMPMDisplay = dom.getElementById("clock-am-pm-display");
        clockHourButton = dom.getElementById("clock-hour-button");
        toggleTimeFormatButton = dom.getElementById("toggle-time-format-button");
        clockMinuteButton = dom.getElementById("clock-minute-button");
        
        // Alarm1 Elements
        alarm1StateButton = dom.getElementById("alarm-1-state-button");
        alarm1TimeDisplay = dom.getElementById("alarm-1-time-display");
        alarm1AMPMDisplay = dom.getElementById("alarm-1-am-pm-display");
        alarm1HourButton = dom.getElementById("alarm-1-hour-button");
        alarm1MinuteButton = dom.getElementById("alarm-1-minute-button");
        alarm1SnoozeButton = dom.getElementById("alarm-1-snooze-button");
    
        // Alarm2 Elements
        alarm2StateButton = dom.getElementById("alarm-2-state-button");
        alarm2TimeDisplay = dom.getElementById("alarm-2-time-display");
        alarm2AMPMDisplay = dom.getElementById("alarm-2-am-pm-display");
        alarm2HourButton = dom.getElementById("alarm-2-hour-button");
        alarm2MinuteButton = dom.getElementById("alarm-2-minute-button");
        alarm2SnoozeButton = dom.getElementById("alarm-2-snooze-button");
        
        // Radio Elements
        radioStationDisplay = dom.getElementById("radio-station-display");
        radioFrequencyButton = dom.getElementById("radio-frequency-button");
        radioTuneDownButton = dom.getElementById("radio-tune-down-button");
        radioPlayButton = dom.getElementById("radio-play-button");
        radioStopButton = dom.getElementById("radio-stop-button");
        radioTuneUpButton = dom.getElementById("radio-tune-up-button");
        
    }
    
    /**
     * Method to initialize all element listeners
     */
    private static void setupActionListeners() {
        // Clock Listeners
        clockHourButtonListener();
        toggleTimeFormatButtonListener();
        clockMinuteButtonListener();
        
        // Alarm Listeners
        alarmStateButtonListener();
        alarmHourButtonListener();
        alarmMinuteButtonListener();
        alarmSnoozeButtonListener();
        
        // Radio Listeners
        radioTuneDownButtonListener();
        radioPlayButtonListener();
        radioFrequencyButtonListener();
        radioTuneUpButtonListener();
    }

    /**
     * Method to listen on clock hour button and add 1 hour to the Clock
     */
    private static void clockHourButtonListener() {
        EventListener listener = new EventListener() {
            @Override
            public void handleEvent(Event ev) {
                
            }
        };
        ((EventTarget) clockHourButton).addEventListener("click", listener, false);
    }
    
    /**
     * Method to listen on toggle time format button and toggle the time format for all time displays
     */
    private static void toggleTimeFormatButtonListener() {
        EventListener listener = new EventListener() {
            @Override
            public void handleEvent(Event ev) {
                
            }
        };
        ((EventTarget) toggleTimeFormatButton).addEventListener("click", listener, false);
    }
    
    /**
     * Method to listen on clock minute button and add 1 minute to the Clock
     */
    private static void clockMinuteButtonListener() {
        EventListener listener = new EventListener() {
            @Override
            public void handleEvent(Event ev) {
                
            }
        };
        ((EventTarget) clockMinuteButton).addEventListener("click", listener, false);
    }
    
    /**
     * Method to listen on alarm state buttons and enable / disable the alarm
     */
    private static void alarmStateButtonListener() {
        EventListener listener = new EventListener() {
            @Override
            public void handleEvent(Event ev) {
                Element element = (Element) ev.getTarget();
                int alarmNumber = Integer.parseInt(element.getAttribute("data-num"));
                
                if ( element.getAttribute("data-state").equals("disabled") ) {
                    system.enableAlarm(alarmNumber);
                    element.setTextContent("Enabled");
                    element.setAttribute("class", element.getAttribute("class").replace("btn-danger", "btn-success"));
                    element.setAttribute("data-state", "enabled");
                    
                }
                else {
                    if ( system.isAlarmSounding(alarmNumber) ) {
                        system.disableAlarm(alarmNumber);
                    
                        alarmButtonUpdater.updateAlarmButtonsOnDisable(alarmNumber);   
                    }
                    element.setTextContent("Disabled");
                    element.setAttribute("class", element.getAttribute("class").replace("btn-success", "btn-danger"));
                    element.setAttribute("data-state", "disabled");
                }
            }
        };
        ((EventTarget) alarm1StateButton).addEventListener("click", listener, false);
        ((EventTarget) alarm2StateButton).addEventListener("click", listener, false);
    }
    
    /**
     * Method to listen on alarm hour button and add 1 hour to the specified Alarm
     */
    private static void alarmHourButtonListener() {
        EventListener listener = new EventListener() {
            @Override
            public void handleEvent(Event ev) {
                Element element = (Element) ev.getTarget();
                int alarmNumber = Integer.parseInt(element.getAttribute("data-num"));
                Element timeDisplay = alarmNumber == 1 ? alarm1TimeDisplay : alarm2TimeDisplay;
                Element amPMDisplay = alarmNumber == 1 ? alarm1AMPMDisplay : alarm2AMPMDisplay;
                        
                system.addHourToAlarm(alarmNumber);
                timeDisplayUpdater.updateTimeDisplay("alarm ".concat(Integer.toString(alarmNumber)), system.getAlarmTime(alarmNumber));
            }
        };
        ((EventTarget) alarm1HourButton).addEventListener("click", listener, false);
        ((EventTarget) alarm2HourButton).addEventListener("click", listener, false);
    }
    
    /**
     * Method to listen on alarm hour button and add 1 minute to the specified Alarm
     */
    private static void alarmMinuteButtonListener() {
        EventListener listener = new EventListener() {
            @Override
            public void handleEvent(Event ev) {
                Element element = (Element) ev.getTarget();
                int alarmNumber = Integer.parseInt(element.getAttribute("data-num"));
                Element timeDisplay = alarmNumber == 1 ? alarm1TimeDisplay : alarm2TimeDisplay;
                Element amPMDisplay = alarmNumber == 1 ? alarm1AMPMDisplay : alarm2AMPMDisplay;
                        
                system.addMinuteToAlarm(alarmNumber);
                timeDisplayUpdater.updateTimeDisplay("alarm ".concat(Integer.toString(alarmNumber)), system.getAlarmTime(alarmNumber));
            }
        };
        ((EventTarget) alarm1MinuteButton).addEventListener("click", listener, false);
        ((EventTarget) alarm2MinuteButton).addEventListener("click", listener, false);
    }
    
    /**
     * Method to listen on alarm snooze button and snooze the specified Alarm.
     * Also hides the snooze button and shows the alarm hour and minute buttons.
     */
    private static void alarmSnoozeButtonListener() {
        EventListener listener = new EventListener() {
            @Override
            public void handleEvent(Event ev) {                
                Element element = (Element) ev.getTarget();
                int alarmNumber = Integer.parseInt(element.getAttribute("data-num"));
                
                system.snoozeAlarm(alarmNumber);
                
                alarmButtonUpdater.updateAlarmButtonsOnSnooze(alarmNumber);
            }
        };
        ((EventTarget) alarm1SnoozeButton).addEventListener("click", listener, false);
        ((EventTarget) alarm2SnoozeButton).addEventListener("click", listener, false);
    }
    
    /**
     * Method to listen on radio tune down button and tune radio down
     */
    private static void radioTuneDownButtonListener() {
        EventListener listener = new EventListener() {
            @Override
            public void handleEvent(Event ev) {
                system.changeStation(-1);
                radioStationDisplay.setTextContent(system.getStation());
            }
        };
        ((EventTarget) radioTuneDownButton).addEventListener("click", listener, false);
    }

    /**
     * Method to listen on radio play button and play it / turn it off
     */
    private static void radioPlayButtonListener() {
        EventListener listener = new EventListener() {
            @Override
            public void handleEvent(Event ev) {
                if(system.getIsRadioPlaying()) {
                    system.turnOffRadio();
                    radioButtonUpdater.updateRadioButtonsOnStop(system.getStation());
                } else {
                    if ( !system.anyAlarmsSounding() ) {
                        system.playRadio();
                        radioButtonUpdater.updateRadioButtonsOnPlay(system.getStation());
                    }
                }
            }
        };
        ((EventTarget) radioPlayButton).addEventListener("click", listener, false);
        ((EventTarget) radioStopButton).addEventListener("click", listener, false);
    }

    /**
     * Method to listen on radio tune up button and tune radio up
     */
    private static void radioTuneUpButtonListener() {
        EventListener listener = new EventListener() {
            @Override
            public void handleEvent(Event ev) {
                system.changeStation(1);
                radioStationDisplay.setTextContent(system.getStation());
            }
        };
        ((EventTarget) radioTuneUpButton).addEventListener("click", listener, false);
    }

    /**
     * Method to listen on radio toggle frequency button
     */
    private static void radioFrequencyButtonListener() {
        EventListener listener = new EventListener() {
            @Override
            public void handleEvent(Event ev) {
                system.toggleAMFM();
                radioStationDisplay.setTextContent(system.getStation());
                if (system.getUseAM()) {
                  radioFrequencyButton.setAttribute("class", radioFrequencyButton.getAttribute("class").replace("btn-info", "btn-warning"));
                  radioFrequencyButton.setTextContent("AM");
                } else {
                  radioFrequencyButton.setAttribute("class", radioFrequencyButton.getAttribute("class").replace("btn-warning", "btn-info"));
                  radioFrequencyButton.setTextContent("FM");
                }
            }
        };
        ((EventTarget) radioFrequencyButton).addEventListener("click", listener, false);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
