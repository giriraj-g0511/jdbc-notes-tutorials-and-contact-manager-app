package demoPrograms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
/* Update name by using roll number*/
public class UpdateDataPreparedStatement {

	public static void main(String[] args) {
		try {
			
			Scanner sc = new Scanner(System.in);
			
			Class.forName("org.postgresql.Driver");
			
			Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbcDemo", "postgres", "Java");
			
			PreparedStatement ps=con.prepareStatement("update student set name=? where rollno=?");
			
			System.out.println("Enter name");
			ps.setString(1,sc.nextLine());
			
			System.out.println("Enter roll number");
			ps.setInt(2, sc.nextInt());
			
			ps.execute();
			
			con.close();
			
			System.out.println("Name data Updated");
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
