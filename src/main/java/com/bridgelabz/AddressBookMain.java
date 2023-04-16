package com.bridgelabz;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class AddressBookMain {
	public static HashMap<String, AddressBook> addressBookMap = new HashMap<String, AddressBook>();


	public static void addAddressBook() {
		Scanner in = new Scanner(System.in);
		AddressBook addressBook = new AddressBook();

		System.out.println("Enter the name of the new Address Book: ");
		addressBook.setAddressBookName(in.next());

		if (addressBookMap.containsKey(addressBook.getAddressBookName())) {
			System.out.println("Address Book already exists");
			return;
		}

		addressBookMap.put(addressBook.getAddressBookName(), addressBook);
		System.out.println("Address Book Added");
		System.out.println();

		boolean status = true;
		while (status) {
			System.out.println("1. Add a Contact to this Address Book");
			System.out.println("2. Close this Address Book");
			int choice = in.nextInt();
			switch (choice) {
				case 1:
					addressBook.addContact();
					System.out.println(addressBook);
					System.out.println("Contact Added");
					System.out.println();
					break;
				case 2:
					status = false;
					break;
				default:
					System.out.println("Enter a valid choice");
			}
		}
	}

	public static void addContacts() {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the name of the address book you want to add contact:");
		String name = in.next();

		if (addressBookMap.containsKey(name)) {
			AddressBook Temp = addressBookMap.get(name);
			Temp.addContact();
			System.out.println(Temp);
			System.out.println("Contact Added");
			System.out.println();
		} else
			System.out.println("Given Address Book not Found\n");
	}

	public static void editContact() {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the name of the address book, the contact you want to edit:");
		String name = in.next();

		if (addressBookMap.containsKey(name)) {
			AddressBook Temp = addressBookMap.get(name);
			Temp.editDetails();
		} else
			System.out.println("Given Address Book not Found\n");
	}

	public static void deleteContact() {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the name of the address book, the contact you want to Delete :");
		String name = in.next();

		if (addressBookMap.containsKey(name)) {
			AddressBook Temp = addressBookMap.get(name);
			Temp.deleteDetails();
		} else
			System.out.println("Given Address Book not Found\n");
	}

	public static void searchByCity() {
		Scanner userInput = new Scanner(System.in);
		System.out.println("Enter the name of the City where the Person resides : ");
		String cityName = userInput.next();
		System.out.println("Enter the name of the Person : ");
		String personName = userInput.next();

		for (AddressBook addressBook : addressBookMap.values()) {
			ArrayList<ContactPerson> contactList = addressBook.getContacts();
			contactList.stream()
					.filter(person -> person.getFirstName().equals(personName) && person.getCity().equals(cityName))
					.forEach(person -> System.out.println(person));

		}
	}

	public static void searchByState() {
		Scanner userInput = new Scanner(System.in);
		System.out.println("Enter the name of the State where the Person resides : ");
		String stateName = userInput.next();
		System.out.println("Enter the name of the Person : ");
		String personName = userInput.next();

		for (AddressBook addressBook : addressBookMap.values()) {
			ArrayList<ContactPerson> contactList = ((AddressBook) addressBook).getContacts();
			contactList.stream()
					.filter(person -> person.getFirstName().equals(personName) && person.getState().equals(stateName))
					.forEach(person -> System.out.println(person));

		}
	}

	public static void displayAddressBook() {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the name of the address book you want to Display:");
		String name = in.next();
		if (addressBookMap.containsKey(name)) {
			AddressBook Temp = addressBookMap.get(name);
			System.out.println(Temp);
		} else
			System.out.println("Given Address Book not Found\n");
	}


	public static void displaySortedAddressBook(){
		Scanner in = new Scanner(System.in);
		System.out.println("Enter name of address book you want to Display:");
		String name = in.next();
		if(addressBookMap.containsKey(name)) {
			AddressBook Temp = addressBookMap.get(name);
			System.out.println("Choose option to sort contacts in Address Book:");
			System.out.println("1.First Name");
			System.out.println("2. City");
			System.out.println("3. State");
			System.out.println("4. Zip Code");
			int choice = in.nextInt();

			List<ContactPerson> sortedList = new ArrayList<>();
			switch (choice){
				case 1:
					sortedList = Temp.getContacts().stream().sorted(Comparator.comparing(ContactPerson::getFirstName)).collect(Collectors.toList());
					break;
				case 2:
					sortedList = Temp.getContacts().stream().sorted(Comparator.comparing(ContactPerson::getCity)).collect(Collectors.toList());
					break;
				case 3:
					sortedList = Temp.getContacts().stream().sorted(Comparator.comparing(ContactPerson::getState)).collect(Collectors.toList());
					break;
				case 4:
					sortedList = Temp.getContacts().stream().sorted(Comparator.comparing(ContactPerson::getZip)).collect(Collectors.toList());
					break;
				default:
					System.out.println("Choose Valid Option");
					break;
			}
			System.out.println("The Sorted Contacts: ");
			System.out.println(sortedList);
			System.out.println();
		}
		else
			System.out.println("Address Book not Found");
	}

	public static void writeToFile() {
		String path = "C:\\Users\\pramo\\JavaApp\\Day28_AddressBook\\src\\main\\java\\com\\bridgelabz\\AddressBook.txt";
		StringBuffer addressBookBuffer = new StringBuffer();
		addressBookMap.values().stream().forEach(contact -> {
			String personDataString = contact.toString().concat("\n");
			addressBookBuffer.append(personDataString);
		});

		try {
			Files.write(Paths.get(path), addressBookBuffer.toString().getBytes());
		}
		catch (IOException e) {
			System.out.println("Catch block");
		}
	}

	public static void readFromFile() {
		String path = "C:\\Users\\pramo\\JavaApp\\Day28_AddressBook\\src\\main\\java\\com\\bridgelabz\\AddressBook.txt";
		System.out.println("Reading from : " + path + "\n");
		try {
			Files.lines(new File(path).toPath()).forEach(employeeDetails -> System.out.println(employeeDetails));
		}
		catch(IOException e){
			System.out.println("Catch block");
		}
	}

	public static void writeToCSVFile() {
		FileWriter fileWriter = null;
		String csvPath = "C:\\Users\\pramo\\JavaApp\\Day28_AddressBook\\src\\main\\java\\com\\bridgelabz\\AddressBooks.csv";

		try {
			fileWriter = new FileWriter(csvPath);
		} catch (IOException e) {
			e.printStackTrace();
		}

		CSVWriter writer = new CSVWriter(fileWriter);
		List<String[]> csvLines = new ArrayList<>();

		addressBookMap.keySet().stream().forEach(bookName -> addressBookMap.get(bookName).getContacts()
				.stream().forEach(contact -> csvLines.add(new String[]{contact.toString()})));


		writer.writeAll(csvLines);

		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void readFromCSVFile(){
		String csvPath = "C:\\Users\\pramo\\JavaApp\\Day28_AddressBook\\src\\main\\java\\com\\bridgelabz\\AddressBooks.csv";
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(csvPath);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		CSVReader reader = new CSVReaderBuilder(fileReader).build();

		List<String[]> linesOfData = null;

		try {
			linesOfData = reader.readAll();
		} catch (IOException | CsvException e) {

			e.printStackTrace();
		}

		System.out.println("\nReading data from csv file:");
		linesOfData.stream().forEach(lines -> {
			for (String value : lines)
				System.out.print(value + "\t");
			System.out.println();
		});

		try {
			reader.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}



	public static void main(String[] args) {
		System.out.println("Welcome to Address Book Program");

		Scanner in = new Scanner(System.in);
		boolean status= true;
		while(status) {
			System.out.println("*******************\nSelect Option : ");
			System.out.println("1. Add New Address Book");
			System.out.println("2. Add Contact");
			System.out.println("3. Edit Contact");
			System.out.println("4. Delete Contact");
			System.out.println("5. Search Person by Region");
			System.out.println("6. Display Dictionary of Address Books");
			System.out.println("7. Display Address Books Contacts");
			System.out.println("8. Display Contacts in Sorted Order based on particular details");
			System.out.println("9. Write From File");
			System.out.println("10. Read From file");
			System.out.println("11. Write to CSVFile");
			System.out.println("12. Read from CSVFile");
			System.out.println("13. exit");
			int choice = in.nextInt();

			switch (choice) {
				case 1:
					addAddressBook();
					System.out.println();
					break;
				case 2:
					addContacts();
					break;
				case 3:
					editContact();
					break;
				case 4:
					deleteContact();
					break;
				case 5:
					Scanner userInput = new Scanner(System.in);
					System.out.println("Enter \n1.Search By City\n2.Search By State");
					int searChoice = userInput.nextInt();
					if(searChoice==1)
						searchByCity();
					else
						searchByState();
					break;
				case 6:
					System.out.println(addressBookMap);
					break;
				case 7:
					displayAddressBook();
					break;
				case 8:
					displaySortedAddressBook();
					break;
				case 9:
					writeToFile();
					break;
				case 10:
					readFromFile();
					break;
				case 11:
					writeToCSVFile();
					break;
				case 12:
					readFromCSVFile();
					break;
				default:
					status=false;
			}
		}
	}
}


