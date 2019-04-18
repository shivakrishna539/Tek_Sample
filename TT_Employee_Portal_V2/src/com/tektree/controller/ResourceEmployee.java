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
@WebServlet("/ResourceEmployee")
public class ResourceEmployee extends HttpServlet{
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
   	    
   	    
   	    
   	    String name = "";
   	    String skill = "";
   	    String designation = "";
   	    
   	    name = req.getParameter("name");
   	    skill = req.getParameter("Skill");
     	designation = req.getParameter("Designation");
   	    
   	    try {
			Statement st1 = con.createStatement();
			
			Statement s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
 	                  ResultSet.CONCUR_READ_ONLY);
			String List1 = "SELECT * FROM \"tektree\".\"employee\" WHERE 1=1";
		   	   ResultSet r = s.executeQuery(List1);
	   	    r.last();
	   	    int count = r.getRow();
	   	    r.beforeFirst();
			int flag=0;
			           
		
	   	              
   	    String List = "SELECT * FROM \"tektree\".\"employee\" WHERE first_name='"+name+"' AND "
   	    		+ " skill='"+skill+"' AND designation='"+designation+"'";
   	    
   	    
   	    //Name
   	    if( name != ""){
   	    	List1 = List1 + "AND UPPER(first_name)=UPPER('"+name+"')";
   	    }
   	   
   	    //Skill
   	    if( skill != "" ){	
   	    	List1 = List1 + "AND UPPER(skill)=UPPER('"+skill+"')";
	    }
   	  
   	    //Designation
   	    if( designation != ""){
   	    	List1 = List1 + "AND UPPER(designation)=UPPER('"+designation+"') ORDER BY created_on DESC";
	    }
   	 
