/**
 * 
 */
package main.java.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 *
 * @author Michael Massarelli
 */
public class Ping {
    public static final String TAG = "Ping";
    
    public static String ping(String name) {
	String pingResult = "";
	String pingCmd = "ping " + name + " -a -4";
        try {
            Runtime r = Runtime.getRuntime();
            Process p = r.exec(pingCmd);

            BufferedReader in = new BufferedReader(new
            InputStreamReader(p.getInputStream()));
            String inputLine = in.readLine() != null ? in.readLine() : "";
            inputLine = inputLine.substring(inputLine.indexOf('[') + 1, inputLine.lastIndexOf(']'));
            pingResult = inputLine;
            in.close();
        } catch (IOException e) {
            System.out.println(e);
        }
        return pingResult;
    }
}
