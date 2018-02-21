package UI;

import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import CartoonImage.cookies.URLManager;
import Chatting.AcceepServer;
import Chatting.Client;
import Chatting.ReceiveListener;
import Chatting.Receiver;
import Chatting.Server;




public class Cartoon2 extends JFrame implements ActionListener, ReceiveListener{
	
	private JTextField text1 = null;
	private JList chatlist= null;
	private JButton button_back = null, button_next = null, button_before = null, button_send = null ;
	private JScrollPane chatlist_scroll = null;
	private DefaultListModel model = null;
	private ImageIcon image = null;
	private JPanel panel = null;
	private JLabel cartonView = null;
	private ScrollPane scroll = null;


	private Client client = null;

	
	private int page = 0;
	private String Slink = null;
	private byte[] carton = null;
	
	public Cartoon2(String title, String link) {	//�Ű������� Ŭ���� ������ �ѱ� ����.
		super(title); // ��ܿ� ������ ǥ���ϱ� ���ؼ� �Ű������� ����
		
		InputStream in = URLManager.getURLInputStream(link, URLManager.USER_AGENT_PC);
		
		try{
			Document doc = Jsoup.parse(in, URLManager.ENCODING_UTF8,"");
			Elements root = doc.select("div[class=contents]");
			Elements rankList = doc.select("div[class=separator]");
			Elements rankList2 = rankList.select("a");
	
			
			Slink = rankList2.get(page).attr("href");
			carton = URLManager.getImage(
					Slink ,link);
			
			
			setLayout(null);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(290,160, 900, 700);
			setResizable(false);
			
		
			
			System.out.println("Cartoon_link : " + link);
			panel = new JPanel();
			scroll = new ScrollPane();
			panel.setBounds(10,10,573, 625);
			panel.setLayout(null);
			
			
			button_back = new JButton("�� �ܰ��");
			button_back.setBounds(50,630,110,30);	
			button_back.addActionListener(new ActionListener() { 
	       // @Override 
	        public void actionPerformed(ActionEvent e) { 
	        	
	        	MainUI ui = new MainUI();
	        	setVisible(false);
	        }
			}); 
			
			button_next = new JButton("����������");
			button_next.setBounds(450,630,110,30);	
			button_next.addActionListener(new ActionListener() { 
	       // @Override 
	        public void actionPerformed(ActionEvent e) { 
	        	page = page + 1;
	    		
	        	if(root.size() < page) {
	        		JOptionPane.showMessageDialog(null, "������ ������ �Դϴ�.");
	        		page = page - 1;
	        	}
	        	
				Slink = rankList2.get(page).attr("href");
				carton = URLManager.getImage(
						Slink ,link);
				
	       
	        	 // System.out.println(carton);
	        	  cartonView.setIcon(new ImageIcon(carton));
	        	  cartonView.revalidate();
	        	  cartonView.repaint();
	        	  cartonView.update(cartonView.getGraphics());
	        }
	        
			}); 
			
			
			button_before = new JButton("����������");
			button_before.setBounds(250,630,110,30);	
			button_before.addActionListener(new ActionListener() { 
	       // @Override 
	        public void actionPerformed(ActionEvent e) { 
	        	page = page-1;
	        	
				Slink = rankList2.get(page).attr("href");
				carton = URLManager.getImage(Slink ,link);
	
	        	  cartonView.setIcon(new ImageIcon(carton));
	        	  cartonView.revalidate();
	        	  cartonView.repaint();
	        	  cartonView.update(cartonView.getGraphics());
	        }
	        
			}); 
			
			image = new ImageIcon(carton);
			cartonView = new JLabel(image);
			
			
			chatlist = new JList(new DefaultListModel());
			model = (DefaultListModel) chatlist.getModel();
			
			chatlist_scroll = new JScrollPane(chatlist);
			chatlist_scroll.setBounds(583, 20, 300, 602);
		
	
			text1 = new JTextField();
			text1.setBounds(583, 630, 215, 30);
	
			button_send = new JButton("����");
			button_send.setBounds(803,630,80,30);	
			button_send.addActionListener(this);
			
			scroll.setSize(570,625);
			scroll.add(cartonView);
			add(scroll);
			add(button_back);
			add(button_next);
			add(button_before);
			add(chatlist_scroll);
			add(text1);
			add(button_send);
	
			setVisible(true);
	
			client = new Client();
			client.addReceiveListener(this);
			
			
			client.start();
		}catch(java.lang.IndexOutOfBoundsException e1){
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "�������� �ʴ� �������Դϴ�.");

		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		//boolean clear = false;
		if( o == button_send)	// ���
		{	
			String str = text1.getText();
			client.sendMessage(str);
	
			 text1.setText("");
			 

		}
	}
	

	@Override
	public void Receive(String msg) {	//������� �κ�
		// TODO Auto-generated method stub
		boolean a = msg.matches(".*broad/.*");
		if(a){
			for(int i = 1; i<msg.split("/").length;i++)
			{

				System.out.println(msg.split("/")[i]);
			}
		}
		else{
				model.addElement(msg);
				System.out.println(msg);
		}
	}


}
