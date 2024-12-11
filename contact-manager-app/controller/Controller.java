package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Contact;

public class Controller {

	static Connection con;
	static {
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/contactbook", "postgres", "Java");

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean addContact(Contact contact) {
		try {

			PreparedStatement ps1 = con.prepareStatement("insert into contactlist values(?,?,?,?)");
			ps1.setString(1, contact.getName());
			ps1.setString(2, contact.getCompany());
			ps1.setString(3, contact.getEmail());
			ps1.setString(4, contact.getCity());

			PreparedStatement ps2 = con.prepareStatement("insert into mobilenumbers values(?,?)");

			ps2.setLong(1, contact.getMobileNumber());
			ps2.setString(2, contact.getName());

			ps1.execute();
			ps2.execute();
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public void addNumberToExistingContact(String name, long number) {
		try {
			PreparedStatement ps = con.prepareStatement("insert into mobilenumbers values(?,?)");

			ps.setLong(1, number);
			ps.setString(2, name);

			ps.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean deleteContactByName(String name) {
		try {

			PreparedStatement ps1 = con.prepareStatement("delete from contactlist where name=?");
			ps1.setString(1, name);

			PreparedStatement ps2 = con.prepareStatement("delete from mobilenumbers where contact_name=?");
			ps2.setString(1, name);

			ps2.execute();
			ps1.execute();
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public void displayContactList() {
		try {

			PreparedStatement ps = con.prepareStatement(
					"select cont.name from mobilenumbers mn, contactlist cont where mn.contact_name=cont.name order by cont.name asc");
			ResultSet rs = ps.executeQuery();
			System.out.println("=============================");
			System.out.println("\tName");
			System.out.println("=============================");

			while (rs.next()) {

				System.out.println("\t" + rs.getString("name"));
				System.out.println("-----------------------------");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\n");
	}

	public boolean isNamePresent(String name) {
		try {
			PreparedStatement ps = con.prepareStatement("select name from contactlist");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("name").equalsIgnoreCase(name)) {
					return true;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean isMobileNumberPresent(long mobileNumber) {
		try {
			PreparedStatement ps = con.prepareStatement("select mobilenumber from mobilenumbers");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (mobileNumber == (rs.getLong(1))) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void searchByName(String name) {
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(
					"select con.name,mb.mobilenumber from contactlist con , mobilenumbers mb where con.name=mb.contact_name and mb.contact_name like'%"
							+ name + "%' order by con.name");
			ResultSet rs = ps.executeQuery();
			System.out.println("=============================");
			System.out.println("Name\t\tMobile Number");
			System.out.println("=============================");
			while (rs.next()) {
				System.out.println(rs.getString("name") + "\t\t" + rs.getLong("mobilenumber"));
				System.out.println("-----------------------------");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
	}

	public void searchByMobileNumber(long mobNo) {
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(
					"select con.name,mb.mobilenumber from contactlist con , mobilenumbers mb where con.name=mb.contact_name and CAST(mb.mobilenumber AS TEXT) LIKE '%"
							+ mobNo + "%'");
			ResultSet rs = ps.executeQuery();
			System.out.println("=============================");
			System.out.println("Mobile Number\t\tName");
			System.out.println("=============================");
			while (rs.next()) {
				System.out.println(rs.getString("mobilenumber") + "\t\t" + rs.getLong("name"));
				System.out.println("-----------------------------");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
	}

	public void searchByCity(String city) {

		PreparedStatement ps;
		try {
			ps = con.prepareStatement(
					"select con.name,con.city,mb.mobilenumber from contactlist con , mobilenumbers mb where con.name=mb.contact_name and con.city like'%"
							+ city + "%' order by con.city");
			ResultSet rs = ps.executeQuery();
			System.out.println("=============================");
			System.out.println("City\t\tName\t\tMobile Number");
			System.out.println("=============================");
			while (rs.next()) {
				System.out.println(
						rs.getString("city") + "\t\t" + rs.getString("name") + "\t\t" + rs.getLong("mobilenumber"));
				System.out.println("-----------------------------");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
	}

	public void searchByEmail(String data) {
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(
					"select con.name,con.email,mb.mobilenumber from contactlist con , mobilenumbers mb where con.name=mb.contact_name and con.email like'%"
							+ data + "%' order by con.email");
			ResultSet rs = ps.executeQuery();
			System.out.println("=============================");
			System.out.println("Email\t\tName\t\tMobile Number");
			System.out.println("=============================");
			while (rs.next()) {
				System.out.println(
						rs.getString("email") + "\t\t" + rs.getString("name") + "\t\t" + rs.getLong("mobilenumber"));
				System.out.println("-----------------------------");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
	}

	public void searchByCompany(String data) {
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(
					"select con.name,con.company,mb.mobilenumber from contactlist con , mobilenumbers mb where con.name=mb.contact_name and con.company like'%"
							+ data + "%' order by con.company");
			ResultSet rs = ps.executeQuery();
			System.out.println("=============================");
			System.out.println("Company\t\tName\t\tMobile Number");
			System.out.println("=============================");
			while (rs.next()) {
				System.out.println(
						rs.getString("company") + "\t\t" + rs.getString("name") + "\t\t" + rs.getLong("mobilenumber"));
				System.out.println("-----------------------------");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
	}

	public boolean isMobileNoValid(long mobileNumber) {
		if (mobileNoLength(mobileNumber) && mobileNoFirstDigit(mobileNumber)) {
			return true;
		}
		return false;
	}

	private boolean mobileNoFirstDigit(long mobileNumber) {
		long firstDigit = mobileNumber / 1000000000;
		if (firstDigit > 6) {
			return true;
		}
		return false;
	}

	private boolean mobileNoLength(long mobileNumber) {
		int count = 0;
		while (mobileNumber > 0) {
			count++;
			mobileNumber /= 10;
		}
		if (count == 10) {
			return true;
		}
		return false;
	}

	public boolean isEmailValid(String email) {
		if (email == null || email.isEmpty()) {
			return false;
		}
	
		// Check if there's at least one '@' symbol and one '.' symbol after '@'
		int atSymbolIndex = email.indexOf('@');
		int dotSymbolIndex = email.indexOf('.', atSymbolIndex);
	
		// Check for valid positions of '@' and '.' symbols
		return atSymbolIndex > 0 && dotSymbolIndex > atSymbolIndex + 1 && dotSymbolIndex < email.length() - 1;
	
	}

	public boolean updateContact(Contact contact,String oldName) {
			
			return deleteContactByName(oldName) && addContact(contact);
	}

	public void displayContactByName(String name) {
		try {
			
			PreparedStatement ps=con.prepareStatement("select * from contactlist con,mobilenumbers mb where con.name=mb.contact_name and con.name = '"+name+"'");
			ResultSet rs=ps.executeQuery();System.out.println("=======================================================================================");
			System.out.println("Name\t\tMobile Number\t\tCompany\t\tCity\t\tEmail");
			System.out.println("=======================================================================================");
			while (rs.next()) {
				System.out.println(rs.getString("name") + "\t\t" + rs.getLong("mobilenumber")+"\t\t"+rs.getString("company") + "\t\t"+rs.getString("city") + "\t\t" +rs.getString("email") );
				System.out.println("---------------------------------------------------------------------------------------");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
	}

}
