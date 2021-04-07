package com.online.garments.deal.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.online.garments.deal.bean.CustomerBean;
import com.online.garments.deal.bean.ManagerBean;
import com.online.garments.deal.exception.ApplicationException;
import com.online.garments.deal.exception.DatabaseException;
import com.online.garments.deal.exception.DuplicateRecordException;
import com.online.garments.deal.util.JDBCDataSource;

public class ManagerModel {
	
private static Logger log = Logger.getLogger(ManagerModel.class);

	
	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM Manager");
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
	
	public ManagerBean findByLogin(String login) throws ApplicationException {
		log.debug("Model findBy Name Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM manager WHERE Login=?");
		ManagerBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, login);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new ManagerBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setEmailId(rs.getString(3));
				bean.setContactNo(rs.getString(4));
				bean.setAddress(rs.getString(5));
				bean.setLogin(rs.getString(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Manager by Login");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findBy Name End");
		return bean;
	}
	
	
	public ManagerBean findByPk(long id) throws ApplicationException {
		log.debug("Model findBy PK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM Manager WHERE ID=?");
		ManagerBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new ManagerBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setEmailId(rs.getString(3));
				bean.setContactNo(rs.getString(4));
				bean.setAddress(rs.getString(5));
				bean.setLogin(rs.getString(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Manager by Pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findBy Pk End");
		return bean;
	}
	
	
	
	public long add(ManagerBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;
		ManagerBean duplicataLogin = findByLogin(bean.getLogin());

		if (duplicataLogin != null) {
			throw new DuplicateRecordException("Login Id already exists");
		}
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();

			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Manager VALUES(?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getEmailId());
			pstmt.setString(4, bean.getContactNo());
			pstmt.setString(5, bean.getAddress());
			pstmt.setString(6, bean.getLogin());
			pstmt.setString(7, bean.getCreatedBy());
			pstmt.setString(8, bean.getModifiedBy());
			pstmt.setTimestamp(9, bean.getCreatedDatetime());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());
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
			throw new ApplicationException("Exception : Exception in add Manager");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}
	
	
	public void delete(ManagerBean bean) throws ApplicationException {
		log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Manager WHERE ID=?");
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
			throw new ApplicationException("Exception : Exception in delete Manager");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Started");
	}
	
	
	
	public void update(ManagerBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;
		ManagerBean duplicataCustomer = findByLogin(bean.getLogin());

		// Check if updated Role already exist
		if (duplicataCustomer != null && duplicataCustomer.getId() != bean.getId()) {
			throw new DuplicateRecordException("Login Id already exists");
		}
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE Manager SET Name=?,EmailId=?,ContactNo=?,Address=?,Login=?,CREATEDBY=?,MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDDATETIME=? WHERE ID=?");
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getEmailId());
			pstmt.setString(3, bean.getContactNo());
			pstmt.setString(4, bean.getAddress());
			pstmt.setString(5, bean.getLogin());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());
			pstmt.setLong(10, bean.getId());
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
			throw new ApplicationException("Exception in updating Manager ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}
	
	
	public List search(ManagerBean bean) throws ApplicationException {
        return search(bean, 0, 0);
    }

   
    public List search(ManagerBean bean, int pageNo, int pageSize)
            throws ApplicationException {
        log.debug("Model search Started");
        StringBuffer sql = new StringBuffer("SELECT * FROM Manager WHERE 1=1");
        if (bean != null) {
            if (bean.getId() > 0) {
                sql.append(" AND id = " + bean.getId());
            }
            if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" AND Name LIKE '" + bean.getName() + "%'");
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
                bean = new ManagerBean();
                bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setEmailId(rs.getString(3));
				bean.setContactNo(rs.getString(4));
				bean.setAddress(rs.getString(5));
				bean.setLogin(rs.getString(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));
                list.add(bean);
            }
            rs.close();
        } catch (Exception e) {
           log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in search Manager");
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
        StringBuffer sql = new StringBuffer("select * from Manager");
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
                ManagerBean bean = new ManagerBean();
                bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setEmailId(rs.getString(3));
				bean.setContactNo(rs.getString(4));
				bean.setAddress(rs.getString(5));
				bean.setLogin(rs.getString(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));
                list.add(bean);
            }
            rs.close();
        } catch (Exception e) {
          //  log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting list of Manager");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model list End");
        return list;

    }

}
