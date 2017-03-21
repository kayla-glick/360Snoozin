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
        clock = null;
    }
    
    /**
     * Unless we can test the creation of the Timer and execution of the
     * task, right now there are no other relevant methods to test.
     */
    @Test
    public void test() {
        assertTrue(true);
    }
}
