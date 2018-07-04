package conferencetrackmanagement.pojo;

import conferencetrackmanagement.utility.Constants;

public class Track {
    private Session morningSession;
    private Session lunchSession;
    private Session afternoonSession;
    private Session networkingSession;

    public Track() {
        morningSession = new Session();
        morningSession.setRemainingMinutes((int)Constants.MORNING_SESSION_MAX_MINUTES);
        lunchSession = new Session();
        afternoonSession = new Session();
        afternoonSession.setRemainingMinutes((int)Constants.AFTERNOON_SESSION_MAX_MINUTES);
        networkingSession = new Session();
    }

    public Session getMorningSession() {
        return morningSession;
    }

    public void setMorningSession(Session morningSession) {
        this.morningSession = morningSession;
    }

    public Session getLunchSession() {
        return lunchSession;
    }

    public void setLunchSession(Session lunchSession) {
        this.lunchSession = lunchSession;
    }

    public Session getAfternoonSession() {
        return afternoonSession;
    }

    public void setAfternoonSession(Session afternoonSession) {
        this.afternoonSession = afternoonSession;
    }

    public Session getNetworkingSession() {
        return networkingSession;
    }

    public void setNetworkingSession(Session networkingSession) {
        this.networkingSession = networkingSession;
    }
}
