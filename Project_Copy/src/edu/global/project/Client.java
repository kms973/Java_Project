package edu.global.project;


import java.net.Socket;

import edu.global.project.utility.Utility;

public class Client {

	public static void main(String[] args) {
		String serverIP = "127.0.0.1";

		Socket socket = null;

		try {
			socket = new Socket(serverIP, 8888);
			System.out.println("서버에 연결되었습니다.");
			
			Utility utility = new Utility();
			utility.run();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
