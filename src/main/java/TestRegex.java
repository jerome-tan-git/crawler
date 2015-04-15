package main.java;

import java.util.regex.Pattern;

public class TestRegex {
	public static void main(String[] args)
	{
		Pattern SESSION = Pattern
				.compile("http://edu\\.yy\\.com/course/detail\\?id=[0-9]+");
		System.out.println(SESSION.matcher("http://edu.yy.com/course/detail?id=14362").matches());
	}
}
