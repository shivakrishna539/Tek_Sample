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
				 		" <head>   \r\n" + 
				 		"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
				 		"  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css\">\r\n" + 
				 		"  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\r\n" + 
				 		"  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js\"></script>\r\n" + 
				 		"  \r\n" + 
				 		"  <style>\r\n" + 
				 		"   hr.line{border-top: 1px solid ;}\r\n" + 
				 		"   input[type=\"text\"],[type=\"date\"],[type=\"Email\"]\r\n" + 
				 		"	{\r\n" + 
				 		"	  width:120px;\r\n" + 
				 		"	}\r\n" + 
				 		"  </style>\r\n" + 
				 		"  \r\n" + 
				 		"  <script>\r\n" + 
				 		"       function myFunction1()\r\n" + 
				 		"	       {\r\n" + 
				 		"               document.getElementById(\"dis\").disabled = false;\r\n" + 
				 		"			   document.getElementById(\"dis1\").disabled = false;\r\n" + 
				 		"			   document.getElementById(\"dis2\").disabled = false;\r\n" + 
				 		"           }\r\n" + 
				 		"      function myFunction()\r\n" + 
				 		"	       {\r\n" + 
				 		"               document.getElementById(\"dis\").disabled = true;\r\n" + 
				 		"			   document.getElementById(\"dis1\").disabled = true;\r\n" + 
				 		"			   document.getElementById(\"dis2\").disabled = true;\r\n" + 
				 		"           }\r\n" + 
				 		"  </script>\r\n" + 
				 		" </head>\r\n" + 
				 		" <body style=\"background-color :  #a3c2c2;margin:5px;\">\r\n" + 
				 		" <h2><b>Employee Name</b></h2><hr class=\"line\"/>\r\n"
				 		+ "<input class=\"form-control input-sm\" type=\"text\" name = \"name\" value="+name+" />" + 
				 		" <!-- ----------------------------------------------------------------------------->\r\n" + 
				 		
				 		"		 <h2><b>Placement Details</b></h2>\r\n" + 
				 		"	<!------------------------------------------------------------>\r\n" + 
				 		"    \r\n" + 
				 		"	    \r\n" + 
				 		"          <h4><b>Employement type </b></h4>\r\n" + 
				 		"	<div>\r\n"
				 		+ "<form action=\"PlacementRegistration\" method=\"post\" class=\"form-group\">" +  
				 		"		  <label class=\"radio-inline\">\r\n" + 
				 		"            <input type=\"radio\" onclick=\"myFunction()\" name=\"yesno\" value=\"Permanent\"/> <b>Permanent</b>\r\n" + 
				 		"		  </label>\r\n" + 
				 		"		  <label class=\"radio-inline\">\r\n" + 
				 		"            <input type=\"radio\" onclick=\"myFunction1()\" name=\"yesno\" value=\"Contract\"/> <b>Contract</b> \r\n" + 
				 		"		  </label>\r\n" + 
				 		"	<div class=\"container-fluid\">\r\n" + 
				 		"      <div class=\"row\">\r\n" + 
		
				 		"            <div id=\"main\" class=\"table-responsive\">\r\n" + 
				 		"			\r\n" + 
				 		"             <table class=\"table \">\r\n" + 
				 		"                 <tr >\r\n" + 
				 		"                  <td ><b>Name</b></td>\r\n" + 
				 		"                  <td ><input class=\"form-control input-sm\" type=\"text\" name = \"name\" value="+emp_name+" /></td>\r\n" + 
				 		"                  <td><b>Date of Joining</b></td>\r\n" + 
				 		"                  <td><input class=\"form-control input-sm\" type=\"date\" name =\"doj\"/></td>\r\n" + 
				 		"                  <td><b>Employee Placement Id </b></td>\r\n" + 
				 		"	              <td><input class=\"form-control input-sm\" type=\"text\" name = \"epid\"/></td>\r\n" + 
				 		"                 </tr>\r\n" + 
				 		"                 <tr>\r\n" + 
				 		"				  <td><b>Offer Date</td>\r\n" + 
				 		"	              <td><input class=\"form-control input-sm\" type=\"date\" name = \"odate\"/></td>\r\n" + 
				 		"                  <td><b>Work Location</b></td>\r\n" + 
				 		"                  <td><input class=\"form-control input-sm\" type=\"text\" name = \"location\"/></td>\r\n" + 
				 		"                  <td><b>On Board Date</b></td>\r\n" + 
				 		"				  <td><input class=\"form-control input-sm\" type=\"date\" name = \"obdate\" /></td>\r\n" + 
				 		"                 </tr>\r\n" + 
				 		"                 <tr>\r\n" + 
				 		"                  <td><b>CTC </b></td>\r\n" + 
				 		"	              <td><input class=\"form-control input-sm\" type=\"text\" name = \"ctc\" /></td>\r\n" + 
				 		"				  <td><b>Application Id</b></td>\r\n" + 
				 		"	              <td><input class=\"form-control input-sm\" type=\"text\" name =\"appid\" /></td>\r\n" + 
				 		"	              <td><b>CTC Fixed</b></td>\r\n" + 
				 		"	              <td><input id=\"dis\"  class=\"form-control input-sm\" type=\"text\" name =\"ctcfix\" /></td>\r\n" + 
				 		"                  \r\n" + 
				 		"                 </tr>\r\n" + 
				 		"               <tr>\r\n" + 
				 		"			     <td><b>CTC Variable</b></td>\r\n" + 
				 		"                 <td><input id=\"dis1\"  class=\"form-control input-sm\" type=\"text\" name =\"ctcvar\"/></td>\r\n" + 
				 		"                 <td><b>SOW Start Date </</td>\r\n" + 
				 		"                 <td><input class=\"form-control input-sm\" type=\"date\" name = \"ssd\"/></td>\r\n" + 
				 		"                 <td><b>SOW End Date  </b></td>\r\n" + 
				 		"	             <td><input class=\"form-control input-sm\" type=\"date\" name=\"sed\"/></td>\r\n" + 
				 		"	             </tr>\r\n" + 
				 		"                <tr>\r\n" + 
				 		"				 <td><b>Rate per Month</b></td>\r\n" + 
				 		"	             <td><input id=\"dis2\" class=\"form-control input-sm\" type=\"text\" name = \"rpm\" /></td>            \r\n" + 
				 		"                 <td><b>Recuriter</label></td>\r\n" + 
				 		"                 <td><input class=\"form-control input-sm\" type=\"text\" name = \"recruiter\"/></td>\r\n" + 
				 		"                 <td><b>Placement Status</b></td>\r\n" + 
				 		"                 <td><input class=\"form-control input-sm\" type=\"text\" name = \"pstatus\"/></td> \r\n" + 
				 		"	            </tr>\r\n" + 
				 		"				<tr>\r\n" + 
				 		"				  <td><b>Remarks</b></td>\r\n" + 
				 		"	             <td><input class=\"form-control input-sm\" type=\"text\" name = \"remarks\" /></td> \r\n" + 
				 		"				 </tr>\r\n" + 
				 		"         </table>\r\n" + 
				 		"		    <div class=\"col-lg-4 col-lg-offset-8 col-md-4 col-md-offset-8 col-sm-offset-8 col-xs-offset-5\">\r\n" + 
				 		"		      <button class=\"btn btn-primary\" >Submit</button>\r\n" + 
				 		"              <!--<button class=\"btn btn-primary\" >Next</button>\r\n" + 
				 		"              <button class=\"btn btn-primary\" >Cancel</button>-->\r\n" + 
				 		"			</div>\r\n" + 
				 		"	    </div>\r\n" + 
				 		"	</div>\r\n"
				 		+ "</div>" + 
				 		
				 		"</form><br/>\r\n" +
				 		"   </div>      \r\n" + 
				 		" </body>\r\n" + 
				 		"</html>");
		
	    
	    //RequestDispatcher rd = req.getRequestDispatcher("TT_placement_screen_webpage_after_selection.html");
	    //rd.forward(req, res);
	 	 
   	 } catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
