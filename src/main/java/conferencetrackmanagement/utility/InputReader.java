package conferencetrackmanagement.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author neesha
 */

public class InputReader {
    public static List<String> readFile(String fileName) {
        List<String> inputList = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String strLine = null;
            while ((strLine = br.readLine()) != null) {
                inputList.add(strLine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputList;
    }

}
