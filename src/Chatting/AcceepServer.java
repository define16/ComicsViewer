package Chatting;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class AcceepServer extends Thread {

	private ReceiveListener listener;
	private ServerSocket serverSocket = null;
	private static final int SERVER_PORT = 6637;
	private boolean serverFlag = true;
	private String users = "broad";
	
	private String user;

	
	public void closeServer()
	{
		serverFlag = false;
		
		try 
		{
			serverSocket.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		
		System.out.println("[SYSTEM] 서버가 종료 되었습니다.");
		System.exit(0);
	}
	
	public boolean startServer()
	{
		try{
			serverSocket = new ServerSocket(SERVER_PORT);
		}catch(IOException e)
		{
			return false;
		}
		super.start();
		return true;
	}
	
	public void run()
	{
		while(serverFlag)
		{

			try {
				System.out.println("[SYSTEM] 새로운 클라이언트의 접속을 기다립니다...");
				listener.Receive("[SYSTEM] 새로운 클라이언트의 접속을 기다립니다...");
				Socket socket = serverSocket.accept();
				
				System.out.println(socket.getInetAddress().getHostAddress());
				int ran = -1;
				do
				{
					ran = (int)(Math.random()*ServerList.SERVER_MAX);
				}while(!ServerList.serverList[ran].isIdle());
				
				System.out.println("[SYSTEM] 클라이언트(" + socket.getInetAddress().getHostAddress() +")가 접속하였습니다.");
				listener.Receive("[SYSTEM] 클라이언트(" + socket.getInetAddress().getHostAddress() +")가 접속하였습니다.");
				
			
				ServerList.serverList[ran].addReceiveListener(listener);
				ServerList.serverList[ran].setServer(ran,socket);				
				ServerList.serverList[ran].startServer();
				
				
//				if(ServerList.serverList[ran].getclient_id() == -1)
//					users += "";
//				else
//					users += "/" + ServerList.serverList[ran].getclient_id();
				
				ServerList.serverList[ran].sendMessage(users);
				

				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				break;
			}
		}
	}
	
	public void start()
	{
		throw new RuntimeException("Don't call the start()");
	}

	public void addReceiveListener(ReceiveListener listener) {
		// TODO Auto-generated method stub
		this.listener = listener;
	}


		
}
