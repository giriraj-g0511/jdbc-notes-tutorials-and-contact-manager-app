package view;

import controller.Controller;
import model.Contact;
import java.util.Scanner;

public class View {

	static Scanner sc = new Scanner(System.in);

	static Controller controller;

	public static void main(String[] args) {

		/*
		 * welcome message
		 */
		choice();

	}

	private static void choice() {
		while (true) {
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^");
			System.out.println("Choose an option:");
			System.out.println("------------------------");
			System.out.println("1) Add new Contact");
			System.out.println("2) Add to existing contact");
			System.out.println("3) Update contact");
			System.out.println("4) Show contact list");
			System.out.println("5) Search contact");
			System.out.println("6) Delete contact by name");
			System.out.println("7) Display contact details by name");
			System.out.println("0) to close application");
			String option = sc.nextLine();

			System.out.println("- - - - - - - - - - - - -");
			char optn = option.charAt(0);

			switch (optn) {
			case '1':
				createNewContact();
				break;

			case '2':
				addNumberToExistingContact();
				break;

			case '3':
				updateContact();
				break;

			case '4':
				displayContactsList();
				break;

			case '5':
				searchContact();
				break;

			case '6':
				deleteContactByName();
				break;
				
			case '7':
				displayContactByName();
				break;

			case '0':
				System.exit(0);

			default:
				System.out.println("Invalid option, try again...\n");
				break;
			}
		}
	}

	private static void displayContactByName() {
		controller=new Controller();
		String name;
		controller.displayContactList();
		while(true) {
			System.out.println("Enter name:");
			name = sc.nextLine();
			if (controller.isNamePresent(name)) {
				controller.displayContactByName(name);
				return;
				}
			else {
				System.out.println("Incorrect name, try again...");
			}
		}
		
	}

	public static void createNewContact() {
		controller = new Controller();
		String name;
		while (true) {
			System.out.println("Enter name:");
			name = sc.nextLine();
			if (name != "") {
				break;
			} else {
				System.out.println("Wrong input, try again...");
			}
		}

		if (!controller.isNamePresent(name)) {

			long mobileNumber;
			while (true) {
				System.out.println("Enter mobile number:");
				mobileNumber = sc.nextLong();
				sc.nextLine();
				if (controller.isMobileNoValid(mobileNumber)) {
					break;
				} else {
					System.out.println("Wrong input, try again...");
				}
			}

			if (!controller.isMobileNumberPresent(mobileNumber)) {

				Contact contact = new Contact();
				contact.setName(name);
				contact.setMobileNumber(mobileNumber);
				System.out.println("Note* :All fields are mandatory to save contact.");

				String city;
				while (true) {
					System.out.println("Enter city:");
					city = sc.nextLine();
					if (city != "") {
						break;
					} else {
						System.out.println("Wrong input, try again...");
					}
				}
				contact.setCity(city);

				String company;
				while (true) {

					System.out.println("Enter company:");
					company = sc.nextLine();
					if (company != "") {
						break;
					} else {
						System.out.println("Wrong input, try again...");
					}
				}
				contact.setCompany(company);

				String email;
				while (true) {

					System.out.println("Enter email:");
					email = sc.nextLine();
					if (controller.isEmailValid(email)) {
						break;
					} else {
						System.out.println("Wrong input, try again...");
					}
				}
				contact.setEmail(email);

				controller.addContact(contact);
				System.out.println("Contact saved. :)\n");
			} else {
				System.out.println("Already there is contact saved for " + mobileNumber + " this number.");
				System.out.println(
						"If you want to see more details choose option as search contact then by mobile number.\n");
			}

		} else {
			System.out.println("Already there is contact saved as " + name + ".");
			System.out.println("You can enter another name or add number to existing contact.\n");
		}

	}

	public static void addNumberToExistingContact() {
		controller = new Controller();
		System.out.println("Enter name:");
		String name = sc.nextLine();

		if (controller.isNamePresent(name)) {

			long mobNo;
			while (true) {
				System.out.println("Enter number:");
				mobNo = sc.nextLong();
				if (controller.isMobileNoValid(mobNo)) {
					break;
				} else {
					System.out.println("Wrong mobile number, try again...");
				}
			}

			if (!controller.isMobileNumberPresent(mobNo)) {
				controller.addNumberToExistingContact(name, mobNo);
				System.out.println("Mobile number saved. :)\n");
			} else {
				System.out.println("Already these mobile number saved for more detail search by mobile number.\n");
			}

		} else {
			System.out.println("Please create contact first there is no any contact saved as " + name + ".\n");
		}

	}

