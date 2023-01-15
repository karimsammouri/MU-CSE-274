import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

/**
 * Name: Karim R. Sammouri
 * Instructor: Prof. James Kiper
 * Course: CSE 274F
 * Description: Program #2 – Linked List Algorithms Tester.
 * Attribution: Everything is written by me.
 */

class LinkedAlgorithmsTests {

	@Test
	void testConstructor() {
		String[] names = {"Karim", "Jake", "Sarah", "Jonathan"};
		String[] names2 = null;
		LinkedAlgorithms list1 = new LinkedAlgorithms();
		LinkedAlgorithms list2 = new LinkedAlgorithms(names);
		LinkedAlgorithms list3 = new LinkedAlgorithms(list2);
		assertEquals(0, list1.size());
		assertEquals(4, list2.size());
		assertEquals(4, list3.size());
		assertEquals("[]", list1.toString());
		assertEquals("[Karim, Jake, Sarah, Jonathan]", list2.toString());
		assertEquals("[Karim, Jake, Sarah, Jonathan]", list3.toString());
		try {
			LinkedAlgorithms list4 = new LinkedAlgorithms(names2);
		} catch (NullPointerException ex) {
			System.out.println("Constructor NullPointerException Test Pass");
		}
	}

	@Test
	void testToArray() {
		String[] names = {"Karim", "Jake", "Sarah", "Jonathan"};
		LinkedAlgorithms list1 = new LinkedAlgorithms();
		LinkedAlgorithms list2 = new LinkedAlgorithms(names);
		LinkedAlgorithms list3 = new LinkedAlgorithms(list2);
		String[] listArray1 = list1.toArray();
		String[] listArray2 = list2.toArray();
		String[] listArray3 = list3.toArray();
		assertEquals(0, listArray1.length);
		assertEquals(4, listArray2.length);
		assertEquals(4, listArray3.length);

		for (int i = 0; i < names.length; i++) {
			assertEquals(names[i], listArray2[i]);
			assertEquals(names[i], listArray3[i]);
		}
	}

	@Test
	void testEqualsLinkedList() {
		String[] names = {"Karim", "Jake", "Sarah", "Jonathan"};
		String[] names2 = {"Karim", "Yara", "Ben"};
		String[] names3 = {"Mayar", "Rasha", "Santa"};
		LinkedAlgorithms list1 = new LinkedAlgorithms();
		LinkedAlgorithms list2 = new LinkedAlgorithms(names);
		LinkedAlgorithms list3 = new LinkedAlgorithms(list2);
		LinkedAlgorithms list4 = new LinkedAlgorithms(names2);
		LinkedAlgorithms list5 = new LinkedAlgorithms(names3);
		assertEquals(false, list1.equalsLinkedList(list2));
		assertEquals(true, list2.equalsLinkedList(list3));
		assertEquals(false, list3.equalsLinkedList(list4));
		assertEquals(false, list4.equalsLinkedList(list5));
	}

	@Test
	void testContains() {
		String[] names = {"Karim", "Jake", "Sarah", "Jonathan"};
		String[] names2 = {"Karim", "Yara", "Ben"};
		LinkedAlgorithms list1 = new LinkedAlgorithms();
		LinkedAlgorithms list2 = new LinkedAlgorithms(names);
		LinkedAlgorithms list3 = new LinkedAlgorithms(list2);
		LinkedAlgorithms list4 = new LinkedAlgorithms(names2);
		assertEquals(false, list1.contains("Karim"));
		assertEquals(true, list2.contains("Sarah"));
		assertEquals(true, list3.contains("Sarah"));
		assertEquals(false, list4.contains("Sarah"));
	}

	@Test
	void testFind() {
		String[] names = {"Karim", "Jake", "Sarah", "Jonathan"};
		String[] names2 = {"Karim", "Yara", "Ben"};
		LinkedAlgorithms list1 = new LinkedAlgorithms();
		LinkedAlgorithms list2 = new LinkedAlgorithms(names);
		LinkedAlgorithms list3 = new LinkedAlgorithms(list2);
		LinkedAlgorithms list4 = new LinkedAlgorithms(names2);
		assertEquals(-1, list1.find("Karim"));
		assertEquals(0, list2.find("Karim"));
		assertEquals(2, list2.find("Sarah"));
		assertEquals(-1, list2.find("Yara"));
		assertEquals(2, list3.find("Sarah"));
		assertEquals(1, list4.find("Yara"));
		assertEquals(2, list4.find("Ben"));
	}

