/**
 * 
 */
package main.java.ui;

import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import main.java.Main;
import main.java.net.IPUtils;

/**
 * 
 *
 * @author Michael Massarelli
 */
@SuppressWarnings("serial")
public class MainPanel extends JPanel {
    public static final String TAG = "MainPanel";

    
    
    public MainPanel() {
	super(new FlowLayout(FlowLayout.CENTER));
	if (Main.subs) 
	    setBorder(new MatteBorder(1,0,0,0,getForeground()));
	init();
    }


    /**
     * 
     */
    private void init() {	
	add(new Label(" Local Host:", 100));
	add(new Label(IPUtils.getHostName() + "  (127.0.0.1)","Computer Name",150));
	add(new Label(" Domain:", 100));
	add(new Label(IPUtils.getDomain(), "Network Name", 150));
	add(new Label(" Local IP:", 100));
	add(new Label(IPUtils.getLocalIP(), "Local Network IP Address",150));
	add(new Label(" IPv4:", 100));
	add(new Label(IPUtils.getExternalIP(), "External IP Address", 150));
	add(new Label(" IPv6:", 100));
	add(new Label(IPUtils.getIPv6(), 150));
	add(new Label("MAC Address:", 100));
	add(new Label(IPUtils.getAddress("mac"), 150));
    }
    
    public void refresh() {
	((Label)getComponent(1)).setText(IPUtils.getHostName() + "  (127.0.0.1)");
	((Label)getComponent(3)).setText(IPUtils.getDomain());
	((Label)getComponent(5)).setText(IPUtils.getLocalIP());
	((Label)getComponent(7)).setText(IPUtils.getExternalIP());
	((Label)getComponent(9)).setText(IPUtils.getIPv6());
	
    }
    
}
