/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;
<<<<<<< Updated upstream

import java.time.Instant;
=======
>>>>>>> Stashed changes
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class AlarmTest {
    protected Alarm alarm;
    
    public AlarmTest() {
        
    }
    
<<<<<<< Updated upstream
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
        alarm.snooze();
        
        Instant snoozeTime = alarm.getTime().plus(Alarm.SNOOZE_INTERVAL, Alarm.SNOOZE_UNIT);
                
        java.lang.System.out.println("---Testing setIsActive(boolean isActive)");
        java.lang.System.out.println("------If isActive == false, should not reset snoozeTime");
        alarm.setIsActive(false);
        assert(alarm.getSnoozeTime().equals(snoozeTime));
    }
    
    @Test
    public void testSetIsActiveResetsSnoozeTimeIfTrue() {
        alarm.snooze();
        
        Instant snoozeTime = alarm.getTime().plus(Alarm.SNOOZE_INTERVAL, Alarm.SNOOZE_UNIT);
        
        java.lang.System.out.println("---Testing setIsActive(boolean isActive)");
        java.lang.System.out.println("------If isActive == true, should reset snoozeTime");
        alarm.setIsActive(true);
        assert(alarm.getSnoozeTime().equals(alarm.getTime()));
=======
    @Test
    public void testClockConstructor() {
        // TODO review the generated test code and remove the default call to fail.
        Clock testClock = new Clock();
        assertEquals(testClock.getAlarms(), );
>>>>>>> Stashed changes
    }
    
    /**
     * Method snooze()
     */
    @Test
    public void testSnoozeIncrementsSnoozeTime() {
        alarm.setIsSounding(true);
        alarm.snooze();
        
        Instant snoozeTime = alarm.getTime().plus(Alarm.SNOOZE_INTERVAL, Alarm.SNOOZE_UNIT);
        
        java.lang.System.out.println("---Testing snooze()");
        java.lang.System.out.println("------Should increment snoozeTime");      
        assertTrue(alarm.getSnoozeTime().equals(snoozeTime));
    }
    
    @Test
    public void testSnoozeSetsIsSoundingToFalse() {
        alarm.setIsSounding(true);
        alarm.snooze();
        
        Instant snoozeTime = alarm.getTime().plus(Alarm.SNOOZE_INTERVAL, Alarm.SNOOZE_UNIT);
        
        java.lang.System.out.println("---Testing snooze()");
        java.lang.System.out.println("------Should set isSounding = false");
        assertFalse(alarm.getIsSounding());
    }
}
