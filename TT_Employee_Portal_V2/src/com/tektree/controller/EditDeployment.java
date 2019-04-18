package com.tektree.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
 * Servlet implementation class EditDeployment
 */
@WebServlet("/EditDeployment")
public class EditDeployment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		res.setContentType("text/html");
	    PrintWriter out=res.getWriter();
	    
	    HttpSession session = req.getSession(false);
	    Enumeration en=req.getParameterNames();
	    
	    String param="";
	   
	    while(en.hasMoreElements())
		{
	         param = (String) en.nextElement();
		}
	   
	    session.setAttribute("emp_id",param);
	    int param1=Integer.parseInt(param);
	    Connection con = null;
		try {
			con = ConnectionManager.getConnection();
		} catch (Throwable e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}    
		try {
			Statement st1 = con.createStatement();
			
			 String details = "SELECT * FROM \"tektree\".\"hr_employee_placement\" WHERE employee_id='"+param1+"'";
			 
			 ResultSet rs = st1.executeQuery(details);
			  while(rs.next()){  
	            	//out.print(rs.getString(3));
	            	out.print("<form action='EditServlet2' method='post'>");  
	                out.print("<table>"); 
	                out.print("<tr><td>Employee_placement_Id</td><td><input type='number' name='eplaceid' value="+rs.getInt(1)+"></td></tr>");
	                out.print("<tr><td>employee_code:</td><td><input type='text' name='ecode' value='"+rs.getString(2)+"'/></td></tr>");  
	                out.print("<tr><td>Client_Name:</td><td><input type='text' name='cname' value="+rs.getString(3)+"></td></tr>");  
	                out.print("<tr><td>Work_Location</td><td><input type='text' name='wlocation' value='"+rs.getString(4)+"'/></td></tr>");  
	                out.print("<tr><td>Employment_Type:</td><td><input type='text' name='etype' value="+rs.getString(5)+"></td></tr>"); 
	                out.print("<tr><td>DOJ</td><td><input type='date' name='doj' value="+rs.getDate(6)+"></td></tr>");  
	                out.print("<tr><td>Offer_date</td><td><input type='date' name='odate' value="+rs.getDate(7)+"></td></tr>");  
	                out.print("<tr><td>Application_id</td><td><input type='number' name='appid' value="+rs.getInt(8)+"></td></tr>");  
	                out.print("<tr><td>CTC</td><td><input type='number' name='ctc' value='"+rs.getInt(9)+"'></td></tr>");  
	                out.print("<tr><td>CTC_Fixed</td><td><input type='number' name='cfixed' value="+rs.getString(10)+"></td></tr>");  
	                out.print("<tr><td>CTC_Variable:</td><td><input type='number' name='cvariable' value='"+rs.getInt(11)+"'/></td></tr>");  
	                out.print("<tr><td>rate_per_month:</td><td><input type='number' name='rpm' value="+rs.getLong(12)+"></td></tr>");  
	                out.print("<tr><td>sow_start_date</td><td><input type='Date' name='sowstart' value='"+rs.getDate(13)+"'/></td></tr>");  
	                out.print("<tr><td>Sow_end_date:</td><td><input type='Date' name='sowend' value="+rs.getDate(14)+"></td></tr>"); 
	                out.print("<tr><td>Onboard_date</td><td><input type='Date' name='ondate' value="+rs.getDate(15)+"></td></tr>");  
	                out.print("<tr><td>remarks</td><td><input type='text' name='remarks' value="+rs.getString(16)+"></td></tr>");  
	                out.print("<tr><td>recruiter</td><td><input type='text' name='recruiter' value="+rs.getString(17)+"></td></tr>");  
	                out.print("<tr><td>Placement_Status</td><td><input type='text' name='pstatus' value='"+rs.getString(18)+"'></td></tr>");  
	                out.print("<tr><td>Created_On</td><td><input type='text' name='con' value="+rs.getString("Created_On")+"></td></tr>"); 
	                out.print("<tr><td>Created_By</td><td><input type='text' name='cby' value="+rs.getString("Created_By")+"></td></tr>");  
	                out.print("<tr><td>Last_Updated_On</td><td><input type='text' name='uon' value="+rs.getString("Last_Updated_On")+"></td></tr>"); 
	                out.print("<tr><td>Last_Updated_By</td><td><input type='text' name='uby' value="+rs.getString("Last_Updated_By")+"></td></tr>");
	                out.print("<tr><td>Employee_id</td><td><input type='number' name='eid' value="+rs.getInt(23)+"></td></tr>");
	                out.print("<tr><td colspan='2'><input type='submit' value='Edit & Save '/></td></tr>");  
	                out.print("</table>");  
	                out.print("</form>");  
	                  
	                out.close();  
				}
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
	}

}
