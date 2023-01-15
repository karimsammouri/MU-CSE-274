/** 
 * Name: Karim R. Sammouri
 * Instructor: Prof. James Kiper
 * Course: CSE 274F
 * Attribution: Everything aside from the prewritten code given by the 
 * course is written by me.
 * Date: Nov 1, 2020
 */

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

public class Data {

	private ArrayList<Event> list;

	public static void main(String[] args) throws IOException {

		System.out.println("STARTING");
		// This puts all the data from the file into a list
		Data data = new Data("covid-data.csv");
		PrintWriter output = new PrintWriter("output.txt");

		// You can use the display method to write your results
		// to a file. This makes it easier to view larger results.
		// Set<String> locations = data.allLocations();
		// display(output, locations);
		// display(output, data.allLocations());
		// System.out.println(data.casesByMonth());
		display(output, data.allLocations());

		// Set<String> locations = data.allLocations();
		// System.out.println(locations);
		// System.out.println(locations.size());

		output.close();
		System.out.println("DONE");
	}

	/*
	 * Returns a set of all location names in the database
	 */
	public Set<String> allLocations() {
		Set<String> result = new TreeSet<String>();

		for (Event event : list) {
			result.add(event.location);
		}

		return result;
	}

	/*
	 * Returns a set of all continent names in the database
	 */
	public Set<String> allContinents() {
		Set<String> result = new TreeSet<String>();

		for (Event event : list) {
			result.add(event.continent);
		}

		return result;
	}

	/*
	 * Returns all the locations in a particular continent. The empty set is
	 * returned if the continent does not have any locations.
	 */
	public Set<String> getLocationsInThisContinent(String continent) {
		Set<String> result = new TreeSet<String>();

		for (Event event : list) {
			if (event.continent.equals(continent)) {
				result.add(event.location);
			}
		}

		return result;
	}

	/*
	 * Returns a map keyed to a continent, where the value is the total number
	 * of deaths in that continent.
	 */
	public Map<String, Integer> deathsByContinent() {
		Map<String, Integer> result = new TreeMap<String, Integer>();

		for (Event event : list) {
			if (result.containsKey(event.continent)) {
				result.put(event.continent,
						result.get(event.continent) + event.deaths);
			} else {
				result.put(event.continent, event.deaths);
			}
		}

		return result;
	}

	/*
	 * Returns a map keyed to a location, where the value is the total number of
	 * cases for that location. The map should only contain locations that had
	 * at least 1 case.
	 */
	public Map<String, Integer> casesByLocation() {
		Map<String, Integer> result = new TreeMap<String, Integer>();

		for (Event event : list) {
			if (event.cases > 0) {
				if (result.containsKey(event.location)) {
					result.put(event.location,
							result.get(event.location) + event.cases);
				} else {
					result.put(event.location, event.cases);
				}
			}
		}

		return result;
	}

	/*
	 * For a specified month (0 is January, 1 is February, and so on), returns a
	 * map keyed to a location, where the value is the total number of cases for
	 * that location. The map should only contain locations that had at least
	 * one case. If there are no cases for a given month, returns an empty map.
	 * NOTE: Date has a getMonth() method you can use. You may get a warning
	 * that getMonth() is a deprecated method, but that's ok.
	 */
	public Map<String, Integer> casesByLocation(int month) {
		Map<String, Integer> result = new TreeMap<String, Integer>();

		for (Event event : list) {
			if (event.cases > 0 && event.date.getMonth() == month) {
				if (result.containsKey(event.location)) {
					result.put(event.location,
							result.get(event.location) + event.cases);
				} else {
					result.put(event.location, event.cases);
				}
			}
		}

		return result;
	}

	// solved in video
	/*
	 * Returns a map keyed to the continent, where the value for each continent
	 * is a set of all locations in that continent.
	 */
	public Map<String, Set<String>> locationsByContinent() {
		Map<String, Set<String>> result = new TreeMap<String, Set<String>>();

		for (Event event : list) {
			Set<String> temp;
			if (result.containsKey(event.continent)) {
				temp = result.get(event.continent);
			} else {
				temp = new TreeSet<String>();
			}
			temp.add(event.location);
			result.put(event.continent, temp);
		}

		return result;
	}

	/*
	 * Returns a map keyed to a location's 3-letter code, where the value is the
	 * total number of times that code appears in the data.
	 */
	public Map<String, Integer> codeCounts() {
		Map<String, Integer> result = new TreeMap<String, Integer>();

		for (Event event : list) {
			if (result.containsKey(event.abbreviation)) {
				result.put(event.abbreviation,
						result.get(event.abbreviation) + 1);
			} else {
				result.put(event.abbreviation, 1);
			}
		}

		return result;
	}

	/*
	 * Returns a map keyed to the date, where the value is the total number of
	 * cases for that date.
	 */
	public Map<Date, Integer> casesByDate() {
		Map<Date, Integer> result = new TreeMap<Date, Integer>();

		for (Event event : list) {
			if (result.containsKey(event.date)) {
				result.put(event.date, result.get(event.date) + event.cases);
			} else {
				result.put(event.date, event.cases);
			}
		}

		return result;
	}

