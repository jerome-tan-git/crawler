package main.java.wangyi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

public class MainThread {
	public static Vector<String> urlPool = new Vector();
	public static FileWriter fw;
	public static PrintWriter pw;
	public synchronized  static  void result(String line)
	{
		if(MainThread.pw!=null)
		{
			pw.println(line);
			pw.flush();
		}
	}
	
	public synchronized  static  String getURL()
	{
		String url = null;
		if(MainThread.urlPool.size()>0)
		{
			url = urlPool.get(0);
			urlPool.remove(0);
		}
		return url;
	}
	public static void main(String[] args) throws InterruptedException, IOException
	{
		MainThread.fw = new FileWriter("./wangyi.tsv");
		MainThread.pw = new PrintWriter(MainThread.fw);
		
		BufferedReader br = new BufferedReader(new FileReader("./url.txt"));
		String line = null;
		while((line = br.readLine())!=null)
		{
			MainThread.urlPool.add(line);
		}
		int threadCount = 10;
		CrawlThread[] ct = new CrawlThread[threadCount];
		Thread[] t = new Thread[threadCount];
		for(int i=0;i<threadCount;i++)
		{
			ct[i] = new CrawlThread();
			t[i] = new Thread(ct[i]);
			Thread.sleep(1000);
			t[i].start();
		}
		
		for(int i=0;i<threadCount;i++)
		{
			t[i].join();
		}
	}
}
