package milestonethree;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ContactTest {

	@Test
	//tests that contact only accepts values within character limit
	void testCharacterLimit() {
		Contact con = new Contact("thisisovertencharacters", "thisisovertencharacters", 
				"thisisovertencharacters", "4930594969456840", "thisisoverthirtycharacters 1029475");
		assertEquals("",con.getID());
		assertEquals("",con.getFirstName());
		assertEquals("",con.getLastName());
		assertEquals("",con.getAddress());
		assertEquals("",con.getPhone());
	}
	@Test
	//test if null values are accepted
	void testNull(){
		Contact con = new Contact(null, null, null, null, null);
		assertNotNull(con.getID());
		assertNotNull(con.getFirstName());
		assertNotNull(con.getLastName());
		assertNotNull(con.getPhone());
		assertNotNull(con.getAddress());
	}
	@Test
	//test if phone number allows letters
	void testAllowsLetters() {
		Contact con = new Contact("test", "test", 
				"test", "tttttttttt", "test 1029475");
		assertEquals("", con.getPhone());
	}
	@Test
	//test that phone number accepts 10 digits
	void testPhoneOnlyTenDigits() {
		Contact con = new Contact("test", "test", 
				"test", "1111111111", "test 1029475");
		assertEquals("1111111111", con.getPhone());
			
	}

}
