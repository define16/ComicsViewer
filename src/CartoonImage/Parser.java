package CartoonImage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import CartoonImage.cookies.URLManager;
import UI.MainUI;

public class Parser {
	public static ArrayList<Data>list()
	{
		ArrayList<Data> da = new ArrayList<>();
		String url = "http://zangsisi.net/";
		String Slink = null;
		String title = null;
		boolean endflag = false;
		Data d;
		InputStream in = URLManager.getURLInputStream(url, URLManager.USER_AGENT_PC);
		
		try{
			Document doc = Jsoup.parse(in, URLManager.ENCODING_UTF8,"");
			Elements root = doc.select("div[id=manga-list]");
			Elements rankList = root.select("a");
			for(int j = 0; j<rankList.size();++j){
				title = rankList.get(j).text();
				d = new Data(title);
				da.add(d);
			}
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}    
		return da;
	}
	
	
	public static ArrayList<Data>SearchTitle(String keyword)
	{
		ArrayList<Data> da = new ArrayList<>();
		String url = "http://zangsisi.net/";
		String Slink = null;
		String title = null, title2 = null;
		boolean endflag = false;
		Data d;
		InputStream in = URLManager.getURLInputStream(url, URLManager.USER_AGENT_PC);
		
		try{
			Document doc = Jsoup.parse(in, URLManager.ENCODING_UTF8,"");
			Elements root = doc.select("div[id=manga-list]");
			Elements rankList = root.select("a");
			for(int j = 0; j<rankList.size();++j){
				Slink = rankList.get(j).attr("href");
				title = rankList.get(j).text();

				if(title.equals(keyword)){ 
					InputStream in2 = URLManager.getURLInputStream(Slink, URLManager.USER_AGENT_PC);
					Document doc2 = Jsoup.parse(in2, URLManager.ENCODING_UTF8,"");
					Elements root2 = doc2.select("div[id=post]");
					Elements subroot2 = root2.select("div[class=contents]");
					Elements rankList2 = subroot2.select("p");
				
					for(int i = 0; i < rankList2.size(); i++) {
						title2 = rankList2.get(i).text();

						d = new Data(title2);
						da.add(d);
						endflag = true;
					}
				}
				if(endflag)
					break;
			}
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}    
		return da;
	}
	
	
	public static String SearchLink(String keyword, String keyword2)
	{
		ArrayList<Data> da = new ArrayList<>();
		String url = "http://zangsisi.net/";
		String Slink = null, Slink2 = null;
		String title = null, title2 = null, result = null;
		boolean endflag = false;
		Data d;
		InputStream in = URLManager.getURLInputStream(url, URLManager.USER_AGENT_PC);
		
		try{
			Document doc = Jsoup.parse(in, URLManager.ENCODING_UTF8,"");
			Elements root = doc.select("div[id=manga-list]");
			Elements rankList = root.select("a");

			for(int j = 0; j<rankList.size();++j){
				Slink = rankList.get(j).attr("href");
				title = rankList.get(j).text();
	
				if(title.equals(keyword)){ 
					InputStream in2 = URLManager.getURLInputStream(Slink, URLManager.USER_AGENT_PC);
					Document doc2 = Jsoup.parse(in2, URLManager.ENCODING_UTF8,"");
					Elements root2 = doc2.select("div[id=post]");
					Elements subroot2 = root2.select("div[class=contents]");
					Elements rankList2 = subroot2.select("p");
					Elements rankList_link = subroot2.select("a");
					for(int i = 0; i < rankList2.size(); i++) {		
						title2 = rankList2.get(i).text();
						if(title2.equals(keyword2)){
							Slink2 = rankList_link.get(i).attr("href");
							endflag = true;
						}
					}
				}
				if(endflag)
					break;
			}
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}    
		
		return Slink2;
	}

}
