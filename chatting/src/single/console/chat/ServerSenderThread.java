package single.console.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

//서버가 클라이언트로 데이터를 전송하는 작업을 수행하는 쓰레드
public class ServerSenderThread extends Thread {

	Socket socket;

	public ServerSenderThread(Socket socket) {
		super();
		this.socket = socket;

	}

	@Override
	public void run() {
		PrintWriter out = null;
		BufferedReader keyin = null;
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			keyin = new BufferedReader(new InputStreamReader(System.in));
			String sendMsg = ""; // 클라이언트로 보낼 메시지
			while (true) {
				sendMsg = keyin.readLine();
				if (sendMsg == null) {
					break;
				}
				out.println(sendMsg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
