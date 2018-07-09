package conferencetrackmanagement.main;

import conferencetrackmanagement.exception.ConferenceException;
import conferencetrackmanagement.pojo.Conference;
import conferencetrackmanagement.utility.InputReader;
import conferencetrackmanagement.utility.Scheduler;

/**
 * @author neesha
 */

public class Main {
    public static void main(String args[]) {
        try {
            if (args.length < 1)
                throw new ConferenceException("Path to input file must be provided");
            Scheduler scheduler = new Scheduler(InputReader.readFile(args[0]));
            scheduler.scheduleTalks();
            Conference conference = scheduler.getConference();
            conference.organizeTracks();
            System.out.println(conference.toString());
        } catch (ConferenceException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
