/**
 * 
 */
package main.java.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author Michael Massarelli
 */
public class IPUtils {
    public static final String TAG = "IPUtils";
    /*
     * Get Local Information (Host, Domain, IP)
     * Get Server Information (Name, IP)
     * 
     */

    public static String getExternalIP() {
	URL myIP;
	try {
	    myIP = new URL("http://checkip.amazonaws.com");
	    BufferedReader in = new BufferedReader(new InputStreamReader(myIP.openStream()));
	    return in.readLine();
	} catch (SocketTimeoutException se) {
	    System.err.println("Socket Timeout");
	    se.printStackTrace();
	} catch (IOException ie) {
	    
	}
	return null;
    }

    public static String getLocalIP() {
	try {
	    return InetAddress.getLocalHost().getHostAddress();
	} catch (UnknownHostException e) {
	    e.printStackTrace();
	}
	return null;
    }

    public static String getDomain() {
	try {
	    return InetAddress.getLocalHost().getCanonicalHostName().substring(InetAddress.getLocalHost().getCanonicalHostName().indexOf('.') + 1);
	} catch (UnknownHostException e) {
	    e.printStackTrace();
	}
	return null;
    }

    public static String getDomainExternalIP() {
	try {
	    return InetAddress.getByName(getDomain()).getHostAddress();
	} catch (UnknownHostException e) {
	    e.printStackTrace();
	}
	return null;
    }

    public static String getHostName() {
	try {
	    return InetAddress.getLocalHost().getHostName();
	} catch (UnknownHostException e) {
	    e.printStackTrace();
	}
	return null;
    }

