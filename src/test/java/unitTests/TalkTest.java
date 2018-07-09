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
    public void shouldThrowConferenceExceptionGivenInvalidInput() throws ConferenceException{
        String s = "";
        exception.expect(ConferenceException.class);
        exception.expectMessage("Input is empty");
        Talk.createTalkFromString(s);
    }
}