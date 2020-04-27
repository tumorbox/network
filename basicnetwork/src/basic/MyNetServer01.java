package basic;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MyNetServer01 {
	public static void main(String[] args) {
		//클라이언트와 통신할 수 있도록 준비
		try {
			ServerSocket server = new ServerSocket(12345);
			System.out.println("서버준비 완료.... 클라이언트의 접속을 기다림");
			//클라이언트가 통신할 수 있도록 준비
			//클라이언트가 접속하면 클라이언트의 소캣객체를 리턴
			Socket client = server.accept();
			InetAddress clientInfo = client.getInetAddress();
			System.out.println("접속한 클라이언트 : "+client.getInetAddress());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
