package demoPrograms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InserDataPreparedStatement {
	public static void main(String[] args) {
		
		try {
			Scanner sc=new Scanner(System.in);
			
			Class.forName("org.postgresql.Driver");
			
			Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbcDemo", "postgres", "Java");
			

			PreparedStatement ps = con.prepareStatement("insert into student values(?,?,?,?)");
			System.out.println("Enter student roll number");
			ps.setInt(1,sc.nextInt());
			

			sc.nextLine();
			System.out.println("Enter name of student");
			ps.setString(2, sc.nextLine());
			
			System.out.println("Enter mobile number of student");
			ps.setLong(3, sc.nextLong());
			
			System.out.println("Enter alternate mobile number");
			ps.setLong(4, sc.nextLong());
			
			ps.execute();
			
			con.close();
			
			System.out.println("Data inserted");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
