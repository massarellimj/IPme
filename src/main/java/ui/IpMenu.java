/**
 * 
 */
package main.java.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import main.java.Main;
import main.java.tray.IPmeTray;

/**
 * 
 *
 * @author Michael Massarelli
 */
@SuppressWarnings("serial")
public class IpMenu extends JMenuBar {
    public static final String TAG = "IpMenu";
    
    private JMenu settings, view;
    private JMenuItem refresh;
    private JMenuItem toTray;
    private JMenuItem network, info;
    
    public IpMenu(MainFrame frame) {
	super();
	view = new JMenu("View");
	network = new JMenuItem("Network");
	network.setMnemonic('N');
	network.setAccelerator(KeyStroke.getKeyStroke('N', InputEvent.CTRL_DOWN_MASK));
	network.addActionListener(viewLis);
	info = new JMenuItem("Information");
	info.setMnemonic('I');
	info.setAccelerator(KeyStroke.getKeyStroke('I', InputEvent.CTRL_DOWN_MASK));
	info.addActionListener(viewLis);
	settings = new JMenu("Settings");
	refresh = new JMenuItem("Refresh");
	refresh.setMnemonic('R');
	refresh.setAccelerator(KeyStroke.getKeyStroke("F5"));
	refresh.addActionListener(setttingsLis);
	toTray = new JMenuItem("Hide to System Tray");
	toTray.setMnemonic('H');
	toTray.setAccelerator(KeyStroke.getKeyStroke('H', InputEvent.CTRL_DOWN_MASK));
	toTray.addActionListener(setttingsLis);
	settings.add(refresh);
	settings.addSeparator();
	settings.add(toTray);
	view.add(info);
	view.add(network);
	
	//add(view);
	add(settings);
    }
    
    ActionListener setttingsLis = new ActionListener() {
	@Override
	public void actionPerformed(ActionEvent e) {
	    if (((JMenuItem)e.getSource()).getText() == "Hide to System Tray") {
		IPmeTray.hideToTray();
	    } else {
		MainFrame.mainPanel.refresh();
	    }
	    Thread[] threads = {};
	    Thread.currentThread().getThreadGroup().enumerate(threads);
	    for (Thread t :threads) {
		System.out.println(t.getName());
	    }
	}
	
    };
    
    ActionListener viewLis = new ActionListener() {
	@Override
	public void actionPerformed(ActionEvent e) {
	    if (((JMenuItem)e.getSource()).getText() == "Information") {
		if (!Main.frame.getContentPane().equals(new MainPanel())) {
		    Main.frame.setContentPane(new MainPanel());
		}
	    } else {
		if (!Main.frame.getContentPane().equals(new Network())) {
		    Main.frame.setContentPane(new Network());
		}
	    }
	    Main.frame.revalidate();
	    Main.frame.repaint();
	    Thread[] threads = {};
	    Thread.currentThread().getThreadGroup().enumerate(threads);
	    for (Thread t :threads) {
		System.out.println(t.getName());
	    }
	}
	
    };
}