   	            if(name == "" && skill == "" && designation == "") {
   	            	  
   	            	 String query="SELECT * FROM \"tektree\".\"employee\" ORDER BY created_on DESC";
   	            	 ResultSet rs1=st1.executeQuery(query);
   	            	 ResultSetMetaData rmd1 = rs1.getMetaData();
   	        	     int col_count1 = rmd1.getColumnCount();
   	        	    
   	        	     RequestDispatcher rd1 = req.getRequestDispatcher("TT_EMP_RegistrationForm_html_Boot.html");
   	      	         rd1.include(req, res);
   	      	         boolean empty1 = true;
   	  	    
   	      	         if(rs1 != null) {
   	      	        	 pw.println("<br><html> <script> " +
   " function openAddress() " +
   " {  document.getElementById(\"contents\").style.display = \"block\";	} "+ 
	
	" $(document).ready(function() { "+
        " $('#example').DataTable();} ); </script><body>"+
   	   	    			"<form action=\"getxsl\" method=\"post\">"
   	   	    			+ "<div class=\"container-fluid\">"
   	   	    			+ "<table class=\"table table-striped table-bordered\" style=\"width:100%\">");
   	      	        	 int rowNum = 0;
   	      	        	 //Displaying Column Names
   	      	        	 pw.println("<tr>");
   	   	    	
   	      	        	 pw.println("<th>" + rmd1.getColumnName(1).toUpperCase() + "</th>");
   	      	        	 pw.println("<th>" + rmd1.getColumnName(3).toUpperCase() + "</th>");
   	      	        	 pw.println("<th>" + rmd1.getColumnName(16).toUpperCase() + "</th>");
   	      	        	 pw.println("<th>" + rmd1.getColumnName(18).toUpperCase() + "</th>");
   	      	        	 pw.println("<th>" + rmd1.getColumnName(19).toUpperCase() + "</th>");
   	      	             //pw.println("<th>" + rmd1.getColumnName(21).toUpperCase() + "</th>");
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
   	      	        		// pw.println("<td>" + rs1.getString("created_on") + "</td>");
   	      	        		 //for(int i=1;i<=col_count-4;i++) {
   	      	        		 //	pw.println("<td>" + rs.getString(i) + "</td>");
   	      	        		 //}
   	   				
   	      	        		 a[in] = Integer.parseInt(rs1.getString("employee_id"));
   	      	        		
   	      	        		 pw.println("<td><input type=\"submit\" formaction=\"SelectEmployee\" target=\"self\" name="+a[in]+" value=\"Deploy\">"
   	   						+ "<input type=\"submit\" formaction=\"EditRegisteredEmployee\" target=\"_self\" name="+a[in]+" value=\"Edit\" >"
   	   						+ "<input type=\"submit\" formaction=\"EmployeeDetails\" target=\"_self\" name="+a[in]+" value=\"View\">"
   	   						+ "<input type=\"submit\" formaction=\"InternalProjects\" target=\"_self\" name="+a[in]+" value=\"Assign\"></td>");
   	      	        		 pw.println("</tr>");
   	   			   
   	      	        		 empty1 = false;	
   	      	        		 in = in + 1;
   	      	        		 flag=1;
   	      	        	 }
   	                 }
   	              }else {
   	              
   	              
   	    ResultSet rs=st1.executeQuery(List1);
   	    
   	    ResultSetMetaData rmd = rs.getMetaData();
   	    int col_count = rmd.getColumnCount();
   	
   	    if(flag==0) {
   	    RequestDispatcher rd = req.getRequestDispatcher("TT_EMP_RegistrationForm_html_Boot.html");
 	    rd.include(req, res);
   	    }
 	    boolean empty = true;
 	    
 	     if(rs != null) {
   	    	pw.println("<br><html><body>"
   	    			+ "<form action=\"getxsl\" method=\"post\"><table class=\"table table-striped table-bordered\" style=\"width:100%\">");
   	    	int rowNum = 0;
   	    	//Displaying Column Names
   	    	//String file = "D:/MyFirstExcel.xlsx";
   	        //XSSFWorkbook workbook = new XSSFWorkbook();
 	        //XSSFSheet sheet = workbook.createSheet("Resources List");
   	    	pw.println("<tr>");
   	    	
   	    	pw.println("<th>" + rmd.getColumnName(1).toUpperCase() + "</th>");
   	    	pw.println("<th>" + rmd.getColumnName(3).toUpperCase() + "</th>");
   	    	pw.println("<th>" + rmd.getColumnName(16).toUpperCase() + "</th>");
   	    	pw.println("<th>" + rmd.getColumnName(18).toUpperCase() + "</th>");
   	    	pw.println("<th>" + rmd.getColumnName(19).toUpperCase() + "</th>");
			//for(int i=1;i<=col_count-4;i++) {
			//	pw.println("<th>" + rmd.getColumnName(i).toUpperCase() + "</th>");
				
			//}
			pw.println("<td></td>");
			pw.println("</tr>");
			
			int in=1;
   	    	while(rs.next()) {
   	    		pw.println("<tr>");
   	    		//Row row = sheet.createRow(rowNum++);
   	    		int colNum = 0;
   	   	        int a[] = new int[count+1];
   	   	        pw.println("<td>" + rs.getString("employee_id") + "</td>");
   	   	        pw.println("<td>" + rs.getString("first_name") + "</td>");
   	   	        pw.println("<td>" + rs.getString("designation") + "</td>");
   	   	        pw.println("<td>" + rs.getString("current_location") + "</td>");
   	   	        pw.println("<td>" + rs.getString("total_experience") + "</td>");
   		  		//for(int i=1;i<=col_count-4;i++) {
   					//Cell cell = row.createCell(colNum++);
   				//	pw.println("<td>" + rs.getString(i) + "</td>");
   					//cell.setCellValue(rs.getString(i));
   				//}
   				
   			    a[in] = Integer.parseInt(rs.getString("employee_id"));
   				pw.println("<td><input type=\"submit\" formaction=\"SelectEmployee\" target=\"_self\" name="+a[in]+" value=\"Deploy\">"
   						+ "<input type=\"submit\" formaction=\"EditRegisteredEmployee\" target=\"_self\" name="+a[in]+" value=\"Edit\">"
   						+ "<input type=\"submit\" formaction=\"EmployeeDetails\" target=\"_self\" name="+a[in]+" value=\"View\">"
   						+ "<input type=\"submit\" formaction=\"InternalProjects\" target=\"_self\" name="+a[in]+" value=\"Assign\"</td>");
   				pw.println("</tr>");
   			    
   				empty = false;	
   				in = in + 1;
   	    	}
   	    	//FileOutputStream outputStream = new FileOutputStream(file);
            //workbook.write(outputStream);
            //workbook.close();
   	    	//pw.println("</table><br/><input type=\"submit\" value=\"Get File\"></form></body></form></html>");
 	    
 	      if(empty) {
 	    	  pw.println("<html><body><h3 style=\"margin-left:500px\"><font color=\"red\">No records found</h3></body></html>");
 	      }
 	   
   	    } 
   	 }
   	    }
   	    catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  	
   	    
	}



	

}
