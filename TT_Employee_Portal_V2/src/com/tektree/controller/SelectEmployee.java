package com.tektree.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tektree.ConnectionManager.ConnectionManager;
@WebServlet("/SelectEmployee")
public class SelectEmployee extends HttpServlet{
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
	    Enumeration en=req.getParameterNames();
	  
	    String param="";
	    while(en.hasMoreElements())
		{
             param = (String) en.nextElement();
		}
	    System.out.println(req.getParameter(param));
	    //Database Connection
	    Connection con = null;
		try {
			con = ConnectionManager.getConnection();
		} catch (Throwable e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
   	    
   	    session.setAttribute("emp_id",param);
   	    
   	    try {
			Statement st1 = con.createStatement();
			
			 String emp_name = "SELECT first_name FROM \"tektree\".\"employee\" WHERE employee_id='"+param+"'";
			 
			 ResultSet r = st1.executeQuery(emp_name);
			 String name="";
			 while(r.next()) {
				 name=r.getString("first_name");
			 }
			 //session.setAttribute("name",name);
			 
			 pw.println("<!DOCTYPE html>\r\n" + 
			 		"<html>\r\n" + 
			 		"<head>\r\n" + 
			 		"<script src=\" TT_placement_screen_scriptFile.js\" ></script>\r\n" + 
			 		" <link href=  \"TT_placement_screen_stylesheet_New.css\"  rel = \"stylesheet\"/>\r\n" + 
			 		"\r\n" + 
			 		"</head>\r\n" + 
			 		"\r\n" + 
			 		"<body>\r\n" + 
			 		"\r\n" + 
			 		"<div class = \"a\">\r\n" + 
			 		"<div class = \"edetails\">\r\n" + 
			 		"<h3>Employee Details</h3> \r\n" + 
			 		"<br>\r\n" + 
			 		"<form  action=\"TT_Employee_List.html\" id=\"form2\" method=\"post\">\r\n" + 
			 		"Employee Full Name : "+name+"" + 
			 		"</form>\r\n" + 
			 		"<br>\r\n" + 
			 		"</div>\r\n"
			 		+ "<form action=\"PlacementRegistration\" method=\"post\" target=\"_self\">" + 
			 		"<div class =\"pdetails\">\r\n" + 
			 		"<h3>Placement Details</h3>\r\n" + 
			 		"Employement type : &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp\r\n" + 
			 		"<input type=\"radio\" onclick=\"javascript: myFunction();\" name=\"yesno\" id=\"prmnt\" value=\"Permanent\"/> Permanent \r\n" + 
			 		"<input type=\"radio\" onclick=\"javascript: myFunction1();\" name=\"yesno\" id=\"cntrct\" value=\"Contract\"/> Contract <br><br>\r\n" + 
			 		"Client Name : &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <input class=\"ip\" type =\"text\" name = \"name\" > &nbsp&nbsp&nbsp&nbsp&nbsp\r\n" + 
			 		" Date of joining : &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <input class=\"ip\" type = \"date\" name =\"doj\"> <br><br>\r\n" + 
			 		" Employee Placement Id : <input class=\"ip\" type = \"text\" name = \"epid\">    &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp \r\n" + 
			 		" Offer Date : &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <input class=\"ip\" type = \"date\" name = \"odate\"> <br><br>\r\n" + 
			 		" Work Location : &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <input class=\"ip\" type = \"text\" name = \"location\">   &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp   \r\n" + 
			 		" Onboard date : &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <input class=\"ip\" type = \"date\" name = \"obdate\"><br><br> \r\n" + 
			 		"CTC : &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <input class=\"ip\" type = \"number\" name = \"ctc\"> <br><br> \r\n" + 
			 		"\r\n" + 
			 		"<div id = \"cntsalary\" style = \"display : none\">\r\n" + 
			 		"CTC Fixed :&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <input class=\"ip\" type =\"number\" name =\"ctcfix\" id =\"ctcfix\">   &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp \r\n" + 
			 		"CTC Variable : &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <input class=\"ip\" type= \"number\" name =\"ctcvar\" id=\"ctcvar\"><br><br>\r\n" + 
			 		"\r\n" + 
			 		"SOW start date : &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <input class=\"ip\" type = \"date\" name = \"ssd\"> &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp\r\n" + 
			 		"SOW end date : &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input type = \"date\" name=\"sed\"><br><br>\r\n" + 
			 		"Rate per month : &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <input class=\"ip\" type = \"number\" name = \"rpm\"> <br><br>\r\n" + 
			 		"</div>\r\n" + 
			 		"\r\n" + 
			 		"<div id=\"appid\" style=\"display:none\"> \r\n" + 
			 		"Application Id : &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <input class=\"ip\" type = \"number\" name = \"appid\" id = \"appid\"> \r\n" + 
			 		"</div> \r\n" + 
			 		"\r\n" + 
			 		"<br>\r\n" + 
			 		"\r\n" + 
			 		"</div>\r\n" + 
			 		"<div class =\"btm\">\r\n" + 
			 		"Recruiter : &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <input class=\"ip\" type = \"text\" name = \"recruiter\">   &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp\r\n" + 
			 		"Placement Status : &nbsp&nbsp&nbsp&nbsp&nbsp<input class=\"ip\" type = \"text\" name = \"pstatus\"><br><br>\r\n" + 
			 		"Remarks : &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp <input class=\"ip\" style=\"height:30px\" type = \"text\" name = \"remarks\"> <br><br>\r\n" + 
			 		"\r\n" + 
			 		"<input type=\"submit\" value=\"Submit\" class=\"btn\">"
			 		+ "<a href='TT_EMP_Registration.html' class=\"btn\">Back</a>  " + 
			 		"\r\n"
			 		+ "</form>" + 
			 		" <div id=\"contents1\" style=\"display:none;\">\r\n" + 
			 		"       <iframe src=\"file:///C:/Users/Sathya/eclipse-workspace/TT_login_screen_prjct/WebContent/TT_Employee_list/Employee_List_backBtn.html\" width=\"600px\" height=\"400px\"></iframe>\r\n" + 
			 		"    </div>\r\n" + 
			 		"\r\n" + 
			 		"</div>\r\n" + 
			 		"\r\n" + 
			 		"\r\n" + 
			 		"</div>\r\n" + 
			 		"\r\n" + 
			 		"\r\n" + 
			 		"\r\n" + 
			 		"</body>\r\n" + 
			 		"</html>");
		
	    
	    //RequestDispatcher rd = req.getRequestDispatcher("TT_placement_screen_webpage_after_selection.html");
	    //rd.forward(req, res);
	 	 
   	 } catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
