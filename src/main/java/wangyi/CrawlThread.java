package main.java.wangyi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CrawlThread implements Runnable {

	WebDriver driver = null;

	public CrawlThread() {

		System.setProperty("phantomjs.binary.path",
				"C:\\phantomjs\\phantomjs.exe");

	}

	public void run() {
		System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
		driver = new ChromeDriver();
		String url = null;
		while ((url = MainThread.getURL()) != null) {
			this.matchHtml(url);
		}
		driver.quit();
		// TODO Auto-generated method stub

	}

	private void matchHtml(String url) {
		driver.get(url);
		new WebDriverWait(driver, 30).until(new  ExpectedCondition<Boolean>() {

			public Boolean apply(WebDriver input) {
				return input.findElement(By.tagName("body")).getText().contains("收藏");
			}

		  });
		System.out.println(url);
		// java.io.File screenShotFile = ((TakesScreenshot) driver)
		// .getScreenshotAs(OutputType.FILE);
		// FileUtils.copyFile(screenShotFile, new java.io.File("D:\\a2.png"));
		String pageSource = driver.getPageSource();
		Document doc = Jsoup.parse(pageSource);
		List<String> infoArray = new ArrayList<String>();

		Elements el = doc.select("span[class=j-info]");
		;// title
		if (el.size() > 0) {
			infoArray.add(el.get(0).text());
		} else {
			infoArray.add("no title");
		}
		//
		el = doc.select("div[class=discountArea f-pa j-discountArea f-cb] p");// price
		if (el.size() > 0) {
			infoArray.add(el.get(0).text());
		} else {
			infoArray.add("no price");
		}
		//
		el = doc.select("div[class=j-info f-cb] div:eq(1)");// teacher name
		if (el.size() > 0) {
			infoArray.add(el.get(0).text());
		} else {
			infoArray.add("no teacher name");
		}
		//
		//
		// el = doc.select("span[class=renqi]");// teacher_rating
		// if (el.size() > 0) {
		// infoArray.add(el.get(0).text());
		// } else
		// {
		infoArray.add("no teacher rating");
		// }
		//
		// el =
		// doc.select("div[class=ltxt j-ltxt f-richEditorText edueditor_styleclass_0 edueditor_styleclass_1]");//
		// teacher desc
		// if (el.size() > 0) {
		// infoArray.add(el.get(0).text());
		// } else
		// {
		infoArray.add("no teacher desc");
		// }
		//
		el = doc.select("p[class=j-info]:contains(鍒嗙被锛�");// category path
		if (el.size() > 0) {
			infoArray.add(el.get(0).text());
		} else {
			infoArray.add("no category path");
		}
		//
		el = doc.select("div[class=g-sd1 left j-chimg] img");// picture
		if (el.size() > 0) {
			infoArray.add(el.get(0).attr("src"));
		} else {
			infoArray.add("no picture");
		}
		//
		// el = doc.select("div[class=timeCon]");// start date
		// if (el.size() > 0) {
		// infoArray.add(el.get(0).text().split("|")[0]);
		// } else
		{
			infoArray.add("No start date");// start date
		}
		//
		//
		el = doc.select("span[class=f-db f-pa valid]");// end date
		if (el.size() > 0) {
			infoArray.add(el.get(0).text());
		} else {
			infoArray.add("no end date");
		}
		//
		// el = doc.select("div[class=picCon] p");//video length same
		// if(el.size()>0)
		// {
		// infoArray.add(el.get(0).text());
		// }
		// else
		{
			infoArray.add("no video length");
		}
		//
		el = doc.select("span[class=f-fl f-thide ks]");// course hour
		if (el.size() > 0) {
			infoArray.add(String.valueOf(el.size()));
		} else {
			infoArray.add("no course hour");
		}
		//
		//
		// el = doc.select("span:contains(鎷呬繚鏈� em");//expire date
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
		el = doc.select("p[class=j-info f-thide] a");// school url
		if (el.size() > 0) {
			infoArray.add(el.get(0).attr("href"));
		} else {
			infoArray.add("no school URL");
		}
		//
		//
		// el = doc.select("p[class=lname f-thide j-lname]");//school name
		if (el.size() > 0) {
			infoArray.add(el.get(0).text());
		} else {
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
		el = doc.select("span[class=j-info starall] div[class=star on]");// rate
		if (el.size() > 0) {
			infoArray.add(el.size() + "");
		} else {
			infoArray.add("no rate");
		}
		//
		el = doc.select("span[class=cmt j-cmt]");// comments count
		if (el.size() > 0) {
			infoArray.add(el.get(0).text());
		} else {
			infoArray.add("no comments_count");
		}
		//
		el = doc.select("em[class=num j-num f-fl]");// enrolled_count
		String enrolled_count = "";
		if (el.size() > 0) {
			enrolled_count = el.get(0).text();
			infoArray.add(enrolled_count);
		} else {
			infoArray.add("no enrolled_count");
		}
		// detailContentLeft
		el = doc.select("div[class=cintrocon j-courseintro]");// desc with
																// outline
		if (el.size() > 0) {
			infoArray.add(el.get(0).text());
		} else {
			infoArray.add("no desc");// desc ajax by
		}

		el = doc.select("div[class=m-chapterList f-pr]");// outline
		if (el.size() > 0) {
			infoArray.add(el.get(0).text());
		} else {
			infoArray.add("no outline");
		}

		infoArray.add(driver.getCurrentUrl());// video URL
		//
		// el = doc.select("dl[class=CourseTabIntro]");// intro
		// if (el.size() > 0) {
		// infoArray.add(el.get(0).text());
		// }
		// else
		{
			infoArray.add("no intro");
		}

		// el = doc.select("ul[class=CRL_Students] li");// purchased_count
		if (enrolled_count != null && enrolled_count.length() > 0) {
			infoArray.add(enrolled_count);
		} else {
			infoArray.add("no purchased_count");
		}
		String line = "";
		for (String str : infoArray) {

			line += str + "\t";
		}
		MainThread.result(line);
	}
}
