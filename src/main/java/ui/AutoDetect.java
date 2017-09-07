/**
 * 
 */
package main.java.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.java.net.IPUtils;

/**
 * 
 *
 * @author Michael Massarelli
 */
@SuppressWarnings("serial")
public class AutoDetect extends JDialog {
    public static final String TAG = "AutoDetect";
    
    public List<NetworkInterface> interfaces = IPUtils.getNetworkInterfaces();
    private JList<Object> bigList, rlist;
    private JButton ok, cancel;
    private JScrollPane sp;
    private JPanel right, buttons;
    private List<List<String>> list;
    
    public AutoDetect() {
	super();
	setTitle("Auto Detect");
	init();
	pack();
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	setVisible(true);
    }

    /**
     * 
     */
    private void init() {
	sp = new JScrollPane();
	JPanel center = new JPanel(new FlowLayout(FlowLayout.CENTER));
	right = new JPanel(new GridLayout(6,1,5,5));
	right.setPreferredSize(new Dimension(200,160));
	list = new ArrayList<List<String>>();
	rlist = new JList<Object>();
	rlist.setPreferredSize(new Dimension(100,100));
	List<String> iNames = new ArrayList<String>();
	for (NetworkInterface ni : interfaces) {
	    iNames.add(ni.getDisplayName());
	    List<String> n = new ArrayList<String>();
	    Enumeration<InetAddress> e = ni.getInetAddresses();
	    while (e.hasMoreElements()) {
		String ad = e.nextElement().toString().substring(1);
		n.add(ad.contains("%")?ad.substring(0, ad.lastIndexOf('%')):ad);
	    }
	    list.add(n);
	}
	bigList = new JList<Object>(iNames.toArray());
	bigList.addListSelectionListener(new ListSelectionListener() {
	    @Override
	    public void valueChanged(ListSelectionEvent e) {
		right.removeAll();
		for (String s : list.get(bigList.getSelectedIndex())) {
		    right.add(new JLabel(s));
		}
		right.revalidate();
		right.repaint();
	    }
	});
	sp.setViewportView(bigList);
	sp.setPreferredSize(new Dimension(220,160));
	
	ok = new JButton("OK");
	ok.setPreferredSize(new Dimension(100,24));
	ok.addActionListener(buttonLis);
	cancel = new JButton("Cancel");
	cancel.setPreferredSize(new Dimension(90,24));
	cancel.addActionListener(buttonLis);
	buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	buttons.setPreferredSize(new Dimension(320,34));
	buttons.add(ok);
	buttons.add(cancel);
	
	center.add(sp);
	center.add(right);
	getContentPane().add(center, "Center");
	getContentPane().add(buttons, "South");
	
    }
    
    ActionListener buttonLis = new ActionListener() {
	@Override
	public void actionPerformed(ActionEvent e) {
	    if (((JButton)e.getSource()).getText() == "OK") {
		System.out.println("OK CLICKED");
	    } else {
		dispose();
	    }
	}
    };
    
    public static void main(String[] args) {
	new AutoDetect();
    }
    
    
}
