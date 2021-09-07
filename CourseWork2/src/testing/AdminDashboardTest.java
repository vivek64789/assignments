package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import frontend.Publishers;

class AdminDashboardTest {
	
	frontend.AdminDashboard adminDashboardObj;

	@BeforeEach
	void setUp() throws Exception {
		adminDashboardObj = new frontend.AdminDashboard();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void fetchPublisherDataTest() {
		ArrayList<Publishers> actual = adminDashboardObj.fetchPublisherData();
		assertNotEquals(null, actual);
	}

}
