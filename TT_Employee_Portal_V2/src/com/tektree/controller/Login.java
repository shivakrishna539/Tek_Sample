package com.tektree.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/Login")
public class Login extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		
		res.setContentType("text/html");
	    PrintWriter pw=res.getWriter();
	    
	    String username=req.getParameter("name");
	    String password=req.getParameter("pwd");
	    
	   
	    if(username.equals("admin") && password.equals("admin")){
	    	
	    	
	    	//Session of user
	    	HttpSession session = req.getSession(true);
	    	session.setAttribute("user", username);
	    	
	    	//Navigating to Resource deployment portal
	    	RequestDispatcher rd = req.getRequestDispatcher("TT_Employee_Portal.html");
	    	rd.include(req, res); 
	    	
	    	//RequestDispatcher rd3 = req.getRequestDispatcher("RegistrationList");
	         //rd3.forward(req, res);
	    	return;
	    }
	    
	    pw.println("Invalid Credentials!");
	    
	}
}
