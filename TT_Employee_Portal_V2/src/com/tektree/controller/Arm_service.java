package com.tektree.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tektree.ConnectionManager.ConnectionManager;
@WebServlet("/Arm_service")
public class Arm_service extends HttpServlet{

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
	    System.out.println("In arm service"); 
 //retrieving form parameters
	    
	    Date Date1 = Date.valueOf(req.getParameter("date"));    
	    int Quick_book =Integer.parseInt(req.getParameter("qkbk"));
	    String Invoice_No = req.getParameter("invceno");
	    String Amt_Rs = req.getParameter("amtrs");
	    String S_T = req.getParameter("st");
	    String SBC = req.getParameter("sbc");
	    String KKC = req.getParameter("kkc");
	    String CGST = req.getParameter("cgst");
	    String SGST = req.getParameter("sgst");
	    String IGST = req.getParameter("igst");
	    String IGST_for_SEZ = req.getParameter("igstsez");
	    String Total_Amnt_Rs = req.getParameter("tamnt");
	    String TDS  = req.getParameter("tds");
	    String Amt_to_be_Recd = req.getParameter("amtrcd");
	    String Amnt_Recd = req.getParameter("amtrcvd");
	    String Balance = req.getParameter("blnce");
	    Date Due_Date =  Date.valueOf(req.getParameter("duedt"));
	    Date Amnt_Rx_date =Date.valueOf(req.getParameter("amtrxdt"));
	    
	    Date Cheque_Date = Date.valueOf(req.getParameter("chqdt"));
	    String Cheque_no = req.getParameter("chqno");
	    String  Form_16A = req.getParameter("f16a");
	    String  St_claim = req.getParameter("stclm");
	    String Raised_To = req.getParameter("rsdto");
	    String Month  = req.getParameter("month");
	    String Consultant_Name =  req.getParameter("cnsltname");
	 
	    Connection con = null;
		try {
			con = ConnectionManager.getConnection();
		} catch (Throwable e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
   	    try {
   	    	
   	    
   	    	PreparedStatement st=con.prepareStatement("INSERT INTO tektree.invoice_details(date, quick_book, invoice_no, amount, amt_usd,"
   	    			+ " s_t, sbc, kkc, cgst, sgst, igst, igst_for_sez, total_amount, tds, amount_to_be_record,"
   	    			+ " amount_record, balance)	VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
   			st.setDate(1,Date1);
   			st.setInt(2,Quick_book);
   			st.setString(3,Invoice_No);
   			st.setString(4,Amt_Rs);
   			st.setString(5,"1234");
   			st.setString(6,S_T);
   			st.setString(7,SBC);
   			st.setString(8,KKC);
   			st.setString(9,CGST);
   			st.setString(10,SGST);
   			st.setString(11,IGST);
   			st.setString(12,IGST_for_SEZ);
   			st.setString(13,Total_Amnt_Rs);
   			st.setString(14,TDS);
   			st.setString(15,Amt_to_be_Recd);
   			st.setString(16,Amnt_Recd);
   			st.setString(17,Balance);
   			
   	        st.executeUpdate();
   	     st.close();
   	   
  	  PreparedStatement st1 = null;
  	  
  
   	
   		Statement s = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                 ResultSet.CONCUR_READ_ONLY);
   		String List1 = "select invoice_id from tektree.invoice_details where invoice_no = Invoice_No";
   		ResultSet r = s.executeQuery(List1);
   		
   		int val = 0; 
   	 while(r.next()) {
   		val = Integer.parseInt(r.getString("invoice_id")) ; 
   	 }
 	System.out.println("in to 2nd insert");
   	 
   st1=con.prepareStatement("INSERT INTO tektree.rx_details"
 	     		+ "(invoice_id, due_date, amount_rx_date, cheque_date, cheque_no_neft, form_16_a, st_claim, raised_to, month, consultant_name)"
 	     		+ " VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?);");
   	        st1.setInt(1,val); 
			st1.setDate(2,Due_Date);
			st1.setDate(3,Amnt_Rx_date);
			st1.setDate(4,Cheque_Date);
			st1.setString(5,Cheque_no);
			st1.setString(6,Form_16A);
			st1.setString(7,St_claim);
			st1.setString(8,Raised_To);
			st1.setString(9,Month);
			st1.setString(10,Consultant_Name);
			
	        st1.executeUpdate();
	        st1.close();
   	 
   	 RequestDispatcher rd = req.getRequestDispatcher("Account_recievables.html");
 	 rd.forward(req, res);
 	
	}
   	 catch (Exception e) {
			e.printStackTrace();    
	    
	    }
   	 
	}}
