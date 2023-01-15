/** 
 * Name: Karim R. Sammouri
 * Instructor: Prof. James Kiper
 * Course: CSE 274F
 * Description: This program contains the main method that will read StockQuotes.txt.
 * Attribution: Everything aside from the prewritten code given by the 
 * course is written by me.
 * Date: Oct 10, 2020
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

public class StockTracker {
	
	public static void main(String[] args) throws FileNotFoundException {

		Comparator<Stock> comp = new StockComparator<Stock>();

		OrderedStockList stocks = new OrderedStockList(comp);
		

		FileReader fin = new FileReader(args[0]);
		Scanner filePtr = new Scanner(fin);
		filePtr.useDelimiter("[,\\n]"); // delimiters are commas, line feeds

		// read the file and add values to stocks.
		// Use next() to read the strings, nextDouble() to read the price
		// and nextInt() to read the volume
		while (filePtr.hasNext()) {
			Stock stock = new Stock(filePtr.next(), filePtr.next(), filePtr.next(),
					filePtr.nextDouble(), filePtr.nextInt());
			stocks.insert(stock);
		}
		
		filePtr.close();
	}
	
	// This method returns the minimum price for a given stock 
	// over all of there dates
	static double minPrice(OrderedStockList stocks, String symbol) {
		double result = Double.MAX_VALUE;
		StockListIterator iter = new StockListIterator(stocks);
		while (iter.hasNext()) {
			Stock stock = iter.next();
			if (stock.getSymbol().equals(symbol)) {
				if (stock.getClosingPrice() < result) {
					result = stock.getClosingPrice();
				}
			}
		}
		return result;
	}
	
	// This method return the total value of all stocks in this that were sold 
	// on date.  This is the closing price time the volume.
	static double totalValueForDay( OrderedStockList stocks, String date) {
		double result = 0;
		StockListIterator iter = new StockListIterator(stocks);
		while (iter.hasNext()) {
			Stock stock = iter.next();
			if (stock.getDate().equals(date)) {
				result += stock.getClosingPrice() * stock.getVolume();
			}
		}
		return result;
	}
	
	// This method finds the maximum increase from one day to the next 
	// for this stock for all of the days in the list
	static double maxIncrease(OrderedStockList stocks, String symbol) {
		double result = 0;
		Stock prevStock = null;
		StockListIterator iter = new StockListIterator(stocks);
		while (iter.hasNext()) {
			Stock stock = iter.next();
			if (stock.getSymbol().equals(symbol)) {
				if (prevStock != null && stock.getClosingPrice()
						- prevStock.getClosingPrice() > result) {
					result = stock.getClosingPrice()
							- prevStock.getClosingPrice();
				}
				prevStock = stock;
			}
		}
		return result;
	}

}
