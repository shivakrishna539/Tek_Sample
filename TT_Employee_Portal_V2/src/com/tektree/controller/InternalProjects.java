package com.tektree.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tektree.ConnectionManager.ConnectionManager;

/**
 * Servlet implementation class InternalProjects
 */
@WebServlet("/InternalProjects")
public class InternalProjects extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    
	 
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		res.setContentType("text/html");
	    PrintWriter pw=res.getWriter();
	    
	    HttpSession session = req.getSession(false);
	    Enumeration en=req.getParameterNames();
	  
	    String param="";
	    while(en.hasMoreElements())
		{
             param = (String) en.nextElement();
		}
	    System.out.println(param);
	    
		try {
			Connection con = ConnectionManager.getConnection();
			Statement st1 = con.createStatement();
			
			 String emp_name = "SELECT first_name FROM \"tektree\".\"employee\" WHERE employee_id='"+param+"'";
			 
			 ResultSet r = st1.executeQuery(emp_name);
			 String name="";
			 while(r.next()) {
				 name=r.getString("first_name");
			 }
			
			 pw.println("<!DOCTYPE html>\r\n" + 
			 		" <head>\r\n" + 
			 		"   <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
			 		"   <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css\">\r\n" + 
			 		"   <link rel=\"stylesheet\" href=\"Bootstrap.css\">\r\n" + 
			 		"   <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\r\n" + 
			 		"   <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js\"></script>\r\n" + 
			 		"   \r\n" + 
			 		"   <style>\r\n" + 
			 		"    input[type=text],input[type=date]\r\n" + 
			 		"	{\r\n" + 
			 		"	  width:200px;\r\n" + 
			 		"	}\r\n" + 
			 		"   </style>\r\n" + 
			 		"   \r\n" + 
			 		" </head>\r\n" + 
			 		" <body style= \"background-color:#85adad;margin:2px;\">\r\n" + 
			 		"    <div class=\"main\">\r\n" + 
			 		"	  <div class=\"container\">\r\n"
			 		+ " <form action=\"InternalProjectRegistration\" method=\"post\" class=\"form-group\">" + 
			 		"	    <div class=\"row\">\r\n" + 
			 		"		 <form class=\"form-inline\">\r\n" + 
			 		"		  <label style=\"font-size:15px\">Employee Name:</label>\r\n" + 
			 		"		  <input type=\"text\" value ="+name+" class=\"form-control input-sm\" style=\"width:200px;\" readonly/>\r\n" + 
			 		"			  \r\n" + 
			 		"		 </form>\r\n" + 
			 		"		</div>\r\n" + 
			 		"	  </div>\r\n" + 
			 		"	</div><br/><br/>\r\n" + 
			 		"	<div class=\"main\">\r\n" + 
			 		"	  <div class=\"container\">\r\n" + 
			 		"	    <div class=\"row\">\r\n" + 
			 		"		  <div class=\"table-responsive\">\r\n" + 
			 		"		     <table class=\"table\">\r\n" + 
			 		"			    <tr>\r\n" + 
			 		"				  <td><b>Project Name </b></td>\r\n" + 
			 		"	              <td><input type=\"text\" name=\"pname\" class=\"form-control\"/></td>\r\n" + 
			 		"	              <td><b>Start Date</td>\r\n" + 
			 		"	              <td><input type=\"date\" name=\"dob\" class=\"form-control\"/></td>\r\n" + 
			 		"				</tr>\r\n" + 
			 		"				<tr>\r\n" + 
			 		"				  <td><b>End Date </b></td>\r\n" + 
			 		"	              <td><input type=\"date\" name=\"dob\" class=\"form-control\"/></td>\r\n" + 
			 		"	              <td><b>Project Manager</td>\r\n" + 
			 		"	              <td><input type=\"text\" name=\"pm\" class=\"form-control\"/></td>\r\n" + 
			 		"				</tr>\r\n" + 
			 		"			 </table>\r\n" + 
			 		"			 <button "+
		    "class=\"btn btn-primary col-lg-offset-9 col-md-offset-10 col-sm-offset-10 col-xs-offset-11\">Submit</button>" + 
			 		"		  </div>\r\n" + 
			 		"		  </form>\r\n" + 
			 		"		</div>\r\n" + 
			 		"	  </div>\r\n" + 
			 		"	</div>\r\n" + 
			 		" </body>\r\n" + 
			 		"</html>");
			
			
		} catch (Throwable e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			}

}
