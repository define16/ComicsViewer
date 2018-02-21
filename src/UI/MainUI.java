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
	private String name; // 검색키워드
	private Parser p;
	private ArrayList<Data> list = new ArrayList<>();
	
	public MainUI() {
		super("만화보기");
		
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(290,160, 345, 700);
		setBounds(450,200, 600, 700);
		setResizable(false);
		
		Cartoon_list = new JList(new DefaultListModel());
		Cartoon_list_model = (DefaultListModel) Cartoon_list.getModel();
		
		Cartoon_list_scroll = new JScrollPane(Cartoon_list);
		Cartoon_list_scroll.setBounds(20, 60, 300, 560);
		Cartoon_list_model.addElement("만화를 검색해주세요!"); 
		MouseListener mouseListener = new MouseAdapter() { 	//리스트 클릭했을 때 
	         public void mouseClicked(MouseEvent mouseEvent) {     
	            if (mouseEvent.getClickCount() == 2) { 
	               int index = Cartoon_list.locationToIndex(mouseEvent.getPoint()); 
	               if (index >= 0) { 
	                  Object o = Cartoon_list_model.getElementAt(index); 
	                  
	           	}
	                
	               } 
	            } 
	         }; 
	    
	         
	    // 만화 목록보기
	    index_list = new JList(new DefaultListModel());
	    index_list_model = (DefaultListModel) index_list.getModel();
	 		
	 	index_list_scroll = new JScrollPane(index_list);
	 	index_list_scroll.setBounds(330, 60, 250, 560);
	 	index_list_model.addElement("-------------------------목록-------------------------");

	 	list = p.list();
		for(int i = 0; i < 82 ; i++) {	
			index_list_model.addElement(list.get(i).toString()); 
		}
		
		
		
		text1 = new JTextField();
		text1.setBounds(60, 20, 260, 30);


		button_search = new JButton("검색");
		button_search.setBounds(330,20,80,30);	
		button_search.addActionListener(new ActionListener() { 
	        @Override 
	        public void actionPerformed(ActionEvent e) { 	// 검색 버튼
	        	
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

		button_next = new JButton("보기");
		button_next.setBounds(500,630,80,30);	
		button_next.addActionListener(new ActionListener() { 
        @Override 
        public void actionPerformed(ActionEvent e) { 	// 보기 버튼
           int index = Cartoon_list.getSelectedIndex(); 
     
           if( index != -1 && Cartoon_list_model.size() != 0 ) { 
              Object o = Cartoon_list_model.getElementAt(index); 
              String title = (String)o;
              if(name.equals("아카메가 벤다") ||name.equals("ACMA GAME") || name.equals("도박마") || name.equals("오늘부터 신령님") 
            		  || name.equals("장난을 잘치는 타카기 양") || name.equals("테라포마스") || name.equals("피아노의 숲"))
              {
            	  String link = p.SearchLink(name ,title);
            	  cartoon3 = new Cartoon3(title,link);
              }
              else if(name.equals("사남") ||name.equals("블랙 클로버") ||name.equals("리쿠도") ||name.equals("리얼") ||name.equals("로봇x레이저빔") 
            		  ||name.equals("아오오니") ||name.equals("원피스") ||name.equals("헌터x헌터") || name.equals("하이큐") || name.equals("플래티넘 엔드") 
            		  || name.equals("파이어 펀치") || name.equals("토리코") || name.equals("테니스의 왕자") || name.equals("킹덤") || name.equals("약속의 네버랜드") 
            		  || name.equals("원펀맨 히어로대전"))
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
		
		button_exit = new JButton("종료");
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
