import conferencetrackmanagement.exception.ConferenceException;
import conferencetrackmanagement.pojo.Conference;
import conferencetrackmanagement.utility.InputReader;
import conferencetrackmanagement.utility.Scheduler;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SchedulerTest {

    @Test
    public void testOneDayScheduler() throws ConferenceException, FileNotFoundException {
        testScheduler("res/OneDayConference");
    }

    @Test
    public void testManyDaysScheduler() throws ConferenceException, FileNotFoundException {
        testScheduler("res/ManyDaysConference");
    }

    @Test(expected = ConferenceException.class)
    public void testTalkTooLongException() throws ConferenceException, FileNotFoundException {
        testScheduler("res/ConferenceWith299MinTalk");
    }

    private void testScheduler(String inputFile) throws ConferenceException, FileNotFoundException {
        Scheduler scheduler = new Scheduler(InputReader.readFile(inputFile + ".txt"));
        Conference conference = scheduler.getConference();
        Scanner scanner = new Scanner(new File(inputFile + "Output.txt"));
        String text = scanner.useDelimiter("\\A").next();
        assertTrue(text.trim().equals(conference.toString().trim()));
        scanner.close();
    }
}
