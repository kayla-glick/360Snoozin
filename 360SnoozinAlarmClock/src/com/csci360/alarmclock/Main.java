/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

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
    private static Element radioTuneDownButton;
    private static Element radioPlayButton;
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
            }
        });
        engine.load(Main.class.getResource("main.html").toExternalForm());
        enableFirebug(engine); // Comment out this line to remove the debugger from the view

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
//        radioStationDisplay = dom.getElementById("radio-station-display");
//        radioTuneDownButton = dom.getElementById("radio-tune-down-button");
//        radioPlayButton = dom.getElementById("radio-play-button");
//        radioTuneUpButton = dom.getElementById("radio-tune-up-button");
        
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
//        radioTuneDownButtonListener();
//        radioPlayButtonListener();
//        radioTuneUpButtonListener();
    }

    /**
     * Method to listen on clock hour button and add an hour
     */
    private static void clockHourButtonListener() {
        EventListener listener = new EventListener() {
            @Override
            public void handleEvent(Event ev) {
                java.lang.System.out.println("Add an hour to clock");
            }
        };
        ((EventTarget) clockHourButton).addEventListener("click", listener, false);
    }
    
    /**
     * Method to listen on toggle time format button and toggle format
     */
    private static void toggleTimeFormatButtonListener() {
        EventListener listener = new EventListener() {
            @Override
            public void handleEvent(Event ev) {
                java.lang.System.out.println("Toggle time format");
            }
        };
        ((EventTarget) toggleTimeFormatButton).addEventListener("click", listener, false);
    }
    
    /**
     * Method to listen on clock minute button and add a minute
     */
    private static void clockMinuteButtonListener() {
        EventListener listener = new EventListener() {
            @Override
            public void handleEvent(Event ev) {
                java.lang.System.out.println("Add a minute to clock");
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
                java.lang.System.out.println("Enable/Disable alarm 1 or 2");
            }
        };
        ((EventTarget) alarm1StateButton).addEventListener("click", listener, false);
        ((EventTarget) alarm2StateButton).addEventListener("click", listener, false);
    }
    
    /**
     * Method to listen on alarm hour button and add an hour
     */
    private static void alarmHourButtonListener() {
        EventListener listener = new EventListener() {
            @Override
            public void handleEvent(Event ev) {
                java.lang.System.out.println("Add an hour to alarm 1 or 2");
            }
        };
        ((EventTarget) alarm1HourButton).addEventListener("click", listener, false);
        ((EventTarget) alarm2HourButton).addEventListener("click", listener, false);
    }
    
    /**
     * Method to listen on alarm minute button and add a minute
     */
    private static void alarmMinuteButtonListener() {
        EventListener listener = new EventListener() {
            @Override
            public void handleEvent(Event ev) {
                java.lang.System.out.println("Add a minute to alarm 1 or 2");
            }
        };
        ((EventTarget) alarm1MinuteButton).addEventListener("click", listener, false);
        ((EventTarget) alarm2MinuteButton).addEventListener("click", listener, false);
    }
    
    /**
     * Method to listen on alarm snooze button and snooze alarm
     */
    private static void alarmSnoozeButtonListener() {
        EventListener listener = new EventListener() {
            @Override
            public void handleEvent(Event ev) {
                java.lang.System.out.println("Snooze alarm 1 or 2");
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
                java.lang.System.out.println("Tune radio down");
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
                java.lang.System.out.println("Play / Turn off radio");
            }
        };
        ((EventTarget) radioPlayButton).addEventListener("click", listener, false);
    }
    
    /**
     * Method to listen on radio tune up button and tune radio up
     */
    private static void radioTuneUpButtonListener() {
        EventListener listener = new EventListener() {
            @Override
            public void handleEvent(Event ev) {
                java.lang.System.out.println("Tune radio up");
            }
        };
        ((EventTarget) radioTuneUpButton).addEventListener("click", listener, false);
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
