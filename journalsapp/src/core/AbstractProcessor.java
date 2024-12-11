package core;

public abstract class AbstractProcessor {
	String name;
	
	public abstract void create(String name);
	
	public abstract void delete(String name);
	
	public abstract String get();
	
	
}
