/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

import java.time.Instant;
import java.time.LocalTime;
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
        java.lang.System.out.println();
        system = null;
    }
    
    /**
     * Method addHourToClock()
     */
    @Test
    public void testAddHourToClockAddsHour() {
        java.lang.System.out.println("---Testing addHourToClock()");
        java.lang.System.out.println("------Should add an hour to the clock");
        system.addHourToClock();
        assertTrue(system.getClockTime().getHour() == 1 && system.getClockTime().getMinute() == 0);
    }
    
    /**
     * Method addMinuteToClock()
     */
    @Test
    public void testAddMinuteToClockAddsHour() {
        java.lang.System.out.println("---Testing addMinuteToClock()");
        java.lang.System.out.println("------Should add an minute to the clock");
        system.addMinuteToClock();
        assertTrue(system.getClockTime().getHour() == 0 && system.getClockTime().getMinute() == 1);
    }
    
    /**
     * Method getClockTime()
     */
    @Test
    public void testGetClockTimeReturnsClockTime() {                
        java.lang.System.out.println("---Testing getClockTime()");
        java.lang.System.out.println("------Should return the clock's time");
        assertTrue(system.getClockTime().getHour() == 0 && system.getClockTime().getMinute() == 0);
    }
    
    /**
     * Method addHourToAlarm(int n)
     */
    @Test
    public void testAddHourToAlarmAddsHour() {
        java.lang.System.out.println("---Testing addHourToAlarm(int n)");
        java.lang.System.out.println("------Should add an hour to the alarm");
        system.addHourToAlarm(1);
        assertTrue(system.getAlarmTime(1).getHour() == 1 && system.getAlarmTime(1).getMinute() == 0);
    }
    
    /**
     * Method addMinuteToClock(int n)
     */
    @Test
    public void testAddMinuteToAlarmAddsHour() {
        java.lang.System.out.println("---Testing addMinuteToAlarm(int n)");
        java.lang.System.out.println("------Should add an minute to the alarm");
        system.addMinuteToAlarm(1);
        assertTrue(system.getAlarmTime(1).getHour() == 0 && system.getAlarmTime(1).getMinute() == 1);
    }
    
    /**
     * Method getAlarmTime(int n)
     */
    @Test
    public void testGetAlarmTimeReturnsAlarmTime() {                
        java.lang.System.out.println("---Testing getAlarmTime(int n)");
        java.lang.System.out.println("------Should return the alarm's time");
        assertTrue(system.getAlarmTime(1).getHour() == 0 && system.getAlarmTime(1).getMinute() == 0);
    }
    
    /**
     * Method enableAlarm(int n)
     */
    @Test
    public void testEnableAlarmEnablesAlarm() {
        java.lang.System.out.println("---Testing enableAlarm(int n)");
        java.lang.System.out.println("------Should enable the corresponding alarm");
        system.enableAlarm(1);
        assertTrue(system.clock.alarms[0].getIsActive());
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
        assertFalse(system.clock.alarms[0].getIsActive());
    }
    
    /**
     * Method snoozeAlarm(int n)
     */
    @Test
    public void testSnoozeAlarmSnoozesAlarm() {  
        java.lang.System.out.println("---Testing snoozeAlarm(int n)");
        java.lang.System.out.println("------Should snooze the alarm");
        system.clock.alarms[0].setIsSounding(true);
        system.snoozeAlarm(1);
        assertTrue(system.clock.alarms[0].getSnoozeTime().getHour() == 0 && system.clock.alarms[0].getSnoozeTime().getMinute() == 10);
    }
    
    /**
     * Method isAlarmSounding(int n)
     */
    @Test
    public void testIsAlarmSoundingReturnsAttribute() {
        java.lang.System.out.println("---Testing isAlarmSounding(int n)");
        java.lang.System.out.println("------Should return the correct value");
        system.clock.alarms[0].setIsSounding(true);
        assertTrue(system.isAlarmSounding(1));
    }
    
    /**
     * Method anyAlarmsSounding()
     */
    @Test
    public void testAnyAlarmsSoundingReturnsTrueIfAlarmsSounding() {
        java.lang.System.out.println("---Testing anyAlarmsSounding()");
        java.lang.System.out.println("------Should return true if alarms sounding");
        system.clock.alarms[0].setIsSounding(true);
        assertTrue(system.anyAlarmsSounding());
    }
    
    @Test
    public void testAnyAlarmsSoundingReturnsFalseIfAlarmsOff() {
        java.lang.System.out.println("------Should return false if alarms off");
        assertFalse(system.anyAlarmsSounding());
    }
    
    /**
     * Method attemptToSoundAlarms()
     */
    @Test
    public void testAttemptToSoundAlarmsReturnsAlarmsSounding() {
        java.lang.System.out.println("---Testing attemptToSoundAlarms()");
        java.lang.System.out.println("------Should return the numbers of alarms that are sounding");
        system.clock.alarms[0].setIsActive(true);
        system.clock.alarms[0].setIsSounding(true);
        assertTrue(system.attemptToSoundAlarms()[0] == 1);
    }
    
    @Test
    public void testAttemptToSoundAlarmsReturnsZeroIfNotSounding() {
        java.lang.System.out.println("------Should return the numbers of alarms that are sounding");
        assertTrue(system.attemptToSoundAlarms()[0] == 0);
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
