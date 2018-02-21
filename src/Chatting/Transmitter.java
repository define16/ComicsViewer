package Chatting;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;


public class Transmitter {

	private PrintWriter out = null;
	public Transmitter(OutputStream os)
	{
		out = new PrintWriter(new OutputStreamWriter(os));
	}
	
	public void close()
	{
		if(out != null)
			out.close();
	}
	public void sendMessage(String msg)
	{
		if(out != null)
		{
			out.println(msg);
			out.flush();
		}
	}
}
