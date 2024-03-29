package basic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class MyNetClient03 {
	public static void main(String[] args) {
		
		Socket socket = null;
		InputStream is = null; //클라이언트와 input할 수 있는 스트림
		DataInputStream dis = null;	//최종적으로 클라이언트와DataInputStream 을 이용해서 클라이언트로부터 전송되는
									//데이터를 읽기
		OutputStream os = null;	//클라이언트와 output할 수 있는 스트림
		DataOutputStream dos =null;
		
		//서버와 통신할 수 있는 소켓객체를 생성
		try {
			socket = new Socket("70.12.115.72",12345);
			System.out.println("서버접속 완료....:"+socket);
			
			//클라이언트에서도 서버와 통신할 수 있는 소켓객체로 부터 input/output스트림을 구한다.
			//InputStream얻기
			is = socket.getInputStream();
			dis = new DataInputStream(is);
			
			//OutputStream얻기
			os = socket.getOutputStream();
			dos = new DataOutputStream(os);
			
			//1. 클라이언트 <- 서버
			String data = dis.readUTF();
			System.out.println("서버가 전송하는 메시지1 :"+data);
			
			//2. 클라이언트 <- 서버
			int su = dis.readInt();
			System.out.println("========서버가 전송하는 수의 구구단========");
			for(int i=1;i<=9;i++) {
				System.out.println(su+"*"+i+"="+su*i);
			}
			System.out.println("========서버가 전송하는 수========");
			if(su%2==1) {
				System.out.println(su+"단");
				dos.writeUTF("안녕 서버, 난 클라이언트. 너가 보낸 수는 홀수야");
			} else {
				System.out.println(su+"단");
				dos.writeUTF("안녕 서버, 난 클라이언트. 너가 보낸 수는 짝수야");
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
