package demoPrograms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReadData {
	public static void main(String[] args) {
		
		try{
			
		//Step 1
		Class.forName("org.postgresql.Driver");
		
		//Step 2
		Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbcDemo","postgres","Java");
		
		//Step 3
		Statement stm=con.createStatement();
		
		//Step 4
		boolean b=stm.execute("select * from student");
		System.out.println(b);
		ResultSet rs= stm.getResultSet();
		
		while (rs.next()) {
				System.out.println(rs.getInt("rollno")+"\t"+rs.getString("name")+"\t"+rs.getLong("mobnum")+"\t"+rs.getLong("alternatemobnum"));
				System.out.println("---------------------------------------------------------------------");
			}
		
		//Step 5
		con.close();
		System.out.println("Executed");
		
		}
		catch(ClassNotFoundException | SQLException e){
			e.printStackTrace();
		}
	}
}