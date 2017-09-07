/**
 * 
 */
package main.java.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.java.net.IPUtils;
import main.java.net.Ping;

/**
 * 
 *
 * @author Michael Massarelli
 */
@SuppressWarnings("serial")
public class Network extends JPanel {
    public static final String TAG = "Network";
    
    private JScrollPane sp;
    private JList<String> list;
    private JLabel host, ip;
    private JPanel right;
    
    public Network() {
	super(new FlowLayout(FlowLayout.CENTER));
	((FlowLayout)getLayout()).setHgap(2);
	init();
    }

    /**
     * 
     */
    private void init() {
	sp = new JScrollPane();
	sp.setPreferredSize(new Dimension(180,130));
	sp.setBorder(new LineBorder(getForeground(), 1));
	String[] a = {};
	list = new JList<String>((String[]) IPUtils.getNames().toArray(a));
	list.addListSelectionListener(new ListSelectionListener() {
	    @Override
	    public void valueChanged(ListSelectionEvent e) {
		host.setText("Host: " + list.getSelectedValue());
		ip.setText("IP: " + Ping.ping(list.getSelectedValue()));
	    }
	});
	host = new JLabel("Host:");
	host.setPreferredSize(new Dimension(150,22));
	ip = new JLabel("IP:");
	ip.setPreferredSize(new Dimension(150,22));
	right = new JPanel(new FlowLayout());
	right.setPreferredSize(new Dimension(150,100));
	right.add(host);
	right.add(ip);
	sp.setViewportView(list);
	add(sp);
	add(right);
    }
}
