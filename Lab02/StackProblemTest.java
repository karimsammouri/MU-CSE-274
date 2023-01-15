import static org.junit.Assert.*;
import org.junit.Test;

public class StackProblemTest {

	@Test
	public void testSum() {
		ArrayStack<Integer> nums = new ArrayStack<>();
		assertEquals(0, StackProblems.sum(nums));
		
		int randomSize = 50 + (int)(20 * Math.random());
		int sum = 0;
		
		for (int i = 0; i < randomSize; i++) {
			int num = (int)(25 * Math.random());
			sum += num;
			nums.push(num);
		}
		
		assertEquals(sum, StackProblems.sum(nums));
	}
	
	@Test
	public void testPushUnder() {
		ArrayStack<Integer> nums = new ArrayStack<>();
		StackProblems.pushUnder(nums, 10);
		
		assertEquals(10, (int)nums.pop());
		assertTrue(nums.isEmpty());
		
		nums.clear();
		nums.push(10);
		nums.push(20);
		nums.push(30);
		StackProblems.pushUnder(nums, 99);
		
		assertEquals(30, (int)nums.pop());
		assertEquals(99, (int)nums.pop());
		assertEquals(20, (int)nums.pop());
		assertEquals(10, (int)nums.pop());
		assertTrue(nums.isEmpty());
	}
	
	@Test
	public void testSumSkipDuplicates() {
		int[] data = {4, 1, 2, 2, 7, 2, 8, 8, 8, 4};
		ArrayStack<Integer> stack = new ArrayStack<>();
		for (int num : data) {
			stack.push(num);
		}		
		assertEquals(28, StackProblems.sumSkipDuplicates(stack));
		
		data = new int[] {1, 2, 3, 4, 5, 6};
		stack = new ArrayStack<>();
		for (int num : data) {
			stack.push(num);
		}		
		assertEquals(21, StackProblems.sumSkipDuplicates(stack));
		
		data = new int[] {1, 1, 1, 1, 1};
		stack = new ArrayStack<>();
		for (int num : data) {
			stack.push(num);
		}		
		assertEquals(1, StackProblems.sumSkipDuplicates(stack));
		
		data = new int[] {1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5, 6};
		stack = new ArrayStack<>();
		for (int num : data) {
			stack.push(num);
		}		
		assertEquals(21, StackProblems.sumSkipDuplicates(stack));
		
		data = new int[] {};
		stack = new ArrayStack<>();
		for (int num : data) {
			stack.push(num);
		}		
		assertEquals(0, StackProblems.sumSkipDuplicates(stack));
	}
	
	@Test
	public void testStringToStack() {
		String testString = "This is a test string.";
		ArrayStack<Character> result = StackProblems.stringToStack(testString);
		
		String resultString = "";
		while (!result.isEmpty()) {
			resultString = result.pop() + resultString;
		}
		
		assertEquals(testString, resultString);				
	}
	
	@Test
	public void testEmptyStringToStack() {
		String testString = "";
		ArrayStack<Character> result = StackProblems.stringToStack(testString);
		assertTrue(result.isEmpty());
	}
	
	@Test
	public void testReverseStack() {
		int[] data = new int[50];		
		for (int i = 0; i < data.length; i++) {
			data[i] = (int)(10 * Math.random());
		}		
		
		ArrayStack<Integer> start = new ArrayStack<>();			
		for (int num : data) {
			start.push(num);
		}

		StackProblems.reverseStack(start);

		for (int num : data) {
			assertEquals(num, (int)start.pop());
		}
		
		assertTrue(start.isEmpty());
		
	}
	
	
	@Test
	public void testCopyStack() {
		
		ArrayStack<Integer> emptyStack = new ArrayStack<>();	
		ArrayStack<Integer> emptyCopy = StackProblems.copyStack(emptyStack);
		assertNotSame(emptyStack, emptyCopy);
		assertTrue(emptyStack.isEmpty());
		assertTrue(emptyCopy.isEmpty());
		
		int[] data = new int[50];
		
		for (int i = 0; i < data.length; i++) {
			data[i] = (int)(10 * Math.random());
		}
		ArrayStack<Integer> start = new ArrayStack<>();	
		// fill the stack with what's in the array		
		for (int num : data) {
			start.push(num);
		}
		// Now copy the stack
		ArrayStack<Integer> startCopy = StackProblems.copyStack(start);
		assertNotSame(start, startCopy);
		
		for (int i = data.length - 1; i >= 0; i--) {
			int startTop = start.pop();
			int startCopyTop = startCopy.pop();
			assertEquals(startTop, data[i]);
			assertEquals(startCopyTop, data[i]);
		}
		
		assertTrue(start.isEmpty());
		assertTrue(startCopy.isEmpty());
	}
	
	
	
	@Test
	public void testIsPalindrome() {
		assertTrue(StackProblems.isPalindrome("a"));
		assertTrue(StackProblems.isPalindrome("BB"));
		assertTrue(StackProblems.isPalindrome("CCC"));
		assertTrue(StackProblems.isPalindrome("radar"));
		assertTrue(StackProblems.isPalindrome("racecar"));
		assertTrue(StackProblems.isPalindrome("tattarrattat"));
		
		assertFalse(StackProblems.isPalindrome("ab"));
		assertFalse(StackProblems.isPalindrome("aA"));
		assertFalse(StackProblems.isPalindrome("abab"));
		assertFalse(StackProblems.isPalindrome("baaa"));		
	}
	
	

}
