import java.util.*;
import java.text.*;

/**
 * Record.java is a class that controls the instantiation and modification of record objects
 * @author Jonah Moses
 * @date 2019-11-22
 */
public class Record implements Comparable<Record>{
	
	private String firstName; // cannot be empty
	private String lastName; // cannot be empty 
	private String birthdate; 
	private SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
	
	/**
	 * Constructor for Item objects requires title, price, and quantity arguments
	 * @param firstName first name of a person
	 * @param lastName last name of a person 
	 * @param birthDate birth date of a person
	 */
	public Record(String line){
		StringTokenizer st = new StringTokenizer(line, ",");
		setFirstName(st.nextToken());
		setLastName(st.nextToken());
		setBirthdate(st.nextToken());
	}
	
	/**
	 * Override of a record
	 */
	public String toString(){
		return firstName + "," + lastName + "," + birthdate;
	}
	
	
	/**Getter method for first name
	 * 
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**Getter method for first name
	 * 
	 * @return firstName
	 */
	public String getLastName() {
		return lastName;
	}
	
	public String getDate() {
		return birthdate;
	}
	
	/** Setter for first name of Record
	 * 
	 * @param fname the first name in the Record
	 * @return true if valid name is set 
	 */
	public boolean setFirstName(String fname) {
		if(isValidName(fname)) {
			firstName = fname;
			return true;
		}
		else {
			System.out.println("Name cannot be empty");
			return false;
		}
	}
	
	/** Setter for last name of Record
	 * 
	 * @param lname the last name in the Record
	 * @return true if valid name is set 
	 */
	public boolean setLastName(String lname) {
		if(isValidName(lname)) {
			lastName = lname;
			return true;
		}
		else {
			System.out.println("Name cannot be empty");
			return false;
		}
	}
	
	/**A private helper method for validating that name contains only letters and is not blank
	 * 
	 * @param name a name of a person
	 * @return true if name contains only letters and is not blank, false otherwise
	 */
	private static boolean isValidName(String name) {
		if(!blankStringCheck(name) && alphaStringCheck(name))
			return true;
		
		return false;
	}
	
	//setter method for setting birthdate
	public boolean setBirthdate(String birthdate) {
		this.birthdate = birthdate;
		try {
			date.parse(birthdate);
			return true;
		}
		catch(Exception e){
			System.out.printf(e.getMessage());
		}
		return false;
	}
	
	/**Helper method for determining if a string is blank (i.e. if it is empty or has only spaces) 
	 * Various derived classes of Item are dependent on this function
	 * @return true if title is a blank string, false if not	 
	 */
	private static boolean blankStringCheck(String temp) {
		if(temp.isEmpty())
			return true;
		
		int count = 0;
		for(int i = 0; i < temp.length() ; i++) {
			if(temp.charAt(i) == ' ')
				count++;
		}
		return count == temp.length(); 
	}

	/**Helper method for determining if a string contains only alphabetic characters
	 * @return true if title is a blank string, false if not	 
	 */
	private static boolean alphaStringCheck(String temp) {
		for(int i = 0; i < temp.length() ; i++) {
			if(!(Character.isAlphabetic(temp.charAt(i))))
				return false;
		}
		return true; 
	}
	
	//override of compareTo method
	public int compareTo(Record rec) {
		if (this.getLastName().compareTo(rec.getLastName()) > 0)
			return 1;
		else if (this.getLastName().compareTo(rec.getLastName()) < 0)
			return -1;
		else {
			if (this.getFirstName().compareTo(rec.getFirstName()) > 0)
				return 1;
			else if (this.getFirstName().compareTo(rec.getFirstName()) < 0)
				return -1;
			else
				return 0;
			}
	}
	
	//reads a string from input that is not blank and does not contain numbers
	public static String readString() {
		String word;
		Scanner sc = new Scanner(System.in);
		while(true) {
			try {
				word = sc.nextLine();
				if(isValidName(word)){
					return word;
				}
				else {
					throw new IllegalArgumentException("Invalid entry - cannot be blank or contain numbers:");
				}
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}  
		}
	}
	
	//reads a string from input that is in dd/MM/yyyy format
	public static String readDate() {
		String date;
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat validDate = new SimpleDateFormat("dd/MM/yyyy");
		
		while(true) {
			try {
				date = sc.nextLine();
				validDate.parse(date);
				return date;
				}
			catch(Exception e){
				System.out.println("Invalid entry - the date must be formatted like dd/MM/yyyy:");
			} 
		}
	}
}