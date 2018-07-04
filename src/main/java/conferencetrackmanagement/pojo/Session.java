package conferencetrackmanagement.pojo;

import java.util.ArrayList;
import java.util.List;

public class Session {
    List<Talk> talks;
    int remainingMinutes;

    public Session() {
        talks = new ArrayList<Talk>();
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
}
