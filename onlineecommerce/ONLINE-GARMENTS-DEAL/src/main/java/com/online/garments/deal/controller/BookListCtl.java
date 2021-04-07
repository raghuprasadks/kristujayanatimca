package com.online.garments.deal.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.online.garments.deal.bean.BaseBean;
import com.online.garments.deal.bean.LoginBean;
import com.online.garments.deal.bean.PaymentBean;
import com.online.garments.deal.exception.ApplicationException;
import com.online.garments.deal.model.PaymentModel;
import com.online.garments.deal.util.DataUtility;
import com.online.garments.deal.util.PropertyReader;
import com.online.garments.deal.util.ServletUtility;

/**
 * Servlet implementation class BookListCtl
 */
@WebServlet(name = "BookListCtl", urlPatterns = { "/ctl/BookListCtl" })
public class BookListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log = Logger.getLogger(PaymentListCtl.class);

	/**
	 * Populates bean object from request parameters
	 * 
	 * @param request
	 * @return
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("PaymentListCtl populateBean method start");
		PaymentBean bean = new PaymentBean();
		bean.setProductId(DataUtility.getLong(request.getParameter("Pro")));
 		log.debug("PaymentListCtl populateBean method end");
		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("PaymentListCtl doGet method start");
		List list = null;
		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
		
		long idd=DataUtility.getLong(request.getParameter("idd"));
		
		PaymentModel model = new PaymentModel();
		PaymentBean bean = (PaymentBean) populateBean(request);
		try {
			
			if(idd>0) {
				PaymentBean pBean=new PaymentBean();
				pBean.setId(idd);
				model.delete(pBean);
				ServletUtility.setSuccessMessage("Data Deleted Succsessfully", request);
			}
			
			HttpSession session=request.getSession();
			LoginBean lBean=(LoginBean)session.getAttribute("user");
			if(lBean.getRole()==3) {
				bean.setLogin(lBean.getLogin());
			}
			
			list = model.search(bean, pageNo, pageSize);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No Record Found", request);
			}
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);

		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			e.printStackTrace();
			return;
		}
		log.debug("PaymentListCtl doGet method end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("PaymentListCtl doPost method start");
		List list = null;

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));

		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;

		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		PaymentBean bean = (PaymentBean) populateBean(request);

		PaymentModel model = new PaymentModel();
		String[] ids = request.getParameterValues("ids");
		String op = DataUtility.getString(request.getParameter("operation"));

		if (OP_SEARCH.equalsIgnoreCase(op) || OP_NEXT.equalsIgnoreCase(op) || OP_PREVIOUS.equalsIgnoreCase(op)) {

			if (OP_SEARCH.equalsIgnoreCase(op)) {

				pageNo = 1;

			} else if (OP_NEXT.equalsIgnoreCase(op)) {

				pageNo++;
			} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {

				pageNo--;
			}
		} else if (OP_NEW.equalsIgnoreCase(op)) {
			ServletUtility.redirect(OGDView.PRODUCT_LIST_CTL, request, response);
			return;
		} else if (OP_DELETE.equalsIgnoreCase(op)) {
			pageNo = 1;
			if (ids != null && ids.length > 0) {
				PaymentBean deletebean = new PaymentBean();
				for (String id : ids) {
					deletebean.setId(DataUtility.getInt(id));
					try {
						model.delete(deletebean);
					} catch (ApplicationException e) {
						ServletUtility.handleException(e, request, response);
						e.printStackTrace();
						return;
					}
				}
				ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
			} else {
				ServletUtility.setErrorMessage("Select at least one record", request);
			}
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(OGDView.BOOK_LIST_CTL, request, response);
			return;

		}

		try {
			
			HttpSession session=request.getSession();
			LoginBean lBean=(LoginBean)session.getAttribute("user");
			if(lBean.getRole()==3) {
				bean.setLogin(lBean.getLogin());
			}

			list = model.search(bean, pageNo, pageSize);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("NO Record Found", request);
			}
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);
		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			e.printStackTrace();
			return;
		}

		log.debug("PaymentListCtl doPost method end");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return OGDView.BOOK_LIST_VIEW;
	}

}
