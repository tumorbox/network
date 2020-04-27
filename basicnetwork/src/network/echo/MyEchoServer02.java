package network.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

//바이너리데이터를 읽고 쓰지 않는 경우 - 문자열
public class MyEchoServer02 {
	public static void main(String[] args) {
		Socket client = null;
		BufferedReader in = null;
		PrintWriter out = null;

		try {
			ServerSocket server = new ServerSocket(12345);
			System.out.println("서버접속 완료....");
			while (true) {
				client = server.accept();
				InetAddress clientInfo = client.getInetAddress();
				System.out.println("접속한 클라이언트 : " + clientInfo.getHostAddress());
				in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				out = new PrintWriter(client.getOutputStream(), true);

				// **********통신시작************
				String resMsg = "";
				String sendMsg = "";
				// 1. 서버->클라이언트
				out.println("안녕 클라이언트?");
				while (true) {
					//2. 서버<-클라이언트
					resMsg = in.readLine();
					//3. 서버->클라이언트
					if (resMsg == null) {
						break;
					} else if (resMsg.startsWith("안녕")|resMsg.startsWith("하이")) {
						out.println(clientInfo.getHostAddress() + "님 반가워요");
					} else if (resMsg.startsWith("오늘 날짜는")) {
						out.println("오늘 날짜는 "+new Date().toString());
					} else {
						out.println(clientInfo.getHostAddress() + "어겨 가~");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
