package conferencetrackmanagement.utility;

import conferencetrackmanagement.exception.ConferenceException;
import conferencetrackmanagement.pojo.Conference;
import conferencetrackmanagement.pojo.Talk;
import conferencetrackmanagement.pojo.Track;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author neesha
 */

public class Scheduler {
    private List<String> input;
    private Conference conference;
    private List<Talk> talks;
    private int totalMinutes = 0;
    private int numberOfTracks;

    /**
     * @param input
     * @throws ConferenceException
     */
    public Scheduler(List<String> input) throws ConferenceException {
        this.input = input;
        this.conference = new Conference();
        convertInputToTalks();
    }

    /**
     * Returns the conference object
     * @return
     */
    public Conference getConference() {
        return conference;
    }

    /**
     * This method schedules the talks in the conference
     * @throws ConferenceException
     */
    public void scheduleTalks() throws ConferenceException {
        setNumberOfTracks();
        assignTalksToSessionsAndTracks();
        assignTalkTimes();
    }

    /**
     * This method determines the start time of a talk based on the start time of the previous talk and its length.
     */
    private void assignTalkTimes() {
        for (Track track : conference.getTracks()) {
            Time morningTime = new Time(9, 00, true);
            for (Talk talk : track.getMorningSession().getTalks()) {
                talk.setStartTime(morningTime);
                morningTime = morningTime.addDuration(talk.getDuration());
            }
            track.getLunchSession().getTalks().add(new Talk("Lunch", 60, new Time(12, 0, false), ""));
            Time afternoonTime = new Time(1, 0, false);
            for (Talk talk : track.getAfternoonSession().getTalks()) {
                talk.setStartTime(afternoonTime);
                afternoonTime = afternoonTime.addDuration(talk.getDuration());
            }
            int nHours = (afternoonTime.getMinutes() > 0) ? afternoonTime.getHours() + 1 : afternoonTime.getHours();
            if (nHours < 4)
                nHours = 4;
            track.getNetworkingSession().getTalks().add(new Talk("Networking", 60, new Time(nHours, 0, false), ""));
        }
    }

    /**
     * This method uses first fit bin packing algorithm to assign talks to different sessions and tracks
     * @throws ConferenceException
     */
    private void assignTalksToSessionsAndTracks() throws ConferenceException {
        Collections.sort(talks);
        //System.out.println("Number of tracks : "+numberOfTracks);
        for (int i = 0; i < numberOfTracks; i++)
            conference.getTracks().add(new Track());

        for (Talk talk : talks) {
            boolean isTalkAssigned = false;
            for (Track track : conference.getTracks()) {
                if (track.getMorningSession().getRemainingMinutes() >= talk.getDuration()) {
                    isTalkAssigned = true;
                    track.getMorningSession().getTalks().add(talk);
                    track.getMorningSession().setRemainingMinutes(track.getMorningSession().getRemainingMinutes() - talk.getDuration());
                } else if (track.getAfternoonSession().getRemainingMinutes() >= talk.getDuration()) {
                    isTalkAssigned = true;
                    track.getAfternoonSession().getTalks().add(talk);
                    track.getAfternoonSession().setRemainingMinutes(track.getAfternoonSession().getRemainingMinutes() - talk.getDuration());
                }
                if (isTalkAssigned)
                    break;
            }
            if (!isTalkAssigned)
                throw new ConferenceException("talk does not fit");
        }
    }

    /**
     * This method counts the total minutes of each talk and determines how many tracks (days) would be needed.
     */
    private void setNumberOfTracks() {
        double minutesPerTrack = Constants.AFTERNOON_SESSION_MAX_MINUTES + Constants.MORNING_SESSION_MAX_MINUTES;
        double tracks = totalMinutes / minutesPerTrack;
        double remainder = tracks % 1;
        numberOfTracks = (int) tracks;
        if (remainder > 0.0)
            numberOfTracks++;
    }

    /**
     * This method parses the input into a list of Talks.
     * @throws ConferenceException
     */
    private void convertInputToTalks() throws ConferenceException {
        talks = new ArrayList<>();
        for (String inputString : input) {
            Pattern pattern = Pattern.compile("(.*)(\\s){1}([0-2]?[0-9]?[0-9]{1}min|lightning)\\b");
            Matcher matcher = pattern.matcher(inputString);
            if (!matcher.matches()) {
                throw new ConferenceException("Input format is incorrect");
            }
            int talktime = getTalkTime(matcher.group(3));
            if (talktime > Constants.AFTERNOON_SESSION_MAX_MINUTES)
                throw new ConferenceException("Talk is too long. Will not fit in a Session");
            totalMinutes += talktime;
            talks.add(new Talk(matcher.group(1), talktime, null, matcher.group(3)));
        }
    }

    /**
     * @param timestring
     * @return
     * @throws ConferenceException
     */
    private int getTalkTime(String timestring) throws ConferenceException {
        int talktime = 0;
        if (timestring.endsWith(Constants.MIN_SUFFIX))
            talktime = Integer.parseInt(timestring.substring(0, timestring.indexOf(Constants.MIN_SUFFIX)));
        else if (timestring.endsWith(Constants.LIGHTNING_SUFFIX))
            talktime = Constants.LIGHTNING_MINUTES;
        else
            throw new ConferenceException("Input format is incorrect");
        return talktime;
    }
}
