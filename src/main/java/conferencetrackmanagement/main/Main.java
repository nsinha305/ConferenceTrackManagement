package conferencetrackmanagement.main;

import conferencetrackmanagement.exception.ConferenceException;
import conferencetrackmanagement.pojo.Conference;
import conferencetrackmanagement.utility.Constants;
import conferencetrackmanagement.utility.InputReader;
import conferencetrackmanagement.utility.Scheduler;

public class Main {
    public static void main(String args[]) {
        try {
            //Scheduler scheduler = new Scheduler(InputReader.readFile(Constants.INPUT_FILE_NAME));
            Scheduler scheduler = new Scheduler(InputReader.readFile("res/ConferenceWith299MinTalk.txt"));
            Conference conference = scheduler.getConference();
            System.out.println(conference.toString());
        }
        catch(ConferenceException e){
            System.out.println(e.getMessage());
        }
    }
}
