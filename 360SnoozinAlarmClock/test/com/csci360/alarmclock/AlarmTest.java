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

public class AlarmTest {
    
    protected Alarm alarm;
    
    public AlarmTest() {}
    
    @BeforeClass
    public static void setUpClass() {
        java.lang.System.out.println("Begin AlarmTest");
    }
    
    @AfterClass
    public static void tearDownClass() {
        java.lang.System.out.println("End AlarmTest");
    }
    
    @Before
    public void setUp() {
        alarm = new Alarm();
    }
    
    @After
    public void tearDown() {
        java.lang.System.out.println();
        alarm = null;
    }
    
    /**
     * Method addHour()
     */
    @Test
    public void testAddHourAddsHour() {
        java.lang.System.out.println("--Testing addHour()");
        java.lang.System.out.println("----Should add an hour to time");
        alarm.addHour();
        assertTrue(alarm.getTime().getHour() == 1 && alarm.getTime().getMinute() == 0);
    }
    
    /**
     * Method addMinute()
     */
    @Test
    public void testAddMinuteAddsHour() {
        java.lang.System.out.println("--Testing addMinute()");
        java.lang.System.out.println("----Should add a minute to time");
        alarm.addMinute();
        assertTrue(alarm.getTime().getHour() == 0 && alarm.getTime().getMinute() == 1);
    }
   
    /**
     * Method setIsActive(boolean isActive)
     */
    @Test
    public void testSetIsActiveMaintainsSnoozeTimeIfFalse() {                
        java.lang.System.out.println("--Testing setIsActive(boolean isActive)");
        java.lang.System.out.println("----Should maintain snoozeTime if isActive");
        alarm.snooze(LocalTime.now().withHour(0).withMinute(0));
        alarm.setIsActive(false);
        assertTrue(alarm.getSnoozeTime().getHour() == 0 && alarm.getSnoozeTime().getMinute() == 10);
    }
    
    @Test
    public void testSetIsActiveResetsSnoozeTimeIfTrue() {  
        java.lang.System.out.println("----If isActive == true, should reset snoozeTime");
        alarm.snooze(LocalTime.now().withHour(0).withMinute(0));
        alarm.setIsActive(true);
        assertTrue(alarm.getSnoozeTime().getHour() == 0 && alarm.getSnoozeTime().getMinute() == 0);
    }
    
    /**
     * Method snooze()
     */
    @Test
    public void testSnoozeIncrementsSnoozeTime() {  
        java.lang.System.out.println("--Testing snooze()");
        java.lang.System.out.println("----Should increment snoozeTime");
        alarm.setIsSounding(true);
        alarm.snooze(LocalTime.now().withHour(0).withMinute(0));
        assertTrue(alarm.getSnoozeTime().getHour() == 0 && alarm.getSnoozeTime().getMinute() == 10);
    }
    
    @Test
    public void testSnoozeSetsIsSoundingToFalse() {
        java.lang.System.out.println("----Should set isSounding = false");
        alarm.setIsSounding(true);
        alarm.snooze(LocalTime.now().withHour(0).withMinute(0));
        assertFalse(alarm.getIsSounding());
    }
}
