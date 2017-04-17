/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csci360.alarmclock;

import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FileRadioTest {

  protected FileRadio fileRadioInstance;

  public FileRadioTest() {}

  @BeforeClass
  public static void setUpClass() {
    java.lang.System.out.println("Begin FileRadioTest");
  }

  @AfterClass
  public static void tearDownClass() {
    java.lang.System.out.println("End FileRadioTest");
  }

  @Before
  public void setUp() {
    this.fileRadioInstance = new FileRadio();
  }

  @After
  public void tearDown() {
    java.lang.System.out.println();
    this.fileRadioInstance.stopRadio();
    this.fileRadioInstance = null;
  }

  /**
   * Test of playRadio method, of class FileRadio.
   * Should open a new RadioThread and start it.
   * @throws java.io.IOException
   */
  @Test
  public void testPlayRadio() throws IOException {
    java.lang.System.out.println("--Testing playRadio()");
    java.lang.System.out.println("----Should create a RadioThread");
    fileRadioInstance.playRadio();
    assertFalse(fileRadioInstance.isRadioThreadNull());
  }

  /**
   * Test of stopRadio method, of class FileRadio.
   * Should interrupt and delete the opened RadioThread in fileRadioInstance
   * @throws java.io.IOException
   */
  @Test
  public void testStopRadio() throws IOException {
    java.lang.System.out.println("--Testing stopRadio()");
    java.lang.System.out.println("----Should destroy the RadioThread");
    fileRadioInstance.playRadio();
    fileRadioInstance.stopRadio();
    assertTrue(fileRadioInstance.isRadioThreadNull());
  }

  /**
   * Test of getStation method, of class FileRadio.
   * Should return the name of the current file.
   */
  @Test
  public void testGetStation() throws IOException {
    java.lang.System.out.println("--Testing getStation()");
    java.lang.System.out.println("----Should return current station");
    String expectedName = "FM - Red Cat by The Piano Lady";
    fileRadioInstance.playRadio();
    String actualName = fileRadioInstance.getStation();
    assertEquals("Expected: "+expectedName+"\nGot: "+actualName,
      expectedName, actualName);
  }

  /**
   * Test of toggleAMFM method, of class FileRadio.
   * useAM is the opposite of its previous value.
   * @throws java.io.IOException
   */
  @Test
  public void testToggleAMFM() throws IOException {
    java.lang.System.out.println("--Testing toggleAMFM()");
    java.lang.System.out.println("----Should toggle useAM attribute");
    boolean initialVal = fileRadioInstance.getUseAM();
    fileRadioInstance.toggleAMFM();
    boolean resultVal = fileRadioInstance.getUseAM();
    assertNotEquals(initialVal, resultVal);
  }

  /**
   * Test of tune method, of class FileRadio.
   * tune() should reset and play a playing RadioThread.
   * @throws java.io.IOException
   */
  @Test
  public void testTuneChangesStation() throws IOException {
    java.lang.System.out.println("--Testing tune(int direction)");
    java.lang.System.out.println("----Should change the station if the radio is playing");
    fileRadioInstance.playRadio();
    String oldStation = fileRadioInstance.getStation();
    fileRadioInstance.tune(1);
    String newStation = fileRadioInstance.getStation();
    assertNotEquals(oldStation, newStation);
  }

  /**
   * Test of tune method, of class FileRadio.
   * tune() should reset but not play a RadioThread if it is not already playing.
   * @throws java.io.IOException
   */
  @Test
  public void testTuneNoRadio() throws IOException {
    java.lang.System.out.println("--Testing tune(int direction)");
    java.lang.System.out.println("----Should not change the station if the radio is off");
    String oldStation = fileRadioInstance.getStation();
    fileRadioInstance.tune(1);
    String newStation = fileRadioInstance.getStation();
    assertEquals(oldStation, newStation);
  }

  /**
   * Test of tune method, of class FileRadio.
   * tune() should do nothing if the new index is less than 0.
   * NOTE: If this test fails, there was a error somewhere when nothing
   * should have happened.
   * @throws java.io.IOException
   */
  @Test
  public void testTuneLessThan0() throws IOException {
    java.lang.System.out.println("--Testing tune(int direction)");
    java.lang.System.out.println("----Should do nothing if index < 0");
    String oldStation = fileRadioInstance.getStation();
    fileRadioInstance.tune(-10000000);
    String newStation = fileRadioInstance.getStation();
    assertEquals(oldStation, newStation);
  }

  /**
   * Test of tune method, of class FileRadio.
   * tune() should do nothing if the new index is greater than the number of files.
   * NOTE: If this test fails, there was a error somewhere when nothing
   * should have happened.
   * @throws java.io.IOException
   */
  @Test
  public void testTuneGreaterThanLength() throws IOException {
    java.lang.System.out.println("--Testing tune(int direction)");
    java.lang.System.out.println("----Should do nothing if index > length");
    String oldStation = fileRadioInstance.getStation();
    fileRadioInstance.tune(10000000);
    String newStation = fileRadioInstance.getStation();
    assertEquals(oldStation, newStation);
  }
}
