package demoPrograms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertData {
	public static void main(String[] args) {
		try {
			System.out.println("First step started");
			System.out.println("\nLoad and Register Driver");
			
			System.out.println(Class.forName("org.postgresql.Driver"));
			
			System.out.println("First step ended");
			System.out.println("------------------------\n");
			
			System.out.println("Second step started");
			System.out.println("\nCreate Connection");
			
			Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbcDemo", "postgres", "Java");
			System.out.println(con);
			
			System.out.println("Second step ended");
			System.out.println("------------------------\n");
			
			System.out.println("Third step started");
			System.out.println("\nCreate Statement");
			
			Statement stm=con.createStatement();
			System.out.println(stm);
			
			System.out.println("Third step ended");
			System.out.println("------------------------\n");
			
			System.out.println("Fourth step started");
			System.out.println("\nExecute statement/Query");
			
			boolean b=stm.execute("insert into student values(2,'Sham',987456321)");
			stm.execute("insert into student values(3,'Sham',987456321)");
			System.out.println(b);
			
			System.out.println("Fourth step ended");
			System.out.println("------------------------\n");
			
			System.out.println("Fifth step started");
			System.out.println("\nClose connection");
			
			con.close();
			
			System.out.println("Fifth step ended");
			System.out.println("*************************");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
}
