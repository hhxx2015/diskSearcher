package disk.hao.entity;

public class SearchResult {

	private String URLString;
	private String nameString;
	
	public SearchResult(String uRLString, String nameString) {
		//super();
		this.URLString = uRLString;
		this.nameString = nameString;
	}

	
	public String getURLString() {
		return URLString;
	}
	public void setURLString(String uRLString) {
		URLString = uRLString;
	}
	public String getNameString() {
		return nameString;
	}
	public void setNameString(String nameString) {
		this.nameString = nameString;
	}

	
	public static void main(String[] args) {
		System.out.println("aaa");
	}
	
	
}
