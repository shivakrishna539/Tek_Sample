	package com.tektree.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.tektree.ConnectionManager.ConnectionManager;
@WebServlet("/EditRegisteredEmployee")
public class EditRegisteredEmployee extends HttpServlet {
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
	    session.setAttribute("emp_id",param);
	    
	    //RequestDispatcher rd = req.getRequestDispatcher("TT_RegistrationForm_TT_html.html");
 	    //rd.include(req, res);
	    
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
			
			 String details = "SELECT * FROM \"tektree\".\"employee\" WHERE employee_id='"+param+"'";
			 
			 ResultSet r = st1.executeQuery(details);
			 while(r.next()) {
				 pw.println("<!DOCTYPE html>\r\n" + 
				 		"<html>\r\n" + 
				 		"<head>\r\n" + 
				 		"\r\n" + 
				 		"<style>\r\n" + 
				 		"#a1 { border : 1px solid black;  background-color :  #a3c2c2 ; text-align : left ; padding-left:10px; height : 400px;}\r\n" + 
				 		"#btn {   background-color: #006666 ;\r\n" + 
				 		"  border: 1px rounded;\r\n" + 
				 		"  color: white;\r\n" + 
				 		"  padding: 9px 13px;\r\n" + 
				 		"  text-align: center;\r\n" + 
				 		"  text-decoration: none;\r\n" + 
				 		"  display: inline-block;\r\n" + 
				 		"\r\n" + 
				 		"  margin: 4px 2px;\r\n" + 
				 		"	\r\n" + 
				 		"	\r\n" + 
				 		"  margin-left : 230px;\r\n" + 
				 		"  cursor: pointer;\r\n" + 
				 		"  }\r\n" + 
				 		" <!--  input{margin-left : 40px; } -->\r\n" + 
				 		"input[type=text],input[type=date],.st\r\n" + 
				 		"	{\r\n" + 
				 		"    width: 135px;\r\n" + 
				 		"    margin: 10px 5px;\r\n" + 
				 		"    box-sizing: border-box;  \r\n" + 
				 		"   }\r\n" + 
				 		"\r\n" + 
				 		"#btn2 { background-color: #006666 ;\r\n" + 
				 		"  border: 1px rounded;\r\n" + 
				 		"  color: white;\r\n" + 
				 		"  padding: 8px 11px;\r\n" + 
				 		"  text-align: center;\r\n" + 
				 		"  text-decoration: none;\r\n" + 
				 		"  display: inline-block;\r\n" + 
				 		"\r\n" + 
				 		"  margin: 4px 2px;\r\n" + 
				 		"	\r\n" + 
				 		"	\r\n" + 
				 		"  margin-left : 0px;\r\n" + 
				 		"  cursor: pointer;   }\r\n" + 
				 		"  \r\n" + 
				 		"  #sub {\r\n" + 
				 		"  margin-top:55px;\r\n" + 
				 		"   background-color: #b3b3cc ;\r\n" + 
				 		"  border: 1px rounded;\r\n" + 
				 		"  color: black;\r\n" + 
				 		"  padding: 8px 15px;\r\n" + 
				 		"  text-align: center;\r\n" + 
				 		"  text-decoration: none;\r\n" + 
				 		"  display: inline-block;\r\n" + 
				 		"\r\n" + 
				 		"  margin-left : 250px;\r\n" + 
				 		"  cursor: pointer; }\r\n" + 
				 		"  \r\n" + 
				 		"  \r\n" + 
				 		"  .main-head\r\n" + 
				 		"{\r\n" + 
				 		"	height:4%;\r\n" + 
				 		"	width:100%;\r\n" + 
				 		"	background-color:#b3b3cc;\r\n" + 
				 		"	color:black;\r\n" + 
				 		"    font-family:serif;	\r\n" + 
				 		"}\r\n" + 
				 		"#head\r\n" + 
				 		"{\r\n" + 
				 		"	text-align:center;\r\n" + 
				 		"	\r\n" + 
				 		"	font-size:18px; }\r\n" + 
				 		"  \r\n" + 
				 		"  #navbar {\r\n" + 
				 		"  overflow: hidden;\r\n" + 
				 		"  background-color: #b3b3cc;\r\n" + 
				 		"  height :5%;\r\n" + 
				 		"  margin-top: 75px;\r\n" + 
				 		"}\r\n" + 
				 		"  #navbar a {\r\n" + 
				 		"  float: left;\r\n" + 
				 		"  color: black;\r\n" + 
				 		"  text-decoration: none;\r\n" + 
				 		"}\r\n" + 
				 		"\r\n" + 
				 		"\r\n" + 
				 		"  #dropdown  #dropbtn1 #dropbtn2 {\r\n" + 
				 		"  font-size: 16px;  \r\n" + 
				 		"  border: none;\r\n" + 
				 		"  outline: none;\r\n" + 
				 		"  color: white;\r\n" + 
				 		"  padding: 14px 16px;\r\n" + 
				 		"  background-color: inherit;\r\n" + 
				 		"  font-family: inherit;\r\n" + 
				 		"  border-radius:50px;\r\n" + 
				 		"\r\n" + 
				 		"}\r\n" + 
				 		"  \r\n" + 
				 		"    \r\n" + 
				 		"\r\n" + 
				 		"</style>\r\n" + 
				 		"\r\n" + 
				 		"\r\n" + 
				 		"</head>\r\n" + 
				 		"<body >\r\n" + 
				 		"<br>\r\n" + 
				 		"<form action=\"registration\" method=\"post\">\r\n" + 
				 		"\r\n" + 
				 		"<div id=\"a1\"><br>\r\n" + 
				 		"\r\n" + 
				 		"\r\n" + 
				 		"<table>\r\n" + 
				 		"  <tr>\r\n" + 
				 		"    <td><b>First Name</b></td>\r\n" + 
				 		"    <td><input type=\"text\" name=\"first_name\" value="+r.getString(3)+"</td>\r\n" + 
				 		"    <td><b>Middle Name</b></td>\r\n" + 
				 		"    <td><input type=\"text\" name=\"middle_name\" value="+r.getString(4)+"</td>\r\n" + 
				 		"    <td><b>Last Name</b></td>\r\n" + 
				 		"	<td><input type=\"text\" name=\"last_name\" value="+r.getString(5)+"</td>\r\n" + 
				 		"	<td><b>Date_of_Birth</td>\r\n" + 
				 		"	<td><input type=\"date\" name=\"dob\" value="+r.getString(6)+" /></td>\r\n" + 
				 		"  </tr>\r\n" + 
				 		"  <tr>\r\n" + 
				 		"    <td><b>Gender</b></td>\r\n" + 
				 		"    <td><select class=\"st\" id=\"gender\" name=\"gender\">\r\n" + 
				 		"		          <option value=\"\">"+r.getString(26)+"</option>\r\n" + 
				 		"				  <option value=\"male\">Male</option>\r\n" + 
				 		"				  <option value=\"female\">Female</option>\r\n" + 
				 		"		   </select></td>\r\n" + 
				 		"    <td><b>Employee code</b></td>\r\n" + 
				 		"    <td><input type=\"text\" name=\"employee_code\" value="+r.getString(2)+"/></td>\r\n" + 
				 		"    <td><b>Designation </b></td>\r\n" + 
				 		"	<td><input type=\"text\" name=\"designation\" value="+r.getString(16)+" /></td>\r\n" + 
				 		"	<td><b>Skill</b></td>\r\n" + 
				 		"	<td><input type=\"text\" name=\"skill\" value="+r.getString(17)+" /></td>\r\n" + 
				 		"  </tr>\r\n" + 
				 		"  <tr>\r\n" + 
				 		"    <td><b>Aadhar Number</b></td>\r\n" + 
				 		"    <td><input type=\"text\" name=\"aadhar_no\" value="+r.getString(7)+" /></td>\r\n" + 
				 		"    <td><b>PAN Number </</td>\r\n" + 
				 		"    <td><input type=\"text\" name=\"pan_no\" value="+r.getString(8)+" /></td>\r\n" + 
				 		"    <td><b>Passport Number  </b></td>\r\n" + 
				 		"	<td><input type=\"text\" name=\"passport_no\" value="+r.getString(9)+" /></td>\r\n" + 
				 		"	<td><b>Blood Group</b></td>\r\n" + 
				 		"	<td><input type=\"text\" name=\"blood_group\" value="+r.getString(10)+" /></td>\r\n" + 
				 		"  </tr>\r\n" + 
				 		"  <tr>\r\n" + 
				 		"    <td><b>Marital Status</label></td>\r\n" + 
				 		"    <td><select class=\"st\" id=\"etype\" name=\"marital_status\">\r\n" + 
				 		"		          <option value=\"\">"+r.getString(11)+"</option>\r\n" + 
				 		"				  <option value=\"married\">Married</option>\r\n" + 
				 		"				  <option value=\"un_married\">UnMarried</option>			 \r\n" + 
				 		"		   </select>\r\n" + 
				 		"	</td>\r\n" + 
				 		"    <td><b>Anniversary Date</b></td>\r\n" + 
				 		"    <td><input type=\"date\" name=\"anniversary_date\" value="+r.getString(12)+" /></td>\r\n" + 
				 		"    <td><b>Is External</b></td>\r\n" + 
				 		"	<td><select class=\"st\" id=\"external\" name=\"is_external\">\r\n" + 
				 		"		          <option value=\"\">"+r.getString(13)+"</option>\r\n" + 
				 		"				  <option value=\"yes\">Yes</option>\r\n" + 
				 		"				  <option value=\"no\">No</option>			 \r\n" + 
				 		"		   </select></td>\r\n" + 
				 		"	<td><b>Date of Joining</</td>\r\n" + 
				 		"	<td><input type=\"date\" name=\"doj\" value="+r.getString(25)+" /></td>\r\n" + 
				 		"  </tr>\r\n" + 
				 		"  <tr>\r\n" + 
				 		"    <td><b>Current location </b></td>\r\n" + 
				 		"    <td><input type=\"text\" name=\"current_location\" value="+r.getString(18)+" /></td>\r\n" + 
				 		"    <td><b>Total experience </b></td>\r\n" + 
				 		"    <td><input type=\"text\" name=\"total_experience\" value="+r.getString(19)+"/></td>\r\n" + 
				 		"    <td><b>Current CTC </b></td>\r\n" + 
				 		"	<td><input type=\"text\" name=\"ctc\" value="+r.getString(20)+" /></td>\r\n" + 
				 		"	<td><b>Employement Type </b></td>\r\n" + 
				 		"	<td><select class=\"st\" id=\"etype\" name=\"employee_type\">\r\n" + 
				 		"		          <option value=\"\">"+r.getString(15)+"</option>\r\n" + 
				 		"				  <option value=\"permanent\">Permanent</option>\r\n" + 
				 		"				  <option value=\"Contract\">Contract</option>			 \r\n" + 
				 		"		   </select></td>\r\n" + 
				 		"	</tr>\r\n" + 
				 		"	<tr>\r\n" + 
				 		"	  <td><b>Background Verified By </a></td>\r\n" + 
				 		"	  <td><input type=\"text\" name=\"background_verified_by\" value="+r.getString(14)+"/></td>\r\n" + 
				 		"    </tr>\r\n" + 
				 		" </table>\r\n" + 
				 		"\r\n" + 
				 		"<input type=\"Submit\"  formaction=\"updateEmployeeDetails\" value=\"Update\" id = \"btn\"> \r\n" + 
				 		"<input type=\"button\" id=\"btn2\" onClick=\"location.href='TT_EMP_RegistrationForm_html.html'\" value='Back'>\r\n" + 
				 		"<!--  <input type=\"Submit\" formaction=\"registrationList\" value=\"Employee List\" id = \"btn\">--> \r\n" + 
				 		"</form>\r\n" + 
				 		"\r\n" + 
				 		"\r\n" + 
				 		"\r\n" + 
				 		"\r\n" + 
				 		"\r\n" + 
				 		"</div>\r\n" + 
				 		"\r\n" + 
				 		"\r\n" + 
				 		"</body>\r\n" + 
				 		"</html>");
			 }
			
	    //RequestDispatcher rd= req.getRequestDispatcher("TT_Edit_Employee_Address.html");
	    //rd.include(req, res);
	 	 
   	    } catch (SQLException e) {
			e.printStackTrace();
		}
	  		
}
}