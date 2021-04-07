package com.online.garments.deal.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.online.garments.deal.bean.BaseBean;
import com.online.garments.deal.bean.LoginBean;
import com.online.garments.deal.exception.ApplicationException;
import com.online.garments.deal.exception.DuplicateRecordException;
import com.online.garments.deal.model.LoginModel;
import com.online.garments.deal.util.DataUtility;
import com.online.garments.deal.util.DataValidator;
import com.online.garments.deal.util.PropertyReader;
import com.online.garments.deal.util.ServletUtility;



/**
 * Servlet implementation class UserRegistrationCtl
 */

/**
 * UserRegistration functionality Controller. Performs operation for Validate and add a User 
 * As Student Role
 * 
 * @author NAvigable set
 * @version 1.0
 * @Copyright (c) Navigable Set
 * 
 */

@WebServlet(name = "UserRegistrationCtl", urlPatterns = { "/UserRegistrationCtl" })
public class UserRegistrationCtl extends BaseCtl {
	public static final String OP_SIGN_UP = "SignUp";

	private static Logger log = Logger.getLogger(UserRegistrationCtl.class);
	/**
	 * Validate input Data Entered By User
	 * 
	 * @param request
	 * @return
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("UserRegistrationCtl Method validate Started");

		boolean pass = true;

		String login = request.getParameter("login");
		if (DataValidator.isNull(login)) {
			request.setAttribute("login", PropertyReader.getValue("error.require", "Login Id"));
			pass = false;
		
		} 
		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password", PropertyReader.getValue("error.require", "Password"));
			pass = false;
		}

		if (!request.getParameter("password").equals(
				request.getParameter("confirmPassword"))
				&& !"".equals(request.getParameter("confirmPassword"))) {
			/*ServletUtility.setErrorMessage("Confirm Password did not match",
					request);*/
			request.setAttribute("confirmPassword", PropertyReader.getValue("error.confirmPassword","Confirm Password"));
			pass = false;
		}
		
		
			log.debug("UserRegistrationCtl Method validate Ended");
		return pass;
	}
	
	/**
	 * Populates bean object from request parameters
	 * 
	 * @param request
	 * @return
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("UserRegistrationCtl Method populatebean Started");

		LoginBean bean = new LoginBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setRole(3L);

			bean.setLogin(DataUtility.getString(request.getParameter("login")));
	
			bean.setPassword(DataUtility.getString(request.getParameter("password")));
	
			bean.setConfirmPassword(DataUtility.getString(request
					.getParameter("confirmPassword")));
			
			
			populateDTO(bean, request);
	
			log.debug("UserRegistrationCtl Method populatebean Ended");
	
			return bean;
	}

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserRegistrationCtl() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * Contains display logic
	 */
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("UserRegistrationCtl Method doGet Started");
		ServletUtility.forward(getView(), request, response);

	}
	/**
	 * Contains submit logic
	 */
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in post method");
		log.debug("UserRegistrationCtl Method doPost Started");
	
		String op = DataUtility.getString(request.getParameter("operation"));
		// get model
		LoginModel model = new LoginModel();
		
		long id = DataUtility.getLong(request.getParameter("id"));
		
		
		if (OP_SIGN_UP.equalsIgnoreCase(op)) {
			
			LoginBean bean = (LoginBean) populateBean(request);
			try {
			//	System.out.println("in try sign up");
				long pk = model.add(bean);
				//System.out.println("register");
				bean.setId(pk);
			
				request.getSession().setAttribute("UserBean", bean);
				ServletUtility.setSuccessMessage("User Successfully Registered", request);
				ServletUtility.forward(OGDView.USER_REGISTRATION_VIEW, request, response);
				return;
			} catch (DuplicateRecordException e) {
				log.error(e);
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage(e.getMessage(),
						request);
				ServletUtility.forward(getView(), request, response);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				e.printStackTrace();
				return;
			}
		}else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(OGDView.USER_REGISTRATION_CTL, request, response);
			return;
		}
		log.debug("UserRegistrationCtl Method doPost Ended");
	}
	/**
	 * Returns the VIEW page of this Controller
	 * 
	 * @return
	 */
	@Override
	protected String getView() {
		return OGDView.USER_REGISTRATION_VIEW;
	}

}
