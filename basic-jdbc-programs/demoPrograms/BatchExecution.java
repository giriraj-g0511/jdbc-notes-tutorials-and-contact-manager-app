package demoPrograms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchExecution {

	public static void main(String[] args) {
		
		
		try {
			
			Class.forName("org.postgresql.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbcDemo", "postgres", "Java");
			
			Statement stm= con.createStatement();
			
			stm.addBatch("insert into student values(4,'Rsmesh',9874563210,9518637420)");
			stm.addBatch("update student set mobnum=0 where rollno=1");
			stm.executeBatch();
			ResultSet rs = stm.executeQuery("select * from student");
			
			System.out.println("Roll No\t    Student Name\t Mobile No\t Alternate Mobile No");
			System.out.println("--------------------------------------------------------------------");
			
			while (rs.next()) {
				
				System.out.println(rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getLong(3)+"\t\t"+rs.getLong(4));
				System.out.println("--------------------------------------------------------------------");
				
			}
			
			con.close();
			
			System.out.println("Batch executed");
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