	/*
	 * Returns a list of the locations in the database, sorted by the total
	 * number of cases reported for that location. The location with the fewest
	 * cases should be first, and the location with the most cases should be
	 * last.
	 */
	public List<String> locationsSortedByCaseCount() {
		Map<String, Integer> temp1 = casesByLocation();
		List<Entry> temp2 = new ArrayList<Entry>();
		List<String> result = new ArrayList<String>();

		for (Map.Entry<String, Integer> entry : temp1.entrySet()) {
			temp2.add(new Entry((String) entry.getKey(),
					(Integer) entry.getValue()));
		}

		Collections.sort(temp2, new SortByCases());

		for (Entry entry : temp2) {
			result.add(entry.location);
		}

		return result;
	}

	/*
	 * Returns a map keyed to the date, where the value for that date is the
	 * location (or locations) that had the highest number of cases on that
	 * date. It's possible that the set will contain only one location. But if
	 * there are ties, the set will contain more than one location.
	 */
	public Map<Date, Set<String>> mostCasesByDate() {
		List<Event> temp1 = new ArrayList<Event>();

		for (Event event : list) {
			temp1.add(event);
		}

		Collections.sort(temp1, new SortByDate());

		Map<Date, Set<String>> result = new TreeMap<Date, Set<String>>();
		int max = 0;

		for (Event event : temp1) {
			Set<String> temp;
			if (result.containsKey(event.date)) {
				if (event.cases > max) {
					max = event.cases;
					temp = result.get(event.date);
					temp.clear();
					temp.add(event.location);
					result.put(event.date, temp);
				} else if (event.cases == max) {
					temp = result.get(event.date);
					temp.add(event.location);
					result.put(event.date, temp);
				}
			} else {
				temp = new TreeSet<String>();
				max = event.cases;
				temp.add(event.location);
				result.put(event.date, temp);
			}
		}

		return result;
	}

	/*
	 * Returns a map keyed to a month (0=January, 1=February, and so on), where
	 * the value is the number of reported cases for that month. Only include
	 * months that are listed in the data. NOTE: Date has a getMonth() method
	 * you can use. You may get a warning that getMonth() is a deprecated
	 * method, but that's ok.
	 */
	public Map<Integer, Integer> casesByMonth() {
		Map<Integer, Integer> result = new TreeMap<Integer, Integer>();

		for (Event event : list) {
			if (result.containsKey(event.date.getMonth())) {
				result.put(event.date.getMonth(),
						result.get(event.date.getMonth()) + event.cases);
			} else {
				result.put(event.date.getMonth(), event.cases);
			}
		}

		return result;
	}

	/********** DON'T MODIFY ANY OF THE CODE BELOW ************/

	// Reads file data into an ArrayList of Event objects.
	// Don't modify this code
	public Data(String filename) throws IOException {
		Scanner in = new Scanner(new File(filename));
		list = new ArrayList<Event>();
		in.nextLine();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		while (in.hasNextLine()) {
			String input = in.nextLine();
			String[] line = input.split(",");
			String abbreviation = line[0];
			String continent = line[1];
			String location = line[2];
			Date date = simpleDateFormat.parse(line[3], new ParsePosition(0));
			int cases = Integer.parseInt(line[4]);
			int deaths = Integer.parseInt(line[5]);
			Event d = new Event(abbreviation, continent, location, date, cases,
					deaths);
			list.add(d);
		}

		in.close();
	}

	/*
	 * Writes a collection (list, set) to a specified file. DON'T CHANGE THIS.
	 */
	public static <T> void display(PrintWriter output, Collection<T> items) {
		if (items == null) {
			output.println("null");
			return;
		}
		int LEN = 80;
		String line = "[";
		for (T item : items) {
			line += item.toString() + ",";
			if (line.length() > LEN) {
				output.println(line);
				line = "";
			}
		}
		output.println(line + "]");
	}

	/*
	 * Writes a map to a specified file DON'T CHANGE THIS.
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> void display(PrintWriter output, Map<K, V> items) {
		if (items == null) {
			output.println("null");
			return;
		}

		for (K key : items.keySet()) {
			output.print(key + "---------->");
			Object o = items.get(key);
			if (o instanceof Collection) {
				output.println();
				display(output, (Collection<Object>) items.get(key));
			} else {
				output.println(items.get(key));
			}
		}
	}

	/*
	 * Inner class for organizing event information. DON'T CHANGE THIS.
	 */
	private class Event {
		private String abbreviation;
		private String continent;
		private String location;
		private Date date;
		private int cases;
		private int deaths;

		private Event(String abbreviation, String continent, String location,
				Date date, int cases, int deaths) {
			this.abbreviation = abbreviation;
			this.continent = continent;
			this.location = location;
			this.date = date;
			this.cases = cases;
			this.deaths = deaths;
		}

		@Override
		public String toString() {
			return "[" + abbreviation + "," + continent + "," + location + ","
					+ date + "," + cases + "," + deaths + "]";
		}

	}

	/**
	 * Comparator that sorts by number of cases.
	 */
	private class SortByCases implements Comparator<Entry> {
		public int compare(Entry a, Entry b) {
			if (a.cases != b.cases) {
				return a.cases - b.cases;
			} else {
				return a.location.compareTo(b.location);
			}
		}
	}

	/**
	 * Comparator that sorts by date.
	 */
	private class SortByDate implements Comparator<Event> {
		public int compare(Event a, Event b) {
			if (a.date != b.date) {
				return a.date.compareTo(b.date);
			} else {
				return a.location.compareTo(b.location);
			}
		}
	}

	/**
	 * Inner class for organizing relevant information needed by certain
	 * methods.
	 */
	private class Entry {
		private String location;
		private int cases;

		private Entry(String location, int cases) {
			this.location = location;
			this.cases = cases;
		}
	}
}
