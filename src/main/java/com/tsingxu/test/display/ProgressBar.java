package com.tsingxu.test.display;

public class ProgressBar {

	public static void putline(String line, int maxLen) {
		for (int i = 0; i < maxLen; i++) {
			System.out.print("\b");
		}
		System.out.print(line);
	}

	public static void main(String[] args) throws Exception {
		String str = "";
		while (true) {
			Thread.sleep(500);
			str += ".";
			ProgressBar.putline(str, 100);
		}
	}
}
