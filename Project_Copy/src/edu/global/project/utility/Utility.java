package edu.global.project.utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.TreeMap;

public class Utility extends Thread {
	TreeMap<Integer, String> list = new TreeMap<>();
	TreeMap<Integer, String> nameList = new TreeMap<>();
	TreeMap<Integer, String> dateList = new TreeMap<>();
	TreeMap<Integer, String> textList = new TreeMap<>();

	SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String myString = null;

	Scanner sc;

	private int count1 = 0, count2 = 0;

	public Utility() {

		try (BufferedReader br = new BufferedReader(new FileReader("String.txt"))) {

			count1 = Integer.valueOf(br.readLine());

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		try (BufferedReader br = new BufferedReader(new FileReader("String.txt"))) {
			count2 = Integer.valueOf(br.readLine());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		for (int i = 0; i < count1; i++) {
			try (BufferedReader br = new BufferedReader(new FileReader("String" + (i + 1) + ".txt"))) {

				String str = null;
				str = br.readLine();
				list.put(i, str);

				str = br.readLine();
				textList.put(i, str);

				str = br.readLine();
				nameList.put(i, str);

				str = br.readLine();
				dateList.put(i, str);

				if (str == null)
					break;

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		while (true) {

			System.out.println("------------------------------------------");
			System.out.print("1. 목록 \t 2. 등록 \t 3.내용 \t 4.삭제 \t 5. 종료 \t");

			sc = new Scanner(System.in);
			int menu;

			try {
				menu = sc.nextInt();
			} catch (Exception e) {
				System.out.println("재입력");
				continue;
			}

			if (menu == 1) {
				Title();
			}

			if (menu == 2) {
				Create();
			}
			if (menu == 3) {
				List();
			}
			if (menu == 4) {
				Delete();
			}

			if (menu == 5) {
				System.out.println("종료됨.");

				break;

			}

		}
	}

	public void Title() {
		System.out.println("==========================================");
		System.out.println("번호 \t 제목 \t 작성자  \t 작성일");
		for (int i = 0; i < list.size(); i++) {

			System.out.println((i + 1) + "\t" + list.get(i) + "\t" + nameList.get(i) + "\t" + dateList.get(i));
		}
	}

	public void Create() {
		String title = null, text = null, name = null;
		sc = new Scanner(System.in);
		System.out.print("제목 : ");
		title = sc.nextLine();
		System.out.print("내용 : ");
		text = sc.nextLine();
		System.out.print("작성자 : ");
		name = sc.nextLine();

		count2 += 1;

		try (BufferedWriter bw = new BufferedWriter(new FileWriter("String.txt"))) {

			bw.write((count2) + "");

			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i <= list.size(); i++) {

			if (list.get(i) == null) {
				try (BufferedWriter bw = new BufferedWriter(new FileWriter("String" + (i + 1) + ".txt"))) {
					bw.write(title, 0, title.length());
					bw.newLine();
					bw.write(text, 0, text.length());
					bw.newLine();
					bw.write(name, 0, name.length());

					Date myDate = new Date();
					myString = dtFormat.format(myDate);
					bw.newLine();
					bw.write(myString, 0, myString.length());

				} catch (IOException e) {
					e.printStackTrace();
				}
				Date myDate = new Date();
				myString = dtFormat.format(myDate);

				list.put(i, title);
				nameList.put(i, name);
				textList.put(i, text);
				dateList.put(i, myString);

				break;
			}
		}
	}

	public void List() {
		System.out.println("==========================================");
		System.out.println("번호 \t 제목 \t 작성자 \t 내용 ");
		for (int i = 0; i < list.size(); i++) {

			System.out.println((i + 1) + "\t" + list.get(i) + "\t" + nameList.get(i) + "\t" + textList.get(i));
		}
	}

	public void Delete() {

		System.out.println("==========================================");
		System.out.println("번호 \t 제목 \t 작성자  \t 작성일");
		for (int i = 0; i < list.size(); i++) {

			System.out.println((i + 1) + "\t" + list.get(i) + "\t" + nameList.get(i) + "\t" + dateList.get(i));
		}
		System.out.println("삭제할 번호를 입력해주세요.");
		int del = 0;
		del = sc.nextInt();

		if (list.get(del - 1) != null) {
			try {
				Path filePath = Paths.get(
						"C:\\Users\\USER\\Documents\\workspace-sts-3.9.11.RELEASE\\Project\\String" + del + ".txt");
				Files.deleteIfExists(filePath);
			} catch (Exception e) {
				e.printStackTrace();
			}
			list.remove(del - 1);
			nameList.remove(del - 1);
			textList.remove(del - 1);
			dateList.remove(del - 1);

			list.put(del - 1, null);
			nameList.put(del - 1, null);
			textList.put(del - 1, null);
			dateList.put(del - 1, null);

			System.out.println("삭제됨.");
		} else {
			System.out.println("대상을 잘못 선택하였습니다.");
		}

	}
}
