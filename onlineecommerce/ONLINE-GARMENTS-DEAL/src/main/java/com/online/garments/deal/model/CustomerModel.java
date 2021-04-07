package com.online.garments.deal.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.online.garments.deal.bean.CustomerBean;
import com.online.garments.deal.bean.LoginBean;
import com.online.garments.deal.exception.ApplicationException;
import com.online.garments.deal.exception.DatabaseException;
import com.online.garments.deal.exception.DuplicateRecordException;
import com.online.garments.deal.util.JDBCDataSource;

public class CustomerModel  {
	
	private static Logger log = Logger.getLogger(CustomerModel.class);

	
	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM Customer");
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
	
	public CustomerBean findByName(String name) throws ApplicationException {
		log.debug("Model findBy Name Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM Customer WHERE name=?");
		CustomerBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CustomerBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setAge(rs.getString(3));
				bean.setAddress(rs.getString(4));
				bean.setContectNo(rs.getString(5));
				bean.setProductChoice(rs.getString(6));
				bean.setItemCode(rs.getString(7));
				bean.setMultipleItems(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
				bean.setLogin(rs.getString(13));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Customer by Name");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findBy Name End");
		return bean;
	}
	
	
	public CustomerBean findByContactNo(String contact) throws ApplicationException {
		log.debug("Model findBy contact Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM Customer WHERE contactNo=?");
		CustomerBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, contact);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CustomerBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setAge(rs.getString(3));
				bean.setAddress(rs.getString(4));
				bean.setContectNo(rs.getString(5));
				bean.setProductChoice(rs.getString(6));
				bean.setItemCode(rs.getString(7));
				bean.setMultipleItems(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
				bean.setLogin(rs.getString(13));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Customer by contact");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findBy contact End");
		return bean;
	}
	
	
	public CustomerBean findByPk(long id) throws ApplicationException {
		log.debug("Model findBy PK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM Customer WHERE ID=?");
		CustomerBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CustomerBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setAge(rs.getString(3));
				bean.setAddress(rs.getString(4));
				bean.setContectNo(rs.getString(5));
				bean.setProductChoice(rs.getString(6));
				bean.setItemCode(rs.getString(7));
				bean.setMultipleItems(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
				bean.setLogin(rs.getString(13));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Customer by Pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findBy Pk End");
		return bean;
	}
	
	
	public long add(CustomerBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;
		CustomerBean duplicataLogin = findByContactNo(bean.getContectNo());

		if (duplicataLogin != null) {
			throw new DuplicateRecordException("Contact No Id already exists");
		}
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();

			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Customer VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getAge());
			pstmt.setString(4, bean.getAddress());
			pstmt.setString(5, bean.getContectNo());
			pstmt.setString(6, bean.getProductChoice());
			pstmt.setString(7, bean.getItemCode());
			pstmt.setString(8, bean.getMultipleItems());
			pstmt.setString(9, bean.getCreatedBy());
			pstmt.setString(10, bean.getModifiedBy());
			pstmt.setTimestamp(11, bean.getCreatedDatetime());
			pstmt.setTimestamp(12, bean.getModifiedDatetime());
			pstmt.setString(13,bean.getLogin());
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
			throw new ApplicationException("Exception : Exception in add Customer");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}
	
	
	public void delete(CustomerBean bean) throws ApplicationException {
		log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Customer WHERE ID=?");
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
	
	
	
	public void update(CustomerBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;
		CustomerBean duplicataCustomer = findByContactNo(bean.getContectNo());

		// Check if updated Role already exist
		if (duplicataCustomer != null && duplicataCustomer.getId() != bean.getId()) {
			throw new DuplicateRecordException("Contact No already exists");
		}
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE Customer SET Name=?,age=?,Address=?,ContectNo=?,ProductChoice=?,ItemCode=?,MultipleItems=?,CREATEDBY=?,MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDDATETIME=?,Login=? WHERE ID=?");
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getAge());
			pstmt.setString(3, bean.getAddress());
			pstmt.setString(4, bean.getContectNo());
			pstmt.setString(5, bean.getProductChoice());
			pstmt.setString(6, bean.getItemCode());
			pstmt.setString(7, bean.getMultipleItems());
			pstmt.setString(8, bean.getCreatedBy());
			pstmt.setString(9, bean.getModifiedBy());
			pstmt.setTimestamp(10, bean.getCreatedDatetime());
			pstmt.setTimestamp(11, bean.getModifiedDatetime());
			pstmt.setString(12,bean.getLogin());
			pstmt.setLong(13, bean.getId());
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
			throw new ApplicationException("Exception in updating Customer ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}
	
	
	public List search(CustomerBean bean) throws ApplicationException {
        return search(bean, 0, 0);
    }

   
    public List search(CustomerBean bean, int pageNo, int pageSize)
            throws ApplicationException {
        log.debug("Model search Started");
        StringBuffer sql = new StringBuffer("SELECT * FROM Customer WHERE 1=1");
        if (bean != null) {
            if (bean.getId() > 0) {
                sql.append(" AND id = " + bean.getId());
            }
            if (bean.getContectNo() != null && bean.getContectNo().length() > 0) {
				sql.append(" AND ContectNo LIKE '" + bean.getContectNo() + "%'");
            }
            
            if (bean.getItemCode() != null && bean.getItemCode().length() > 0) {
				sql.append(" AND ItemCode LIKE '" + bean.getItemCode() + "%'");
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
                bean = new CustomerBean();
                bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setAge(rs.getString(3));
				bean.setAddress(rs.getString(4));
				bean.setContectNo(rs.getString(5));
				bean.setProductChoice(rs.getString(6));
				bean.setItemCode(rs.getString(7));
				bean.setMultipleItems(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
				bean.setLogin(rs.getString(13));
                list.add(bean);
            }
            rs.close();
        } catch (Exception e) {
           log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in search Customer");
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
        StringBuffer sql = new StringBuffer("select * from Customer");
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
                CustomerBean bean = new CustomerBean();
                bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setAge(rs.getString(3));
				bean.setAddress(rs.getString(4));
				bean.setContectNo(rs.getString(5));
				bean.setProductChoice(rs.getString(6));
				bean.setItemCode(rs.getString(7));
				bean.setMultipleItems(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
				bean.setLogin(rs.getString(13));
                list.add(bean);
            }
            rs.close();
        } catch (Exception e) {
          //  log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting list of Customer");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model list End");
        return list;

    }

}
