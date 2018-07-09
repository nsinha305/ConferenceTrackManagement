package conferencetrackmanagement.pojo;

import conferencetrackmanagement.utility.Constants;
import conferencetrackmanagement.utility.Time;

import java.util.ArrayList;
import java.util.List;

/**
 * @author neesha
 */

public class Session {
    List<Talk> talks;
    int remainingMinutes;
    SessionType sessionType;

    /**
     * instantiates the Session object with an empty list of Talks.
     */
    public Session(SessionType sessionType) {
        talks = new ArrayList<>();
        this.sessionType = sessionType;
        if (sessionType == SessionType.AFTERNOON) {
            remainingMinutes = (int) Constants.AFTERNOON_SESSION_MAX_MINUTES;
        }
        if (sessionType == SessionType.MORNING) {
            remainingMinutes = (int) Constants.MORNING_SESSION_MAX_MINUTES;
        }
    }

    public int getRemainingMinutes() {
        return remainingMinutes;
    }

    public void setRemainingMinutes(int remainingMinutes) {
        this.remainingMinutes = remainingMinutes;
    }

    public List<Talk> getTalks() {
        return talks;
    }

    public void setTalks(List<Talk> talks) {
        this.talks = talks;
    }

    public SessionType getSessionType() {
        return sessionType;
    }

    public void setSessionType(SessionType sessionType) {
        this.sessionType = sessionType;
    }

    public boolean canFit(Talk talk) {
        if (remainingMinutes >= talk.getDuration())
            return true;
        return false;
    }

    public void addTalk(Talk talk) {
        talks.add(talk);
        remainingMinutes = remainingMinutes - talk.getDuration();
        talk.setAssigned(true);
    }

    public Time defineTalkTimes() {
        Time startTime = null;
        if (sessionType == SessionType.MORNING) {
            startTime = new Time(9, 00, true);
        }
        if (sessionType == SessionType.AFTERNOON) {
            startTime = new Time(1, 00, false);
        }
        if (sessionType == SessionType.LUNCH) {
            getTalks().add(new Talk("Lunch", 60, new Time(12, 0, false), ""));
            return null;
        }
        for (Talk talk : getTalks()) {
            talk.setStartTime(startTime);
            startTime = startTime.addDuration(talk.getDuration());
        }
        return startTime;
    }
}
