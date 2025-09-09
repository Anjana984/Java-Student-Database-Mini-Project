import java.sql.*;
import java.util.Scanner;

public class MiniProjectJDBC{

public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	try {
		Connection con = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/testdb", "root" , "");
		System.out.println ("connected. Running program!");
		
		Statement stmt = con.createStatement();
		
		System.out.println (" Please Enter the table Name");
		String tableName = sc.nextLine();
		
		String createTable = "CREATE TABLE IF NOT EXISTS "+ tableName + "(" +
		       "id INT PRIMARY KEY AUTO_INCREMENT , " +
			   "name VARCHAR(50) , " +
		       "age INT)";
		
		stmt.executeUpdate(createTable);
		
		while(true) {
			
			System.out.println("\nSelect Task");
			System.out.println("1.Enter Data");
			System.out.println("2.View Data");
			System.out.println("3.Update Data");
			System.out.println("4.Delete Data");
			System.out.println("5.Close Programme");
			System.out.println("Enter your choice");
			int choice = sc.nextInt();
			sc.nextLine();
			
		  switch (choice) {
		      
		  case 1: //Enter Data
			  
			    System.out.println("Please insert the name of the student");
				String studentName = sc.nextLine();
				
				System.out.println("Please Insert the age of the student");
				int studentAge = sc.nextInt();
				
				sc.nextLine();
				
				String  insertSql = "INSERT INTO " + tableName + " (name,age) Values ('" + studentName + "' , " + studentAge + ")";
				int rows = stmt.executeUpdate(insertSql);
				System.out.println("Inserted successfully");
				
				break;
				
		   case 2: // View Data
			   
			   
			   ResultSet rs = stmt.executeQuery ("SELECT * FROM " + tableName );
			   System.out.printf("%-5s %-20s %-5s\n" , "ID" , "Name" , "Age");
			   while (rs.next()) {
				   System.out.printf("%-5d %-20s %-5d\n" , rs.getInt("id") , rs.getString("name") , rs.getInt("age"));
				   
			   }
			   rs.close();
			   break;
			   
		   case 3: //Update Data
			   
			   System.out.println("please Enter the ID you want to edit");
			   int rowId =sc.nextInt();
			   sc.nextLine();
			   
			   System.out.println("Enter new  name of the student");
			   String newStudentName = sc.nextLine();
			   
			   System.out.println("Enter new age of the student");
			   int newStudentAge = sc.nextInt();
			   
			   String updateSql = "UPDATE " + tableName +
					              " SET  name  = '" + newStudentName + "' , age = "+ newStudentAge +
					              " WHERE ID = " + rowId ;
					   
			   
			   int updatedRows = stmt.executeUpdate(updateSql);
			   System.out.println("record updated as follows");
			   
			   ResultSet rsUpdate = stmt.executeQuery ("SELECT * FROM " + tableName + " WHERE ID = " + rowId );
			   System.out.printf("%-5s %-20s %-5s\n" , "ID" , "Name" , "Age");
			   while (rsUpdate.next()) {
				   System.out.printf("%-5d %-20s %-5d\n" , rsUpdate.getInt("id") , rsUpdate.getString("name") , rsUpdate.getInt("age"));
				   
			   }
			   rsUpdate.close();
			   
			   break;
			   
		   case 4: //Delete data
			   
			   System.out.println("Enter the ID of the student you want to delete");
			   int rowIdDelete = sc.nextInt();
			   sc.nextLine();
			   
			   String deleteSql = "DELETE FROM " + tableName + " WHERE Id = " + rowIdDelete ;
			   int deleted = stmt.executeUpdate(deleteSql);
			   System.out.println("Data successfully deleted");
				
			   break;
			   
		   case 5: //Close the program/
			   
			   System.out.println("Closing program..");
			   
			   con.close(); 
			   System.out.println(" Connection closed "); 
			   return;
			  
		  }
		}
		
    } catch (Exception e) {
        e.printStackTrace();
		
		
		
		
	
  }

}}
