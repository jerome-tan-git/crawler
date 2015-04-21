package main.java;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import edu.uci.ics.crawler4j.crawler.Page;

public class xuetangdetal implements ISites {
	private final static Pattern SESSION = Pattern.compile(
			"http://www.xuetangx.com/.+",
			Pattern.CASE_INSENSITIVE);

	public String[] getURLSeed() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("./xuetangx.url"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> urls = new ArrayList<String>();
		String line = null;
		try {
			while((line = br.readLine())!=null)
			{
				urls.add(line);
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] result = new String[urls.size()];
		
		for(int i=0;i<result.length;i++)
		{
			result[i] = urls.get(i);
		}
		// TODO Auto-generated method stub
		return result;
	}

	public String outputFileName() {
		// TODO Auto-generated method stub
		return "./xuetang.tsv";
	}

	public boolean shouldVisit(String href) {
		// TODO Auto-generated method stub
		return SESSION.matcher(href).matches();
	}

	public String[] getFields(Document doc, Page page) {
		System.out.println(page.getWebURL().getURL() + " | "
				+ page.getWebURL().getParentUrl());
		// String[] fields = new String[4];
		ArrayList<String> infoArray = new ArrayList<String>();
		Elements el = doc.select("h1[class=title]");// title
		if (el.size() > 0) {
			infoArray.add(el.get(0).text());
		} else {
			infoArray.add("no title");
		}
		//
		//
		//
		el = doc.select("span[class=price]");// price
		if (el.size() > 0) {
			infoArray.add(el.get(0).text());
		} else 
		{
			infoArray.add("no price");
		}
		//
		el = doc.select("div[class=nickname]");// teacher name
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
		el = doc.select("div[class=about]");// teacher desc
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
		el = doc.select("div[class=col-sm-5 picture] img[class=img-responsive]");// picture
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
		 el = doc.select("div[class=course-item-list-multi] li");//course hour
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
		// el = doc.select("span:contains(µ£±£ÆÚ) em");//expire date
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
		 el = doc.select("span[class^=stars-]");//rate
		 if(el.size()>0)
		 {
		 infoArray.add(el.get(0).attr("class"));
		 }
		 else
		{
			infoArray.add("no rate");
		}
		//
//		 el = doc.select("li[class=tab4]:contains(ÆÀ¼Û) h4");//comments count 
//		 if(el.size()>0)
//		 {
//		 infoArray.add(el.get(0).text());
//		 }
//		 else
		{
			infoArray.add("no comments_count");
		}
		//
		 el = doc.select("div[class=member-num]");//enrolled_count
		 if(el.size()>0)
		 {
		 infoArray.add(el.get(0).text());
		 }
		 else
		{
			infoArray.add("no enrolled_count");
		}
		// detailContentLeft
		 el = doc.select("div[id=course-content]");//desc 
		 if(el.size()>0)
		 {
		 infoArray.add(el.get(0).text());
		 }
		 else
		{
		infoArray.add("no desc");// desc ajax by
		}						

		el = doc.select("div[class=course-item-list-multi]");// outline
		if (el.size() > 0) {
			infoArray.add(el.get(0).text());
		} 
		else 
		{
			infoArray.add("no outline");
		}

		infoArray.add(page.getWebURL().getURL());// video URL
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
		String[] result = new String[infoArray.size()];
		for (int i = 0; i < infoArray.size(); i++) {
			result[i] = infoArray.get(i);
		}

		return result;
	}

	public int getCrawlDepth() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isParseURL(String url) {
		// TODO Auto-generated method stub
		return SESSION.matcher(url).matches();
	}

	public int crawlerCount() {
		// TODO Auto-generated method stub
		return 5;
	}
}
