package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MergeSortTest {
	backend.MergeSort mergeSortObj;
	ArrayList<String> alreadySortedList;
	ArrayList<String> nonSortedList;
	ArrayList<String>alreadySortedDescendingList;

	@BeforeEach
	void setUp() throws Exception {
		mergeSortObj = new backend.MergeSort();
		alreadySortedList = new ArrayList<>();
		nonSortedList = new ArrayList<>();
		
		alreadySortedList.add("Anand");
		alreadySortedList.add("Bibek");
		alreadySortedList.add("For");
		alreadySortedList.add("My");
		alreadySortedList.add("No");
		alreadySortedList.add("Write");
		
		nonSortedList = new ArrayList<>();
		nonSortedList.add("No");
		nonSortedList.add("Write");
		nonSortedList.add("For");
		nonSortedList.add("Bibek");
		nonSortedList.add("My");
		nonSortedList.add("Anand");
		
		alreadySortedDescendingList =new ArrayList<>();
		alreadySortedDescendingList.add("Write");
		alreadySortedDescendingList.add("No");
		alreadySortedDescendingList.add("My");
		alreadySortedDescendingList.add("For");
		alreadySortedDescendingList.add("Bibek");
		alreadySortedDescendingList.add("Anand");
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void MergeSortAlreadySortedDataTest() {
		ArrayList<String> actual = mergeSortObj.divide(alreadySortedList, 0, alreadySortedList.size()-1);
		ArrayList<String> expected = alreadySortedList;
		assertEquals(expected, actual);
	}
	
	@Test
	void MergeSortNonSortedDataTest() {
		ArrayList<String> actual = mergeSortObj.divide(nonSortedList, 0, nonSortedList.size()-1);
		ArrayList<String> expected = alreadySortedList;
		assertEquals(expected, actual);
	}
	
	@Test
	void MergeSortAlreadySortedDataDescendingTest() {
		ArrayList<String> actual = mergeSortObj.divideDescending(alreadySortedList, 0, nonSortedList.size()-1);
		ArrayList<String> expected = alreadySortedDescendingList;
		assertEquals(expected, actual);
	}
	
	@Test
	void MergeSortNonSortedDataDescendingTest() {
		ArrayList<String> actual = mergeSortObj.divideDescending(nonSortedList, 0, nonSortedList.size()-1);
		ArrayList<String> expected = alreadySortedDescendingList;
		assertEquals(expected, actual);
	}
	
}
