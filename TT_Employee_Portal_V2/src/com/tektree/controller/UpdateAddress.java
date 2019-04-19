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
 * Servlet implementation class UpdateAddress
 */
@WebServlet("/UpdateAddress")
public class UpdateAddress extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("Text/html");
		PrintWriter out= response.getWriter();
		//String Employee_Id=request.getParameter("Employee_id");
		// int emp_id=Integer.parseInt(Employee_Id);
		HttpSession session = request.getSession(false);
        Enumeration en=request.getParameterNames();
	    
	    String param="";
	   
	    while(en.hasMoreElements())
		{
	         param = (String) en.nextElement();
	        
		}
	    
	    
	    int emp_id=Integer.parseInt((String)session.getAttribute("emp_id"));
		try {
			Connection 	con = ConnectionManager.getConnection();
			Statement stmt=con.createStatement();  
            
            ResultSet rs=stmt.executeQuery("select * from \"tektree\".\"employee_address\" where Employee_Id='"+emp_id+"'");
            while(rs.next()){  
            	//out.print(rs.getString(3));
            	out.print("<form action='EditServlet2' method='post'>");  
                out.print("<table>");  
                out.print("<tr><td>Employee_Address_id:</td><td><input type='number' name='aid' value='"+rs.getInt(1)+"' readonly/></td></tr>");  
                out.print("<tr><td>Employee_id:</td><td><input type='number' name='eid' value="+rs.getInt(2)+" readonly></td></tr>");  
                out.print("<tr><td>Address_Temporary</td><td><input type='text' name='pradd' value='"+rs.getString(3)+"'/></td></tr>");  
                out.print("<tr><td>Address_Permanent:</td><td><input type='text' name='pmname' value="+rs.getString("Address_Permanent")+"></td></tr>"); 
                out.print("<tr><td>Email_ID</td><td><input type='text' name='email' value="+rs.getString("Email_ID")+"></td></tr>");  
                out.print("<tr><td>Email_ID_Alt</td><td><input type='text' name='alt_email' value="+rs.getString("Email_ID_Alt")+"></td></tr>");  
                out.print("<tr><td>Phone_1</td><td><input type='text' name='mobile1' value="+rs.getString("Phone_1")+"></td></tr>");  
                out.print("<tr><td>Phone_1</td><td><input type='text' name='mobile2' value='"+rs.getString("Phone_2")+"'></td></tr>");  
                out.print("<tr><td>Emergency_Contact_No</td><td><input type='text' name='alt_mobile' value="+rs.getString("Emergency_Contact_No")+"></td></tr>");  
                out.print("<tr><td>Created_On</td><td><input type='text' name='con' value="+rs.getString("Created_On")+"></td></tr>"); 
                out.print("<tr><td>Created_By</td><td><input type='text' name='cby' value="+rs.getString("Created_By")+"></td></tr>");  
                out.print("<tr><td>Last_Updated_On</td><td><input type='text' name='uon' value="+rs.getString("Last_Updated_On")+"></td></tr>"); 
                out.print("<tr><td>Last_Updated_By</td><td><input type='text' name='uby' value="+rs.getString("Last_Updated_By")+"></td></tr>");  	
                out.print("<tr><td colspan='2'><input type='submit' value='Edit & Save '/></td></tr>");  
                out.print("</table>");  
                out.print("</form>");  
                  
                out.close();  
			}
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.setAttribute("emp_id",emp_id);
	}

}
