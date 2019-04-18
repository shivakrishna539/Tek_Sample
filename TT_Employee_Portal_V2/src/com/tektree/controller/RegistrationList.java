package com.tektree.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.tektree.ConnectionManager.ConnectionManager;
@WebServlet("/RegistrationList")

public class RegistrationList extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		
		res.setContentType("text/html");
	    PrintWriter pw=res.getWriter();
	    
	    HttpSession session = req.getSession(false);
		
	    //Database Connection
	    Connection con = null;
		try {
			con = ConnectionManager.getConnection();
		} catch (Throwable e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
		 	Statement st1 = con.createStatement();
			
			Statement s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		               ResultSet.CONCUR_READ_ONLY);
			String List1 = "SELECT * FROM \"tektree\".\"employee\" ORDER BY created_on DESC";
		   	   ResultSet r = s.executeQuery(List1);
			    r.last();
			    int count = r.getRow();
			    r.beforeFirst();
			int flag=0;
		 	 String query="SELECT * FROM \"tektree\".\"employee\" ORDER BY created_on DESC";
		   	 ResultSet rs1=st1.executeQuery(query);
		   	 ResultSetMetaData rmd1 = rs1.getMetaData();
			     int col_count1 = rmd1.getColumnCount();
			     
			     RequestDispatcher rd1 = req.getRequestDispatcher("TT_Employee_Portal.html");
			     rd1.include(req, res);
			     
			     //RequestDispatcher rd2 = req.getRequestDispatcher("Employee_List_New1.html");
			     //rd2.include(req, res);
			       
			         boolean empty1 = true;
		 
			         if(rs1 != null) {
			       
			        	 int rowNum = 0;
			        	 //Displaying Column Names
			        	 pw.println("<tr>");
		  	
			        	 pw.println("<th>" + rmd1.getColumnName(1).toUpperCase() + "</th>");
			        	 pw.println("<th>" + rmd1.getColumnName(3).toUpperCase() + "</th>");
			        	 pw.println("<th>" + rmd1.getColumnName(5).toUpperCase() + "</th>");
			        	 pw.println("<th>" + rmd1.getColumnName(8).toUpperCase() + "</th>");
			        	 pw.println("<th>" + rmd1.getColumnName(9).toUpperCase() + "</th>");
			        	 pw.println("<th>" + rmd1.getColumnName(21).toUpperCase() + "</th>");
			        	 //for(int i=1;i<=col_count-4;i++) {
			        	 //	pw.println("<th>" + rmd.getColumnName(i).toUpperCase() + "</th>");
				
			        	 //}
			        	 pw.println("<td></td>");
			        	 pw.println("</tr>");
			
			        	 int in=1;
			        	 while(rs1.next()) {
			        		 pw.println("<tr>");
			        		 //Row row = sheet.createRow(rowNum++);
			        		 int colNum = 0;
			        		 int a[] = new int[count+1];
			        		 pw.println("<td>" + rs1.getString("employee_id") + "</td>");
			        		 pw.println("<td>" + rs1.getString("first_name") + "</td>");
			        		 pw.println("<td>" + rs1.getString("designation") + "</td>");
			        		 pw.println("<td>" + rs1.getString("current_location") + "</td>");
			        		 pw.println("<td>" + rs1.getString("total_experience") + "</td>");
			        		 pw.println("<td>" + rs1.getString("created_on") + "</td>");
			        		 //for(int i=1;i<=col_count-4;i++) {
			        		 //	pw.println("<td>" + rs.getString(i) + "</td>");
			        		 //}
					
			        		 a[in] = Integer.parseInt(rs1.getString("employee_id"));
			        		 pw.println("<td><input type=\"submit\" formaction=\"SelectEmployee\" target=\"_self\" name="+a[in]+" value=\"Deploy\">"
							+ "<input type=\"submit\" formaction=\"EditRegisteredEmployee\" target=\"registration\" name="+a[in]+" value=\"Edit\">"
							+ "<input type=\"submit\" formaction=\"EmployeeDetails\" target=\"employee_list\" name="+a[in]+" value=\"View\"></td>");
			        		 pw.println("</tr>");
				   
			        		 empty1 = false;	
			        		 in = in + 1;
			        		
			        	 }
		        }
		 	}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		 	}
		
	}
}

