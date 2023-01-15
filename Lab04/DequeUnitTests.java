import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

public class DequeUnitTests {
	
	@Test(timeout=2000)
	public void dequeGetBackTest() {
		DequeInterface<Integer> Q = new LinkedDeque<>();
		
		assertTrue(throwsCorrectException(NoSuchElementException.class, () -> {Q.getBack();}));
		Q.addToFront(1);
		assertEquals(Q.getBack(), Integer.valueOf(1));

		Q.addToFront(2);
		Q.addToFront(3);
		assertEquals(Q.getBack(), Integer.valueOf(1));
	}
	
	@Test(timeout=2000)
	public void dequeRemoveBackTest() {
		DequeInterface<Integer> Q = new LinkedDeque<>();
		assertTrue(throwsCorrectException(NoSuchElementException.class, () -> {Q.removeBack();}));
		for (int i=0; i<5; i++) {
			Q.addToFront(i);
			assertEquals(Q.removeBack(), Integer.valueOf(i));
		}
		int N = 5;
		for (int i=0; i<N; i++) {
			Q.addToFront(i);
		}
		for (int i=0; i<N; i++) {
			assertEquals(Q.removeBack(), Integer.valueOf(i));			
		}
	}

	@Test(timeout=2000)
	public void dequeAllBackTest() {
		DequeInterface<Integer> Q = new LinkedDeque<>();
		int N = 10;
		for (int i=0; i<N; i++) {
			Q.addToBack(i);
			assertEquals(Q.getBack(), Integer.valueOf(i));
		}
		for (int i=0; i<N; i++) {
			assertEquals(Q.removeBack(), Integer.valueOf(N-i-1));
		}
		assertTrue(Q.isEmpty());
	}
	
	@Test(timeout=2000)
	public void dequeAllFrontTest() {
		DequeInterface<Integer> Q = new LinkedDeque<>();
		int N = 10;
		for (int i=0; i<N; i++) {
			Q.addToFront(i);
			assertEquals(Q.getFront(), Integer.valueOf(i));
		}
		for (int i=0; i<N; i++) {
			assertEquals(Q.removeFront(), Integer.valueOf(N-i-1));
		}
		assertTrue(Q.isEmpty());
	}

	public boolean throwsCorrectException(Class<?> exceptionClass, Runnable code) {
		boolean OK = false;
		try {
			code.run();
		}
		catch (Exception e) {
			if (e.getClass() == exceptionClass) {
				OK = true;
			}
		}
		return OK;
	}
	
}
