/**
 * 
 */
package main.java.net;

import main.java.ui.MainFrame;

/**
 * 
 *
 * @author Michael Massarelli
 */
public class RefreshTask implements Runnable {
    public static final String TAG = "RefreshTask";

    /* (non-Javadoc)
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
	MainFrame.mainPanel.refresh();
    }
}
