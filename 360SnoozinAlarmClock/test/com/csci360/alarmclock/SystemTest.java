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

public class SystemTest {
    
    protected System system;
    
    public SystemTest() {}
    
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
        java.lang.System.out.println("---Testing setClockTime(String time)");
        java.lang.System.out.println("------Should set the clock's time");
        String time = "2000-01-01T12:30:00Z";
        system.setClockTime(time);
        assertTrue(system.clock.getTime().equals(Instant.parse(time)));
    }
    
    /**
     * Method toggleTimeFormat()
     */
    @Test
    public void testToggleTimeFormatSetsClockTimeFormat() {                
        java.lang.System.out.println("---Testing toggleTimeFormat()");
        java.lang.System.out.println("------Should set the clock's time format");
        system.toggleTimeFormat();
        assertTrue(system.clock.getUse24HourFormat());
    }
    
    /**
     * setAlarmTime(String time, int n)
     */
    @Test
    public void testSetAlarmTimeSetsAlarmTime() {
        java.lang.System.out.println("---Testing setAlarmTime(String time, int n)");
        java.lang.System.out.println("------Should set the corresponding alarm's time");
        String time = "2000-01-01T12:30:00Z";
        system.setAlarmTime(time, 1);
        assertTrue(system.clock.getAlarms()[0].getTime().equals(Instant.parse(time)));
    }
    
    /**
     * Method enableAlarm(int n)
     */
    @Test
    public void testEnableAlarmEnablesAlarm() {
        java.lang.System.out.println("---Testing enableAlarm(int n)");
        java.lang.System.out.println("------Should enable the corresponding alarm");
        system.enableAlarm(1);
        assertTrue(system.clock.getAlarms()[0].getIsActive());
    }
    
    /**
     * Method disableAlarm(int n)
     */
    @Test
    public void testDisableAlarmDisablesAlarm() {
        java.lang.System.out.println("---Testing disableAlarm(int n)");
        java.lang.System.out.println("------Should disable the corresponding alarm");
        system.enableAlarm(1);
        system.disableAlarm(1);
        assertFalse(system.clock.getAlarms()[0].getIsActive());
    }
    
    /**
     * Method snoozeAlarms()
     */
    @Test
    public void testSnoozeAlarmsSnoozesAlarms() {  
        java.lang.System.out.println("---Testing snoozeAlarms()");
        java.lang.System.out.println("------Should snooze alarms which are sounding");
        String time = "2000-01-01T12:30:00Z";
        Alarm alarm = system.clock.getAlarms()[0];
        alarm.setTime(Instant.parse(time));
        alarm.setIsSounding(true);
        system.snoozeAlarms();
        assertTrue(system.clock.getAlarms()[0].getSnoozeTime().equals(Instant.parse(time).plus(Alarm.SNOOZE_INTERVAL, Alarm.SNOOZE_UNIT)));
    }
    
    /**
     * Method playRadio()
     */
    @Test
    public void testPlayRadioPlaysRadio() {
        java.lang.System.out.println("---Testing playRadio()");
        java.lang.System.out.println("------Should play the radio");
        system.playRadio();
        assertTrue(system.radio.isPlaying());
    }
    
    /**
     * Method turnOffRadio()
     */
    @Test
    public void testTurnOffRadioTurnsOffRadio() {
        java.lang.System.out.println("---Testing turnOffRadio()");
        java.lang.System.out.println("------Should turn off the radio");
        system.playRadio();
        system.turnOffRadio();
        assertFalse(system.radio.isPlaying());
    }
    
    /**
     * Method toggleAMFM()
     */
    @Test
    public void testToggleAMFMTogglesRadioAMFM() { 
        java.lang.System.out.println("---Testing toggleAMFM()");
        java.lang.System.out.println("------Should toggle the radio's AM/FM signal");
        system.toggleAMFM();
        assertTrue(system.radio.getUseAM());
    }
    
    /**
     * Method changeStation(int direction)
     */
    @Test
    public void testChangeStationChangesStation() {  
        java.lang.System.out.println("---Testing changeStation(int direction)");
        java.lang.System.out.println("------Should change the radio station in the corresponding direction");
        system.playRadio();
        system.changeStation(1);
        assertTrue(system.radio.getStation().contains("Secret"));
    }
}
