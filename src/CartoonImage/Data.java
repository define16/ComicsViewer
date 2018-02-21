package CartoonImage;

public class Data {
	String keyword, link;

	public Data(String keyword, String link)
	{
		this.keyword = keyword;
		this.link = link;
	}
	
	public Data(String keyword)
	{
		this.keyword = keyword;
	}
	
	public String getkeyword()
	{
		return keyword;
	}
	public void setkeyword(String keyword)
	{
		this.keyword = keyword;
	}
	
	public String getLink()
	{
		return link;
	}
	public void setLink(String link)
	{
		this.link = link;
	}

	public boolean equals(Object obj)
	{
		Data d = (Data)obj;
		return d.getkeyword().equals(keyword) &&
				d.getLink().equals(link);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%s",keyword);		
	} 
	
}
