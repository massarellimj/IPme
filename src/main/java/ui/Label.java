/**
 * 
 */
package main.java.ui;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;


/**
 * 
 *
 * @author Michael Massarelli
 */
@SuppressWarnings("serial")
public class Label extends JLabel {
    public static final String TAG = "Label";
    
    
    public Label(String txt, int width, int horizontalAlignment, boolean enabled) {
	super(txt);
	if (horizontalAlignment < 5) {
	    setHorizontalAlignment(horizontalAlignment);
	    setPreferredSize(new Dimension(width, 22));
	}
	else {
	    setHorizontalAlignment(SwingConstants.LEFT);
	    setVerticalAlignment(SwingConstants.TOP);
	    setPreferredSize(new Dimension(width, horizontalAlignment));
	}
	setEnabled(enabled);
    }
    
    public Label(String txt, int width, int horizontalAlignment) {
	this(txt,width, horizontalAlignment, true);
    }
    
    public Label(String txt, int width) {
	this(txt, width, SwingConstants.LEFT, true);
    }
    
    public Label(String txt, String tool, int width) {
	this(txt, width, SwingConstants.LEFT, true);
	setToolTipText(tool);
    }
}
