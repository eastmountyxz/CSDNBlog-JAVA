package bean;

public class TrainManage {
	
	private String trainid;       //����
	private String start;         //������
	private String end;           //Ŀ�ĵ�
	private String time;          //�г�ʱ��
	private float yzprice;        //Ӳ��Ʊ��
	private float rzprice;        //����Ʊ��
	private float ywprice;        //Ӳ��Ʊ��
	private float rwprice;        //����Ʊ��
	private String root;          //����·�� 
	
	public String getTrainid() { return trainid; }
	public String getStart() { return start; }
	public String getEnd() { return end; }
	public String getTime() { return time; }
	public Float getYzprice() { return yzprice; }
	public Float getRzprice() { return rzprice; }
	public Float getYwprice() { return ywprice; }
	public Float getRwprice() { return rwprice; }
	public String getRoot() { return root; }
	
	public void setTrainid(String str) { this.trainid =  str; }
	public void setStart(String str) { this.start =  str; }
	public void setEnd(String str) { this.end = str; }
	public void setTime(String str) { this.time = str; }
	public void setYzprice(Float price) { this.yzprice = price; }
	public void setRzprice(Float price) { this.rzprice = price; }
	public void setYwprice(Float price) { this.ywprice = price; }
	public void setRwprice(Float price) { this.rwprice = price; }
	public void setRoot(String str) { this.root = str; }
	
}
