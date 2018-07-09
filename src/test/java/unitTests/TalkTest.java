package unitTests;

import conferencetrackmanagement.exception.ConferenceException;
import conferencetrackmanagement.pojo.Talk;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * @author neesha on 7/9/18.
 * @project ConferenceTrackManagement
 */
public class TalkTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldThrowNullExceptionGivenNullString() throws ConferenceException {
        String s = null;
        exception.expect(ConferenceException.class);
        exception.expectMessage("Input is null");
        Talk.createTalkFromString(s);
    }

    @Test
    public void shouldThrowConferenceExceptionGivenInvalidInput() throws ConferenceException {
        String s = "";
        exception.expect(ConferenceException.class);
        exception.expectMessage("Input is empty");
        Talk.createTalkFromString(s);
    }

    @Test
    public void shouldReturnValidTalkGivenValidInput() throws ConferenceException {
        String s = "User Interface CSS in Rails Apps 30min";
        Talk talk = Talk.createTalkFromString(s);
        assertEquals(talk.getTitle(), "User Interface CSS in Rails Apps");
        assertEquals(talk.getDuration(), 30);
    }

    @Test
    public void shouldReturnValidLightningTalkGivenValidInput() throws ConferenceException {
        String s = "Ruby on Rails Legacy App Maintenance lightning";
        Talk talk = Talk.createTalkFromString(s);
        assertEquals(talk.getTitle(), "Ruby on Rails Legacy App Maintenance");
        assertEquals(talk.getDuration(), 5);
    }

    @Test
    public void shouldReturnIntegerDurationGivenLightning() throws ConferenceException{
        String s = "lightning";
        assertEquals(Talk.parseDuration(s), 5);
    }

    @Test
    public void shouldReturnIntegerDurationGivenValidDuration() throws ConferenceException{
        String s = "60min";
        assertEquals(Talk.parseDuration(s), 60);
    }

    @Test
    public void shouldThrowConferenceExceptionGivenInvalidDuration() throws ConferenceException{
        String s = "60";
        exception.expect(ConferenceException.class);
        exception.expectMessage("Input format is incorrect");
        Talk.parseDuration(s);
    }
}
