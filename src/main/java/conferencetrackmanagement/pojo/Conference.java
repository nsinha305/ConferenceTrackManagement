package conferencetrackmanagement.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author neesha
 */

public class Conference {
    List<Track> tracks;

    public Conference() {
        tracks = new ArrayList<Track>();
    }

    @Override
    public String toString() {
        String conference = "";
        int trackCount = 1;
        for (Track track : tracks) {
            conference += "Track " + (trackCount++) + "\n";
            for (Talk talk : track.getMorningSession().getTalks()) {
                conference += talk.toString() + "\n";
            }
            for (Talk talk : track.getLunchSession().getTalks()) {
                conference += talk.toString() + "\n";
            }
            for (Talk talk : track.getAfternoonSession().getTalks()) {
                conference += talk.toString() + "\n";
            }
            for (Talk talk : track.getNetworkingSession().getTalks()) {
                conference += talk.toString() + "\n";
            }
            conference += "\n";
        }
        return conference;
    }

    public List<Track> getTracks() {
        return tracks;
    }
}
