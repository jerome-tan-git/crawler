package main.java;

import java.util.ArrayList;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import edu.uci.ics.crawler4j.crawler.Page;

public class cto51 implements ISites {

	private final static Pattern SESSION = Pattern.compile(
			"http://edu\\.51cto\\.com/course/course_id-[0-9]+\\.html",
			Pattern.CASE_INSENSITIVE);

	public String[] getURLSeed() {
		// http://www.chuanke.com/course/72351163642544128______2.html?page=1
		int pageLen = 108;// 108
		String[] pages = new String[pageLen];
		for (int i = 1; i <= pageLen; i++) {
			pages[i - 1] = "http://edu.51cto.com/?do=course&m=courseList&diff=3&attr=3&end=0&sort=&asc=0&page="
					+ i;
		}
		// TODO Auto-generated method stub
		return pages;
	}

	public String outputFileName() {
		// TODO Auto-generated method stub
		return "./cto51.tsv";
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
		Elements el = doc.select("div[class=CourseIntro_Text] h1");// title
		if (el.size() > 0) {
			infoArray.add(el.get(0).text());
		} else {
			infoArray.add("no title");
		}
		//
		//
		//
		el = doc.select("p[class=fl]");// price
		if (el.size() > 0) {
			infoArray.add(el.get(0).text());
		} else {
			infoArray.add("no price");
		}
		//
		el = doc.select("div[class=CourseTeacher_Info] h2");// teacher name
		if (el.size() > 0) {
			infoArray.add(el.get(0).text());
		} else {
			infoArray.add("no teacher name");
		}
		//
		//
		el = doc.select("div[class=CourseTeacher_Info] span[class=red]");// teacher_rating
		if (el.size() > 0) {
			infoArray.add(el.get(0).text());
		} else {
			infoArray.add("no teacher rating");
		}
		//
		el = doc.select("div[class=CourseTeacher_Info] p");// teacher desc
		if (el.size() > 0) {
			infoArray.add(el.get(0).text());
		} else {
			infoArray.add("no teacher desc");
		}
		//
		el = doc.select("ul[class=Crumbs]");// category path
		if (el.size() > 0) {
			infoArray.add(el.get(0).text());
		} else {
			infoArray.add("no category path");
		}
		//
		el = doc.select("div[class=CourseImg] img");// picture
		if (el.size() > 0) {
			infoArray.add(el.get(0).attr("src"));
		} else {
			infoArray.add("no picture");
		}
		//
		el = doc.select("div[class=class=CourseIntro_Text] p:contains(发布时间)");// start date
		if (el.size() > 0) {
			infoArray.add(el.get(0).text());
		} else {
			infoArray.add("No start date");// start date
		}
		//
		//
		// el = doc.select("span[class=mr30]:contains(结束时间) em");//end date
		// if(el.size()>0)
		// {
		// infoArray.add(el.get(0).text());
		// }
		// else
		{
			infoArray.add("no end date");
		}
		//
		 el = doc.select("div[class=class=CourseIntro_Text] p:contains(时　　长)");//video length same
		 if(el.size()>0)
		 {
		 infoArray.add(el.get(0).text());
		 }
		 else
		{
			infoArray.add("no video length");
		}
		//
		 el = doc.select("div[class=CourseImg] p");//course hour
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
		// el = doc.select("a[class=store-link]");//school url
		// if(el.size()>0)
		// {
		// infoArray.add(el.get(0).attr("href"));
		// }
		// else
		{
			infoArray.add("no school URL");
		}
		//
		//
		// el = doc.select("div[class=teacher-info] p");//school name the same
		// as teacher name
		// if(el.size()>0)
		// {
		// infoArray.add(el.get(0).text());
		// }
		// else
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
		 el = doc.select("li[class=tab4]:contains(综合评分) h4");//rate
		 if(el.size()>0)
		 {
		 infoArray.add(el.get(0).text());
		 }
		 else
		{
			infoArray.add("no rate");
		}
		//
		 el = doc.select("li[class=tab4]:contains(评价) h4");//comments count 
		 if(el.size()>0)
		 {
		 infoArray.add(el.get(0).text());
		 }
		 else
		{
			infoArray.add("no comments_count");
		}
		//
		// el = doc.select("a[class=login-link] strong em");//enrolled_count
		// if(el.size()>0)
		// {
		// infoArray.add(el.get(0).text());
		// }
		// else
		{
			infoArray.add("no enrolled_count");
		}
		// detailContentLeft

		infoArray.add("no desc");// desc ajax by
									// http://www.chuanke.com/?mod=course&act=show&do=brief&sid=1095070&courseid=83606&r=0.9632144784581542

		el = doc.select("li[id=course_lessions_lists]");// outline
		if (el.size() > 0) {
			infoArray.add(el.get(0).text());
		} else {
			infoArray.add("no outline");
		}

		infoArray.add(page.getWebURL().getURL());// video URL
		//
		el = doc.select("dl[class=CourseTabIntro]");// intro
		if (el.size() > 0) {
			infoArray.add(el.get(0).text());
		} else {
			infoArray.add("no intro");
		}

		el = doc.select("ul[class=CRL_Students] li");// purchased_count
		if (el.size() > 0) {
			infoArray.add(el.size()+"");
		} else {
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
