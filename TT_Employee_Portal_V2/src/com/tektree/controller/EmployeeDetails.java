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
@WebServlet("/EmployeeDetails")
public class EmployeeDetails extends HttpServlet{
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
		
		 String emp_name = "SELECT * FROM \"tektree\".\"employee\" WHERE employee_id='"+param+"'";
		 
		 ResultSet r = st1.executeQuery(emp_name);
		 ResultSetMetaData rmd = r.getMetaData();
	   	 int col_count = rmd.getColumnCount();
	   
	   	 /*pw.println("<html><head><link href=\"Resources1.css\" rel = \"stylesheet\"/></head><body>"
	    			+ "<form action=\"getxsl\" method=\"post\"><table>");
	    	//Displaying Column Names
	    	//String file = "D:/MyFirstExcel.xlsx";
	        //XSSFWorkbook workbook = new XSSFWorkbook();
	        //XSSFSheet sheet = workbook.createSheet("Resources List");
	    	pw.println("<tr>");
			for(int i=1;i<=col_count;i++) {
				pw.println("<th>" + rmd.getColumnName(i).toUpperCase() + "</th>");
				
			}
			pw.println("<td></td>");
			pw.println("</tr>");
			
			int in=1;
	    	while(r.next()) {
	    		pw.println("<tr>");
	    		//Row row = sheet.createRow(rowNum++);
	    		int colNum = 0;
	   	       
				for(int i=1;i<=col_count;i++) {
					//Cell cell = row.createCell(colNum++);
					pw.println("<td>" + r.getString(i) + "</td>");
					//cell.setCellValue(rs.getString(i));
				}
				pw.println("</tr>");
	    	}*/
	   	 
	   	 
	   	while(r.next()) {
	   		
	   		pw.println("<!DOCTYPE html>\r\n" + 
			 		"<html>\r\n" + 
			 		"<head>\r\n" + 
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
			 		
			 		"<div id=\"a1\"><br>\r\n" + 
			 		
			 		
			 		"<table> " +
			 		"  <tr> \r\n " + 
			 		"    <td><b>"+rmd.getColumnName(1)+"</b></td>\r\n" + 
			 		"    <td><input type=\"text\" value="+r.getString(1)+" readonly></td>\r\n" + 
			 		"    <td><b>"+rmd.getColumnName(2)+"</b></td>\r\n" + 
			 		"    <td><input type=\"text\" value="+r.getString(2)+" readonly></td>\r\n" + 
			 		"    <td><b>"+rmd.getColumnName(3)+"</b></td>\r\n" + 
			 		"	<td><input type=\"text\" value="+r.getString(3)+" readonly></td>\r\n" + 
			 		"	<td><b>"+rmd.getColumnName(4)+"</td>\r\n" + 
			 		"	<td><input type=\"text\" value="+r.getString(4)+" readonly/></td>\r\n" + 
			 		"  </tr>\r\n" + 
			 		"  <tr>\r\n" + 
			 		"    <td><b>"+rmd.getColumnName(5)+"</b></td>\r\n" + 
			 		"    <td><input type=\"text\" value="+r.getString(5)+" readonly/></td>\r\n" + 
			 		"    <td><b>"+rmd.getColumnName(6)+"</b></td>\r\n" + 
			 		"    <td><input type=\"text\" value="+r.getString(6)+" readonly/></td>\r\n" + 
			 		"    <td><b>"+rmd.getColumnName(7)+"</b></td>\r\n" + 
			 		"	<td><input type=\"text\" value="+r.getString(7)+" readonly/></td>\r\n" + 
			 		"	<td><b>"+rmd.getColumnName(8)+"</b></td>\r\n" + 
			 		"	<td><input type=\"text\" value="+r.getString(8)+" readonly/></td>\r\n" + 
			 		"  </tr>\r\n" + 
			 		"  <tr>\r\n" + 
			 		"    <td><b>"+rmd.getColumnName(9)+"</b></td>\r\n" + 
			 		"    <td><input type=\"text\" value="+r.getString(9)+" readonly/></td>\r\n" + 
			 		"    <td><b>"+rmd.getColumnName(10)+"</</td>\r\n" + 
			 		"    <td><input type=\"text\" value="+r.getString(10)+" readonly/></td>\r\n" + 
			 		"    <td><b>"+rmd.getColumnName(11)+"</b></td>\r\n" + 
			 		"	<td><input type=\"text\" value="+r.getString(11)+" readonly/></td>\r\n" + 
			 		"	<td><b>"+rmd.getColumnName(12)+"</b></td>\r\n" + 
			 		"	<td><input type=\"text\" value="+r.getString(12)+" readonly/></td>\r\n" + 
			 		"  </tr>\r\n" + 
			 		"  <tr>\r\n" + 
			 		"    <td><b>"+rmd.getColumnName(13)+"</label></td>\r\n" + 
			 		"    <td><input type=\"text\" value="+r.getString(13)+" readonly/></td>\r\n" + 
			 		"	</td>\r\n" + 
			 		"    <td><b>"+rmd.getColumnName(14)+"</b></td>\r\n" + 
			 		"    <td><input type=\"text\" value="+r.getString(14)+" readonly/></td>\r\n" + 
			 		"    <td><b>"+rmd.getColumnName(15)+"</b></td>\r\n" + 
			 		"	<td><input type=\"text\" value="+r.getString(15)+" readonly/></td>\r\n" + 
			 		"	<td><b>"+rmd.getColumnName(16)+"</</td>\r\n" + 
			 		"	<td><input type=\"text\" value="+r.getString(16)+" readonly/></td>\r\n" + 
			 		"  </tr>\r\n" + 
			 		"  <tr>\r\n" + 
			 		"    <td><b>"+rmd.getColumnName(17)+"</b></td>\r\n" + 
			 		"    <td><input type=\"text\" value="+r.getString(17)+" readonly/></td>\r\n" + 
			 		"    <td><b>"+rmd.getColumnName(18)+"</b></td>\r\n" + 
			 		"    <td><input type=\"text\" value="+r.getString(18)+" readonly/></td>\r\n" + 
			 		"    <td><b>"+rmd.getColumnName(19)+"</b></td>\r\n" + 
			 		"	 <td><input type=\"text\" value="+r.getString(19)+" readonly/></td>\r\n" + 
			 		"	 <td><b>"+rmd.getColumnName(20)+" </b></td>\r\n" + 
			 		"	 <td><input type=\"text\" value="+r.getString(20)+" readonly/></td>\r\n" + 
			 		"	</tr>\r\n" + 
			 		"	<tr>\r\n" + 
			 		"	  <td><b>"+rmd.getColumnName(21)+" </a></td>\r\n" + 
			 		"	  <td><input type=\"text\" value="+r.getString(21)+" readonly/></td>\r\n" +  
			 		"     <td><b>"+rmd.getColumnName(22)+" </a></td> \r\n"+ 
			 		"     <td><input type=\"text\" value="+r.getString(22)+" readonly/></td>\r\n"   +
			 	    "     <td><b>"+rmd.getColumnName(23)+" </a></td>\r\n\" + \r\n" + 
			 	    "     <td><input type=\"text\" value="+r.getString(23)+" readonly/></td>\r\n " + 
			 	    "     <td><b>"+rmd.getColumnName(24)+" </a></td>\r\n"+ 
			 	    "	  <td><input type=\"text\" value="+r.getString(24)+" readonly/></td>\r\n" +
 			 	    "</tr>\r\n" + 
			 		"<tr>\r\n"  +
			 		"<td><b>"+rmd.getColumnName(25)+" </a></td>\r\n"  +
			 		"<td><input type=\"text\" value="+r.getString(25)+" readonly/></td>\r\n"  +
 			 		"<td><b>"+rmd.getColumnName(26)+" </a></td>\r\n "+ 
 			 		"<td><input type=\"text\" value="+r.getString(26)+" readonly/></td>\r\n"  +
 			 		"</tr>\r\n" +
			 		" </table>\r\n" + 
			 		"\r\n" + 
			 		"<input type=\"Submit\"  formaction=\"\" value=\"Delete\" id = \"btn\"> \r\n" + 
			 		"<input type=\"button\" id=\"btn2\" onClick=\"location.href='TT_Employee_List.html'\" value='Back'>\r\n" + 
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
	   	 
	   	 
		 
     }catch (SQLException e) {
			e.printStackTrace();
		}
}
}	
