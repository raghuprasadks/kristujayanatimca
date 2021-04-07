package com.online.garments.deal.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.online.garments.deal.bean.BaseBean;
import com.online.garments.deal.bean.CustomerBean;
import com.online.garments.deal.bean.LoginBean;
import com.online.garments.deal.bean.ManagerBean;
import com.online.garments.deal.bean.PaymentBean;
import com.online.garments.deal.bean.ProductBean;
import com.online.garments.deal.exception.ApplicationException;
import com.online.garments.deal.exception.DuplicateRecordException;
import com.online.garments.deal.model.CustomerModel;
import com.online.garments.deal.model.LoginModel;
import com.online.garments.deal.model.ManagerModel;
import com.online.garments.deal.model.PaymentModel;
import com.online.garments.deal.model.ProductModel;
import com.online.garments.deal.util.DataUtility;
import com.online.garments.deal.util.DataValidator;
import com.online.garments.deal.util.PropertyReader;
import com.online.garments.deal.util.ServletUtility;

/**
 * Servlet implementation class CustomerCtl
 */
@WebServlet(name = "CustomerCtl", urlPatterns = { "/CustomerCtl" })
public class CustomerCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log = Logger.getLogger(CustomerCtl.class);
	
	 @Override
	    protected boolean validate(HttpServletRequest request) {
	    	log.debug("CustomerCtl validate method start");
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
	        if (DataValidator.isNull(request.getParameter("age"))) 
	        {
	            request.setAttribute("age",PropertyReader.getValue("error.require", "Age"));
	            pass = false;
	        }
	        
	        
	        if (DataValidator.isNull(request.getParameter("address"))) 
	        {
	            request.setAttribute("address",PropertyReader.getValue("error.require", "Address"));
	            pass = false;
	        }
	        
	        String op=DataUtility.getString(request.getParameter("operation"));
	        HttpSession session=request.getSession();
	        LoginBean lBean=(LoginBean)session.getAttribute("user");
	        
	        if(lBean==null) {
	        	
	        	String login = request.getParameter("login");


	        	if (DataValidator.isNull(login)) {
	    			request.setAttribute("login", PropertyReader.getValue("error.require", "Login Id"));
	    			pass = false;
	    		
	    		} 
	    		if (DataValidator.isNull(request.getParameter("password"))) {
	    			request.setAttribute("password", PropertyReader.getValue("error.require", "Password"));
	    			pass = false;
	    		}
	    		
	    		if (DataValidator.isNull(request.getParameter("confirmPassword"))) {
	    			request.setAttribute("confirmPassword", PropertyReader.getValue("error.require", "Confirm Password"));
	    			pass = false;
	    		}
	    		 if(!OP_PAYMENT.equalsIgnoreCase(op)) {
	    		
	    		if (!request.getParameter("password").equals(
	    				request.getParameter("confirmPassword"))
	    				&& !"".equals(request.getParameter("confirmPassword"))) {
	    			/*ServletUtility.setErrorMessage("Confirm Password did not match",
	    					request);*/
	    			request.setAttribute("confirmPassword", PropertyReader.getValue("error.confirmPassword","Confirm Password"));
	    			pass = false;
	    		}
	    		 }
	        }
	        
	        
	        if(OP_PAYMENT.equalsIgnoreCase(op)) {
	        	pass=true;
	        }

	        log.debug("CustomerCtl validate method end");
	        return pass;
	    }
	 
	 
	 @Override
	    protected BaseBean populateBean(HttpServletRequest request) {

	        log.debug("CustomerCtl Method populatebean Started");

	        CustomerBean bean = new CustomerBean();

	        bean.setId(DataUtility.getLong(request.getParameter("id")));

	        bean.setName(DataUtility.getString(request.getParameter("name")));
	        
	        bean.setAge(DataUtility.getString(request.getParameter("age")));
	        
	        bean.setContectNo(DataUtility.getString(request.getParameter("contact")));

	        bean.setAddress(DataUtility.getString(request.getParameter("address")));
	        
	        HttpSession session=request.getSession();
	        LoginBean lBean=(LoginBean)session.getAttribute("user");
	        
	        if(lBean==null) {
	        	
	        	bean.setLogin(DataUtility.getString(request.getParameter("login")));
	        	
				bean.setPassword(DataUtility.getString(request.getParameter("password")));
		
				bean.setConfirmPassword(DataUtility.getString(request
						.getParameter("confirmPassword")));
	        }else {
	        	
	        	bean.setLogin(lBean.getLogin());
	        	bean.setPassword(lBean.getPassword());
	        }
	        

	        populateDTO(bean, request);

	        log.debug("CustomerCtl Method populatebean Ended");

	        return bean;
	    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("CustomerCtl Method doGet Started");
        System.out.println("in do get");
        String op = DataUtility.getString(request.getParameter("operation"));
        long id = DataUtility.getLong(request.getParameter("id"));

        HttpSession session=request.getSession(true);
        long pid=DataUtility.getLong(request.getParameter("pid"));
        if(pid==0) {
        ServletUtility.redirect(OGDView.PRODUCT_LIST_CTL, request, response);	
        }else {
        session.setAttribute("pid", pid);
       
        
        ServletUtility.setOpration("Add", request);
        CustomerModel model = new CustomerModel();
        if (id > 0 || op != null) {
            CustomerBean bean;
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
        }
        log.debug("CustomerCtl Method doGett Ended");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("CustomerCtl Method doPost Started");
        System.out.println("in do post");
        String op = DataUtility.getString(request.getParameter("operation"));

        // get model
        HttpSession session=request.getSession();
        CustomerModel model = new CustomerModel();

        long id = DataUtility.getLong(request.getParameter("id"));
        ServletUtility.setOpration("Add", request);
       
        if (OP_SAVE.equalsIgnoreCase(op)) {

            CustomerBean bean = (CustomerBean) populateBean(request);
            
            try {
                if (id > 0) {
                	
                    model.update(bean);
                    ServletUtility.setSuccessMessage("Data is successfully Updated",request);
                    ServletUtility.setOpration("Edit", request);
                    ServletUtility.setBean(bean, request);
                } else {
                   session.setAttribute("cusBean", bean);
                  ServletUtility.forward(OGDView.PAYMENT_VIEW, request, response);
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

        	CustomerBean bean = (CustomerBean) populateBean(request);
            try {
                model.delete(bean);
                ServletUtility.redirect(OGDView.CUSTOMER_LIST_CTL, request,response);
                return;

            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            }

        } else if (OP_CANCEL.equalsIgnoreCase(op)) {
        	 
        	ServletUtility.redirect(OGDView.CUSTOMER_LIST_CTL, request, response);


        }else if (OP_RESET.equalsIgnoreCase(op)) {
    		ServletUtility.redirect(OGDView.CUSTOMER_CTL, request, response);
    		return;
    }else if(OP_PAYMENT.equalsIgnoreCase(op)) {
    		CustomerBean cBean=(CustomerBean)session.getAttribute("cusBean");
    		ProductModel pModel=new ProductModel();
    		long  pId=((Long) session.getAttribute("pid")).longValue();
    		
    		
    		try {
    			
      	        LoginBean lBean=(LoginBean)session.getAttribute("user");
    			if(lBean==null) {
    				LoginModel lModel=new LoginModel();
    				LoginBean lbBean=new LoginBean();
    				lbBean.setLogin(cBean.getLogin());
    				lbBean.setPassword(cBean.getPassword());
    				lbBean.setRole(3L);
    				lModel.add(lbBean);
    			}    			
    			ProductBean pBean=pModel.findByPk(pId);
        		cBean.setItemCode(pBean.getItemCode());
        		if(lBean!=null) {
        			cBean.setLogin(lBean.getLogin());	
        		}
        		long pk=model.add(cBean);
        		PaymentModel pyModel=new PaymentModel();
        		PaymentBean pyBean=new PaymentBean();
        		pyBean.setCustomerId(pk);
        		pyBean.setProductId(pId);
        		pyBean.setAmount(pBean.getPrice());
        		pyBean.setLogin(cBean.getLogin());
        		if(lBean!=null) {
        			pyBean.setLogin(lBean.getLogin());	
        		}
        			
        	
        		pyModel.add(pyBean);
        		ServletUtility.setSuccessMessage("Payment Done Successfully", request);
        		ServletUtility.forward(OGDView.SUCCESS_VIEW, request, response);
			} catch (ApplicationException e) {
				 log.error(e);
	                ServletUtility.handleException(e, request, response);
	                return;
			} catch (DuplicateRecordException e) {
	            ServletUtility.setErrorMessage(e.getMessage(), request);
			}
    }
        
        log.debug("CustomerCtl Method doPost Ended");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return OGDView.CUSTOMER_VIEW;
	}

}
