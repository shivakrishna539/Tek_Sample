package com.tektree.ConnectionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	public static final String URL = "jdbc:postgresql://10.0.0.130:5432/Resource_Deployment??ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
	public static final String USER = "postgres";
	public static final String PASS = "root";
	public static final String driverName="org.postgresql.Driver";
	/**
	 * Get a connection to database
	 * @return Connection object
	 * @throws Throwable 
	*/
	public static Connection getConnection() throws Throwable {
	    try {
	       Class.forName(driverName);
	       Connection con= (Connection) DriverManager.getConnection(URL, USER, PASS);
	       return con;
	    } catch (SQLException ex) {
	        throw new RuntimeException("Error connecting to the database", ex);
	    }
	   
	}

}

	