	public static void deleteContactByName() {
		controller = new Controller();
		controller.displayContactList();
		System.out.println("Enter name:");
		String name = (sc.nextLine());

		if (controller.isNamePresent(name)) {
			controller.deleteContactByName(name);
			System.out.println("Contact deleted. :)\n");
		} else {
			System.out.println("There is no contact present for " + name + ".\n");
		}

	}

	public static void displayContactsList() {
		controller = new Controller();
		controller.displayContactList();
	}

	private static void searchContact() {

		while (true) {
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^");
			System.out.println("Search by:");
			System.out.println("------------------------");
			System.out.println("1) Name");
			System.out.println("2) Mobile Number");
			System.out.println("3) email");
			System.out.println("4) City");
			System.out.println("5) Company");
			System.out.println("6) Go back");
			String option = sc.next();
			sc.nextLine();
			System.out.println("- - - - - - - - - - - - -");
			char optn = option.charAt(0);
			switch (optn) {
			case '1':
				searchByName();
				break;

			case '2':
				searchByMobileNo();
				break;

			case '3':
				searchByMail();
				break;

			case '4':
				searchByCity();
				break;

			case '5':
				searchByCompany();
				break;

			case '6':
				return;

			default:
				System.out.println("Incorrect option, try again...");
				break;
			}
		}
	}

	private static void searchByName() {
		controller = new Controller();
		System.out.println("Enter name:");
		String name = sc.nextLine();
		controller.searchByName(name);
	}

	private static void searchByMobileNo() {

		controller = new Controller();
		System.out.println("Enter mobile number:");
		long mobNo = sc.nextLong();
		sc.nextLine();
		if (mobNo >= 0) {
			controller.searchByMobileNumber(mobNo);
		} else {
			System.out.println("Wrong input, try again...");
		}
	}

	private static void searchByMail() {
		controller = new Controller();
		System.out.println("Enter email");
		String email = sc.next();
		sc.nextLine();
		controller.searchByEmail(email);
	}

	private static void searchByCity() {
		controller = new Controller();
		System.out.println("Enter city");
		String city = sc.nextLine();
		controller.searchByCity(city);
	}

	private static void searchByCompany() {
		controller = new Controller();
		System.out.println("Enter company name");
		String company = sc.next();
		sc.nextLine();
		controller.searchByCompany(company);
	}

	public static void updateContact() {
		/*
		 * What to update Firstly search for name or mobile number Then according to
		 * that change or update the data for update or edit first display old column
		 * data to decide that data also be need to change or not if data need to be
		 * change then
		 * 
		 * at time of update firstly show contact list then choose one contact then
		 * firstly show all data related to conatct and that data will be stored because
		 * if any data not changed then it will store previous data
		 * 
		 * 
		 * at the time of search or find we have to use like operator from SQL to match
		 * pattern suppose we are finding the data by first letter of name then
		 * application have to display all the name which have that letter in between
		 * name
		 * 
		 * 
		 */

		controller = new Controller();
		String oldName;
		controller.displayContactList();
		while (true) {
			System.out.println("Enter current name:");
			oldName = sc.nextLine();
			if (controller.isNamePresent(oldName)) {
				controller.displayContactByName(oldName);
				
			
				Contact contact = new Contact();

				String newName;
				while (true) {
					System.out.println("Enter updated name:");
					newName = sc.nextLine();
					if (newName!="") {
						break;
					} else {
						System.out.println("Wrong input, try again...");
					}
				}

				contact.setName(newName);
				
				long mobileNumber;
				while (true) {
					System.out.println("Enter updated mobile number:");
					mobileNumber = sc.nextLong();
					sc.nextLine();
					if (controller.isMobileNoValid(mobileNumber)) {
						break;
					} else {
						System.out.println("Wrong input, try again...");
					}
				}
				contact.setMobileNumber(mobileNumber);
				System.out.println("Note* :All fields are mandatory to save contact.");

				String company;
				while (true) {

					System.out.println("Enter updated company:");
					company = sc.nextLine();
					if (company != "") {
						break;
					} else {
						System.out.println("Wrong input, try again...");
					}
				}
				contact.setCompany(company);

				String email;
				while (true) {

					System.out.println("Enter updated email:");
					email = sc.nextLine();
					if (controller.isEmailValid(email)) {
						break;
					} else {
						System.out.println("Wrong input, try again...");
					}
				}
				contact.setEmail(email);
				
				String city;
				while (true) {
					System.out.println("Enter updated city:");
					city = sc.nextLine();
					if (city != "") {
						break;
					} else {
						System.out.println("Wrong input, try again...");
					}
				}
				contact.setCity(city);

				controller.updateContact(contact,oldName);
				System.out.println("Contact update saved. :)\n");
				return;

			} else {
				System.out.println("Incorrect name, try again...");
			}
		}

	}

}