	@Test
	void testGetFirstAndGetLast() {
		String[] names = {"Karim", "Jake", "Sarah", "Jonathan"};
		String[] names2 = {"Karim", "Yara", "Ben"};
		String[] names3 = {};
		LinkedAlgorithms list1 = new LinkedAlgorithms(names);
		LinkedAlgorithms list2 = new LinkedAlgorithms(names2);
		LinkedAlgorithms list3 = new LinkedAlgorithms(names3);
		assertEquals("Karim", list1.getFirst());
		assertEquals("Jonathan", list1.getLast());
		assertEquals("Karim", list2.getFirst());
		assertEquals("Ben", list2.getLast());
		try {
			list3.getFirst();
		} catch (NoSuchElementException ex) {
			System.out.println("getFirst() NoSuchElementException Test Pass");
		}
		try {
			list3.getLast();
		} catch (NoSuchElementException ex) {
			System.out.println("getLast() NoSuchElementException Test Pass");
		}
	}

	@Test
	void testGetAt() {
		String[] names = {"Karim", "Jake", "Sarah", "Jonathan"};
		String[] names2 = {"Karim", "Yara", "Ben"};
		LinkedAlgorithms list1 = new LinkedAlgorithms(names);
		LinkedAlgorithms list2 = new LinkedAlgorithms(names2);
		assertEquals("Karim", list1.getAt(0));
		assertEquals("Sarah", list1.getAt(2));
		assertEquals("Jonathan", list1.getAt(3));
		assertEquals("Yara", list2.getAt(1));
		assertEquals("Ben", list2.getAt(2));
		try {
			list1.getAt(-1);
		} catch (IndexOutOfBoundsException ex) {
			System.out.println("getAt() IndexOutOfBoundsException Test Pass");
		}
		try {
			list1.getAt(4);
		} catch (IndexOutOfBoundsException ex) {
			System.out
					.println("getAt() IndexOutOfBoundsException Test Two Pass");
		}
	}

	@Test
	void testInsertFirstAndInsertLast() {
		String[] names = {"Karim", "Jake", "Sarah", "Jonathan"};
		String[] names2 = {};
		LinkedAlgorithms list1 = new LinkedAlgorithms(names);
		LinkedAlgorithms list2 = new LinkedAlgorithms(names2);
		assertEquals("[Karim, Jake, Sarah, Jonathan]", list1.toString());
		assertEquals(4, list1.size());
		list1.insertFirst("Kyle");
		assertEquals("[Kyle, Karim, Jake, Sarah, Jonathan]", list1.toString());
		assertEquals(5, list1.size());
		list1.insertLast("Bella");
		assertEquals("[Kyle, Karim, Jake, Sarah, Jonathan, Bella]",
				list1.toString());
		assertEquals(6, list1.size());
		assertEquals("[]", list2.toString());
		assertEquals(0, list2.size());
		list2.insertLast("King");
		assertEquals("[King]", list2.toString());
		assertEquals(1, list2.size());
	}

	@Test
	void testInsertAt() {
		String[] names = {"Karim", "Jake", "Sarah", "Jonathan"};
		LinkedAlgorithms list1 = new LinkedAlgorithms(names);
		LinkedAlgorithms list2 = new LinkedAlgorithms();
		assertEquals("[Karim, Jake, Sarah, Jonathan]", list1.toString());
		assertEquals(4, list1.size());
		list1.insertAt(0, "Kyle");
		assertEquals("[Kyle, Karim, Jake, Sarah, Jonathan]", list1.toString());
		assertEquals(5, list1.size());
		list1.insertAt(1, "Meredith");
		assertEquals("[Kyle, Meredith, Karim, Jake, Sarah, Jonathan]",
				list1.toString());
		assertEquals(6, list1.size());
		list1.insertAt(4, "Maddie");
		assertEquals("[Kyle, Meredith, Karim, Jake, Maddie, Sarah, Jonathan]",
				list1.toString());
		assertEquals(7, list1.size());
		list1.insertAt(6, "Wes");
		assertEquals(
				"[Kyle, Meredith, Karim, Jake, Maddie, Sarah, Wes, Jonathan]",
				list1.toString());
		assertEquals(8, list1.size());
		list1.insertAt(8, "Ryan");
		assertEquals(
				"[Kyle, Meredith, Karim, Jake, Maddie, Sarah, Wes, Jonathan, Ryan]",
				list1.toString());
		assertEquals(9, list1.size());
		list2.insertAt(0, "one");
		assertEquals("[one]", list2.toString());
		assertEquals(1, list2.size());
		try {
			list1.insertAt(-1, "test");
		} catch (IndexOutOfBoundsException ex) {
			System.out
					.println("insertAt() IndexOutOfBoundsException Test Pass");
		}
		try {
			list1.insertAt(10, "test");
		} catch (IndexOutOfBoundsException ex) {
			System.out.println(
					"insertAt() IndexOutOfBoundsException Test Two Pass");
		}
	}

