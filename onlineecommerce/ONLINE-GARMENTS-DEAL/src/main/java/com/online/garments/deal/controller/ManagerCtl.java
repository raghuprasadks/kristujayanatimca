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
import com.online.garments.deal.bean.ManagerBean;
import com.online.garments.deal.exception.ApplicationException;
import com.online.garments.deal.exception.DuplicateRecordException;
import com.online.garments.deal.model.LoginModel;
import com.online.garments.deal.model.ManagerModel;
import com.online.garments.deal.util.DataUtility;
import com.online.garments.deal.util.DataValidator;
import com.online.garments.deal.util.PropertyReader;
import com.online.garments.deal.util.ServletUtility;

/**
 * Servlet implementation class ManagerCtl
 */
@WebServlet(name = "ManagerCtl", urlPatterns = { "/ctl/ManagerCtl" })
public class ManagerCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log = Logger.getLogger(ManagerCtl.class);
	
	 @Override
	    protected boolean validate(HttpServletRequest request) {
	    	log.debug("ManagerCtl validate method start");
	        boolean pass = true;

	        
	        
	        if (DataValidator.isNull(request.getParameter("name"))) {
	            request.setAttribute("name",PropertyReader.getValue("error.require", "Name"));
	            pass = false;
	        }

	        

	        if (DataValidator.isNull(request.getParameter("contact"))) 
	        {
	            request.setAttribute("contact",PropertyReader.getValue("error.require", "Contact No"));
	            pass = false;
	            
	        }else if(!DataValidator.isPhoneNo(request.getParameter("contact")))
	        {
				request.setAttribute("contact", PropertyReader.getValue("error.invalid","Contact No"));
				pass=false;		
			}		
	        if (DataValidator.isNull(request.getParameter("email"))) 
	        {
	            request.setAttribute("email",PropertyReader.getValue("error.require", "Email "));
	            pass = false;
	        } else if (!DataValidator.isEmail(request.getParameter("email"))) {
	            request.setAttribute("email",PropertyReader.getValue("error.email", "Email "));
	            pass = false;
	        }
	        
	        
	        if (DataValidator.isNull(request.getParameter("address"))) 
	        {
	            request.setAttribute("address",PropertyReader.getValue("error.require", "Address"));
	            pass = false;
	        }
	       
	        
	        if("-----Select-----".equalsIgnoreCase(request.getParameter("role"))){
	        	request.setAttribute("role",PropertyReader.getValue("error.require", "Role"));
	        	pass=false;
	        }
	        
	        String login = request.getParameter("login");


			if (DataValidator.isNull(login)) {
				request.setAttribute("login",
						PropertyReader.getValue("error.require", "Login Id"));
				pass = false;
			} 
			
			
			if (DataValidator.isNull(request.getParameter("password"))) {
				request.setAttribute("password",
						PropertyReader.getValue("error.require", "Password"));
				pass = false;

			} else if (!DataValidator.isPassword(request.getParameter("password"))) {
				request.setAttribute("password",
						PropertyReader.getValue("error.password", "Password"));
				return false;
			}else if (!DataValidator.isPassword(request.getParameter("password"))) {
				request.setAttribute("password",
						PropertyReader.getValue("error.password", "Password"));
				return false;
			}

			if (!request.getParameter("password").equals(
					request.getParameter("confirmPassword"))
					&& !"".equals(request.getParameter("confirmPassword"))) {
				/*ServletUtility.setErrorMessage("Confirm Password did not match",
						request);*/
				request.setAttribute("confirmPassword", PropertyReader.getValue("error.confirmPassword","Confirm Password"));
				pass = false;
			}


	        log.debug("ManagerCtl validate method end");
	        return pass;
	    }
	 
	 @Override
	    protected BaseBean populateBean(HttpServletRequest request) {

	        log.debug("ManagerCtl Method populatebean Started");

	        ManagerBean bean = new ManagerBean();

	        bean.setId(DataUtility.getLong(request.getParameter("id")));

	        bean.setName(DataUtility.getString(request.getParameter("name")));
	        
	        bean.setRole(DataUtility.getLong(request.getParameter("role")));
	        
	        bean.setContactNo(DataUtility.getString(request.getParameter("contact")));

	        bean.setEmailId(DataUtility.getString(request.getParameter("email")));

	        bean.setAddress(DataUtility.getString(request.getParameter("address")));
	        
	        bean.setLogin(DataUtility.getString(request.getParameter("login")));
	    	
			bean.setPassword(DataUtility.getString(request.getParameter("password")));
			bean.setConfirmPassword(DataUtility.getString(request
					.getParameter("confirmPassword")));

	        populateDTO(bean, request);

	        log.debug("ManagerCtl Method populatebean Ended");

	        return bean;
	    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("ManagerCtl Method doGet Started");
        System.out.println("in do get");
        String op = DataUtility.getString(request.getParameter("operation"));
        long id = DataUtility.getLong(request.getParameter("id"));

        // get model
        ServletUtility.setOpration("Add", request);
        ManagerModel model = new ManagerModel();
        if (id > 0 || op != null) {
            ManagerBean bean;
            try {
                bean = model.findByPk(id);
                ServletUtility.setOpration("Edit", request);
                ServletUtility.setBean(bean, request);
            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            }
        }
        ServletUtility.forward(getView(), request, response);
        log.debug("ManagerCtl Method doGett Ended");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("ManagerCtl Method doPost Started");
        System.out.println("in do post");
        String op = DataUtility.getString(request.getParameter("operation"));

        // get model

        ManagerModel model = new ManagerModel();

        long id = DataUtility.getLong(request.getParameter("id"));
        ServletUtility.setOpration("Add", request);
       
        if (OP_SAVE.equalsIgnoreCase(op)) {

            ManagerBean bean = (ManagerBean) populateBean(request);
            LoginModel lModel=new LoginModel();
            LoginBean lBean=new  LoginBean();
            try {
                if (id > 0) {
                	LoginBean lbBean= lModel.findByLogin(bean.getLogin());
                	lbBean.setLogin(bean.getLogin());
                	lbBean.setPassword(bean.getPassword());
                	lbBean.setRole(bean.getRole());
                	lModel.update(lbBean);
                    model.update(bean);
                    ServletUtility.setSuccessMessage("Data is successfully Updated",request);
                    ServletUtility.setOpration("Edit", request);
                    ServletUtility.setBean(bean, request);
                } else {
                	lBean.setLogin(bean.getLogin());
                	lBean.setPassword(bean.getPassword());
                	lBean.setRole(bean.getRole());
                	lModel.add(lBean);
                    long pk = model.add(bean);
                    ServletUtility.setSuccessMessage("Data is successfully saved",request);
                }

            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            } catch (DuplicateRecordException e) {
                ServletUtility.setBean(bean, request);
                ServletUtility.setErrorMessage(e.getMessage(), request);
            }
            ServletUtility.forward(getView(), request, response);
        }

        else if (OP_DELETE.equalsIgnoreCase(op)) {

            ManagerBean bean = (ManagerBean) populateBean(request);
            try {
                model.delete(bean);
                ServletUtility.redirect(OGDView.MANAGER_CTL, request,response);
                return;

            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            }

        } else if (OP_CANCEL.equalsIgnoreCase(op)) {
        	 
        	ServletUtility.redirect(OGDView.MANAGER_CTL, request, response);


        }else if (OP_RESET.equalsIgnoreCase(op)) {
    		ServletUtility.redirect(OGDView.MANAGER_CTL, request, response);
    		return;
    }
        
        log.debug("ManagerCtl Method doPost Ended");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return OGDView.MANAGER_VIEW;
	}

}
