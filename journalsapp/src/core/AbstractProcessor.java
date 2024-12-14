package core;

public abstract class AbstractProcessor {
	
	public abstract void create(); //changed the parameter to match other classes.
	
	public abstract void delete(String name, String date);
	
	public abstract void delete(String name); //added new delete to handle 1 and 2 parameter deletions.

	//deleted get abstract method as not needed for all. 
	
}
