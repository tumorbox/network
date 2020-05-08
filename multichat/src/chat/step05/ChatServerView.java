package chat.step05;

import java.awt.EventQueue;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class ChatServerView extends JFrame {

	JPanel contentPane;
	JTextArea taclientlist;
	JButton btnchangeport;
	JButton btnstartServer;
	JButton btnstop;
	// 1. 소켓프로그래밍을 하기 위한 준비
	ServerSocket server;
	Socket socket;
	
	// 5-6. 클라이언트와 통신하기 위한 입출력 스트림 변수 선언
	InputStream is;
	InputStreamReader ir;
	BufferedReader br;
	
	OutputStream os;
	PrintWriter pw;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatServerView frame = new ChatServerView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ChatServerView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 673, 513);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		taclientlist = new JTextArea();
		taclientlist.setBounds(12, 50, 472, 415);
		contentPane.add(taclientlist);

		JLabel label = new JLabel("\uC811\uC18D\uC790:");
		label.setFont(new Font("HY견고딕", Font.BOLD, 14));
		label.setBounds(12, 10, 120, 35);
		contentPane.add(label);

		btnchangeport = new JButton("\uD3EC\uD2B8\uBCC0\uACBD");
		btnchangeport.setFont(new Font("HY견고딕", Font.BOLD, 14));
		btnchangeport.setBounds(516, 50, 129, 35);
		contentPane.add(btnchangeport);

		btnstartServer = new JButton("\uC11C\uBC84\uC2DC\uC791");
		btnstartServer.setFont(new Font("HY견고딕", Font.BOLD, 14));
		btnstartServer.setBounds(516, 95, 129, 35);
		contentPane.add(btnstartServer);

		btnstop = new JButton("\uC11C\uBC84\uC911\uC9C0");
		btnstop.setFont(new Font("HY견고딕", Font.BOLD, 14));
		btnstop.setBounds(516, 140, 129, 35);
		contentPane.add(btnstop);
		btnstartServer.addActionListener(new ChatServerListener(this));
		btnstop.addActionListener(new ChatServerListener(this));
	}

	// 2. 클라이언트의 접속을 기다리면서 서비스를 시작할 수 있는 메소드를 정의
	public void serverStart(int port) {
		try {
			server = new ServerSocket(port);
			taclientlist.append("사용자 접속 대기중");
			if (server != null) {
				// 클라이언트의 접속을 기다리는 처리
				connection();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 3. 사용자 접속을 위한 메소드 정의
	public void connection() {
		
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					// accept하면 계속 작동 따라서 쓰레드로 만들어서 돌려야함
					// 메인쓰레드는 화면단에서 이벤트처리
					// accept하는 쓰레드 하나 만들어야함
					socket = server.accept();
					// 클라이언트 1. client가 접속하면 클라이언트의 ip정보를 출력
					String ip = socket.getInetAddress().getHostAddress();
					taclientlist.append(ip+"==========사용자 접속!!\n");
					
					// 5-8. 클라이언트가 접속하면 통신할 수 있도록 스트림 얻기 메소드 호출
					ioWork();
					
					// 5-9. 클라이언트가 전송한 nickname 출력하기
					System.out.println("클라이언트가 전송한 닉네임 : ");
					String nickname = br.readLine();
					System.out.println("nickname"+nickname);
					taclientlist.append("********"+nickname+"님이 입장********");
					
					// 5-10. 서버가 클라이언트에게 환영메시지를 전송
					pw.println("접속을 환영합니다");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}
	
	// 5-7. 클라이언트와 통신할 수 있또록 소켓으로부터 input/output스트림을 얻기
	public void ioWork() {
		try {
			is = socket.getInputStream();
			ir = new InputStreamReader(is);
			br = new BufferedReader(ir);
			
			os = socket.getOutputStream();
			pw = new PrintWriter(os,true);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}