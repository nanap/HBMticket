package controller;


import java.io.*;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*; 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import com.mpowerpayments.mpower.*;


public class PayServlet extends HttpServlet{
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException{

		req.getRequestDispatcher("/WEB-INF/pay.jsp").forward(req,resp);

		// if (req.getSession().getAttribute("Username") == null) {

		// 	resp.sendRedirect("/login?next=" + req.getRequestURI());
		// 	return;
		// }
  //   		req.getRequestDispatcher("/WEB-INF/settings.jsp").forward(req,resp);
	} 

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException{

			

			// boolean buy = Boolean.parseBoolean(req.getParameter("buy"));
			int x = Integer.parseInt(req.getParameter("buy"));

		

			if (x == 1 || x == 2 || x == 3 || x == 4 ){
				//Setting up your MPower API keys
				MPowerSetup setup = new MPowerSetup();
				setup.setMasterKey("15d68052-1567-4036-8d6b-cbaba6e01efc");
				setup.setPrivateKey("live_private_deKCawNOuS1q8LwN_2VLFuPdS7c");
				setup.setPublicKey("live_public_CuQPEnWX6KlPm7MFx5AvgbtIj90");
				setup.setToken("3d2d0341a0424b04e065"); 
				setup.setMode("live"); 
				

				//Checkout Store Configuration
				MPowerCheckoutStore store = new MPowerCheckoutStore();
				store.setName("HBMticket");
				store.setTagline("A night of worship");
				store.setPhoneNumber("0242561793");
				store.setPostalAddress("K.I.A, P.O.Box 9831, Airport - Accra");
				store.setWebsiteUrl("http://localhost:8080/");

				//Initialize a Checkout Invoice
				MPowerCheckoutInvoice invoice = new MPowerCheckoutInvoice (setup, store);

				 
				/* You can optionally set a generation invoice description text which can 
				be used in cases where your invoice does not need an items list or in cases
				where you need to include some extra descriptive information to your invoice */
				invoice.setDescription("Psalm 96:9 Worship the LORD in holy attire; Tremble before Him, all the earth. God richly bless you.");

				//Setting total amount chargable
				if (x == 1 ) {
					invoice.setTotalAmount(20.00);
					// Adding items to your invoice
				
				invoice.addItem(" 1 Tickets",1,20.00,20.00,"1 Ticket purchased from HBMticket - Please Show this at the entrance - Sales Agent (Nana Opoku Agyeman-Prempeh - 0242561793 / 0504561793.");
				
				invoice.setTotalAmount(20.00);
	

				}else if (x == 2) {

					// Adding items to your invoice
				invoice.addItem("2 Tickets",2,20.00,40.00,"2 Tickets purchased from HBMticket - Please Show this at the entrance - Sales Agent (Nana Opoku Agyeman-Prempeh - 0242561793 / 0504561793");

				invoice.setTotalAmount(40.00);
	
				}else if (x == 3) {

					
					// Adding items to your invoice
			
				invoice.addItem("3 Tickets",3,20.00,60.00,"3 Tickets purchased from HBMticket - Please Show this at the entrance - Sales Agent (Nana Opoku Agyeman-Prempeh - 0242561793 / 0504561793");
	
				invoice.setTotalAmount(60.00);			

				}else{
					
					// Adding items to your invoice
				invoice.addItem("4 Tickets",4,20.00,80.00,"4 Tickets purchased from HBMticket - Please Show this at the entrance - Sales Agent (Nana Opoku Agyeman-Prempeh - 0242561793 / 0504561793");

				invoice.setTotalAmount(80.00);
				}
				

				// Setting the return URL on an invoice instance. 
				// This will overwrite any global settings for return URL
				 invoice.setReturnUrl("http://hbmticket.herokuapp.com/");

				// The code below depicts how to create the checkout invoice on our servers
				// and redirect to the checkout page.
				if (invoice.create()) {
				  System.out.println(invoice.getStatus());
				  System.out.println(invoice.getResponseText());
				  System.out.println(invoice.getInvoiceUrl());

				  // Globally setting return URL, the piece of code below 
				  // should be included with the checkout shop setup code
//				  MPowerStore store = new MPowerStore();
				  //store.setReturnUrl("http://google.com/");
				 
				  

				  	resp.sendRedirect(invoice.getInvoiceUrl());

				} else {
				  System.out.println(invoice.getResponseText());
				  System.out.println(invoice.getStatus());
				}
			}


			       
	}
}

