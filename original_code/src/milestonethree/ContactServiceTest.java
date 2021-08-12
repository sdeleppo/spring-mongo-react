package milestonethree;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ContactServiceTest {
	
	@Test
	//adds two contacts to make sure they are correctly added
	void testAddContact() {
		Contact con = ContactService.addContact("111", "Sarah", "Deleppo", "1111111111", "274 lane");
		Contact con2 = ContactService.addContact("112", "Sarah", "Deleppo", "2222222222", "274 lane");
		assertEquals(con, ContactService.getContact("111"));
		assertEquals(con2, ContactService.getContact("112"));
	}
	@Test
	//tries to add duplicate ID to list, should only allow unique IDs
	void testUniqueAdd() {
		Contact con = ContactService.addContact("120", "Sarah", "Deleppo", "4444444444", "274 lane");
		Contact con2 = ContactService.addContact("120", "John", "Deleppo", "4444444444", "274 lane");
		assertEquals("Sarah", ContactService.getContact("120").getFirstName());
	}

	@Test
	//test if contact was correctly updated
	void testUpdateContact() {
		Contact add = ContactService.addContact("120", "Sarah", "Deleppo", "5555555555", "274 lane");
		Contact con = ContactService.updateContact("120", "Test", "Change", "6666666666", "124 street");
		assertEquals(con.getFirstName(), ContactService.getContact("120").getFirstName());
		assertEquals(con.getLastName(), ContactService.getContact("120").getLastName());
		assertEquals(con.getPhone(), ContactService.getContact("120").getPhone());
		assertEquals(con.getAddress(), ContactService.getContact("120").getAddress());
	}

	@Test
	//test if contact was successfully deleted
	void testDeleteContact() {
		Contact add = ContactService.addContact("111", "Sarah", "Deleppo", "5555555555", "274 lane");
		String con = ContactService.deleteContact("111");
		assertNull(ContactService.getContact(con));
	}

}
