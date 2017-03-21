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
        Instant time = Instant.parse("2000-01-01T12:30:00Z");
        
        alarm.setTime(time);
    }
    
    @After
    public void tearDown() {
        alarm = null;
    }
    
    /**
     * Method setIsActive(boolean isActive)
     */
    @Test
    public void testSetIsActiveMaintainsSnoozeTimeIfFalse() {                
        java.lang.System.out.println("---Testing setIsActive(boolean isActive)");
        java.lang.System.out.println("------If isActive == false, should not reset snoozeTime");
        alarm.snooze();
        Instant snoozeTime = alarm.getTime().plus(Alarm.SNOOZE_INTERVAL, Alarm.SNOOZE_UNIT);
        alarm.setIsActive(false);
        assert(alarm.getSnoozeTime().equals(snoozeTime));
    }
    
    @Test
    public void testSetIsActiveResetsSnoozeTimeIfTrue() {  
        java.lang.System.out.println("---Testing setIsActive(boolean isActive)");
        java.lang.System.out.println("------If isActive == true, should reset snoozeTime");
        alarm.snooze();
        Instant snoozeTime = alarm.getTime().plus(Alarm.SNOOZE_INTERVAL, Alarm.SNOOZE_UNIT);
        alarm.setIsActive(true);
        assert(alarm.getSnoozeTime().equals(alarm.getTime()));
    }
    
    /**
     * Method snooze()
     */
    @Test
    public void testSnoozeIncrementsSnoozeTime() {  
        java.lang.System.out.println("---Testing snooze()");
        java.lang.System.out.println("------Should increment snoozeTime");
        alarm.setIsSounding(true);
        alarm.snooze();
        Instant snoozeTime = alarm.getTime().plus(Alarm.SNOOZE_INTERVAL, Alarm.SNOOZE_UNIT);
        assertTrue(alarm.getSnoozeTime().equals(snoozeTime));
    }
    
    @Test
    public void testSnoozeSetsIsSoundingToFalse() {
        java.lang.System.out.println("---Testing snooze()");
        java.lang.System.out.println("------Should set isSounding = false");
        alarm.setIsSounding(true);
        alarm.snooze();
        Instant snoozeTime = alarm.getTime().plus(Alarm.SNOOZE_INTERVAL, Alarm.SNOOZE_UNIT);
        assertFalse(alarm.getIsSounding());
    }
}
