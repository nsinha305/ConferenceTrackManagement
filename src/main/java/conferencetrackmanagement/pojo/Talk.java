package conferencetrackmanagement.pojo;

import conferencetrackmanagement.utility.Time;

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
}