	@Test
	void testInsertBeforeAndInsertAfter() {
		String[] names = {"Karim", "Jake", "Sarah", "Jonathan"};
		LinkedAlgorithms list1 = new LinkedAlgorithms(names);
		assertEquals("[Karim, Jake, Sarah, Jonathan]", list1.toString());
		assertEquals(4, list1.size());
		list1.insertBefore("Jenny", "Karim");
		assertEquals("[Jenny, Karim, Jake, Sarah, Jonathan]", list1.toString());
		assertEquals(5, list1.size());
		list1.insertBefore("Jolly", "Jake");
		assertEquals("[Jenny, Karim, Jolly, Jake, Sarah, Jonathan]",
				list1.toString());
		assertEquals(6, list1.size());
		list1.insertBefore("Mike", "Jonathan");
		assertEquals("[Jenny, Karim, Jolly, Jake, Sarah, Mike, Jonathan]",
				list1.toString());
		assertEquals(7, list1.size());
		list1.insertAfter("Mint", "Karim");
		assertEquals("[Jenny, Karim, Mint, Jolly, Jake, Sarah, Mike, Jonathan]",
				list1.toString());
		assertEquals(8, list1.size());
		list1.insertAfter("Sally", "Jonathan");
		assertEquals(
				"[Jenny, Karim, Mint, Jolly, Jake, Sarah, Mike, Jonathan, Sally]",
				list1.toString());
		assertEquals(9, list1.size());
		try {
			list1.insertBefore("Jake", "test");
		} catch (NoSuchElementException ex) {
			System.out
					.println("insertBefore() NoSuchElementException Test Pass");
		}
		try {
			list1.insertAfter("Jake", "test");
		} catch (NoSuchElementException ex) {
			System.out
					.println("insertAfter() NoSuchElementException Test Pass");
		}
	}

	@Test
	void testReverse() {
		String[] names = {"Karim", "Jake", "Sarah", "Jonathan"};
		LinkedAlgorithms list1 = new LinkedAlgorithms(names);
		LinkedAlgorithms list2 = new LinkedAlgorithms();
		assertEquals("[Karim, Jake, Sarah, Jonathan]", list1.toString());
		assertEquals("[]", list2.toString());
		list1.reverse();
		list2.reverse();
		assertEquals("[Jonathan, Sarah, Jake, Karim]", list1.toString());
		assertEquals("[]", list2.toString());
	}

	@Test
	void testRemoveFirst() {
		String[] names = {"Karim", "Jake", "Sarah", "Jonathan"};
		String[] names2 = {"Sarah"};
		LinkedAlgorithms list1 = new LinkedAlgorithms(names);
		LinkedAlgorithms list2 = new LinkedAlgorithms(names2);
		assertEquals("Karim", list1.removeFirst());
		assertEquals("[Jake, Sarah, Jonathan]", list1.toString());
		assertEquals("Sarah", list2.removeFirst());
		assertEquals("[]", list2.toString());
		try {
			list2.removeFirst();
		} catch (NoSuchElementException ex) {
			System.out
					.println("removeFirst() NoSuchElementException Test Pass");
		}
	}

