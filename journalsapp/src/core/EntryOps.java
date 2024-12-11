package core;

public class EntryOps extends AbstractProcessor {

String fileName;
String Keyword;

	public String getTime() {
		return fileName;
	}
	
	@Override
	public void create(String fileName) {
		
	}

	@Override
	public void delete(String fileName) {

	}
	
	public void write(String fileName) {
		
	}
	
	public boolean search(String fileName, String Keyword) {
		
		return true;
	}

	@Override
	public String get() {
		return null;
	}

}
