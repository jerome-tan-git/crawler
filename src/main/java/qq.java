package main.java;

import java.util.ArrayList;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import edu.uci.ics.crawler4j.crawler.Page;

public class qq implements ISites {

	private final static Pattern SESSION = Pattern.compile(
			"http://ke\\.qq\\.com/cgi\\-bin/courseDetail\\?course_id=[0-9]+",
			Pattern.CASE_INSENSITIVE);

	public String[] getURLSeed() {
		// http://www.chuanke.com/course/72351163642544128______2.html?page=1
		int pageLen = 108;// 108
		String[] pages = new String[pageLen];
		for (int i = 1; i <= pageLen; i++) {
			pages[i - 1] = "http://ke.qq.com/cgi-bin/courseList?mt=1002&page=" + i;
		}
		// TODO Auto-generated method stub
		return pages;
	}

	public String outputFileName() {
		// TODO Auto-generated method stub
		return "./qq.tsv";
	}

	public boolean shouldVisit(String href) {
		// TODO Auto-generated method stub
		return SESSION.matcher(href).matches();
	}

	public String[] getFields(Document doc, Page page) {
		// TODO Auto-generated method stub
		System.out.println(page.getWebURL().getURL() + " | "
				+ page.getWebURL().getParentUrl());
		// String[] fields = new String[4];
		ArrayList<String> infoArray = new ArrayList<String>();
		Elements el = doc.select("h1");// title
		if (el.size() > 0) {
			infoArray.add(el.get(0).text());
		} else {
			infoArray.add("no title");
		}
		//
		//
		//
		el = doc.select("span[class=mod-course-card__price_free]");// price
		if (el.size() > 0) {
			infoArray.add(el.get(0).text());
		} else {
			infoArray.add("no price");
		}
		//
		el = doc.select("em[class=school-name]");// teacher name
		if (el.size() > 0) {
			infoArray.add(el.get(0).text());
		} else {
			infoArray.add("no teacher name");
		}
		//
		//
		el = doc.select("ul[class=mod-side-bar-right__score] li");// teacher_rating
		if (el.size() > 0) {
			infoArray.add(el.get(0).text());
		} else {
			infoArray.add("no teacher rating");
		}
		//
//		el = doc.select("div[class=mbd] p");// teacher desc
//		if (el.size() > 0) {
//			infoArray.add(el.get(0).text());
//		} else 
		{
			infoArray.add("no teacher desc");
		}
		//
		el = doc.select("div[class=mod-breadcrumbs]");// category path
		if (el.size() > 0) {
			infoArray.add(el.get(0).text());
		} else {
			infoArray.add("no category path");
		}
		//
		el = doc.select("img[class=mod-course-banner__img]");// picture
		if (el.size() > 0) {
			infoArray.add(el.get(0).attr("src"));
		} else {
			infoArray.add("no picture");
		}
		//
//		el = doc.select("div[class=timeCon]");// start date
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
//		 el = doc.select("div[class=picCon] p");//video length same
//		 if(el.size()>0)
//		 {
//		 infoArray.add(el.get(0).text());
//		 }
//		 else
		{
			infoArray.add("no video length");
		}
		//
		 el = doc.select("div[class=mod-course-banner__social] span");//course hour
		 if(el.size()>0)
		 {
		 infoArray.add(el.get(0).text());
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
		 el = doc.select("a[class=mod-side-bar-right__school-title]");//school url
		 if(el.size()>0)
		 {
		 infoArray.add(el.get(0).attr("href"));
		 }
		 else
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
		 el = doc.select("span[class=mod-course-banner__star-wrap] i");//rate
		 if(el.size()>0)
		 {
		 infoArray.add(el.size()+"");
		 }
		 else
		{
			infoArray.add("no rate");
		}
		//
		 el = doc.select("li[id=js_comment_list]");//comments count 
		 if(el.size()>0)
		 {
		 infoArray.add(el.get(0).text());
		 }
		 else
		{
			infoArray.add("no comments_count");
		}
		//
		 el = doc.select("a[class=mod-course-banner__social] span:contains(人观看)");//enrolled_count
		 if(el.size()>0)
		 {
		 infoArray.add(el.get(0).text());
		 }
		 else
		{
			infoArray.add("no enrolled_count");
		}
		// detailContentLeft
		 el = doc.select("div[id=js_basic_tab]");//desc
		 if(el.size()>0)
		 {
		 infoArray.add(el.get(0).text());
		 }
		 else
		{
		infoArray.add("no desc");// desc ajax by
		}						

		el = doc.select("div[id=js_dir_tab]");// outline
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
		return 1;
	}

	public boolean isParseURL(String url) {
		// TODO Auto-generated method stub

		return SESSION.matcher(url).matches();
	}

	public int crawlerCount() {
		// TODO Auto-generated method stub
		return 10;
	}

}
