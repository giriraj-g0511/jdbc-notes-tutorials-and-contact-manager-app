package demoPrograms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteData {
	public static void main(String[] args) {
		try{
			
			//Step 1
			Class.forName("org.postgresql.Driver");
			
			//Step 2
			Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbcDemo","postgres","Java");
			
			//Step 3
			Statement stm=con.createStatement();
			
			//Step 4
			//Deleting the data of roll number 3 student
			stm.execute("delete from student where rollno=3");
			
			//Step 5
			con.close();
			System.out.println("Executed");
		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
