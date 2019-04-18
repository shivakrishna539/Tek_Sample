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
 * Servlet implementation class ExperienceList
 */
@WebServlet("/ExperienceList")
public class ExperienceList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
   
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("Text/html");
		PrintWriter pw= response.getWriter();
		HttpSession session = request.getSession(false);
		//int emp_id = Integer.parseInt((String)session.getAttribute("emp_id"));
		RequestDispatcher rd1 = request.getRequestDispatcher("TT_Experience_Details_After_Load.html");
	         rd1.include(request, response);
		try {
			Connection con = null;
			try {
				con = ConnectionManager.getConnection();
			} catch (Throwable e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			PreparedStatement pstmt=con.prepareStatement("select * from \"tektree\".\"employee_experience\"");
			//pstmt.setInt(1, Employee_Id);
			pw.print("<table class=\"table table-striped table-bordered\" style=\"width:100%\">");  
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
			
			while(rs.next())
			{
				int a =rs.getInt("Employee_Id");
				pw.println("<tr><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getInt(5)+"</td><td>"+rs.getString(6)+"</td><td>"+rs.getString(7)+"</td><td>"+rs.getString(8)+"</td><td>"+rs.getDate(9)+"</td><td>"+rs.getDate(10)+"</td><td><a href='UpdateAddress?Employee_Id="+rs.getInt("Employee_Id")+"' name='"+a+"'>edit</a></td></tr>");  
			
			}
			pw.println("<a href='ExcelFile'download>Export</a>");
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
