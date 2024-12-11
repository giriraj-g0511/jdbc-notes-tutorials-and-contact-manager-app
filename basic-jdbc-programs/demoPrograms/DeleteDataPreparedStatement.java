package demoPrograms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
/*Delete record by using roll number as primary key*/
public class DeleteDataPreparedStatement {
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		
		try {
			Class.forName("org.postgresql.Driver");
			
			Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbcDemo", "postgres", "Java");
			
			PreparedStatement ps=con.prepareStatement("delete from student where rollno=?");

			System.out.println("Enter roll number to delete student data");
			ps.setInt(1,sc.nextInt());
			
			ps.execute();
			
			con.close();
			
			System.out.println("Student data deleted");
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
