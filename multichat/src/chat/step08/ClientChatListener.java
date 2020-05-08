package chat.step08;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ClientChatListener implements ActionListener{
	ClientChatView view;

	public ClientChatListener(ClientChatView view) {
		super();
		this.view = view;
	}
	
	// onclick과 같다. 
	// 버튼을 누를 떼, 엔터키를 입력할 때 actionPerformed가 호출된다.
	@Override
	public void actionPerformed(ActionEvent e) {	
		// 8-1. 채팅대화를 전송하는 경우 프로토콜, 대화내용, nickname
		if(e.getSource()==view.txtinput|e.getSource()==view.btnsend) {
			// trim() 양끝 공백을 지워서 enter키 작동할 수 있다.
			view.sendMsg("chatting/"+view.txtinput.getText().trim()+"/"+view.nickname);
			view.txtinput.setText("");
		}
	}
	
	
}
