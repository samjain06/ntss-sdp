package com.ntss.registration.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.ntss.registration.model.User;

public class UserDao {
	
	int rows;

	public void registerUser(User user) {
		try {
			
			/*Initiating connection to DB*/
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String ConnectionURL= "jdbc:sqlserver://localhost:1433;DatabaseName=NTSS;encrypt=true;trustServerCertificate=true";
			Connection con = DriverManager.getConnection(ConnectionURL,"adminNew","admin");

			/*Inserting User data into DB*/
			String sql = "INSERT INTO [USER] VALUES (?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(sql);
			preparedStmt.setString(1, user.getEmail());
			preparedStmt.setString(2, user.getPwd());

			/*Executing DB query*/
			rows = preparedStmt.executeUpdate();

			/*Closing the DB connection*/
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