	@Test
	void testRemoveLast() {
		String[] names = {"Karim", "Jake", "Sarah", "Jonathan"};
		String[] names2 = {"Sarah"};
		LinkedAlgorithms list1 = new LinkedAlgorithms(names);
		LinkedAlgorithms list2 = new LinkedAlgorithms(names2);
		assertEquals("Jonathan", list1.removeLast());
		assertEquals("[Karim, Jake, Sarah]", list1.toString());
		assertEquals("Sarah", list2.removeLast());
		assertEquals("[]", list2.toString());
		try {
			list2.removeLast();
		} catch (NoSuchElementException ex) {
			System.out.println("removeLast() NoSuchElementException Test Pass");
		}
	}

	@Test
	void testRemoveAt() {
		String[] names = {"Karim", "Jake", "Sarah", "Jonathan"};
		LinkedAlgorithms list1 = new LinkedAlgorithms(names);
		String[] names2 = {"Karim"};
		LinkedAlgorithms list2 = new LinkedAlgorithms(names2);
		assertEquals("Karim", list1.removeAt(0));
		assertEquals("[Jake, Sarah, Jonathan]", list1.toString());
		assertEquals("Sarah", list1.removeAt(1));
		assertEquals("[Jake, Jonathan]", list1.toString());
		assertEquals("Jonathan", list1.removeAt(1));
		assertEquals("[Jake]", list1.toString());
		assertEquals("Jake", list1.removeAt(0));
		assertEquals("[]", list1.toString());
		try {
			list2.removeAt(-1);
		} catch (IndexOutOfBoundsException ex) {
			System.out
					.println("removeAt() IndexOutOfBoundsException Test Pass");
		}
		try {
			list2.removeAt(1);
		} catch (IndexOutOfBoundsException ex) {
			System.out.println(
					"removeAt() IndexOutOfBoundsException Test Two Pass");
		}
	}

	@Test
	void testRemoveFirstOccurenceOf() {
		String[] names = {"Karim", "Jake", "Jake", "Sarah", "Jake", "Jonathan"};
		String[] names2 = {"Karim", "Sarah", "Jonathan"};
		LinkedAlgorithms list1 = new LinkedAlgorithms(names);
		LinkedAlgorithms list2 = new LinkedAlgorithms(names2);
		assertTrue(list1.removeFirstOccurrenceOf("Jake"));
		assertEquals("[Karim, Jake, Sarah, Jake, Jonathan]", list1.toString());
		assertTrue(list1.removeFirstOccurrenceOf("Jake"));
		assertEquals("[Karim, Sarah, Jake, Jonathan]", list1.toString());
		assertFalse(list2.removeFirstOccurrenceOf("Jake"));
		assertEquals("[Karim, Sarah, Jonathan]", list2.toString());
	}

	@Test
	void testRemoveAllOccurencesOf() {
		String[] names = {"Karim", "Jake", "Jake", "Sarah", "Jake", "Jonathan"};
		String[] names2 = {"Karim", "Sarah", "Jonathan"};
		LinkedAlgorithms list1 = new LinkedAlgorithms(names);
		LinkedAlgorithms list2 = new LinkedAlgorithms(names2);
		assertEquals(3, list1.removeAllOccurrencesOf("Jake"));
		assertEquals("[Karim, Sarah, Jonathan]", list1.toString());
		assertEquals(0, list2.removeAllOccurrencesOf("Jake"));
		assertEquals("[Karim, Sarah, Jonathan]", list2.toString());
	}

	@Test
	void testToUpper() {
		String[] names = {"Karim", "Jake", "Sarah", "Jonathan"};
		LinkedAlgorithms list1 = new LinkedAlgorithms(names);
		LinkedAlgorithms list2 = new LinkedAlgorithms();
		assertEquals("[Karim, Jake, Sarah, Jonathan]", list1.toString());
		assertEquals("[]", list2.toString());
		list1.toUpper();
		list2.toUpper();
		assertEquals("[KARIM, JAKE, SARAH, JONATHAN]", list1.toString());
		assertEquals("[]", list2.toString());
	}

	@Test
	void testGetConcatention() {
		String[] names = {"Karim", "Jake", "Sarah", "Jonathan"};
		LinkedAlgorithms list1 = new LinkedAlgorithms(names);
		LinkedAlgorithms list2 = new LinkedAlgorithms();
		assertEquals("KarimJakeSarahJonathan", list1.getConcatenation());
		assertEquals("", list2.getConcatenation());
	}

