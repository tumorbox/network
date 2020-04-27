package basic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class MyNetServer03 {
	public static void main(String[] args) {
		
		Socket client = null;
		InputStream is = null;
		DataInputStream dis = null;
		OutputStream os = null;	
		DataOutputStream dos =null;
		Random rand = new Random();
		
		
		try {
			ServerSocket server = new ServerSocket(12345);
			System.out.println("서버준비 완료.... 클라이언트의 접속을 기다림");
			
			while(true) {
			client = server.accept();
			InetAddress clientInfo = client.getInetAddress();
			System.out.println("접속한 클라이언트 : "+client.getInetAddress());
		
			is = client.getInputStream();
			dis = new DataInputStream(is);
	
			os = client.getOutputStream();
			dos = new DataOutputStream(os);
			
			//1. 서버 -> 클라이언트
			dos.writeUTF("안녕하세요 클라이언트님.");
			//2. 서버 -> 클라이언트
			int su = rand.nextInt(8)+2;
			dos.writeInt(su);
			
			//3. 서버 <- 클라이언트
			String data = dis.readUTF();
			System.out.println("클라이언트가 보낸 메시지 : "+data);
			
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
