package network.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

//바이너리데이터를 읽고 쓰지 않는 경우 - 문자열
public class MyEchoClient03 {
	public static void main(String[] args) {
		Socket socket = null;
		BufferedReader in = null;
		PrintWriter out = null;
		BufferedReader keyin = null;
		try {
			socket = new Socket("70.12.115.72", 12345);
			System.out.println("서버접속 완료...." + socket);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			keyin = new BufferedReader(new InputStreamReader(System.in));

			// **********통신시작************
			String sendMsg = ""; // 서버로 보낼 메시지
			String resMsg = ""; // 서버에서 받는 메시지 저장
			
			resMsg = in.readLine();
			System.out.println("서버>>" + resMsg);
			while ((sendMsg = keyin.readLine()) != null) {
				out.println(sendMsg);
				resMsg = in.readLine();
				System.out.println("서버>>" + resMsg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
