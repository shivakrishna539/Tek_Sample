package com.tektree.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tektree.ConnectionManager.ConnectionManager;

/**
 * Servlet implementation class AddressDetails
 */
@WebServlet("/AddressList")
public class AddressList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("Text/html");
		PrintWriter pw= response.getWriter();
		HttpSession session = request.getSession(false);
		//int emp_id = Integer.parseInt((String)session.getAttribute("emp_id"));
		RequestDispatcher rd1 = request.getRequestDispatcher("TT_Address_details.html");
	         rd1.include(request, response);
		try {
			Connection con = null;
			try {
				con = ConnectionManager.getConnection();
			} catch (Throwable e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
			PreparedStatement pstmt=con.prepareStatement("select * from \"tektree\".\"employee_address\"");
			//pstmt.setInt(1, Employee_Id);
			pw.println("<form method='get'>");
			pw.print("<table  class=\"table table-striped table-bordered\" style=\"width:100%\">");  
			ResultSet rs=pstmt.executeQuery();
		//int i=stmt.executeUpdate(("insert into \"tektree\".\"employee_address\"(employee_id,address_temporary,address_permanent,email_id,email_id_alt,phone_1,phone_2,emergency_contact_no)values((select Employee_Id from \"tektree\".\"employee\" where Employee_Code = 'TT1'),'"+Address_Temporary+"','"+Address_Permanent+"','"+Email_ID+"','"+Email_ID_Alt+"','"+Phone_1+"','"+Phone_2+"','"+Emergency_Contact_No+"')"));
//			int i=stmt.executeUpdate(("insert into \"tektree\".\"employee_education\"(employee_education_id,employee_id,degree,qualification,institute_name,passed_year,score_percentage)values(2,2,'"+Degree+"','"+Qualification+"','"+Institute_Name+"','"+Passed_Year+"','"+Score_Percentage+"')"));
			ResultSetMetaData rsd=rs.getMetaData();
			int total=rsd.getColumnCount();
			//System.out.println("in address list service");
			pw.println("<tr>");
			for(int i=3;i<=total-4;i++)
			{
				pw.println("<th>"+rsd.getColumnName(i)+"</th>");
			}
			pw.println("</tr>");
			 int in=1;
			while(rs.next())
			{
				int count = rs.getRow();
				
				 int colNum = 0;
 	        int a[] = new int[count+1];
 	           a[in] = rs.getInt("employee_id");
                  	       
				pw.println("<tr><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td><td>"+rs.getString(8)+"</td><td>"+rs.getString(9)+"</td>");  
				
				pw.println("<td><input type='submit' formaction='UpdateAddress' target='_self' name="+a[in]+" value='Edit' method='get'/></td></tr>");   		
				pw.println("</form>");
				in = in + 1;
			}
			pw.println("<a href='ExcelFile'download>Export</a>");
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
