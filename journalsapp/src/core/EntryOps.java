package core;

/*
 * Date is DIR
 * Time is FILE
 */

import java.io.*;
import java.time.*;
import java.util.*;

public class EntryOps extends AbstractProcessor {

	private String currentDate;
	private String currentTime;
	private String keyword;
	private Map<String, ArrayList<String>> dateToFileEntries = new HashMap<>();
	
 

	private String getFileDate() {
		//get current local Date, set as custom string.
		String year = String.valueOf(LocalDateTime.now().getYear());
		String month = String.valueOf(LocalDateTime.now().getMonth());
		String day = String.valueOf(LocalDateTime.now().getDayOfYear());
		currentDate = year + "-" + month + "-" + day;
		
		return currentDate;
	}
	
	private String getFileTime() {
		//get current local Time, set as custom string.
		String hour = String.valueOf(LocalDateTime.now().getHour());
		String minute = String.valueOf(LocalDateTime.now().getMinute());
		String second = String.valueOf(LocalDateTime.now().getSecond());
		String nano = String.valueOf(LocalDateTime.now().getNano());
		String uuid = UUID.randomUUID().toString(); // unique identifier
		currentTime = hour + "-" + minute + "-" + second + "-" + nano + "" + uuid;
		
		
		return currentTime;
	}
	
	@Override
	public void create() {//Only works for creation of DIR/FILE of LocalDateTime now
		//THINGS TO DO:
		//Handle journal entry creations at different time locations.
		getFileTime();
		getFileDate();
		
		
		if (!dateToFileEntries.containsKey(currentDate)) {
			ArrayList<String> mapList = new ArrayList<>();
			mapList.add(currentTime);
			dateToFileEntries.put(currentDate, mapList);
			
		}
		else if (dateToFileEntries.containsKey(currentDate)) {
			ArrayList<String> mapList = new ArrayList<>();
			mapList = dateToFileEntries.get(currentDate);
			mapList.add(currentTime);
			dateToFileEntries.replace(currentDate, mapList);
			
		}
		
		File file = new File(currentTime + ".txt");
		
		try {
			//print for testing or else remove the print.
			System.out.println(file.createNewFile());
		} 
		catch (IOException e) {
			System.out.println("Error creating a file:" + e.getMessage());
			return;
		}
	}

	
	//Can choose from a menu which gets the file to remove, and the date it belongs to. 
	//we use the date it belongs to and enter it into the parameters, to replace the characters of the specific date.
	

	@Override
	public void delete(String fileToRemove, String date) {
		//delete FILE
		for(Map.Entry<String, ArrayList<String>> entry: dateToFileEntries.entrySet()) {
			ArrayList<String> mapList = new ArrayList<>();
			if (entry.getValue().contains(fileToRemove)) {

				mapList = entry.getValue();
				mapList.remove(fileToRemove);
				File file = new File(fileToRemove + ".txt");
				file.delete();
			}
		}
	}
	public void delete(String dateToRemove) {
		//delete DIR
		dateToFileEntries.remove(dateToRemove);
		File file = new File(dateToRemove + ".txt");
		file.delete();
	}
	
	
	public String write(String fileName, String userText) {
		File file = new File(fileName + ".txt");
		if (!file.exists()) {
			String errorMessage = "Create an entry before trying to write in it!";
			System.out.println(errorMessage);
			return errorMessage;
		}
			
			try ( PrintWriter printWriter = new PrintWriter(fileName + ".txt")) {
				printWriter.print(userText);
			} 
			catch (FileNotFoundException e) {
				String errorMessage = "This File doesn't exist!" + e.getMessage();
				System.out.println("This File doesn't exist!" + e.getMessage());
				return errorMessage;
			}
			
		String completionMessage = "Writing...";
		return completionMessage;
	}
	
	public <T> T search(T fileName, T Keyword) {
		if (dateToFileEntries.containsKey(fileName) && dateToFileEntries.get(fileName).contains(keyword)) {
			return fileName;
		}
		else {
			System.out.println("Can't find the keyword anywhere! Enter again");
			return null;
		}
		
	}

}

