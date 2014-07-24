package com.varian.rest;
import javax.ws.rs.*;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.ws.rs.core.*;

import java.sql.*;
import java.util.ArrayList;
import java.lang.String;

import org.json.JSONObject;

import java.util.List;


@Path("/patients")
public class Patients { 
	
	FileHandler fh; 
	static Logger logger = Logger.getLogger("MyLog");
	public  void setfileHandler() {
		try {	
			 // This block configure the logger with handler and formatter  
	      fh = new FileHandler("C:\\TEMP\\logging.txt"); 
	      
	      //fh is the handler used by our logger.
	      logger.addHandler(fh);
	      
	      //simpleFormatter is formatter which prints error messages in human readable format.
	      SimpleFormatter formatter = new SimpleFormatter();  
	      
	      //simple formatter will be used by handler to log messages to file.
	      fh.setFormatter(formatter);  
		}
		catch (Exception ex) {
			System.out.println(ex);
			
		}
	}
	
	@POST	
	@Consumes(MediaType.APPLICATION_JSON)
	public void savename(String incomingData) {
			try {
				if(incomingData == null) 
					throw new IllegalArgumentException("Null data cant be added to database");
			}
			catch( IllegalArgumentException expection) {
				System.out.println(expection.getMessage());
			}
			
		   try { 
			setfileHandler();
		        // the following statement is used to log any messages  
			logger.info("Save Button is clicked");
		    JSONObject json = new JSONObject(incomingData);
			Class.forName("com.mysql.jdbc.Driver"); 

			/*A connection (session) with a specific database. SQL statements are executed and results are returned within the context of a connection*/
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost/varian","root","Vai@1234"); 

			/*The object ST is used for executing a static SQL statement and returning the results it produces.*/
			Statement st= con.createStatement();
		

		
			st.executeUpdate("insert into usernames value ('"+json.getString("message").trim()+"')");
			st.close();
			con.close();
			

		}catch (Exception ex) {
			logger.severe(ex.getMessage());
		}
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN) 
	public String returnName() {
		setfileHandler();
		List<String> myList = new ArrayList<String>();
		try {
			
			logger.info("Load Button is clicked");
			JSONObject obj = new JSONObject();
			
			Class.forName("com.mysql.jdbc.Driver"); 
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost/varian","root","Vai@1234"); 
			Statement st= con.createStatement();
			
			ResultSet rs = st.executeQuery("select * from usernames");
			while(rs.next()) 
			{  
				  obj.put("name", rs.getString("name"));
				  myList.add(obj.toString());
			}
			st.close();
			con.close();
		} catch(Exception ex ) {
			logger.severe(ex.getMessage());
		}
		return myList.toString();	
	}
	}

