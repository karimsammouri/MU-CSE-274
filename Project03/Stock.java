
public class Stock {

	private String symbol;
	private String stockName;
	private String closingDate;
	private double closingPrice;
	private int volume;
	
	public Stock( String sym, String name, String date, double price, int vol) {
		symbol = sym;
		stockName = name;
		closingDate = date;
		closingPrice = price;
		volume = vol;
	}
	
	public String getSymbol() {
		return symbol;
	}
	public String getStockName() {
		return stockName;
	}
	public String getDate() {
		return closingDate;
	}
	public double getClosingPrice() {
		return closingPrice;
	}
	public int getVolume() {
		return volume;
	}
	public void setSymbol(String sym) {
		symbol = sym;
	}
	public void setStockName(String name) {
		stockName = name;
	}
	public void setDate(String date) {
		this.closingDate = date;
	}
	public void setClosingPrice(double price) {
		closingPrice = price;
	}
	public void setVolume(int vol) {
		volume = vol;
	}
	
	public String toString() {
		return symbol + " " + stockName + " " + closingDate + " " + "$" + closingPrice + " " + volume;
	}
}