    public static void checkHosts(String subnet){
	try {
	    int timeout=1000;
	    for (int i=1;i<255;i++){
		String host=subnet + "." + i;
		if (InetAddress.getByName(host).isReachable(timeout)){
		    System.out.println(host + " is reachable");
		    //System.out.println(InetAddress.getByAddress(host.getBytes()).getHostName());
		}
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
    
    public static void getIPv62() {
	try {
	    Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
	    while (nis.hasMoreElements()) {
		NetworkInterface next = nis.nextElement();
		Enumeration<InetAddress> ias = next.getInetAddresses();
		if (ias.hasMoreElements()) {
		    System.out.println(next.getName());
		    System.out.println(next.getDisplayName());
		    while (ias.hasMoreElements()) {
			InetAddress n = ias.nextElement();
			if (n.getHostAddress().contains("%")) {
			    System.out.println(n.getHostAddress().substring(0, n.getHostAddress().lastIndexOf('%')));
			} else {
			    System.out.println(n.getHostAddress());
			}
		    }
		    System.out.println("--------------------");
		}
	    }
	} catch (SocketException e) {
	    e.printStackTrace();
	}
    }
    
    public static String getIPv6() {
	try {
	    Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
	    while (nis.hasMoreElements()) {
		NetworkInterface local = nis.nextElement();
		if (local.getDisplayName().contains("Microsoft")) {
		    Enumeration<InetAddress> loc = local.getInetAddresses();
		    String host =  loc.nextElement().getHostAddress();
		    return host.contains("%") ? host.substring(0, host.indexOf('%')) : host;
		}
	    }
	} catch (SocketException e) {
	    e.printStackTrace();
	}
	return null;
    }

    public static List<NetworkInterface> getNetworkInterfaces() {
	List<NetworkInterface> interfaces = new ArrayList<NetworkInterface>();
	try {
	    Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
	    while (nis.hasMoreElements()) {
		NetworkInterface next = nis.nextElement();
		if (next.getInetAddresses().hasMoreElements()) {
		    interfaces.add(next);
		}
	    }
	} catch (SocketException e) {
	    e.printStackTrace();
	}
	return interfaces;
    }
    
    public static String[] getNetworkInterfaces2() {
	String[] a = {};
	List<String> interfaces = new ArrayList<String>();
	try {
	    Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
	    while (nis.hasMoreElements()) {
		NetworkInterface next = nis.nextElement();
		if (next.getInetAddresses().hasMoreElements()) {
		    interfaces.add(next.getDisplayName());
		}
	    }
	} catch (SocketException e) {
	    e.printStackTrace();
	}
	return interfaces.toArray(a);
    }
    
    public static void main(String[] args) {
	for (NetworkInterface s : getNetworkInterfaces()) {
	    Enumeration<InetAddress> i = s.getInetAddresses();
	    while (i.hasMoreElements()) {
		System.out.println(i.nextElement());
	    }
	}
    }

    public static void showActiveLan() {
	try {
	    Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();
	    while(nis.hasMoreElements())
	    {
		Object iface = nis.nextElement();
		System.out.println(iface.toString());
		NetworkInterface ni = (NetworkInterface) iface;
		Enumeration<InetAddress> ias = ni.getInetAddresses();
		while (ias.hasMoreElements())
		{
		    InetAddress ia = (InetAddress) ias.nextElement();
		    System.out.println(ia.getHostAddress());
		}

	    }
	} catch (SocketException ex) {
	    ex.printStackTrace();
	}
    }

    public static List<String> getNames() {
	List<String> names = new ArrayList<String>();
	Process process;
	try {
	    process = Runtime.getRuntime().exec("net view");
	    InputStream in=process.getInputStream();
	    BufferedReader br = null;
	    String line;
	    try {
		br = new BufferedReader(new InputStreamReader(in));
		while ((line = br.readLine()) != null) {
		    if (line.startsWith("\\")) {
			line = line.substring(2);
			if (line.contains(" ")) {
			    line = line.substring(0, line.indexOf(" "));
			    names.add(line);
			}
		    }
		}
	    } catch (IOException e) {
		e.printStackTrace();
	    } finally {
		if (br != null) {
		    try {
			br.close();
		    } catch (IOException e) {
			e.printStackTrace();
		    }
		}
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return names;
    }
    
    public static void byName(String name) {
	try {
	      InetAddress host;
	      if (name.length() == 0) {
	        host = InetAddress.getLocalHost();
	      } else {
	        host = InetAddress.getByName(name);
	      }
	      System.out.println("Host:'" + host.getHostName()
	          + "' has address: " + host.getHostAddress());

	    } catch (UnknownHostException e) {
	      e.printStackTrace();
	    }
    }
    
    
    public static InetAddress getLanIP() {
	InetAddress lanIp = null;
	String ipAddress = null;
        try {
            Enumeration<NetworkInterface> net = null;
	    net = NetworkInterface.getNetworkInterfaces();
	    while (net.hasMoreElements()) {
	            NetworkInterface element = net.nextElement();
	            Enumeration<InetAddress> addresses = element.getInetAddresses();
	            while (addresses.hasMoreElements() && !isVMMac(element.getHardwareAddress())) {
	                InetAddress ip = addresses.nextElement();
	                if (ip instanceof Inet4Address) {
	                    if (ip.isSiteLocalAddress()) {
	                        ipAddress = ip.getHostAddress();
	                        lanIp = InetAddress.getByName(ipAddress);
	                    }
	                }
	            }
	    }
	} catch (SocketException | UnknownHostException e) {
	    e.printStackTrace();
	}
        return lanIp;
    }
    
    public static String getAddress(String addressType) {
        String address = "";
        InetAddress lanIp = getLanIP();
        if (lanIp == null) return null;
	if (addressType.equals("ip")) {
	    address = lanIp.toString().replaceAll("^/+", "");
	} else if (addressType.equals("mac")) {
	    address = getMacAddress(lanIp);
	}
        return address;
    }
    
    private static String getMacAddress(InetAddress ip) {
        String address = null;
        try {
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            byte[] mac = network.getHardwareAddress();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
            address = sb.toString();
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return address;
    }
    
    private static boolean isVMMac(byte[] mac) {
        if(null == mac) return false;
        byte invalidMacs[][] = {
                {0x00, 0x05, 0x69},             //VMWare
                {0x00, 0x1C, 0x14},             //VMWare
                {0x00, 0x0C, 0x29},             //VMWare
                {0x00, 0x50, 0x56},             //VMWare
                {0x08, 0x00, 0x27},             //Virtualbox
                {0x0A, 0x00, 0x27},             //Virtualbox
                {0x00, 0x03, (byte)0xFF},       //Virtual-PC
                {0x00, 0x15, 0x5D}              //Hyper-V
        };
        for (byte[] invalid: invalidMacs){
            if (invalid[0] == mac[0] && invalid[1] == mac[1] && invalid[2] == mac[2]) return true;
        }
        return false;
    }
}
