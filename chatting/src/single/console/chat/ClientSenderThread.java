package single.console.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

//클라이언트가 서버로 메시지를 보내느 작업을 수행하는 쓰레드
public class ClientSenderThread extends Thread {
	
	Socket socket;
	
	public ClientSenderThread(Socket socket) {
		super();
		this.socket=socket;
		
	}
	@Override
	public void run() {
		BufferedReader keyin = null;
		PrintWriter out = null;
		try {
			out = new PrintWriter(socket.getOutputStream(),true);
			keyin = new BufferedReader(new InputStreamReader(System.in));
			String sendMsg ="";
			while(true) {
				sendMsg = keyin.readLine();
				if(sendMsg==null) {
					break;
				}
				out.println(sendMsg);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
