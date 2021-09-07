package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mysql.jdbc.Statement;

class DbConnectTest {
	backend.DbConnect dbObj;

	@BeforeEach
	void setUp() throws Exception {
		dbObj = new backend.DbConnect();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void connectionTest() {
		Statement actual = (Statement) dbObj.connection();
		assertNotEquals(null, actual);
	}

}
