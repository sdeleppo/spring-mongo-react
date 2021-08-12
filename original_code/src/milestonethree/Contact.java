package milestonethree;

public class Contact {
	private String ID = ""; //not longer than 10 characters not null, not updatable 
	private String firstName = "";//not longer than 10 characters not null
    private String lastName = "";//not longer than 10 characters not null
    private String phone = "";//not longer than 10 digits, not null
    private String address = "";//not longer than 30 characters not null
    
    public Contact(String ID, String firstName, String lastName, String phone, String address) {
    	this.setID(ID);
    	this.setFirstName(firstName);
    	this.setLastName(lastName);
    	this.setPhone(phone);
    	this.setAddress(address);
    }

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		if (ID != null) { 
			if (ID.length()<=10) 
				this.ID = ID;
		}
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if (firstName != null) {
			if (firstName.length() <= 10) 
				this.firstName = firstName;
		}
		
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if (lastName != null) {
			if (lastName.length() <= 10)
				this.lastName = lastName;
		}
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		if (phone != null) {
			if(phone.matches("[0-9]+") && phone.length() == 10)
				this.phone = phone;
		}
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		if (address != null) {
			if (address.length() <= 30)
				this.address = address;
		}
	}
}
