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
 * Servlet implementation class ViewDeployment
 */
@WebServlet("/ViewDeployment")
public class ViewDeployment extends HttpServlet {
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
			
			 String emp_name = "SELECT * FROM \"tektree\".\"hr_employee_placement\" WHERE employee_id='"+param1+"'";
			 
			 ResultSet rs = st1.executeQuery(emp_name);
			 while(rs.next()){  
	            	//out.print(rs.getString(3));
				    
	            	out.print("<form action='EditServlet2' method='post'>");  
	                out.print("<table align='center'>"); 
	                out.print("<tr><td>Employee_placement_Id</td><td><input type='number' name='eplaceid' value='"+rs.getInt("emp_placement_id")+"' readonly/></td></tr>");
	                out.print("<tr><td>employee_code:</td><td><input type='text' name='ecode' value='"+rs.getString("employee_code")+"' readonly/></td></tr>");  
	                out.print("<tr><td>Client_Name:</td><td><input type='text' name='cname' value='"+rs.getString("client_name")+"' readonly></td></tr>");  
	                out.print("<tr><td>Work_Location</td><td><input type='text' name='wlocation' value='"+rs.getString("work_location")+"' readonly/></td></tr>");  
	                out.print("<tr><td>Employment_Type:</td><td><input type='text' name='etype' value='"+rs.getString("employment_type")+"' readonly></td></tr>"); 
	                out.print("<tr><td>DOJ</td><td><input type='date' name='doj' value='"+rs.getDate("doj")+"' readonly></td></tr>");  
	                out.print("<tr><td>Offer_date:</td><td><input type='date' name='odate' value='"+rs.getDate("offer_date")+"' readonly></td></tr>");  
	                out.print("<tr><td>Application_id</td><td><input type='number' name='appid' value='"+rs.getInt("application_id")+"' readonly></td></tr>");  
	                out.print("<tr><td>CTC</td><td><input type='number' name='ctc' value='"+rs.getInt("ctc")+"' readonly></td></tr>");  
	                out.print("<tr><td>CTC_Fixed</td><td><input type='number' name='cfixed' value='"+rs.getString("ctc_fixed")+"' readonly></td></tr>");  
	                out.print("<tr><td>CTC_Variable:</td><td><input type='number' name='cvariable' value='"+rs.getInt("ctc_variable")+"' readonly/></td></tr>");  
	                out.print("<tr><td>rate_per_month:</td><td><input type='number' name='rpm' value='"+rs.getLong("rate_per_month")+"' readonly></td></tr>");  
	                out.print("<tr><td>sow_start_date</td><td><input type='date' name='sowstart' value='"+rs.getDate("sow_start_date")+"' readonly/></td></tr>");  
	                out.print("<tr><td>Sow_end_date:</td><td><input type='Date' name='sowend' value='"+rs.getDate("sow_end_date")+"' readonly></td></tr>"); 
	                out.print("<tr><td>Onboard_date</td><td><input type='Date' name='ondate' value='"+rs.getDate("onboard_date")+"' readonly></td></tr>");  
	                out.print("<tr><td>remarks</td><td><input type='text' name='remarks' value='"+rs.getString("remarks")+"' readonly></td></tr>");  
	                out.print("<tr><td>recruiter</td><td><input type='text' name='recruiter' value='"+rs.getString("recruiter")+"' readonly></td></tr>");  
	                out.print("<tr><td>Placement_Status</td><td><input type='text' name='pstatus' value='"+rs.getString("placement_status")+"' readonly></td></tr>");  
	                out.print("<tr><td>Created_On</td><td><input type='date' name='con' value='"+rs.getString("Created_On")+"' readonly></td></tr>"); 
	                out.print("<tr><td>Created_By</td><td><input type='text' name='cby' value='"+rs.getString("Created_By")+"' readonly></td></tr>");  
	                out.print("<tr><td>Last_Updated_On</td><td><input type='date' name='uon' value='"+rs.getString("Last_Updated_On")+"' readonly></td></tr>"); 
	                out.print("<tr><td>Last_Updated_By</td><td><input type='text' name='uby' value='"+rs.getString("Last_Updated_By")+"' readonly></td></tr>");
	                out.print("<tr><td>Employee_id</td><td><input type='number' name='eid' value='"+rs.getInt(23)+"' readonly></td></tr>");
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
