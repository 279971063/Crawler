package csust.crawler.orm;

public class IndexLocation {
	private int id;
	private String value;
	
	public IndexLocation() {

	}
	
	public IndexLocation(int id, String value) {
		super();
		this.id = id;
		this.value = value;
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

	@Override
	public String toString() {
		return "IndexLocation [id=" + id + ", value=" + value + "]";
	}
	
	
}
