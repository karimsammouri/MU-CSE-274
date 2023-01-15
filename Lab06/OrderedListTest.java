import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OrderedListTest {

	@Test
	public void testConstructor() {
		OrderedList presidents = new OrderedList();
		assertEquals( 0, presidents.size());
	}
	
	@Test
	public void testInsert() {
		OrderedList presidents = new OrderedList();
		President washington = new President( "George", "Washington", 1790, 8);
		President bush = new President( "George", "Bush", 1989, 4);
		President clinton = new President( "Bill", "Clinton", 1993, 8);
		President fdr = new President( "Franklin", "Roosevelt", 1932, 13);
		President truman = new President( "Harry", "Truman", 1945, 7);
		President ike = new President( "Dwight", "Eisenhower", 1953, 8);
		President jfk = new President( "John", "Kennedy", 1961, 3);
		President ford = new President( "Gerald", "Ford", 1974, 2);
		President carter = new President( "Jimmy", "Carter", 1977, 4);
		President reagan = new President( "Ronald", "Reagan", 1981, 8);
		
		
		presidents.insert(bush);
		assertEquals( 1, presidents.size());
		assertEquals( "[Bush]", presidents.toString());
		
		presidents.insert(clinton);
		assertEquals( 2, presidents.size());
		assertEquals( "[Bush Clinton]", presidents.toString());
		
		presidents.insert(washington);
		assertEquals( 3, presidents.size());
		assertEquals( "[Bush Clinton Washington]", presidents.toString());
		assertEquals( "[Washington Clinton Bush]", presidents.toStringReverse());
		
		presidents.insert(fdr);
		presidents.insert(truman);
		presidents.insert(ike);
		presidents.insert(jfk);
		presidents.insert(ford);
		presidents.insert(carter);
		presidents.insert(reagan);
		
		assertEquals( "[Bush Carter Clinton Eisenhower Ford Kennedy Reagan Roosevelt Truman Washington]", 
				presidents.toString());
		
		assertEquals( "[Washington Truman Roosevelt Reagan Kennedy Ford Eisenhower Clinton Carter Bush]", 
				presidents.toStringReverse());
	}
	


}
