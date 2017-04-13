/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ClockTest {
     
    protected Clock clock;
    
    public ClockTest() {}
    
    @BeforeClass
    public static void setUpClass() {
        java.lang.System.out.println("Begin ClockTest");
    }
    
    @AfterClass
    public static void tearDownClass() {
        java.lang.System.out.println("End ClockTest");
    }
    
    @Before
    public void setUp() {
        clock = new Clock();
    }
    
    @After
    public void tearDown() {
        java.lang.System.out.println();
        clock = null;
    }
    
    /**
     * Method addHour()
     */
    @Test
    public void testAddHourAddsHourToTime() {
        java.lang.System.out.println("---Testing addHour()");
        java.lang.System.out.println("------Should add an hour to the time");
        clock.addHour();
        assertTrue(clock.getTime().getHour() == 1 && clock.getTime().getMinute() == 0);
    }
    
    /**
     * Method addMinute()
     */
    @Test
    public void testAddMinuteAddsMinuteToTime() {
        java.lang.System.out.println("---Testing addMinute()");
        java.lang.System.out.println("------Should add a minute to the time");
        clock.addMinute();
        assertTrue(clock.getTime().getHour() == 0 && clock.getTime().getMinute() == 1);
    }
    
    /**
     * Method addHourToAlarm(int n)
     */
    @Test
    public void testAddHourToAlarmAddsHourToAlarm() {
        java.lang.System.out.println("---Testing addHourToAlarm(int n)");
        java.lang.System.out.println("------Should add an hour to the alarm");
        clock.addHourToAlarm(1);
        assertTrue(clock.alarms[0].getTime().getHour() == 1 && clock.alarms[0].getTime().getMinute() == 0);
    }
    
    /**
     * Method addMinuteToAlarm(int n)
     */
    @Test
    public void testAddMinuteToAlarmAddsHourToAlarm() {
        java.lang.System.out.println("---Testing addMinuteToAlarm(int n)");
        java.lang.System.out.println("------Should add a minute to the alarm");
        clock.addMinuteToAlarm(1);
        assertTrue(clock.alarms[0].getTime().getHour() == 0 && clock.alarms[0].getTime().getMinute() == 1);
    }
    
    /**
     * Method getAlarmTime(int n)
     */
    @Test
    public void testGetAlarmTimeReturnsAlarmTime() {
        java.lang.System.out.println("---Testing getAlarmTime(int n)");
        java.lang.System.out.println("------Should get the alarm's time");
        assertTrue(clock.getAlarmTime(1).getHour() == 0 && clock.getAlarmTime(1).getMinute() == 0);
    }
    
    /**
     * Method enableAlarm(int n)
     */
    @Test
    public void testEnableAlarmEnablesAlarm() {
        java.lang.System.out.println("---Testing enableAlarm(int n)");
        java.lang.System.out.println("------Should enable the alarm");
        clock.enableAlarm(1);
        assertTrue(clock.alarms[0].getIsActive());
    }
    
    /**
     * Method disableAlarm(int n)
     */
    @Test
    public void testDisableAlarmEnablesAlarm() {
        java.lang.System.out.println("---Testing disableAlarm(int n)");
        java.lang.System.out.println("------Should disable the alarm");
        clock.enableAlarm(1);
        clock.disableAlarm(1);
        assertFalse(clock.alarms[0].getIsActive());
    }
    
    /**
     * Method snoozeAlarm(int n)
     */
    @Test
    public void testSnoozeAlarmSnoozesAlarm() {
        java.lang.System.out.println("---Testing snoozeAlarm(int n)");
        java.lang.System.out.println("------Should snooze the alarm");
        clock.alarms[0].setIsSounding(true);
        clock.snoozeAlarm(1);
        assertTrue(clock.alarms[0].getSnoozeTime().getHour() == 0 && clock.alarms[0].getSnoozeTime().getMinute() == 10);
    }
    
    /**
     * Method isAlarmSounding(int n)
     */
    @Test
    public void testIsAlarmSoundingReturnsAttribute() {
        java.lang.System.out.println("---Testing isAlarmSounding(int n)");
        java.lang.System.out.println("------Should return the correct value");
        clock.alarms[0].setIsSounding(true);
        assertTrue(clock.isAlarmSounding(1));
    }
    
    /**
     * Method anyAlarmsSounding()
     */
    @Test
    public void testAnyAlarmsSoundingReturnsTrueIfAlarmsSounding() {
        java.lang.System.out.println("---Testing anyAlarmsSounding()");
        java.lang.System.out.println("------Should return true if alarms sounding");
        clock.alarms[0].setIsSounding(true);
        assertTrue(clock.anyAlarmsSounding());
    }
    
    @Test
    public void testAnyAlarmsSoundingReturnsFalseIfAlarmsOff() {
        java.lang.System.out.println("------Should return false if alarms off");
        assertFalse(clock.anyAlarmsSounding());
    }
    
    /**
     * Method attemptToSoundAlarms()
     */
    @Test
    public void testAttemptToSoundAlarmsReturnsAlarmsSounding() {
        java.lang.System.out.println("---Testing attemptToSoundAlarms()");
        java.lang.System.out.println("------Should return the numbers of alarms that are sounding");
        clock.alarms[0].setIsActive(true);
        clock.alarms[0].setIsSounding(true);
        assertTrue(clock.attemptToSoundAlarms()[0] == 1);
    }
    
    @Test
    public void testAttemptToSoundAlarmsReturnsZeroIfNotSounding() {
        java.lang.System.out.println("------Should return the numbers of alarms that are sounding");
        assertTrue(clock.attemptToSoundAlarms()[0] == 0);
    }
}
