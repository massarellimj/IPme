/**
 * 
 */
package main.java.tray;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import main.java.Main;

/**
 * 
 *
 * @author Michael Massarelli
 */
public class IPmeTray {
    public static final String TAG = "IPmeTray";
    
    static TrayIcon ti;
    
    public static void hideToTray() {
	Main.frame.setFrameVisible(false);
	Image img = null;
	if (!Main.debug) {
	    try {
		img = ImageIO.read(ClassLoader.getSystemResource("resource/logo.gif")).getScaledInstance(20, 20, 0);
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	} else {
	    try {
		img = ImageIO.read(new File("M:/personal/prg/java/IPme/res/resource/logo.gif")).getScaledInstance(20, 20, 0);
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
	ti = new TrayIcon(img, "IPme", new TrayMenu());
	ti.setImageAutoSize(true);
	ti.setToolTip(TrayTooltip.getTooltip());
	try {
	    SystemTray.getSystemTray().add(ti);
	} catch (AWTException e) {
	    e.printStackTrace();
	}
    }
    
    public static void restore() {
	Main.frame.setFrameVisible(true);
	Main.frame.setExtendedState(JFrame.NORMAL);
	SystemTray.getSystemTray().remove(ti);
    }
    
    public static String getBasePath() {
	return new File(".").getAbsolutePath();
    }
}
