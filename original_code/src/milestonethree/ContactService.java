package milestonethree;

import java.util.HashMap;

public class ContactService {
	// Create an empty hash map 
    static HashMap<String, Contact> map = new HashMap<>(); 

    public static Contact addContact (String ID, String firstName, String lastName, String phone, String address) {
    	Contact newContact = new Contact (ID, firstName, lastName, phone, address);
    	if (!map.containsKey(ID)) {
    		map.put(ID, newContact);
    		return newContact;
    	}
    	return null;
    }
    public static Contact updateContact(String ID, String firstName, String lastName, String phone, String address) {
    	Contact updateID = map.get(ID);
    	if (updateID != null) {
    		updateID.setFirstName(firstName);
    		updateID.setLastName(lastName);
    		updateID.setPhone(phone);
    		updateID.setAddress(address);
    	}
    	return updateID;
    }
    public static Contact getContact(String ID) {
    	return(map.get(ID));
    }
    public static String deleteContact (String ID) {
    	map.remove(ID);
    	return ID;
    }
    
}
