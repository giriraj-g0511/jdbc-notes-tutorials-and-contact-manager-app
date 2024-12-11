package demoPrograms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ReadDataPreparedStatement {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		try {
			
			Class.forName("org.postgresql.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbcDemo","postgres","Java");
			
			PreparedStatement ps = con.prepareStatement("select * from student ");
			
			ResultSet rs= ps.executeQuery();
			
			System.out.println("Roll No\t    Student Name\t Mobile No\t Alternate Mobile No");
			System.out.println("--------------------------------------------------------------------");
			
			
			while (rs.next()) {
				
				System.out.println(rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getLong(3)+"\t\t"+rs.getLong(4));
				System.out.println("--------------------------------------------------------------------");
				
			}
			
			ps.execute();
			
			con.close();
			
			System.out.println("Data displayed");
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
