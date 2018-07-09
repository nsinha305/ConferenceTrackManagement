package conferencetrackmanagement.pojo;

import conferencetrackmanagement.exception.ConferenceException;
import conferencetrackmanagement.utility.Constants;
import conferencetrackmanagement.utility.Time;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author neesha
 */

public class Talk implements Comparable<Talk> {
    private String title;
    private int duration;
    private Time startTime;
    private String durationString;
    private boolean isAssigned;

    public Talk(String title, int duration, Time startTime, String durationString) {
        this.title = title;
        this.duration = duration;
        this.startTime = startTime;
        this.durationString = durationString;
        this.isAssigned = false;
    }

    public Talk(String title, int duration) {
        this.title = title;
        this.duration = duration;

    }

    @Override
    public int compareTo(Talk o) {
        return o.duration - this.duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public String getDurationString() {
        return durationString;
    }

    public void setDurationString(String durationString) {
        this.durationString = durationString;
    }

    public boolean isAssigned() {
        return isAssigned;
    }

    public void setAssigned(boolean assigned) {
        isAssigned = assigned;
    }

    @Override
    public String toString() {
        return (startTime.toString() + " " + title + " " + durationString).trim();
    }

    public static Talk createTalkFromString(String s) throws ConferenceException {
        if (s == null)
            throw new ConferenceException("Input is null");
        if (s.isEmpty())
            throw new ConferenceException("Input is empty");

        Pattern pattern = Pattern.compile(Constants.REGEX_PATTERN);
        Matcher matcher = pattern.matcher(s);
        if (!matcher.matches()) {
            throw new ConferenceException("Input format is incorrect");
        }

        Talk talk = new Talk(matcher.group(1), parseDuration(matcher.group(3)));
        return talk;
    }

    public static int parseDuration(String s) throws ConferenceException{
        int talktime = 0;
        if (s.endsWith(Constants.MIN_SUFFIX))
            talktime = Integer.parseInt(s.substring(0, s.indexOf(Constants.MIN_SUFFIX)));
        else if (s.endsWith(Constants.LIGHTNING_SUFFIX))
            talktime = Constants.LIGHTNING_MINUTES;
        else
            throw new ConferenceException("Input format is incorrect");
        return talktime;
    }

}
