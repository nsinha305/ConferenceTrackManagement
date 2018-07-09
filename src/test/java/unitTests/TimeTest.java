package unitTests;

import conferencetrackmanagement.utility.Time;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author neesha
 */

public class TimeTest {

    private Time testTime;

    @Test
    public void testMorning() {
        testTime = new Time(8, 0, true);
        assertTrue(testTime.addDuration(90).toString().equals("09:30 AM"));
    }

    @Test
    public void testMorningToAfterNoon() {
        testTime = new Time(8, 0, true);
        assertTrue(testTime.addDuration(600).toString().equals("06:00 PM"));
    }

    @Test
    public void testAddingTwelveHours() {
        testTime = new Time(8, 0, true);
        assertTrue(testTime.addDuration(720).toString().equals("08:00 PM"));
    }

    @Test
    public void testAddingThirtyHours() {
        testTime = new Time(8, 0, true);
        assertTrue(testTime.addDuration(1800).toString().equals("02:00 PM"));
    }
}
