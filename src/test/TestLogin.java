package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import logic.controller.LoginController;

class TestLogin {
	LoginController lc = new LoginController();
	
	@Test
	void testStudent() throws Exception {
		assertEquals(0, lc.login("mailS", "1234"));
	}
	
	@Test
	void testLibrarian() throws Exception {
		assertEquals(1, lc.login("mailL", "1234"));
	}
	
	@Test
	void testFail() throws Exception {
		assertEquals(-1, lc.login("ab", "00"));
	}
	
}
