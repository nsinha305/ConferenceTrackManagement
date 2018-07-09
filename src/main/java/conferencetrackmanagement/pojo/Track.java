package conferencetrackmanagement.pojo;

import conferencetrackmanagement.utility.Time;

import java.util.ArrayList;
import java.util.List;

/**
 * @author neesha
 */

public class Track {
    private List<Session> sessions;
    private Session morningSession;
    private Session afternoonSession;

    public Track() {
        sessions = new ArrayList<>();
        morningSession = new Session(SessionType.MORNING);
        afternoonSession = new Session(SessionType.AFTERNOON);
        sessions.add(morningSession);
        sessions.add(new Session(SessionType.LUNCH));
        sessions.add(afternoonSession);
        sessions.add(new Session(SessionType.NETWORKING));
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public Session getMorningSession() {
        return morningSession;
    }

    public void setMorningSession(Session morningSession) {
        this.morningSession = morningSession;
    }

    public Session getAfternoonSession() {
        return afternoonSession;
    }

    public void setAfternoonSession(Session afternoonSession) {
        this.afternoonSession = afternoonSession;
    }

    public void organizeTalks() {
        Time networkingSessionStartTime = null;
        for (Session session : getSessions()) {
            if (session.getSessionType() != SessionType.NETWORKING) {
                if (session.getSessionType() == SessionType.AFTERNOON)
                    networkingSessionStartTime = session.defineTalkTimes();
                else
                    session.defineTalkTimes();
            }
        }
        for (Session session : getSessions()) {
            if (session.getSessionType() == SessionType.NETWORKING) {
                int nHours = (networkingSessionStartTime.getMinutes() > 0) ? networkingSessionStartTime.getHours() + 1 : networkingSessionStartTime.getHours();
                if (nHours < 4)
                    nHours = 4;
                session.getTalks().add(new Talk("Networking", 60, new Time(nHours, 0, false), ""));
            }
        }
    }
}
