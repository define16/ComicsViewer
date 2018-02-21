package Chatting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Receiver extends Thread{
	private ReceiveListener listener;
	private boolean threadFlag = true;
	private int client_id = -1;
	private BufferedReader in = null;
	private AcceepServer acceepserver;
	String users;
	
	public Receiver(InputStream is, int client_id)
	{
		in = new BufferedReader(new InputStreamReader(is));
		this.client_id = client_id;
	}

	public void close()
	{
		try{
			if(in != null)
				in.close();
			in = null;
		}catch(IOException e)
		{
			
		}
		threadFlag = false;
		client_id = -1;
	}
	
	public void run()
	{
		acceepserver = new AcceepServer();
		while(threadFlag)
		{
			try
			{
				
				String msg = in.readLine();
				if(msg == null)
					break;
				else
				{
					System.out.println("[SYSTEM] " + client_id + "님으로 부터 온 메시지 : " + msg);
					listener.Receive("[SYSTEM] " + client_id + "님으로 부터 온 메시지 : " + msg);
					
					for(int i=0;i<ServerList.serverList.length;i++)
						ServerList.serverList[i].sendMessage(client_id + "님의 말 : " + msg);
				}
			}catch(IOException e)
			{
				break;
			}
	
		}
			System.out.println("[SYSTEM] " + client_id + "님이 종료하였습니다.");
			listener.Receive("[SYSTEM] " + client_id + "님이 종료하였습니다.");
		
			ServerList.serverList[client_id].closeServer();
			close();
	}
	
	public void addReceiveListener(ReceiveListener listener)
	{
		this.listener = listener;
	}

	
	

}
