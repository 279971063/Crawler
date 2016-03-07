package csust.crawler.orm;

public class KeyWord implements Comparable<KeyWord>{
	private int id;
	private String value;
	private int isadd;

	public KeyWord() {

	}

	
	
	public KeyWord(int id, String value, int isadd) {
		super();
		this.id = id;
		this.value = value;
		this.isadd = isadd;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getIsadd() {
		return isadd;
	}

	public void setIsadd(int isadd) {
		this.isadd = isadd;
	}



	@Override
	public String toString() {
		return "KeyWord [id=" + id + ", value=" + value + ", isadd=" + isadd
				+ "]";
	}



	@Override
	public int compareTo(KeyWord o) {
		if(this.id == o.id){
			return 0;
		}
		else{
			return -1;
		}
	}
	

	
	
	
}
