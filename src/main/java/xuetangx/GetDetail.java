package main.java.xuetangx;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class GetDetail {

	public static Vector<String> urls=new Vector<String>();
	public static FileWriter fw = null;
	public static PrintWriter pw = null;
	public synchronized static String getURL() {
		String url = null;
		if (GetDetail.urls.size() > 0) {
			url = GetDetail.urls.get(0);
			GetDetail.urls.remove(0);
			return url;
		}
		return url;
	}
	public synchronized static void write(List<String> result)
	{
		String line = "";
		
		for(String str : result)
		{
			
			line +=str+ "\t";
		}
		GetDetail.pw.println(line);
		GetDetail.pw.flush();
	}
	public static void main(String[] args) throws IOException, InterruptedException {
		BufferedReader br = null;
		GetDetail.fw = new FileWriter("./xuetangx.tsv");
		GetDetail.pw = new PrintWriter(fw);
		try {
			br = new BufferedReader(new FileReader("./xuetangx.url"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String line = null;
		try {
			while((line = br.readLine())!=null)
			{
				GetDetail.urls.add(line);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int threadCount = 10;
		CThread[] c = new CThread[threadCount];
		Thread[] t = new Thread[threadCount];
		for(int i=0;i<threadCount;i++)
		{
			c[i] = new CThread();
			t[i] = new Thread(c[i]);
			t[i].start();
		}
		for(int i=0;i<threadCount;i++)
		{
			t[i].join();
		}
		
	}
}

class CThread implements Runnable {

	public void run() {
		String url = null;
		while ((url = GetDetail.getURL()) != null) {
			Document document = null;
			try {
				document = Jsoup
						.connect(
								url)
						.userAgent(
								"Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
						.referrer("http://www.baidu.com").get();
				this.getResult(document, url);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	public void getResult(Document doc, String url)
	{
//		System.out.println(page.getWebURL().getURL() + " | "
//				+ page.getWebURL().getParentUrl());
		// String[] fields = new String[4];
		ArrayList<String> infoArray = new ArrayList<String>();
		Elements el = doc.select("h2[id=title1]");// title
		if (el.size() > 0) {
			infoArray.add(el.get(0).text());
		} else {
			
			el = doc.select("div[class=title]");
			if(el.size()>0)
			{
				infoArray.add(el.get(0).text());
			}
			else
			{
					
			System.out.println(url);
			infoArray.add("no title");
			}
		}
		//
		//
		//
//		el = doc.select("span[class=price]");// price
//		if (el.size() > 0) {
//			infoArray.add(el.get(0).text());
//		} else 
		{
			infoArray.add("no price");
		}
		//
		el = doc.select("div[class=fl name] h3");// teacher name
		if (el.size() > 0) {
			infoArray.add(el.get(0).text());
		} else 
		{
			infoArray.add("no teacher name");
		}
		//
		//
//		el = doc.select("span[class=renqi]");// teacher_rating
//		if (el.size() > 0) {
//			infoArray.add(el.get(0).text());
//		} else 
		{
			infoArray.add("no teacher rating");
		}
		//
		el = doc.select("div[class=fl name] p");// teacher desc
		if (el.size() > 0) {
			infoArray.add(el.get(0).text());
		} else 
		{
			infoArray.add("no teacher desc");
		}
		//
//		el = doc.select("div[class=crumbs]");// category path
//		if (el.size() > 0) {
//			infoArray.add(el.get(0).text());
//		} else
		{
			infoArray.add("no category path");
		}
		//
		el = doc.select("div[class=left fl] img");// picture
		if (el.size() > 0) {
			infoArray.add(el.get(0).attr("src"));
		} else 
		{
			infoArray.add("no picture");//picture is in parent page
		}
		//
//		el = doc.select("div[class=mar-l30]");// start date
//		if (el.size() > 0) {
//			infoArray.add(el.get(0).text().split("|")[0]);
//		} else 
		{
			infoArray.add("No start date");// start date
		}
		//
		//
//		 el = doc.select("div[class=timeCon]");//end date
//		 if(el.size()>0)
//		 {
//		 infoArray.add(el.get(0).text().split("|")[1]);
//		 }
//		 else
		{
			infoArray.add("no end date");
		}
		//
//		 el = doc.select("div[class=timebox]");//video length same
//		 if(el.size()>0)
//		 {
//		 infoArray.add(el.get(0).text());
//		 }
//		 else
		{
			infoArray.add("no video length");
		}
		//
		 el = doc.select("div[class=time]");//course hour
		 if(el.size()>0)
		 {
		 infoArray.add(el.size()+"");
		 }
		 else
		{
			infoArray.add("no course hour");
		}
		//
		//
		// el = doc.select("span:contains(担保期) em");//expire date
		// if(el.size()>0)
		// {
		// infoArray.add(el.get(0).text());
		// }
		// else
		{
			infoArray.add("no expire date");
		}
		//
		infoArray.add("no 3rd platform");// no 3rd platform
		//
//		 el = doc.select("div[class=tutor-name] a");//school url
//		 if(el.size()>0)
//		 {
//		 infoArray.add(el.get(0).attr("href"));
//		 }
//		 else
		{
			infoArray.add("no school URL");
		}
		//
		//
//		 el = doc.select("div[class=mbd] h3");//school name
//		 if(el.size()>0)
//		 {
//		 infoArray.add(el.get(0).text());
//		 }
//		 else
		{
			infoArray.add("no school name");
		}
		//
		//
		// el = doc.select("div[class=tb-property-cont course-time]");//type
		// if(el.size()>0)
		// {
		// infoArray.add(el.get(0).text());
		// }
		// else
		{
			infoArray.add("no type");
		}
		//
//		 el = doc.select("span[class^=stars-]");//rate
//		 if(el.size()>0)
//		 {
//		 infoArray.add(el.get(0).attr("class"));
//		 }
//		 else
		{
			infoArray.add("no rate");
		}
		//
//		 el = doc.select("li[class=tab4]:contains(评价) h4");//comments count 
//		 if(el.size()>0)
//		 {
//		 infoArray.add(el.get(0).text());
//		 }
//		 else
		{
			infoArray.add("no comments_count");
		}
		//
		 el = doc.select("p[class=num]");//enrolled_count
		 if(el.size()>0)
		 {
		 infoArray.add(el.get(0).text());
		 }
		 else
		{
			infoArray.add("no enrolled_count");
		}
		// detailContentLeft
		 el = doc.select("section:contains(课程简介)");//desc 
		 if(el.size()>0)
		 {
		 infoArray.add(el.get(0).text());
		 }
		 else
		{
		infoArray.add("no desc");// desc ajax by
		}						

		el = doc.select("scetion[id=course_list]");// outline
		if (el.size() > 0) {
			infoArray.add(el.get(0).text());
		} 
		else 
		{
			infoArray.add("no outline");
		}

		infoArray.add(url);// video URL
		//
//		el = doc.select("dl[class=CourseTabIntro]");// intro
//		if (el.size() > 0) {
//			infoArray.add(el.get(0).text());
//		} 
//		else
		{
			infoArray.add("no intro");
		}

//		el = doc.select("ul[class=CRL_Students] li");// purchased_count
//		if (el.size() > 0) {
//			infoArray.add(el.size()+"");
//		} else 
		{
			infoArray.add("no purchased_count");
		}
		System.out.println(infoArray);
		 GetDetail.write(infoArray);
	}
}