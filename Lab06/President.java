
public class President implements Comparable {
	
	private String firstName;
	private String lastName;
	
	private int termBegin;
	private int termLength;
	
	public President( String first, String last, int start, int len) {
		firstName = first;
		lastName = last;
		termBegin = start;
		termLength = len;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName( String first) {
		firstName = first;
	}
	
	public int getTermBegin() {
		return termBegin;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName( String last) {
		lastName = last;
	}
	
	@Override
	public int compareTo(Object obj) { 
		
		President otherPres = (President) obj;
		if ( this.lastName.compareTo( otherPres.lastName) < 0) return -1;
		else if ( this.lastName.compareTo( otherPres.lastName) > 0) return 1;
		else {
			if ( this.firstName.compareTo( otherPres.firstName) < 0) return -1;
			else if ( this.firstName.compareTo( otherPres.firstName) > 0) return 1;
			else return 0;
		}
	}
	

}
