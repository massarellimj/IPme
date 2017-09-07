package main.java.net;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class NameLookup implements Runnable
{
    byte[] ip;
    String[] names;
    int index;
    public NameLookup(byte[] ip,int index,String[] names)
    {
        this.ip = ip;
        this.names = names;
        this.index = index;
    }
    public void run()
    {
        try
        {
            InetAddress addr = InetAddress.getByAddress(ip);
            names[index]= addr.getHostName();

        }
        catch(UnknownHostException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public static String[] lookUp()
    {
        byte[] ip = {(byte)192,(byte)0,(byte)0,(byte)1};
        String[] names = new String[254];
        Thread threads[] = new Thread[254];

        for(int i=0;i<254;i++)
        {
            ip[3] = (byte)(i+1);
            threads[i] = new Thread(new NameLookup(ip,i,names));
            threads[i].start();
        }
        for(int i=0;i<254;i++)
        {
            try
            {
                threads[i].join();
            }
            catch (InterruptedException e) {
                System.out.print(e.getMessage());
            }
        }
        return names;
    }
    
    public static void main(String[] args) {
	List<String> list = new ArrayList<String>();
	for (String s : NameLookup.lookUp()) {
	    if (!list.contains(s)) {
		list.add(s);
	    }
	};
	for (String s1 : list) {
	    System.out.println(s1);
	}
    }
}