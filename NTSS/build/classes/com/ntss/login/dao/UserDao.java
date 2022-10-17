package com.ntss.login.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.ntss.login.model.User;

public class UserDao {
	
	int rows;

	public User getUser(User user) {
		try {
			
			/*Initiating connection to DB*/
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String ConnectionURL= "jdbc:sqlserver://localhost:1433;DatabaseName=NTSS;encrypt=true;trustServerCertificate=true";
			Connection con = DriverManager.getConnection(ConnectionURL,"adminNew","admin");

			/*getting User data from DB based on the email  - update column name based on table*/
			String sql = "SELECT email, pwd FROM [USER] WHERE email = ?";

			PreparedStatement preparedStmt = con.prepareStatement(sql);
			preparedStmt.setString(1, user.getEmail());

			/*Executing DB query*/
			ResultSet rs = preparedStmt.executeQuery();
			// change the column names email, pwd
			User user1 = new User();
			boolean userExists = false;
			if(rs.next()) {
				user1.setEmail(rs.getString("email"));
				user1.setPwd(rs.getString("pwd"))
				userExists = true;
			}
			if(!userExists) {
				// show the user not found message

				throw Exception("User Doesnt found in data base")
			}

			/*Closing the DB connection*/
			con.close();

			return user1
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
