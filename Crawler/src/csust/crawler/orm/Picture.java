package csust.crawler.orm;

public class Picture {
	private int id;
	private String name;
	private KeyWord keyWord;
	private String description;
	private String objurl;

	public Picture() {

	}

	public Picture(int id, String name, KeyWord keyWord, String description,
			String objurl) {
		super();
		this.id = id;
		this.name = name;
		this.keyWord = keyWord;
		this.description = description;
		this.objurl = objurl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getObjurl() {
		return objurl;
	}

	public void setObjurl(String objurl) {
		this.objurl = objurl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public KeyWord getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(KeyWord keyWord) {
		this.keyWord = keyWord;
	}

	@Override
	public String toString() {
		return "Picture [id=" + id + ", name=" + name + ", keyWord=" + keyWord
				+ ", description=" + description + ", objurl=" + objurl + "]";
	}

}
