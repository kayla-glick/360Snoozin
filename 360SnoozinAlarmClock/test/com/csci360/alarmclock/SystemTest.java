/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

import java.time.Instant;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author kyleglick
 */
public class SystemTest {
    protected System system;
    
    public SystemTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        java.lang.System.out.println("Begin SystemTest");
    }
    
    @AfterClass
    public static void tearDownClass() {
        java.lang.System.out.println("End SystemTest");
    }
    
    @Before
    public void setUp() {
        system = new System();
    }
    
    @After
    public void tearDown() {
        system = null;
    }
    
    /**
     * Method setClockTime(String time)
     */
    @Test
    public void testSetClockTimeSetsClockTime() {
        String time = "2000-01-01T12:30:00Z";
                
        system.setClockTime(time);
        
        java.lang.System.out.println("---Testing setClockTime(String time)");
        java.lang.System.out.println("------Should set the clock's time");
        assertTrue(system.clock.getTime().equals(Instant.parse(time)));
    }
    
    /**
     * Method toggleTimeFormat()
     */
    @Test
    public void testToggleTimeFormatSetsClockTimeFormat() {        
        system.toggleTimeFormat();
        
        java.lang.System.out.println("---Testing toggleTimeFormat()");
        java.lang.System.out.println("------Should set the clock's time format");
        assertFalse(system.clock.getUse24HourFormat());
    }
    
    /**
     * setAlarmTime(String time, int n)
     */
    @Test
    public void testSetAlarmTimeSetsAlarmTime() {
        String time = "2000-01-01T12:30:00Z";
                
        system.setAlarmTime(time, 1);
        
        java.lang.System.out.println("---Testing setAlarmTime(String time, int n)");
        java.lang.System.out.println("------Should set the corresponding alarm's time");
        assertTrue(system.clock.getAlarms()[0].getTime().equals(Instant.parse(time)));
    }
    
    /**
     * Method enableAlarm(int n)
     */
    @Test
    public void testEnableAlarmEnablesAlarm() {
        system.enableAlarm(1);
        
        java.lang.System.out.println("---Testing enableAlarm(int n)");
        java.lang.System.out.println("------Should enable the corresponding alarm");
        assertTrue(system.clock.getAlarms()[0].getIsActive());
    }
    
    /**
     * Method disableAlarm(int n)
     */
    @Test
    public void testDisableAlarmDisablesAlarm() {
        system.enableAlarm(1);
        system.disableAlarm(1);
        
        java.lang.System.out.println("---Testing disableAlarm(int n)");
        java.lang.System.out.println("------Should disable the corresponding alarm");
        assertFalse(system.clock.getAlarms()[0].getIsActive());
    }
    
    /**
     * Method soundAlarms()
     */
    @Test
    public void testSoundAlarmsSetsIsSoundingTrueIfTimeMatches() {
        String time = "2000-01-01T12:30:00Z";
        
        system.setClockTime(time);
        system.setAlarmTime(time, 1);
        system.soundAlarms();
        
        java.lang.System.out.println("---Testing soundAlarms()");
        java.lang.System.out.println("------Should sound alarms with correct times");
        assertTrue(system.clock.getAlarms()[0].getIsSounding());
    }
    
    /**
     * Method snoozeAlarms()
     */
    @Test
    public void testSnoozeAlarmsSnoozesAlarms() {
        String time = "2000-01-01T12:30:00Z";
        
        system.setClockTime(time);
        system.setAlarmTime(time, 1);
        system.soundAlarms();
        system.snoozeAlarms();
        
        java.lang.System.out.println("---Testing snoozeAlarms()");
        java.lang.System.out.println("------Should snooze alarms which are sounding");
        assertTrue(system.clock.getAlarms()[0].getSnoozeTime().equals(Instant.parse(time).plus(Alarm.SNOOZE_INTERVAL, Alarm.SNOOZE_UNIT)));
    }
    
    /**
     * Method playRadio()
     */
    @Test
    public void testPlayRadioPlaysRadio() {
        system.playRadio();
        
        java.lang.System.out.println("---Testing playRadio()");
        java.lang.System.out.println("------Should play the radio");
        assertTrue(system.radio.isPlaying());
    }
    
    /**
     * Method turnOffRadio()
     */
    @Test
    public void testTurnOffRadioTurnsOffRadio() {
        system.playRadio();
        system.turnOffRadio();
        
        java.lang.System.out.println("---Testing turnOffRadio()");
        java.lang.System.out.println("------Should turn off the radio");
        assertFalse(system.radio.isPlaying());
    }
    
    /**
     * Method toggleAMFM()
     */
    @Test
    public void testToggleAMFMTogglesRadioAMFM() {
        system.toggleAMFM();
        
        java.lang.System.out.println("---Testing toggleAMFM()");
        java.lang.System.out.println("------Should toggle the radio's AM/FM signal");
        assertTrue(system.radio.getUseAM());
    }
    
    /**
     * Method changeStation(int direction)
     */
    @Test
    public void testChangeStationChangesStation() {
        system.playRadio();
        system.changeStation(1);        
        
        java.lang.System.out.println("---Testing changeStation(int direction)");
        java.lang.System.out.println("------Should change the radio station in the corresponding direction");
        assertTrue(system.radio.getStation().contains("Secret"));
    }
}
