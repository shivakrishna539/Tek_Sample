package com.tektree.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tektree.ConnectionManager.ConnectionManager;
@WebServlet("/PlacementRegistration")
public class PlacementRegistration extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{	
		HttpSession session = req.getSession(false);
		System.out.println("in placement registration service");
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		String etype=req.getParameter("yesno");
		String emp_id1 = (String) session.getAttribute("emp_id");
		int emp_id = Integer.parseInt(emp_id1);
		
	   // int emp_id=Integer.parseInt(request.getParameter("eid"));
	   // pw.println(emp_id);
		String emp_code=req.getParameter("ecode");
		//String designation=request.getParameter("designation");
		String client_name=req.getParameter("name");
		int placement_id=Integer.parseInt(req.getParameter("epid"));
		//pw.println(placement_id);
		String work_location=req.getParameter("location");
		
		//pw.println(application_id);
		int ctc=Integer.parseInt(req.getParameter("ctc"));
		//pw.println(ctc);
		
		//pw.println(rate_per_month);
		
		java.util.Date doj=Date.valueOf(req.getParameter("doj"));
		//java.sql.Date doj=new java.sql.Date(util_doj);
		java.util.Date offer_date=Date.valueOf(req.getParameter("odate"));
		//String offer_date=request.getParameter("odate");
		java.util.Date onboard_date=Date.valueOf(req.getParameter("obdate"));
		//String onboard_date=request.getParameter("obdate");
		String status=req.getParameter("pstatus");
		String recruiter=req.getParameter("recruiter");
		String remarks=req.getParameter("remarks");
		System.out.println(remarks+etype);
		try
		{
			 Connection con = null;
				try {
					con = ConnectionManager.getConnection();
				} catch (Throwable e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		if(etype.equals("Contract"))
		{
			System.out.println("entered into contract");
			int ctc_fixed=Integer.parseInt(req.getParameter("ctcfix"));
			pw.println(ctc_fixed);
			int ctc_variable=Integer.parseInt(req.getParameter("ctcvar"));
			pw.println(ctc_variable);
			int rate_per_month=Integer.parseInt(req.getParameter("rpm"));
			java.util.Date sow_start=Date.valueOf(req.getParameter("ssd"));
			java.util.Date sow_end=Date.valueOf(req.getParameter("sed"));
		PreparedStatement pstmt=con.prepareStatement("insert into \"tektree\".\"hr_employee_placement\"(emp_placement_id,employee_id,client_name,work_location,employment_type,doj,offer_date,ctc,ctc_fixed,ctc_variable,rate_per_month,sow_start_date,sow_end_date,onboard_date,remarks,recruiter,placement_status) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		pstmt.setInt(1,placement_id);
		pstmt.setInt(2,emp_id);
		//pstmt.setString(2,emp_code);
		pstmt.setString(3,client_name);
		pstmt.setString(4,work_location);
		pstmt.setString(5,etype);
		pstmt.setDate(6,(Date) doj);
		pstmt.setDate(7,(Date) offer_date);		
		pstmt.setInt(8,ctc);
		pstmt.setInt(9, ctc_fixed);
		pstmt.setInt(10, ctc_variable);
		pstmt.setInt(11,rate_per_month);
		pstmt.setDate(12, (Date) sow_start);
		pstmt.setDate(13,(Date) sow_end);
		pstmt.setDate(14,(Date) onboard_date);	
		pstmt.setString(15, remarks);
		pstmt.setString(16,recruiter);
		pstmt.setString(17, status);;
		int i=pstmt.executeUpdate();
		if(i>0)
		{
			RequestDispatcher rd = req.getRequestDispatcher("TT_placement_screen_webpage_New.html");
		    rd.forward(req, res);
		}
		}
		
		else if(etype.equals("Permanent"))
		{
			System.out.println("Entered into permanent");
			int application_id=Integer.parseInt(req.getParameter("appid"));
			PreparedStatement pstmt1=con.prepareStatement("insert into \"tektree\".\"hr_employee_placement\"(emp_placement_id,employee_id,client_name,work_location,employment_type,doj,offer_date,application_id,ctc,onboard_date,remarks,recruiter,placement_status) values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt1.setInt(1,placement_id);
			pstmt1.setInt(2,emp_id);
			//pstmt1.setString(3,emp_code);
			pstmt1.setString(3,client_name);
			pstmt1.setString(4,work_location);
			pstmt1.setString(5,etype);
			pstmt1.setDate(6,(Date) doj);
			pstmt1.setDate(7,(Date) offer_date);		
			pstmt1.setInt(8,application_id);
			pstmt1.setInt(9,ctc);
			pstmt1.setDate(10,(Date) onboard_date);	
			pstmt1.setString(11, remarks);
			pstmt1.setString(12,recruiter);
			pstmt1.setString(13, status);;
			int j=pstmt1.executeUpdate();
			if(j>0)
			{
				RequestDispatcher rd = req.getRequestDispatcher("TT_placement_screen_webpage_New.html");
			 	rd.forward(req, res);
			}
		}
		}
		catch(Exception e){
			pw.println(e);
		}
		
	}	
}
