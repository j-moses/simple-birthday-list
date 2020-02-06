import java.io.*;
import java.util.*;


/**RecordViewer.java is a class that allows the instantiation and modification of an inventory of Items
 * @author Jonah Moses
 * @date 2019-11-13
 */
public class RecordViewer {
	private LinkedList<Record> list = new LinkedList<Record>(); // a LinkedList containing a list of records
	private int index = 0; //
	private final String FILE = "records.txt";

	/**
	 * Default constructor for a RecordViewer object
	 * The constructor contains the main menu of the store which is implemented using a switch statement
	 */
	public RecordViewer() {
		
		int selection;
		boolean flag = true;
		System.out.printf("-= Welcome the to the Record Viewer =-\n");
		while(flag)
		{
			if(!isEmptyList(list)) {
				System.out.println("\nCurrent Record:");
				System.out.println("" + (index + 1) + ") " + list.get(index));
			}
			System.out.printf("\nSelect an option:\n\n1. Add Record\n2. Edit Record\n3. Delete Record\n4. Previous Record\n");
			System.out.printf("5. Next Record\n6. Display Records\n7. Save Records\n8. Load Records\n9. Exit\n");
			selection = readInt();
			switch(selection) {
				case 1:
					addRecord();
					break;
				case 2:
					editRecord();
					break;
				case 3:
					deleteRecord();
					break;
				case 4:
					previousRecord();
					break;
				case 5:
					nextRecord();
					break;
				case 6:
					displayList();
					break;
				case 7:
					saveRecords();
					break;
				case 8:
					System.out.println("Loaded " + loadRecords() + " records to list.");
					break;
				case 9:
					System.out.println("\nExiting the Record Viewer");
					flag = false;
					break;
				default:
					System.out.println("Invalid entry - select a valid option");
					break;
			}
		}
	}
	
	/**
	 * 
	 * @param inventory a LinkedList representing a inventory that stores derived objects of Item objects 
	 * @return returns true if the LinkedList is empty (i.e. no items are in the inventory), false if not
	 */
	private boolean isEmptyList(LinkedList<Record> list) {
		if(list.size() == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Displays a navigation menu for displaying items in the inventory
	 */
	private void displayList() {
		if(!isEmptyList(list)) {
			LinkedList<Record> tempList = (LinkedList<Record>) list.clone();
			System.out.println("\nDisplaying Records (sorted by last name):\n");
			Collections.sort(tempList);
			for (Record r : tempList)
				System.out.println(r);
		}
		else
			System.out.println("\nList is empty");
	}
	
	/**
	 * Override of toString()
	 */
	public String toString()
	{
		String s="";

		for (Record r : list)
		    s += r + "\n";

		return s;
	}
	
	/**
	 * Displays a navigation menu for adding items to the list
	 */
	private void addRecord() {
		System.out.println("Enter first name:");
		String fName = Record.readString();
		
		System.out.println("Enter last name:");
		String lName = Record.readString();
		
		System.out.println("Enter a date: ");
		String date = Record.readDate();
		
		list.add(new Record(fName + "," + lName + "," + date));
		System.out.println("\nThe following record was added:");
		System.out.println(list.get(list.size() - 1));
	}
	
	//goto next record
	private void nextRecord() {
		if(!isEmptyList(list)) {
			if (index == list.size()-1)
				index = 0;
			else
				index++;
		}
		else
			System.out.println("\nList is empty");
	}
	
	//goto previous record
	private void previousRecord() {
		if(!isEmptyList(list)) {
			if (index == 0)
				index = list.size()-1;
			else
				index--;
		}
		else
			System.out.println("\nList is empty");
	}
	
	//edits a record
	private void editRecord() {
		if(!isEmptyList(list)) {	
			System.out.println("Enter first name:");
			String fName = Record.readString();
			
			System.out.println("Enter last name:");
			String lName = Record.readString();
			
			System.out.println("Enter a date: ");
			String date = Record.readDate();
			
			list.set(index, new Record(fName + "," + lName + "," + date));
			System.out.println("\nThe following record was added:\n");
			System.out.println(list.get(index));
		}
		else
			System.out.println("\nList is empty");
	}
	
	//deletes the current record
	private void deleteRecord() {
		if(!isEmptyList(list)) {
			Record temp = list.get(index);
			list.remove(index);
			System.out.println("\nThe following record was removed:\n");
			System.out.println(temp);
		}
		else
			System.out.println("\nList is empty");
	}
	
	/**
	 * Assists the user with saving their inventory to their computer. They must specify the filename to save the inventory to
	 */
	/**
	 * Assists the user with saving their inventory to their computer. They must specify the filename to save the inventory to
	 */
	
	private void saveRecords() {
		if(!isEmptyList(list)) {
			try{
			    FileWriter fileWriter = new FileWriter(FILE);
			    for (Record data : list) {
			    	fileWriter.append("" + data);
			    	fileWriter.append("\n");
			    }
			    
			    fileWriter.flush();
			    fileWriter.close();
			    System.out.println("" + list.size() + " records written to file");
			}
			catch(Exception e) {
				 System.out.println(e); 
			}
		}
		else
			System.out.println("\nList is empty");
	}
	
	/*
	private void saveList() {
		if(!isEmptyList(list)) {
			try{
				FileWriter fout = new FileWriter(FILE);
				
				for(Record data : list) {
					fout.append(data.toString());
					fout.append("\n");
				}

			fout.flush();
			fout.close();
			}
			 catch(Exception e) {
				 System.out.println(e); 
			 }
		}
	}
 	*/
	
	
	/**
	 * Assists the user with loading an existing records from a file on their their computer.
	 * The user must specify the filename to load from.
	 */
	public int loadRecords()
	{
		try
		{
			list.clear();
			String line = null;
			BufferedReader br = new BufferedReader(new FileReader(FILE));
			
			while ((line = br.readLine()) != null) 
			{	
				if (line.trim().length()==0) continue;	// skip empty line
			
				list.add(new Record(line));				
  			}
			br.close();
		}
		catch(IOException ioe)
		{
			System.err.println(ioe);
		}
		index = 0;
		return list.size();
	}
	
	//reads an integer value from the keyboard 
	private static int readInt() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			try {
				return sc.nextInt();
			}
			catch (Exception e) {
				System.out.println("Invalid entry - please enter an integer number: ");
				sc.next();
			}
		}
	}
}