	@Test
	void testGetAlphabeticallyLast() {
		String[] names = {"Karim", "Jake", "Sarah", "Jonathan"};
		String[] names2 = {"Karim", "Sierra", "Sarah", "Jonathan"};
		String[] names3 = {"Zane", "Sierra", "Sarah", "Zans"};
		String[] names4 = {"Zane", "Sierra", "Sarah", "Zaay"};
		String[] names5 = {};
		LinkedAlgorithms list1 = new LinkedAlgorithms(names);
		LinkedAlgorithms list2 = new LinkedAlgorithms(names2);
		LinkedAlgorithms list3 = new LinkedAlgorithms(names3);
		LinkedAlgorithms list4 = new LinkedAlgorithms(names4);
		LinkedAlgorithms list5 = new LinkedAlgorithms(names5);
		assertEquals("Sarah", list1.getAlphabeticallyLast());
		assertEquals("Sierra", list2.getAlphabeticallyLast());
		assertEquals("Zans", list3.getAlphabeticallyLast());
		assertEquals("Zane", list4.getAlphabeticallyLast());
		try {
			list5.getAlphabeticallyLast();
		} catch (NoSuchElementException ex) {
			System.out.println(
					"getAlphabeticallyLast() NoSuchElementException Test Pass");
		}
	}

	@Test
	void testIndexOfAlphabeticallyLast() {
		String[] names = {"Karim", "Jake", "Sarah", "Jonathan"};
		String[] names2 = {"Karim", "Sierra", "Sarah", "Jonathan"};
		String[] names3 = {"Zane", "Sierra", "Sarah", "Zans"};
		String[] names4 = {"Zane", "Sierra", "Sarah", "Zaay"};
		String[] names5 = {};
		LinkedAlgorithms list1 = new LinkedAlgorithms(names);
		LinkedAlgorithms list2 = new LinkedAlgorithms(names2);
		LinkedAlgorithms list3 = new LinkedAlgorithms(names3);
		LinkedAlgorithms list4 = new LinkedAlgorithms(names4);
		LinkedAlgorithms list5 = new LinkedAlgorithms(names5);
		assertEquals(2, list1.indexOfAlphabeticallyLast());
		assertEquals(1, list2.indexOfAlphabeticallyLast());
		assertEquals(3, list3.indexOfAlphabeticallyLast());
		assertEquals(0, list4.indexOfAlphabeticallyLast());
		try {
			list5.indexOfAlphabeticallyLast();
		} catch (NoSuchElementException ex) {
			System.out.println(
					"indexOfAlphabeticallyLast() NoSuchElementException Test Pass");
		}
	}

	@Test
	void testAnagram() {
		String[] names = {"Karim", "Jake", "Sarah", "Jonathan"};
		String[] names2 = {"Sarah", "Jonathan", "Karim", "Jake"};
		String[] names3 = {"Jake", "Jonathan"};
		String[] names4 = {"Yara", "Sean", "Bonny"};
		String[] names5 = {};
		String[] names6 = {"Yanny", "Page"};
		String[] names7 = {"Page", "Yanny"};
		String[] names8 = {"Page", "Pollock", "Page"};
		String[] names9 = {"Page", "Pollock", "Pollock"};
		LinkedAlgorithms list1 = new LinkedAlgorithms(names);
		LinkedAlgorithms list2 = new LinkedAlgorithms(names);
		LinkedAlgorithms list3 = new LinkedAlgorithms(names2);
		LinkedAlgorithms list4 = new LinkedAlgorithms(names3);
		LinkedAlgorithms list5 = new LinkedAlgorithms(names4);
		LinkedAlgorithms list6 = new LinkedAlgorithms(names5);
		LinkedAlgorithms list7 = new LinkedAlgorithms(names6);
		LinkedAlgorithms list8 = new LinkedAlgorithms(names7);
		LinkedAlgorithms list9 = new LinkedAlgorithms(names8);
		LinkedAlgorithms list10 = new LinkedAlgorithms(names9);
		assertEquals(true, list1.anagrams(list2));
		assertEquals(true, list1.anagrams(list3));
		assertEquals(false, list1.anagrams(list4));
		assertEquals(false, list1.anagrams(list5));
		assertEquals(false, list1.anagrams(list6));
		assertEquals(false, list4.anagrams(list7));
		assertEquals(true, list7.anagrams(list8));
		assertEquals(false, list8.anagrams(list9));
		assertEquals(false, list9.anagrams(list10));
	}
}
