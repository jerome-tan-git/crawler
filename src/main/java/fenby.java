package main.java;

import java.util.ArrayList;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import edu.uci.ics.crawler4j.crawler.Page;

public class fenby implements ISites {

	private final static Pattern SESSION = Pattern.compile(
			"http://edu\\.yy\\.com/course/detail\\?id=[0-9]+",
			Pattern.CASE_INSENSITIVE);

	public String[] getURLSeed() {
		// http://www.chuanke.com/course/72351163642544128______2.html?page=1
		int pageLen = 11;// 11
		String[] pages = new String[pageLen];
		for (int i = 1; i <= pageLen; i++) {
			pages[i - 1] = "http://edu.yy.com/course?type=12&listType=1&priceDesc=1&pageNumber=" + i;
		}
		// TODO Auto-generated method stub
		return pages;
	}

	public String outputFileName() {
		// TODO Auto-generated method stub
		return "./yy.tsv";
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
		Elements el = doc.select("div[class=content] h2");// title
		if (el.size() > 0) {
			infoArray.add(el.get(0).text());
		} else {
			infoArray.add("no title");
		}
		//
		//
		//
		el = doc.select("span[class=count]");// price
		if (el.size() > 0) {
			infoArray.add(el.get(0).text());
		} else {
			infoArray.add("no price");
		}
		//
		el = doc.select("p[class=nameCon]");// teacher name
		if (el.size() > 0) {
			infoArray.add(el.get(0).text());
		} else {
			infoArray.add("no teacher name");
		}
		//
		//
		el = doc.select("div[class=fenshu] span:eq(1)");// teacher_rating
		if (el.size() > 0) {
			infoArray.add(el.get(0).text());
		} else {
			infoArray.add("no teacher rating");
		}
		//
		el = doc.select("div[class=mbd] p");// teacher desc
		if (el.size() > 0) {
			infoArray.add(el.get(0).text());
		} else {
			infoArray.add("no teacher desc");
		}
		//
		el = doc.select("div[class=breadCrumbs]");// category path
		if (el.size() > 0) {
			infoArray.add(el.get(0).text());
		} else {
			infoArray.add("no category path");
		}
		//
		el = doc.select("img[class=bigPic]");// picture
		if (el.size() > 0) {
			infoArray.add(el.get(0).attr("src"));
		} else {
			infoArray.add("no picture");
		}
		//
		el = doc.select("div[class=timeCon]");// start date
		if (el.size() > 0) {
			infoArray.add(el.get(0).text().split("|")[0]);
		} else {
			infoArray.add("No start date");// start date
		}
		//
		//
		 el = doc.select("div[class=timeCon]");//end date
		 if(el.size()>0)
		 {
		 infoArray.add(el.get(0).text().split("|")[1]);
		 }
		 else
		{
			infoArray.add("no end date");
		}
		//
		 el = doc.select("div[class=picCon] p");//video length same
		 if(el.size()>0)
		 {
		 infoArray.add(el.get(0).text());
		 }
		 else
		{
			infoArray.add("no video length");
		}
		//
//		 el = doc.select("div[class=CourseImg] p");//course hour
//		 if(el.size()>0)
//		 {
//		 infoArray.add(el.get(0).text());
//		 }
//		 else
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
		 el = doc.select("div[class=picCon] a");//school url
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
		 el = doc.select("div[class=mbd] h3");//school name
		 if(el.size()>0)
		 {
		 infoArray.add(el.get(0).text());
		 }
		 else
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
		 el = doc.select("span[class=fs]");//rate
		 if(el.size()>0)
		 {
		 infoArray.add(el.get(0).text());
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
		 el = doc.select("div[class^=applyCon] em");//enrolled_count
		 if(el.size()>0)
		 {
		 infoArray.add(el.get(0).text());
		 }
		 else
		{
			infoArray.add("no enrolled_count");
		}
		// detailContentLeft
		 el = doc.select("div[id=introduction]");//desc
		 if(el.size()>0)
		 {
		 infoArray.add(el.get(0).text());
		 }
		 else
		{
		infoArray.add("no desc");// desc ajax by
		}						

//		el = doc.select("li[id=course_lessions_lists]");// outline
//		if (el.size() > 0) {
//			infoArray.add(el.get(0).text());
//		} 
//		else 
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
