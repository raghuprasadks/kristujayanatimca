package com.online.garments.deal.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.online.garments.deal.bean.CustomerBean;
import com.online.garments.deal.bean.ProductBean;
import com.online.garments.deal.exception.ApplicationException;
import com.online.garments.deal.exception.DatabaseException;
import com.online.garments.deal.exception.DuplicateRecordException;
import com.online.garments.deal.util.JDBCDataSource;

public class ProductModel {
	
private static Logger log = Logger.getLogger(ProductModel.class);

	
	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM Product");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return pk + 1;
	}
	
	
	public ProductBean findByName(String name) throws ApplicationException {
		log.debug("Model findByName  Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM Product WHERE name=?");
		ProductBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new ProductBean();
				bean.setId(rs.getLong(1));
				bean.setProductName(rs.getString(2));
				bean.setProductQuantity(rs.getString(3));
				bean.setProductChoice(rs.getString(4));
				bean.setItemCode(rs.getString(5));
				bean.setImage(rs.getString(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));
				bean.setPrice(rs.getDouble(11));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Product by Name");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByName  End");
		return bean;
	}
	
	public ProductBean findByItemCode(String code) throws ApplicationException {
		log.debug("Model findByName  Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM Product WHERE ItemCode=?");
		ProductBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, code);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new ProductBean();
				bean.setId(rs.getLong(1));
				bean.setProductName(rs.getString(2));
				bean.setProductQuantity(rs.getString(3));
				bean.setProductChoice(rs.getString(4));
				bean.setItemCode(rs.getString(5));
				bean.setImage(rs.getString(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));
				bean.setPrice(rs.getDouble(11));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Product by Code");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByName  End");
		return bean;
	}
	
	public ProductBean findByNameAndCode(String name,String code) throws ApplicationException {
		log.debug("Model findByNameCode Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM Product WHERE Productname=? and itemCode=?");
		ProductBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			pstmt.setString(2, code);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new ProductBean();
				bean.setId(rs.getLong(1));
				bean.setProductName(rs.getString(2));
				bean.setProductQuantity(rs.getString(3));
				bean.setProductChoice(rs.getString(4));
				bean.setItemCode(rs.getString(5));
				bean.setImage(rs.getString(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));
				bean.setPrice(rs.getDouble(11));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Product by NameAndCode");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findBy Name End");
		return bean;
	}
	
	public ProductBean findByPk(long Id) throws ApplicationException {
		log.debug("Model findBy Id Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM Product WHERE Id=? ");
		ProductBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, Id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new ProductBean();
				bean.setId(rs.getLong(1));
				bean.setProductName(rs.getString(2));
				bean.setProductQuantity(rs.getString(3));
				bean.setProductChoice(rs.getString(4));
				bean.setItemCode(rs.getString(5));
				bean.setImage(rs.getString(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));
				bean.setPrice(rs.getDouble(11));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Product by Id");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findBy ID End");
		return bean;
	}
	
	public long add(ProductBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;
		ProductBean duplicataLogin = findByNameAndCode(bean.getProductName(), bean.getItemCode());

		if (duplicataLogin != null) {
			throw new DuplicateRecordException(" Product is already exists");
		}
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();

			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO product VALUES(?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getProductName());
			pstmt.setString(3, bean.getProductQuantity());
			pstmt.setString(4, bean.getProductChoice());
			pstmt.setString(5, bean.getItemCode());
			pstmt.setString(6, bean.getImage());
			pstmt.setString(7, bean.getCreatedBy());
			pstmt.setString(8, bean.getModifiedBy());
			pstmt.setTimestamp(9, bean.getCreatedDatetime());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());
			pstmt.setDouble(11, bean.getPrice());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Product");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}
	
	public void delete(ProductBean bean) throws ApplicationException {
		log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Product WHERE ID=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			// log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete Customer");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Started");
	}
	
	public void update(ProductBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;
		ProductBean duplicataCustomer = findByNameAndCode(bean.getProductName(), bean.getItemCode());

		// Check if updated Role already exist
		if (duplicataCustomer != null && duplicataCustomer.getId() != bean.getId()) {
			throw new DuplicateRecordException("Product is already exists");
		}
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE Product SET ProductName=?,ProductQuantity=?,ProductChoice=?,ItemCode=?,Image=?,CREATEDBY=?,MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDDATETIME=?,price=? WHERE ID=?");
			pstmt.setString(1, bean.getProductName());
			pstmt.setString(2, bean.getProductQuantity());
			pstmt.setString(3, bean.getProductChoice());
			pstmt.setString(4, bean.getItemCode());
			pstmt.setString(5, bean.getImage());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());
			pstmt.setDouble(10, bean.getPrice());
			pstmt.setLong(11, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating Product ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}
	
	
	public List search(ProductBean bean) throws ApplicationException {
        return search(bean, 0, 0);
    }

   
    public List search(ProductBean bean, int pageNo, int pageSize)
            throws ApplicationException {
        log.debug("Model search Started");
        StringBuffer sql = new StringBuffer("SELECT * FROM Product WHERE 1=1");
        if (bean != null) {
            if (bean.getId() > 0) {
                sql.append(" AND id = " + bean.getId());
            }
            if (bean.getProductName() != null && bean.getProductName().length() > 0) {
				sql.append(" AND ProductName LIKE '" + bean.getProductName() + "%'");
            }
            
            if (bean.getItemCode() != null && bean.getItemCode().length() > 0) {
				sql.append(" AND ItemCode LIKE '" + bean.getItemCode() + "%'");
            }
            
            
           
        }

        // if page size is greater than zero then apply pagination
        if (pageSize > 0) {
            // Calculate start record index
            pageNo = (pageNo - 1) * pageSize;
            sql.append(" Limit " + pageNo + ", " + pageSize);
            // sql.append(" limit " + pageNo + "," + pageSize);
        }
        ArrayList list = new ArrayList();
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                bean = new ProductBean();
                bean.setId(rs.getLong(1));
				bean.setProductName(rs.getString(2));
				bean.setProductQuantity(rs.getString(3));
				bean.setProductChoice(rs.getString(4));
				bean.setItemCode(rs.getString(5));
				bean.setImage(rs.getString(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));
				bean.setPrice(rs.getDouble(11));
                list.add(bean);
            }
            rs.close();
        } catch (Exception e) {
           log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in search Product");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model search End");
        return list;
    }
    
    
    public List list() throws ApplicationException {
        return list(0, 0);
    }

    
    public List list(int pageNo, int pageSize) throws ApplicationException {
        log.debug("Model list Started");
        ArrayList list = new ArrayList();
        StringBuffer sql = new StringBuffer("select * from Product");
        // if page size is greater than zero then apply pagination
        if (pageSize > 0) {
            // Calculate start record index
            pageNo = (pageNo - 1) * pageSize;
            sql.append(" limit " + pageNo + "," + pageSize);
        }
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ProductBean bean = new ProductBean();
                bean.setId(rs.getLong(1));
				bean.setProductName(rs.getString(2));
				bean.setProductQuantity(rs.getString(3));
				bean.setProductChoice(rs.getString(4));
				bean.setItemCode(rs.getString(5));
				bean.setImage(rs.getString(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));
				bean.setPrice(rs.getDouble(11));
                list.add(bean);
            }
            rs.close();
        } catch (Exception e) {
          //  log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting list of Product");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model list End");
        return list;

    }

}
