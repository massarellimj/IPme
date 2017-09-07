/**
 * 
 */
package main.java;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import main.java.net.RefreshTask;
import main.java.ui.MainFrame;

/**
 * 
 *
 * @author Michael Massarelli
 */
public class Main {
    public static final String TAG = "Main";

    public static MainFrame frame;
    public static boolean debug = true;
    public static boolean subs = false;
    /**
     * @param args
     */
    public static void main(String[] args) {
	if (subs) {
	    JFrame.setDefaultLookAndFeelDecorated(true);
	    JDialog.setDefaultLookAndFeelDecorated(true);

	    try {
		SubstanceLookAndFeel.setSkin("org.pushingpixels.substance.api.skin.GraphiteAquaSkin");
	    } catch (Exception e) {
		try {
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
		    e1.printStackTrace();
		} catch (InstantiationException e1) {
		    e1.printStackTrace();
		} catch (IllegalAccessException e1) {
		    e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
		    e1.printStackTrace();
		}
	    }
	} else {
	    try {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
		    | UnsupportedLookAndFeelException e) {
		e.printStackTrace();
	    }
	}

	SwingUtilities.invokeLater(new Runnable() {
	    public void run() {
		frame = new MainFrame();
		frame.setFrameVisible(true);
	    }
	});

	ScheduledThreadPoolExecutor periodicTasksExecutor = new ScheduledThreadPoolExecutor(1, new ThreadFactoryBuilder().build());
	periodicTasksExecutor.scheduleAtFixedRate(new RefreshTask(), 5, 30, TimeUnit.SECONDS);	
    }
}
