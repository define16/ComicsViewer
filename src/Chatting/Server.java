package Chatting;

import java.io.IOException;
import java.net.Socket;

public class Server {
	private ReceiveListener listener;
	private int client_id = -1; //getter를 만들어 값을 받아온다.
	private Socket client_socket = null;
	private Receiver receiver = null;
	private Transmitter transmitter = null;
	
	public boolean isIdle()
	{
		return (client_socket == null);
	}
	public void setServer(int client_id, Socket client_socket)
	{
		this.client_id = client_id;
		this.client_socket = client_socket;
	}
	public void startServer()
	{
		if(client_id == -1 || isIdle())
		{
			return;
		}
		try
		{
			System.out.println("시작합니다.");
			listener.Receive("시작합니다.");
			
	//		transmitter = new Transmitter(client_socket.getOutputStream());
			receiver = new Receiver(client_socket.getInputStream(),client_id);
			receiver.start();
			receiver.addReceiveListener(listener);
			
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public void closeServer()
	{
		client_id= -1;
		client_socket = null;
	}
	public void sendMessage(String msg)
	{
		if(transmitter != null)
			transmitter.sendMessage(msg);
	}
	
	public void addReceiveListener(ReceiveListener listener)
	{
		this.listener = listener;
	}
	
	public int getclient_id()
	{
		return this.client_id;
	}
}
