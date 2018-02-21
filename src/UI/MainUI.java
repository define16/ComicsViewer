package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import CartoonImage.Data;
import CartoonImage.Parser;

public class MainUI extends JFrame {

	private JTextField text1 = null;
	private JList Cartoon_list = null, index_list = null;
	private JButton button_search = null, button_next = null, button_exit = null;
	private JScrollPane Cartoon_list_scroll  = null, index_list_scroll  = null;
	private DefaultListModel Cartoon_list_model = null, index_list_model = null;
	private Cartoon cartoon = null;
	private Cartoon2 cartoon2 = null;
	private Cartoon3 cartoon3 = null;
	private String name; // �˻�Ű����
	private Parser p;
	private ArrayList<Data> list = new ArrayList<>();
	
	public MainUI() {
		super("��ȭ����");
		
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(290,160, 345, 700);
		setBounds(450,200, 600, 700);
		setResizable(false);
		
		Cartoon_list = new JList(new DefaultListModel());
		Cartoon_list_model = (DefaultListModel) Cartoon_list.getModel();
		
		Cartoon_list_scroll = new JScrollPane(Cartoon_list);
		Cartoon_list_scroll.setBounds(20, 60, 300, 560);
		Cartoon_list_model.addElement("��ȭ�� �˻����ּ���!"); 
		MouseListener mouseListener = new MouseAdapter() { 	//����Ʈ Ŭ������ �� 
	         public void mouseClicked(MouseEvent mouseEvent) {     
	            if (mouseEvent.getClickCount() == 2) { 
	               int index = Cartoon_list.locationToIndex(mouseEvent.getPoint()); 
	               if (index >= 0) { 
	                  Object o = Cartoon_list_model.getElementAt(index); 
	                  
	           	}
	                
	               } 
	            } 
	         }; 
	    
	         
	    // ��ȭ ��Ϻ���
	    index_list = new JList(new DefaultListModel());
	    index_list_model = (DefaultListModel) index_list.getModel();
	 		
	 	index_list_scroll = new JScrollPane(index_list);
	 	index_list_scroll.setBounds(330, 60, 250, 560);
	 	index_list_model.addElement("-------------------------���-------------------------");

	 	list = p.list();
		for(int i = 0; i < 82 ; i++) {	
			index_list_model.addElement(list.get(i).toString()); 
		}
		
		
		
		text1 = new JTextField();
		text1.setBounds(60, 20, 260, 30);


		button_search = new JButton("�˻�");
		button_search.setBounds(330,20,80,30);	
		button_search.addActionListener(new ActionListener() { 
	        @Override 
	        public void actionPerformed(ActionEvent e) { 	// �˻� ��ư
	        	
	        ArrayList<Data> da = new ArrayList<>();
	        int i = 0;
	        int blank = 0;
	        name = text1.getText();
	        da = p.SearchTitle(name);
	        Cartoon_list_model.removeAllElements();
			try {
				if ((da.get(i).toString()) == null) {
					++i;
					blank++;
					if(blank > 3) {
						while((da.get(i).toString()) != null) {	
							Cartoon_list_model.addElement(da.get(i).toString()); 
							i++;
						}
					}
					else {
						while((da.get(i).toString()) != null) {	
							Cartoon_list_model.addElement(da.get(i).toString()); 
							
						}
					}
				}
				else {
					while((da.get(i).toString()) != null) {	
						Cartoon_list_model.addElement(da.get(i).toString()); 
						i++;
					}
				}
			}catch (IndexOutOfBoundsException e3) {
				// TODO: handle exception		
				
			}
	      }
	     }); 

		button_next = new JButton("����");
		button_next.setBounds(500,630,80,30);	
		button_next.addActionListener(new ActionListener() { 
        @Override 
        public void actionPerformed(ActionEvent e) { 	// ���� ��ư
           int index = Cartoon_list.getSelectedIndex(); 
     
           if( index != -1 && Cartoon_list_model.size() != 0 ) { 
              Object o = Cartoon_list_model.getElementAt(index); 
              String title = (String)o;
              if(name.equals("��ī�ް� ����") ||name.equals("ACMA GAME") || name.equals("���ڸ�") || name.equals("���ú��� �ŷɴ�") 
            		  || name.equals("�峭�� ��ġ�� Ÿī�� ��") || name.equals("�׶�������") || name.equals("�ǾƳ��� ��"))
              {
            	  String link = p.SearchLink(name ,title);
            	  cartoon3 = new Cartoon3(title,link);
              }
              else if(name.equals("�糲") ||name.equals("�� Ŭ�ι�") ||name.equals("����") ||name.equals("����") ||name.equals("�κ�x��������") 
            		  ||name.equals("�ƿ�����") ||name.equals("���ǽ�") ||name.equals("����x����") || name.equals("����ť") || name.equals("�÷�Ƽ�� ����") 
            		  || name.equals("���̾� ��ġ") || name.equals("�丮��") || name.equals("�״Ͻ��� ����") || name.equals("ŷ��") || name.equals("����� �׹�����") 
            		  || name.equals("���ݸ� ����δ���"))
              {
            	  String link = p.SearchLink(name ,title);
            	  cartoon2 = new Cartoon2(title,link);
              }
              else {
            	  String link = p.SearchLink(name ,title);
                  cartoon = new Cartoon(title, link);
              }
            	  
           }
              setVisible(false);
           } 
        }); 
		
		button_exit = new JButton("����");
		button_exit.setBounds(20,630,80,30);	
		button_exit.addActionListener(new ActionListener() { 
	        @Override 
	        public void actionPerformed(ActionEvent e) { 
	        	System.exit(0);
	        } 
	     }); 
			
		add(index_list_scroll);
		add(Cartoon_list_scroll);
		add(text1);
		add(button_search);
		add(button_next);
		add(button_exit);
		setVisible(true);

		
	}


}
