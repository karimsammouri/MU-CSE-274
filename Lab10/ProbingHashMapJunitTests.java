import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProbingHashMapJunitTests {

	@Test
	void testPut() {
		ProbingHashMap<String, Integer> map = new ProbingHashMap<>();
		
		map.put("Lily",  13);
		map.put("Max",  10);
		map.put("Evelyn",  5);
		map.put("Theo",  0);
		map.put("Lou",  2);
		map.put("Marin",  1);
		map.put("Morgan",  39);
		map.put("Carmen",  38);
		map.put("Kiestin",  31);
		
		assertEquals( 9, map.size());
		assertEquals( 13, map.get("Lily"));
		assertEquals( 10, map.get("Max"));
		assertEquals( 5, map.get("Evelyn"));
		assertEquals( 0, map.get("Theo"));
		assertEquals( 2, map.get("Lou"));
		assertEquals( 1, map.get("Marin"));
		assertEquals(39, map.get("Morgan"));
		assertEquals( 38, map.get("Carmen"));
		assertEquals( 31, map.get("Kiestin"));
		assertTrue(map.toString().contains("[0]	<Morgan, 39, true>"));
		assertTrue(map.toString().contains("[1]	<Lou, 2, true>"));
		assertTrue(map.toString().contains("[2]	null"));
		assertTrue(map.toString().contains("[3]	<Lily, 13, true>"));
		assertTrue(map.toString().contains("[4]	<Max, 10, true>"));
		assertTrue(map.toString().contains("[5]	<Theo, 0, true>"));
		assertTrue(map.toString().contains("[6]	<Carmen, 38, true>"));
		assertTrue(map.toString().contains("[7]	<Kiestin, 31, true>"));
		assertTrue(map.toString().contains("[8]	<Evelyn, 5, true>"));
		assertTrue(map.toString().contains("[9]	<Marin, 1, true>"));
		assertTrue(map.toString().contains("[10]	null"));
		
		ProbingHashMap<Integer, String> map2 = new ProbingHashMap<>();
		map2.put(10,  "Bill");
		map2.put(2,  "Mary");
		map2.put(7,  "Jose");
		map2.put(8,  "Ted");
		map2.put(0,  "Eric");
		map2.put(5,  "Matt");
		map2.put(6,  "Karen");
		map2.put(11,  "Daniela");
		map2.put(22,  "Norm");
		map2.put(1,  "Joe");
		map2.put(0,  "Larry");
		System.out.println("Size: " + map2.size());
		map2.put(33,  "John");
	}

}
