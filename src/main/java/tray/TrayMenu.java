/**
 * 
 */
package main.java.tray;

import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.java.Main;

/**
 * 
 *
 * @author Michael Massarelli
 */
@SuppressWarnings("serial")
public class TrayMenu extends PopupMenu {
    public static final String TAG = "TrayMenu";
    
    private MenuItem exit, restore;
    
    public TrayMenu() {
	super();
	restore = new MenuItem("Restore");
	restore.addActionListener(trayLis);
	exit = new MenuItem("Exit");
	exit.addActionListener(trayLis);
	add(restore);
	add(exit);
    }
    
    ActionListener trayLis = new ActionListener() {
	@Override
	public void actionPerformed(ActionEvent e) {
	    if (((MenuItem)e.getSource()).getLabel() == "Restore") {
		Main.frame.setFrameVisible(true);
		IPmeTray.restore();
	    } else {
		System.exit(0);
	    }
	}
    };
}
