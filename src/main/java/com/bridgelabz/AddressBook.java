package com.bridgelabz;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddressBook {
	ArrayList<ContactPerson> contacts = new ArrayList<>();

	public ArrayList<ContactPerson> getContacts() {
		return contacts;
	}

	public void setContacts(ArrayList<ContactPerson> contacts) {
		this.contacts = contacts;
	}


	private String addressBookName;
	public String getAddressBookName() {
		return addressBookName;
	}

	public void setAddressBookName(String addressBookName) {
		this.addressBookName = addressBookName;
	}

	Scanner in = new Scanner(System.in);

	@Override
	public String toString() {
		return "AddressBook{" +
				"contacts=" + contacts +
				", addressBookName='" + addressBookName + '\'' +
				"}\n";
	}

	public void addContact(){

		System.out.println("Enter the details to add a contact:");

		System.out.print("Enter First Name: ");
		String fName= in.next();

		List<ContactPerson> duplicateName = contacts.stream().filter(contact-> contact.getFirstName().equals(fName)).toList();
		if (!(duplicateName.isEmpty())){
			System.out.println("Contact with given First Name already exists");
			addContact();
			return;
		}
		ContactPerson contact= new ContactPerson();
		contact.setFirstName(fName);
		System.out.print("Enter Last Name: ");
		contact.setLastName(in.next());
		System.out.print("Enter the Address: ");
		contact.setAddress(in.next());
		System.out.print("Enter the City: ");
		contact.setCity(in.next());
		System.out.print("Enter the State: ");
		contact.setState(in.next());
		System.out.print("Enter the ZIP Code: ");
		contact.setZip(in.next());
		System.out.print("Enter the Phone number: ");
		contact.setPhoneNumber(in.next());
		System.out.print("Enter the Email Address: ");
		contact.setEmail(in.next());

		contacts.add(contact);
		System.out.println();
	}

	public void editDetails(){
		System.out.println("Enter the First Name of the Contact you want to Edit:");
		String name= in.next();
		boolean contactFound =false;

		for (ContactPerson Temp : contacts) {
			if (name.equals(Temp.getFirstName())) {
				contactFound=true;
				System.out.println("Current Contact Details:");
				System.out.println(Temp);
				System.out.println("Enter the name of the Field you want to Edit in Contact's Details:");
				System.out.println("1.First Name\t2.Last Name\t3.Address\t4.City\t5.State\t6.ZIP Code\t7.Phone Number\t8.Email Address");
				int choice = in.nextInt();
				switch (choice) {
					case 1:
						System.out.println("Current First Name: " + Temp.getFirstName());
						System.out.print("Enter the First Name: ");
						Temp.setFirstName(in.next());
						break;
					case 2:
						System.out.println("Current Last Name: " + Temp.getLastName());
						System.out.print("Enter the Last Name: ");
						Temp.setFirstName(in.next());
						break;
					case 3:
						System.out.println("Current Address: " + Temp.getAddress());
						System.out.print("Enter the Address: ");
						Temp.setAddress(in.next());
						break;
					case 4:
						System.out.println("Current City: "+Temp.getCity());
						System.out.print("Enter the City: ");
						Temp.setCity(in.next());
						break;
					case 5:
						System.out.println("Current State: "+Temp.getState());
						System.out.println("Enter the State: ");
						Temp.setState(in.next());
						break;
					case 6:
						System.out.println("Current ZIP Code: "+Temp.getZip());
						System.out.println("Enter the ZIP Code: ");
						Temp.setZip(in.next());
						break;
					case 7:
						System.out.println("Current Phone Number: "+Temp.getPhoneNumber());
						System.out.println("Enter the Phone Number: ");
						Temp.setPhoneNumber(in.next());
						break;
					case 8:
						System.out.println("Current Email Address: "+Temp.getEmail());
						System.out.println("Enter the Email Address: ");
						Temp.setEmail(in.next());
						break;
					default:
						System.out.println("Enter a valid field");
						break;
				}
				System.out.println("Contact Edited");
				System.out.println("Contact Details After Editing:");
				System.out.println(Temp);
			}
		}
		if(!contactFound)
			System.out.println("Contact with given name not found");
	}

	public void deleteDetails() {
		System.out.println("Enter the First Name of the Contact you want to Delete:");
		String name = in.next();
		boolean contactFound = false;

		for (ContactPerson Temp : contacts) {
			if (Temp.getFirstName().equals(name)) {
				contactFound = true;
				System.out.println("Details of the Contact you want to Delete:");
				System.out.println(Temp);
				System.out.println("Are you sure you want to Delete the Contact?");
				System.out.println("1. Y \t 2. N");
				int choice = in.nextInt();
				switch (choice) {
					case 1:
						contacts.remove(Temp);
						System.out.println("Contact Deleted");
						break;
					case 2:
						System.out.println("Contact is NOT deleted");
						break;
					default:
						System.out.println("Select a valid option");
						break;
				}
			}
		}
		if (!contactFound)
			System.out.println("Contact with given name not found");
	}
}
