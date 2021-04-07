package com.online.garments.deal.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.online.garments.deal.bean.PaymentBean;
import com.online.garments.deal.bean.ProductBean;
import com.online.garments.deal.exception.ApplicationException;
import com.online.garments.deal.exception.DatabaseException;
import com.online.garments.deal.exception.DuplicateRecordException;
import com.online.garments.deal.util.JDBCDataSource;

public class PaymentModel {
	
private static Logger log = Logger.getLogger(PaymentModel.class);

	
	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM payment");
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
	
	public Integer nextPaymentId() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(paymentId) FROM payment");
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
	
	public PaymentBean findByPk(long Id) throws ApplicationException {
		log.debug("Model findBy Id Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM payment WHERE Id=? ");
		PaymentBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, Id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new PaymentBean();
				bean.setId(rs.getLong(1));
				bean.setPaymentId(rs.getLong(2));
				bean.setCustomerId(rs.getLong(3));
				bean.setProductId(rs.getLong(4));
				bean.setPaymentDate(rs.getDate(5));
				bean.setAmount(rs.getDouble(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));
				bean.setLogin(rs.getString(11));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Payment by Id");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findBy ID End");
		return bean;
	}
	
	public PaymentBean findByProductAndCustomer(long productId,long customerId) throws ApplicationException {
		log.debug("Model findBy Id Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM payment WHERE productId=? and customerId=? ");
		PaymentBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, productId);
			pstmt.setLong(2, customerId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new PaymentBean();
				bean.setId(rs.getLong(1));
				bean.setPaymentId(rs.getLong(2));
				bean.setCustomerId(rs.getLong(3));
				bean.setProductId(rs.getLong(4));
				bean.setPaymentDate(rs.getDate(5));
				bean.setAmount(rs.getDouble(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));
				bean.setLogin(rs.getString(11));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Customer And product by Id");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findBy ID End");
		return bean;
	}
	
	
	public long add(PaymentBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;
		int paymentId = 0;
		PaymentBean duplicataLogin = findByProductAndCustomer(bean.getPaymentId(), bean.getCustomerId());

		if (duplicataLogin != null) {
			throw new DuplicateRecordException("Payment Done Already ");
		}
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			paymentId=nextPaymentId();

			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO payment VALUES(?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setLong(2, paymentId);
			pstmt.setLong(3, bean.getCustomerId());
			pstmt.setLong(4, bean.getProductId());
			pstmt.setDate(5, new java.sql.Date(new java.util.Date().getTime()));
			pstmt.setDouble(6, bean.getAmount());
			pstmt.setString(7, bean.getCreatedBy());
			pstmt.setString(8, bean.getModifiedBy());
			pstmt.setTimestamp(9, bean.getCreatedDatetime());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());
			pstmt.setString(11,bean.getLogin());
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
			throw new ApplicationException("Exception : Exception in add Payment");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}
	
	
	public void delete(PaymentBean bean) throws ApplicationException {
		log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Payment WHERE ID=?");
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
			throw new ApplicationException("Exception : Exception in delete Payment");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Started");
	}
	
	
	public void update(PaymentBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;
		PaymentBean duplicataCustomer = findByProductAndCustomer(bean.getPaymentId(), bean.getCustomerId());

		// Check if updated Role already exist
		if (duplicataCustomer != null && duplicataCustomer.getId() != bean.getId()) {
			throw new DuplicateRecordException("Payment is already Done");
		}
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE payment SET PaymentId=?,CustomerId=?,ProductId=?,paymentDate=?,Amount=?,CREATEDBY=?,MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDDATETIME=?,login=? WHERE ID=?");
			pstmt.setLong(1, bean.getPaymentId());
			pstmt.setLong(2, bean.getCustomerId());
			pstmt.setLong(3, bean.getProductId());
			pstmt.setDate(4, new java.sql.Date( bean.getPaymentDate().getTime()));
			pstmt.setDouble(5, bean.getAmount());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());
			pstmt.setString(10, bean.getLogin());
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
			throw new ApplicationException("Exception in updating Payment");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}
	
	public List search(PaymentBean bean) throws ApplicationException {
        return search(bean, 0, 0);
    }

   
    public List search(PaymentBean bean, int pageNo, int pageSize)
            throws ApplicationException {
        log.debug("Model search Started");
        StringBuffer sql = new StringBuffer("SELECT * FROM Payment WHERE 1=1");
        if (bean != null) {
            if (bean.getId() > 0) {
                sql.append(" AND id = " + bean.getId());
            }
            if (bean.getPaymentId() > 0) {
                sql.append(" AND PaymentId = " + bean.getPaymentId());
            }
            if (bean.getCustomerId() > 0) {
                sql.append(" AND CustomerId = " + bean.getCustomerId());
            }
            
            if (bean.getProductId() > 0) {
                sql.append(" AND ProductId = " + bean.getProductId());
            }
            
            if (bean.getLogin() != null && bean.getLogin().length() > 0) {
				sql.append(" AND Login LIKE '" + bean.getLogin() + "%'");
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
                bean = new PaymentBean();
            	bean.setId(rs.getLong(1));
				bean.setPaymentId(rs.getLong(2));
				bean.setCustomerId(rs.getLong(3));
				bean.setProductId(rs.getLong(4));
				bean.setPaymentDate(rs.getDate(5));
				bean.setAmount(rs.getDouble(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));
				bean.setLogin(rs.getString(11));
                list.add(bean);
            }
            rs.close();
        } catch (Exception e) {
           log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in search Payment");
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
        StringBuffer sql = new StringBuffer("select * from Payment");
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
                PaymentBean bean = new PaymentBean();
                bean.setId(rs.getLong(1));
				bean.setPaymentId(rs.getLong(2));
				bean.setCustomerId(rs.getLong(3));
				bean.setProductId(rs.getLong(4));
				bean.setPaymentDate(rs.getDate(5));
				bean.setAmount(rs.getDouble(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));
				bean.setLogin(rs.getString(11));
                list.add(bean);
            }
            rs.close();
        } catch (Exception e) {
          //  log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting list of Payment");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model list End");
        return list;

    }

}
