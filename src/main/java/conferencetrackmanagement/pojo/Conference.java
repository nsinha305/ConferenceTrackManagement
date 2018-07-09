package conferencetrackmanagement.pojo;

import conferencetrackmanagement.utility.Time;

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
            for(Session session : track.getSessions()){
                for (Talk talk : session.getTalks()) {
                    conference += talk.toString() + "\n";
                }
            }
            conference += "\n";
        }
        return conference;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void assignTracks(int numberOfTracks) {
        for (int i = 0; i < numberOfTracks; i++)
            getTracks().add(new Track());
    }

    public void organizeTracks() {
        for (Track track : getTracks()) {
            track.organizeTalks();
        }
    }
}
