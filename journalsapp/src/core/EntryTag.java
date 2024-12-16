package core;

import java.util.*;

public class EntryTag extends AbstractProcessor{
	private String tagName;
	private String Colour;
	public String userEntredTagName;
	public String userEntredTagColour;
	private Map<String, ArrayList<String>> tagToFile = new HashMap<>();
	
	@Override
	public void create() {
		if (tagToFile.containsKey(tagName)) {
			System.out.println("Tag already exists: " + tagName);
            return;
        }
		tagToFile.put(tagName, new ArrayList<>());
        System.out.println("Tag created as:  " + tagName);
	}

	@Override
	public void delete(String tagName) {
		//if tag exists remove it along with all its files attached to it.
		//deletes TAG
		
		if (tagToFile.containsKey(tagName)) {
			System.out.println("Removed Tag: " + tagName);
			tagToFile.remove(tagName);
            return;
		}
		else {
			System.out.println("Tag " + tagName + " doesn't exist. Create tag before deleting.");
		}
		
	}
	
	@Override
	public void delete(String nameTag, String file) {
		//deletes file from tag association
		
		//create instance of ArrayList
		ArrayList<String> filesAssociatedWithTag = new ArrayList<>();
		//does key exist?
				//	get key
		if (tagToFile.containsKey(file)) {
			//does value exist?
			if ((!tagToFile.get(nameTag).isEmpty()) && tagToFile.get(nameTag).contains(file)) {
				//use key to get map.value
				filesAssociatedWithTag = tagToFile.get(nameTag);
				//remove file from List
				filesAssociatedWithTag.remove(file);
				//replace the new List with existing List
				tagToFile.replace(nameTag, filesAssociatedWithTag);
			}
			else if (tagToFile.get(nameTag).isEmpty()) {
				//its empty system print error
				System.out.println("Tag " + tagName + " doesn't exist. Create tag before deleting file.");
			}
			else if (!tagToFile.get(nameTag).contains(file)) {
				//Can't find the fileName in the list system print error
				System.out.println("File " + file + " is not attached to this tag.");
			}
		}
		
		else {
			System.out.println("Tag " + nameTag + " doesn't exist. Create tag before deleting file association to tag.");
		}
	}

	public void associate(String nameTag, String nameFile){
		//if tag exists, create an arrayList instance
		//arrayList = map.get() // the arrayList equals the value of the map
		// arraylist.add
		//map.replace(tag, arrayList)
		
		if (tagToFile.containsKey(nameTag)) {
			ArrayList<String> filesAssociatedWithTag = new ArrayList<>();
			if (tagToFile.get(nameTag).isEmpty()) {
				filesAssociatedWithTag.add(nameFile);
				tagToFile.replace(nameTag, filesAssociatedWithTag);
			}
			else if (!tagToFile.get(nameTag).isEmpty()) {
				filesAssociatedWithTag = tagToFile.get(nameTag);
				filesAssociatedWithTag.add(nameFile);
				tagToFile.replace(nameTag, filesAssociatedWithTag);
			}
		}
		
	}

	public String getTag(String file) {
		//get the tag associated with a file
		
		
		//iterate through map to find KEY(tagName) of VALUE(file)
		//return KEY
		
		for (Map.Entry<String, ArrayList<String>> entry: tagToFile.entrySet()) {
			if (entry.getValue().contains(file)) {
				return entry.getKey();
			}
		}
		
		
		return null;
	}

	

	
	
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public void setColour(String Colour) {
		this.Colour = Colour;
	}

}
