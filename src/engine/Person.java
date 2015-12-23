package engine;

public class Person {
	
	private String surname="";
	private String name="";
	private int d;
	private int m;
	private int y;
	private String c_born="";
	private String s=""; // TRUE -> M      FALSE -> F
	
	public Person(){}
	
	public void setSurname(String s){
		this.surname=s;
	}
	
	public void setName(String s){
		this.name=s;
	}
	
	public void setDay(String d){
		this.d=Integer.parseInt(d);
	}
	
	public void setMonth(String d){
		this.m=Integer.parseInt(d);
	}
	
	public void setYear(String d){
		this.y=Integer.parseInt(d);
	}
	
	public void setBornCity(String s){
		this.c_born=s;
	}
	
	public void setSex(String b){
		this.s=b;
	}
	
	public String getSurname(){
		return surname;
	}
	
	public String getName(){
		return name;
	}
	
	public String getBornCity(){
		return c_born;
	}
	
	public int getDay(){
		return d;
	}
	
	public int getMonth(){
		return m;
	}
	
	public int getYear(){
		return y;
	}
	
	public String getSex(){
		return s;
	}
	
}
