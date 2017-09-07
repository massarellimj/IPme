/**
 * 
 */
package main.java.tray;

import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

import main.java.Main;
import main.java.net.IPUtils;

/**
 * 
 *
 * @author Michael Massarelli
 */
public class TrayTooltip {
    public static final String TAG = "TrayTooltip";
    
    public static String getTooltip() {
	return "Local:" + getSpaces("Local:" + IPUtils.getLocalIP()) + IPUtils.getLocalIP() + '\n' +
		"IPv4:" + getSpaces("IPv4" + IPUtils.getExternalIP()) + IPUtils.getExternalIP() + '\n' + 
		"IPv6:  " + IPUtils.getIPv6();
    }
    
    
    private static String getSpaces(String string) {
	Font f = Main.frame.getFont();
	FontRenderContext frc = new FontRenderContext(new AffineTransform(),true,true);
	String s = "";
	int i = (int)f.getStringBounds(IPUtils.getIPv6() + "IPv6:  ", frc).getWidth() - (int)f.getStringBounds(string,frc).getWidth();
	System.out.println(i);
	while ((int)f.getStringBounds(s, frc).getWidth() < i) {
	    s = s + " ";
	}
	return s;
    }
}
