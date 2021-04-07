package com.online.garments.deal.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import com.online.garments.deal.bean.BaseBean;
import com.online.garments.deal.bean.LoginBean;
import com.online.garments.deal.bean.ManagerBean;
import com.online.garments.deal.bean.ProductBean;
import com.online.garments.deal.exception.ApplicationException;
import com.online.garments.deal.exception.DuplicateRecordException;
import com.online.garments.deal.model.LoginModel;
import com.online.garments.deal.model.ManagerModel;
import com.online.garments.deal.model.ProductModel;
import com.online.garments.deal.util.DataUtility;
import com.online.garments.deal.util.DataValidator;
import com.online.garments.deal.util.PropertyReader;
import com.online.garments.deal.util.ServletUtility;

/**
 * Servlet implementation class ProductCtl
 */
@WebServlet(name = "ProductCtl", urlPatterns = { "/ctl/ProductCtl" })
@MultipartConfig(maxFileSize = 16177215)
public class ProductCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(ProductCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("ProductCtl validate method start");
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Product Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("code"))) {
			request.setAttribute("code", PropertyReader.getValue("error.require", "Item Code"));
			pass = false;

		}
		if (DataValidator.isNull(request.getParameter("quantity"))) {
			request.setAttribute("quantity", PropertyReader.getValue("error.require", "Quantity "));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("choice"))) {
			request.setAttribute("choice", PropertyReader.getValue("error.require", "Choice"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("price"))) {
			request.setAttribute("price", PropertyReader.getValue("error.require", "Price"));
			pass = false;
		}else if (DataValidator.isNotNull(request.getParameter("price"))
				&& !DataValidator.isDouble(request.getParameter("price"))) {
			request.setAttribute("price", PropertyReader.getValue("error.double", "Price"));
			pass = false;
		}

		Part part = null;
		try {
			part = request.getPart("photo");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String imgName = Paths.get(part.getSubmittedFileName()).getFileName().toString();

		if (DataValidator.isNull(imgName)) {
			request.setAttribute("photo", PropertyReader.getValue("error.require", "Image"));
			pass = false;
		}

		log.debug("ProductCtl validate method end");
		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("ProductCtl Method populatebean Started");

		ProductBean bean = new ProductBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setProductName(DataUtility.getString(request.getParameter("name")));

		bean.setProductChoice(DataUtility.getString(request.getParameter("choice")));

		bean.setProductQuantity(DataUtility.getString(request.getParameter("quantity")));

		bean.setItemCode(DataUtility.getString(request.getParameter("code")));
		
		bean.setPrice(DataUtility.getDouble(request.getParameter("price")));

		populateDTO(bean, request);

		log.debug("ProductCtl Method populatebean Ended");

		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("ProductCtl Method doGet Started");
		System.out.println("in do get");
		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));

		// get model
		ServletUtility.setOpration("Add", request);
		ProductModel model = new ProductModel();
		if (id > 0 || op != null) {
			ProductBean bean;
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
		log.debug("ProductCtl Method doGett Ended");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("ProductCtl Method doPost Started");
		System.out.println("in do post");
		String op = DataUtility.getString(request.getParameter("operation"));

		// get model

		ProductModel model = new ProductModel();

		long id = DataUtility.getLong(request.getParameter("id"));
		ServletUtility.setOpration("Add", request);

		if (OP_SAVE.equalsIgnoreCase(op)) {

			ProductBean bean = (ProductBean) populateBean(request);
			bean.setImage(SubImage(request, response));
			try {
				if (id > 0) {
					model.update(bean);
					ServletUtility.setSuccessMessage("Data is successfully Updated", request);
					ServletUtility.setOpration("Edit", request);
					ServletUtility.setBean(bean, request);
				} else {
					long pk = model.add(bean);
					ServletUtility.setSuccessMessage("Data is successfully saved", request);
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

			ProductBean bean = (ProductBean) populateBean(request);
			try {
				model.delete(bean);
				ServletUtility.redirect(OGDView.PRODUCT_LIST_CTL, request, response);
				return;

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(OGDView.PRODUCT_LIST_CTL, request, response);

		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(OGDView.PRODUCT_CTL, request, response);
			return;
		}

		log.debug("ProductCtl Method doPost Ended");
	}

	protected String SubImage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("");
		String savePath = DataUtility.getString(PropertyReader.getValue("imagePath"));

		File fileSaveDir = new File(savePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}

		Part part = request.getPart("photo");
		String fileName = extractFileName(part);
		part.write(savePath + File.separator + fileName);
		String filePath = fileName;
		System.out.println("Path----" + savePath + File.separator + fileName);

		return fileName;

	}

	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return OGDView.PRODUCT_VIEW;
	}

}
