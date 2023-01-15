/** 
 * Name: Karim R. Sammouri
 * Instructor: Prof. James Kiper
 * Course: CSE 274F
 * Description: This program implements a comparator for the OrderedStockList class.
 * Attribution: Everything aside from the prewritten code given by the 
 * course is written by me.
 * Date: Oct 10, 2020
 */

import java.util.Comparator;

public class StockComparator<T> implements Comparator<Stock> {

	// Add the code for a comparator put the stock objects in order by date, 
	// from early to later. For those stock objects on the same date, 
	// you should put these in order by stock symbol.
	
	public int compare(Stock stock1, Stock stock2) {
		if (!stock1.getDate().equals(stock2.getDate())) { 
		return (stock1.getDate().compareTo(stock2.getDate()));
		} else {
			return (stock1.getSymbol().compareTo(stock2.getSymbol()));
		}
	}
}
