/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sam_word
 */
public class RadioThreadTest {

  protected RadioThread radioThreadInstance;

  public RadioThreadTest() {}

  @BeforeClass
  public static void setUpClass() {
    java.lang.System.out.println("Begin RadioThreadTest");
  }

  @AfterClass
  public static void tearDownClass() {
    java.lang.System.out.println("End RadioThreadTest");
  }

  @Before
  public void setUp() {
    try {
      File someMP3 = new File("src/AudioFiles/FM").listFiles()[0];
      this.radioThreadInstance = new RadioThread(new FileInputStream(someMP3));
    }
    catch(IOException e) {
      java.lang.System.out.println(e+": There was a problem setting up the test.");      
    }
  }

  @After
  public void tearDown() {
    this.radioThreadInstance = null;
  }

  /**
   * Test of start method, of class RadioThread.
   * It should set isPlaying to true.
   */
  @Test
  public void testStart() {
    java.lang.System.out.println("start");
    radioThreadInstance.start();
    assertTrue(radioThreadInstance.getIsPlaying());
  }

  /**
   * Test of interrupt method, of class RadioThread.
   * It should set isPlaying to false
   */
  @Test
  public void testInterrupt() {
    java.lang.System.out.println("interrupt");
    radioThreadInstance.start();
    radioThreadInstance.interrupt();
    assertFalse(radioThreadInstance.getIsPlaying());
  }
  
}
