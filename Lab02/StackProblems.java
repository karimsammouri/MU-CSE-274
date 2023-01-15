/**
 * CSE 274-F lab assignment completed on Aug. 27, 2020.
 * 
 * @author Karim R. Sammouri
 */

public class StackProblems {

	public static void main(String[] args) {

		// Do initial testing here.
		// For example, here is a basic test of the sum method:
		ArrayStack<Integer> stk = new ArrayStack<>();
		stk.push(5);
		stk.push(7);
		System.out.println("12? " + sum(stk));

	}

	/*
	 * Computes the sum of all the numbers in the stack. It's ok to destroy the
	 * stack in the process.
	 */
	public static int sum(ArrayStack<Integer> data) {
		int sum = 0;
		while (!data.isEmpty()) {
			sum += data.pop();
		}
		return sum;
	}

	/*
	 * Puts an integer under the top item in a stack. If the stack is empty, just
	 * put the item on the top. For example: if stk starting at the top is: 7, 8, 5,
	 * 11, then pushUnder(stk, 20) would result in: 7, 20, 8, 5, 11 For example: if
	 * stk is empty, then pushUnder(stk, 20) would result in: 20
	 */
	public static void pushUnder(ArrayStack<Integer> data, int value) {
		if (data.isEmpty()) {
			data.push(value);
		} else {
			int temp = data.pop();
			data.push(value);
			data.push(temp);
		}
	}

	/*
	 * Computes the sum of all the numbers in the stack. However, if two or more
	 * numbers in a row are equal, only add one of them. So, for example, if the
	 * stack contained 4, 1, 2, 2, 7, 2, 8, 8, 8, 4, then the numbers that would be
	 * added would be 4 + 1 + 2 + 7 + 2 + 8 + 4 = 28
	 */
	public static int sumSkipDuplicates(ArrayStack<Integer> data) {
		int sum = 0;
		if (data.isEmpty()) {
			return sum;
		}
		int previous = data.pop();
		sum = previous;
		while (!data.isEmpty()) {
			int current = data.pop();
			if (current != previous) {
				sum += current;
				previous = current;
			}
		}
		return sum;
	}

	/*
	 * Puts all of the characters of a string into a stack, with the first character
	 * of the string at the bottom of the stack and the last character of the string
	 * at the top of the stack.
	 */
	public static ArrayStack<Character> stringToStack(String s) {
		ArrayStack<Character> stack = new ArrayStack<Character>();
		for (int i = 0; i < s.length(); i++) {
			stack.push(s.charAt(i));
		}
		return stack;
	}

	/*
	 * Reverses a given stack, so that the top of the stack becomes the bottom and
	 * the bottom becomes the top. YOU MAY USE ADDITIONAL STACKS AS NEEDED BUT YOU
	 * MAY NOT USE ANY OTHER DATA STRUCTURES (no arrays, ArrayList, HashSet, etc.)
	 */
	public static void reverseStack(ArrayStack<Integer> s) {
		ArrayStack<Integer> temp1 = new ArrayStack<Integer>();
		while (!s.isEmpty()) {
			temp1.push(s.pop());
		}
		ArrayStack<Integer> temp2 = new ArrayStack<Integer>();
		while (!temp1.isEmpty()) {
			temp2.push(temp1.pop());
		}
		while (!temp2.isEmpty()) {
			s.push(temp2.pop());
		}
	}

	/*
	 * Returns an exact copy of the given stack. At the end of this method the
	 * original stack should be the same as when it started, and a new copy of that
	 * stack should be returned. YOU MAY USE ADDITIONAL STACKS AS NEEDED BUT YOU MAY
	 * NOT USE ANY OTHER DATA STRUCTURES (no arrays, ArrayList, HashSet, etc.)
	 */
	public static ArrayStack<Integer> copyStack(ArrayStack<Integer> s) {
		ArrayStack<Integer> answer = new ArrayStack<Integer>();
		ArrayStack<Integer> temp = new ArrayStack<Integer>();
		while (!s.isEmpty()) {
			temp.push(s.pop());
		}
		while (!temp.isEmpty()) {
			answer.push(temp.pop());
			s.push(answer.peek());
		}
		return answer;
	}

	/*
	 * A palindrome reads the same forward and backward. Use a stack to determine if
	 * a given string is a palindrome. Challenge: try not to use any additional
	 * variables (except a counter for any loop). Just the given string and a stack
	 * of Characters.
	 */
	public static boolean isPalindrome(String s) {
		ArrayStack<Character> stack = new ArrayStack<Character>();
		for (int i = 0; i < s.length(); i++) {
			stack.push(s.charAt(i));
		}
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != stack.pop()) {
				return false;
			}
		}
		return true;
	}
}
