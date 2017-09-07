/**
 * 
 */
package main.java.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import main.java.Main;
import main.java.tray.IPmeTray;

/**
 * 
 *
 * @author Michael Massarelli
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame {
    public static final String TAG = "MainFrame";
    
    public static MainFrame frame;
    public static MainPanel mainPanel;
    
    
    public MainFrame() {
	super("IPme");
	MainFrame.frame = this;
	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	setPreferredSize(new Dimension(340, 230));
	if (!Main.debug) {
	    try {
		setIconImage(ImageIO.read(ClassLoader.getSystemResource("resource/logo.gif")).getScaledInstance(20, 20, 0));
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
	init();
	pack();
	setLocationRelativeTo(null);
	setResizable(false);
	addWindowListener(new WindowAdapter() {
	    @Override
	    public void windowClosing(WindowEvent e) {
		IPmeTray.hideToTray();
	    }
	    @Override
	    public void windowIconified(WindowEvent e) {
		IPmeTray.hideToTray();
	    }
	});
    }
    
    public void setFrameVisible(boolean b) {
	final boolean vis = b;
	SwingUtilities.invokeLater(new Runnable() {
	    public void run() {
		setVisible(vis);
	    }
	});
    }


    /**
     * 
     */
    private void init() {
	mainPanel = new MainPanel();
	getContentPane().setLayout(new BorderLayout());
	if (Main.subs) {
	    ((BorderLayout)getContentPane().getLayout()).setHgap(20);
	    ((BorderLayout)getContentPane().getLayout()).setVgap(10);
	} else {
	    ((BorderLayout)getContentPane().getLayout()).setHgap(10);
	    ((BorderLayout)getContentPane().getLayout()).setVgap(5);
	}
	getContentPane().add(mainPanel, "Center");
	setJMenuBar(new IpMenu(this));
    }
    
}
