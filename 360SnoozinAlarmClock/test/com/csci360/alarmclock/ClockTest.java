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
import javax.swing.Timer;

/**
 *
 * @author kyleglick
 */
public class ClockTest {
     
    
    public ClockTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void getTime() {
        
    }
    public void testDefaultConstructor(Clock testDefaultClock){
        java.lang.System.out.println("Running testDefaultConstructor method:");
        assertEquals(testDefaultClock.getAlarms().length, 2);
        assertEquals(testDefaultClock.getHour(), 0);
        assertEquals(testDefaultClock.getMinutes(), 0);
        assertFalse(testDefaultClock.getUse24HourFormat());
        java.lang.System.out.println("End defaultConstructor tests.");
        
    }
    public void testClockConstructor(){
        java.lang.System.out.println("Running testClockConstructor method:");
        int constructHour = 12;
        int constructMinute = 30;
        boolean constructUse24HourFormat = true;
        String ampm = "am";
        
        Clock testClockConstructor = new Clock(constructHour, constructMinute,
                constructUse24HourFormat, ampm);
        
        assertEquals(testClockConstructor.getHour(), constructHour);
        assertEquals(testClockConstructor.getMinutes(), constructMinute);
        assertTrue(testClockConstructor.getUse24HourFormat());
        assertNotEquals(testClockConstructor.getHour(), 0);
        assertEquals(testClockConstructor.getAMPM(), "");
        
        constructUse24HourFormat = false;
        testClockConstructor.setUse24HourFormat(constructUse24HourFormat);
        testClockConstructor.setAMPM("PM");
        assertFalse(testClockConstructor.getUse24HourFormat());
        assertEquals(testClockConstructor.getAMPM(), "PM");
        java.lang.System.out.println("End clockConstructor tests.");
    }
    public void testSetTimeMethod(Clock testClock){
        //Tests the setTime(hour, minutes) with the standard time format
        //default values for hour and minutes are 0.
        java.lang.System.out.println("Running tesets for testSetTimeMethod()");
        int testMinutes = 0;
        int testHour = 0;

        
        
        //These test are designed to test the bounds set for hours for standard
        // and 24 hour formats.
        
        //Standard time tests:
        testHour = 12;
        testClock.setTime(testHour, testMinutes);
        assertEquals(testClock.getHour(), testHour);
        assertNotEquals(testClock.getHour(), 0);
        
        testHour = 0;
        testClock.setTime(testHour, testMinutes);
        assertNotEquals(testClock.getHour(), testHour);
        
        testHour = 13;
        testClock.setTime(testHour, testMinutes);
        assertNotEquals(testClock.getHour(), 13);
        
        
        
        testClock.setUse24HourFormat(true);
        
        testHour = 0;
        testClock.setTime(testHour, testMinutes);
        assertEquals(testClock.getHour(), testHour);
        
        testHour = -1;
        testClock.setTime(testHour, testMinutes);
        assertNotEquals(testClock.getHour(), testHour);
        
        testHour = 13;
        testClock.setTime(testHour, testMinutes);
        assertEquals(testClock.getHour(), testHour);
        
        testHour = 23;
        testClock.setTime(testHour, testMinutes);
        assertEquals(testClock.getHour(), testHour);
        
        testHour = 24;
        testClock.setTime(testHour, testMinutes);
        assertNotEquals(testClock.getHour(), testHour);
        
        testHour = 30;
        testClock.setTime(testHour, testMinutes);
        assertNotEquals(testClock.getHour(), testHour);
        
        //These set of test are designed to test the bounds of the minutes 
        //  variable
        
        testMinutes = -1;
        testClock.setTime(0, testMinutes);
        assertNotEquals(testClock.getMinutes(), testMinutes);
       
        testMinutes = 0;
        testClock.setTime(0, testMinutes);
        assertEquals(testClock.getMinutes(), testMinutes);
        assertNotEquals(testClock.getMinutes(), 1);
        
        testMinutes = 59;
        testClock.setTime(0, testMinutes);
        assertEquals(testClock.getMinutes(), testMinutes);
        
        testMinutes = 60;
        testClock.setTime(0, testMinutes);
        assertNotEquals(testClock.getMinutes(), testMinutes);
        
        testMinutes = 80;
        testClock.setTime(0, testMinutes);
        assertNotEquals(testClock.getMinutes(), testMinutes);
        
        testMinutes = 30;
        testClock.setTime(0, testMinutes);
        assertEquals(testClock.getMinutes(), testMinutes);
        java.lang.System.out.println("End of tesets for testSetTimeMethod()");
    }
    public static void main(String [] args){
        ClockTest clockTest = new ClockTest();
        Clock clockForTesting = new Clock();
        clockTest.testDefaultConstructor(clockForTesting);
        clockTest.testClockConstructor();
        clockTest.testSetTimeMethod(clockForTesting);
        

    }
    }
    


