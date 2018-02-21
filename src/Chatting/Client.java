package Chatting;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread
{
	private static final int ACCEPT_PORT = 6637;
	private static final String ACCEPT_IP = "127.0.0.1"; //203.253.255.99
	
	private BufferedReader receive = null;
	private PrintWriter trans = null;
	private BufferedReader in = null;
	private ReceiveListener listener;
	private ReceiveListener UserList;
	private AcceepServer acceepserver;
	private String msg;

	
	public void run()
	{
		Socket socket = null;

		
		try
		{
			System.out.println("[IP:" + ACCEPT_IP + ",PORT:" + ACCEPT_PORT + "]로 접속중..." );
			listener.Receive("[IP:" + ACCEPT_IP + ",PORT:" + ACCEPT_PORT + "]로 접속중...");
			socket = new Socket(ACCEPT_IP, ACCEPT_PORT);
			System.out.println("서버에 접속하였습니다.");
			listener.Receive("서버에 접속하였습니다.");
			receive = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			trans = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

		}catch(IOException e){
			// 서버 접속 실패 - ip나 port가 잘못되었을 경우
			System.err.println("[ERR]서버 접속 실패");
			return;
		}
		 //서버로 보낼 메시지를 입력받는 동시에 서버에서 오는 메시지를 받음
		try
		{
			while(true)
			{
//				// 서버로 부터 메시지를 받는 것을 기다린다.
				msg = receive.readLine();
//				// 받은 메시지를 출력한다.
				System.out.println(msg);
				listener.Receive(msg);
			}
		}catch(IOException e){
			System.err.println("[ERR]서버 끊김");
		}
		
		// 정상적으로 과정이 끝난 후 해제
		try {
			if( receive != null)
				receive.close();
			if(trans != null)
				trans.close();
			if(socket != null)
				socket.close();
		} catch (IOException e) {
			
		}
	}
	public void sendMessage(String msg)
	{
				trans.println(msg);
				trans.flush();
				this.msg = msg;
	}
	
	public void addReceiveListener(ReceiveListener listener) {
		this.listener = listener;
	}
	


}
