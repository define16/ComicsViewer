package UI;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Chatting.AcceepServer;
import Chatting.Client;
import Chatting.ReceiveListener;
import Chatting.Receiver;
import Chatting.Server;

public class ServerUI extends JFrame implements ReceiveListener{
	private JTextField text1 = null;
	private JList list = null;
	private JScrollPane list_scroll = null;
	private DefaultListModel model = null;
	private AcceepServer acceepserver = null;
	private Client client = null; 
	private Server server = null;
	private Receiver receiver = null;

	public ServerUI()
	{
		super("채팅 서버 UI");
		
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10,10, 400, 300);
		setResizable(false);
		
		list = new JList(new DefaultListModel());
		model = (DefaultListModel) list.getModel();
		
		list_scroll = new JScrollPane(list);
		list_scroll.setBounds(20, 20, 355, 230);
		
		add(list_scroll);

		setVisible(true);
		
		acceepserver = new AcceepServer();
		acceepserver.addReceiveListener(this);
		acceepserver.startServer();	
	}

	@Override //리스트한테 메세지를 띄워줌.
	public void Receive(String msg) {
		model.addElement(msg);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerUI ui1 = new ServerUI();
	
		
	}




}


