package edu.global.project;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		
		ServerSocket serverSocket = null;
		
		Socket socket = null;
		
		try {
			
			serverSocket = new ServerSocket(8888); // 포트번호
			System.out.println("서버가 준비되었습니다.");

			socket = serverSocket.accept();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
