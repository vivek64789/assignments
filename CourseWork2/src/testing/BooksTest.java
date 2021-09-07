package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.AddBooks;

class BooksTest {
	frontend.Books booksObj;

	@BeforeEach
	void setUp() throws Exception {
		booksObj = new frontend.Books("1", "Bookname", "BookPrice", "BookEdition", "BookPublishDate", "BookPublisher", "BookStatus");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void getBookIdTest() {
		String actual = booksObj.getBookID();
		String expected = "1";
		assertEquals(expected, actual);
	}
	
	
	@Test
	void getBookEditionTest() {
		String actual = booksObj.getBookEdition();
		String expected = "BookEdition";
		assertEquals(expected, actual);
	}
	
	

}
