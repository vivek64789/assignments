package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AlgorithmTest {
	backend.Algorithm AlgorithmObj;
	ArrayList<Integer> sortedIntegerData;
	ArrayList<Integer> unsortedIntegerData;
	ArrayList<String> sortedStringData;
	ArrayList<String> unsortedStringData;

	@BeforeEach
	void setUp() throws Exception {
		AlgorithmObj = new backend.Algorithm();
		sortedIntegerData = new ArrayList<>();
		sortedIntegerData.add(1);
		sortedIntegerData.add(3);
		sortedIntegerData.add(6);
		sortedIntegerData.add(8);
		sortedIntegerData.add(9);
		sortedIntegerData.add(10);

		unsortedIntegerData = new ArrayList<>();
		unsortedIntegerData.add(4);
		unsortedIntegerData.add(6);
		unsortedIntegerData.add(2);
		unsortedIntegerData.add(1);
		unsortedIntegerData.add(9);
		unsortedIntegerData.add(5);

		sortedStringData = new ArrayList<>();
		sortedStringData.add("Anand~1");
		sortedStringData.add("Bibek~2");
		sortedStringData.add("Could~3");
		sortedStringData.add("Must~4");
		sortedStringData.add("Write~5");
		sortedStringData.add("ZZZZZ~6");

		unsortedStringData = new ArrayList<>();
		unsortedStringData.add("Must~1");
		unsortedStringData.add("ZZZZZ~2");
		unsortedStringData.add("Could~3");
		unsortedStringData.add("Anand~4");
		unsortedStringData.add("Write~5");
		unsortedStringData.add("Bibek~6");

	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void binarySearchSortedIntegertest() {
		int actual = AlgorithmObj.binarySearch(sortedIntegerData, 3);
		int expected = 1;
		assertEquals(expected, actual);

	}	

	@Test
	void binarySearchUnSortedIntegertest() {
		int actual = AlgorithmObj.binarySearch(unsortedIntegerData, 9);
		int expected = 4;
		assertEquals(expected, actual);
		
	}	
	
	@Test
	void binarySearchSortedStringTest() {
		int actual = AlgorithmObj.binarySearchString(sortedStringData, "Bibek");
		int expected = 1;
		assertEquals(expected, actual);
	}
	
	@Test
	void binarySearchUnSortedStringTest() {
		int actual = AlgorithmObj.binarySearchString(unsortedStringData, "Bibek");
		int expected = 5;
		assertEquals(expected, actual);
	}
}



