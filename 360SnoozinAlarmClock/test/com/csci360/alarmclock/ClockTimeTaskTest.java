/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.TimerTask;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ClockTimeTaskTest {
    protected Clock clock;
    protected TimerTask task;
    
    public ClockTimeTaskTest() {}
    
    @BeforeClass
    public static void setUpClass() {
        java.lang.System.out.println("Begin ClockTimeTaskTest");
    }
    
    @AfterClass
    public static void tearDownClass() {
        java.lang.System.out.println("End ClockTimeTaskTest");
    }
    
    @Before
    public void setUp() {
        clock = new Clock();
        task = new ClockTimeTask(clock);
    }
    
    @After
    public void tearDown() {
        clock = null;
        task = null;
    }

    /**
     * Method run()
     */
    @Test
    public void testRunUpdatesClockTime() { 
        java.lang.System.out.println("---Testing run()");
        java.lang.System.out.println("------Should add 1 minute to clock time");
        Instant time = clock.getTime();
        task.run();
        assertTrue(clock.getTime().equals(time.plus(1, ChronoUnit.MINUTES)));
    }   
}
