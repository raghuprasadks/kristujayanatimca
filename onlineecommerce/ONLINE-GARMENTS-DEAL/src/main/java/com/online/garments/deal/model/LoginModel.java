package com.online.garments.deal.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.online.garments.deal.bean.LoginBean;
import com.online.garments.deal.exception.ApplicationException;
import com.online.garments.deal.exception.DatabaseException;
import com.online.garments.deal.exception.DuplicateRecordException;
import com.online.garments.deal.util.DataUtility;
import com.online.garments.deal.util.JDBCDataSource;

public class LoginModel {

	private static Logger log = Logger.getLogger(LoginModel.class);

	/**
	 * Find next PK of Role
	 * 
	 * @throws DatabaseException
	 */
	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM Login");
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

	public LoginBean findByLogin(String login) throws ApplicationException {
		log.debug("Model findBy LoginId Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM Login WHERE Login=?");
		LoginBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, login);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new LoginBean();
				bean.setId(rs.getLong(1));
				bean.setLogin(rs.getString(2));
				bean.setPassword(rs.getString(3));
				bean.setCreatedBy(rs.getString(4));
				bean.setModifiedBy(rs.getString(5));
				bean.setCreatedDatetime(rs.getTimestamp(6));
				bean.setModifiedDatetime(rs.getTimestamp(7));
				bean.setRole(rs.getLong(8));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by LoginId");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findBy EmailId End");
		return bean;
	}

	public LoginBean findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM Login WHERE ID=?");
		LoginBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new LoginBean();
				bean.setId(rs.getLong(1));
				bean.setLogin(rs.getString(2));
				bean.setPassword(rs.getString(3));
				bean.setCreatedBy(rs.getString(4));
				bean.setModifiedBy(rs.getString(5));
				bean.setCreatedDatetime(rs.getTimestamp(6));
				bean.setModifiedDatetime(rs.getTimestamp(7));
				bean.setRole(rs.getLong(8));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Login by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return bean;
	}

	public long add(LoginBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;
		LoginBean duplicataLogin = findByLogin(bean.getLogin());

		if (duplicataLogin != null) {
			throw new DuplicateRecordException("Login Id already exists");
		}
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();

			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Login VALUES(?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getLogin());
			pstmt.setString(3, bean.getPassword());
			pstmt.setString(4, bean.getCreatedBy());
			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setTimestamp(6, DataUtility.getCurrentTimestamp());
			pstmt.setTimestamp(7, DataUtility.getCurrentTimestamp());
			pstmt.setLong(8,bean.getRole());
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
			throw new ApplicationException("Exception : Exception in add Login");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}

	public void delete(LoginBean bean) throws ApplicationException {
		log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Login WHERE ID=?");
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
			throw new ApplicationException("Exception : Exception in delete Role");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Started");
	}

	public void update(LoginBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;
		LoginBean duplicataRole = findByLogin(bean.getLogin());

		// Check if updated Role already exist
		if (duplicataRole != null && duplicataRole.getId() != bean.getId()) {
			throw new DuplicateRecordException("Login Id already exists");
		}
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE login SET Login=?,Password=?,CREATEDBY=?,MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDDATETIME=?,Role=? WHERE ID=?");
			pstmt.setString(1, bean.getLogin());
			pstmt.setString(2, bean.getPassword());
			pstmt.setString(3, bean.getCreatedBy());
			pstmt.setString(4, bean.getModifiedBy());
			pstmt.setTimestamp(5, bean.getCreatedDatetime());
			pstmt.setTimestamp(6, bean.getModifiedDatetime());
			pstmt.setLong(7,bean.getRole());
			pstmt.setLong(8, bean.getId());
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
			throw new ApplicationException("Exception in updating Login ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}
	
	public List search(LoginBean bean) throws ApplicationException {
        return search(bean, 0, 0);
    }

   
    public List search(LoginBean bean, int pageNo, int pageSize)
            throws ApplicationException {
        log.debug("Model search Started");
        StringBuffer sql = new StringBuffer("SELECT * FROM Login WHERE 1=1");
        if (bean != null) {
            if (bean.getId() > 0) {
                sql.append(" AND id = " + bean.getId());
            }
            if (bean.getLogin() != null && bean.getLogin().length() > 0) {
				sql.append(" AND LOGIN LIKE '" + bean.getLogin() + "%'");
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
                bean = new LoginBean();
                bean.setId(rs.getLong(1));
                bean.setLogin(rs.getString(2));
                bean.setPassword(rs.getString(3));
                bean.setCreatedBy(rs.getString(4));
                bean.setModifiedBy(rs.getString(5));
                bean.setCreatedDatetime(rs.getTimestamp(6));
                bean.setModifiedDatetime(rs.getTimestamp(7));
                bean.setRole(rs.getLong(8));
                list.add(bean);
            }
            rs.close();
        } catch (Exception e) {
           log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in search Login");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model search End");
        return list;
    }
    
    
    public List list() throws ApplicationException {
        return list(0, 0);
    }

    /**
     * Get List of Role with pagination
     * 
     * @return list : List of Role
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws DatabaseException
     *  @throws ApplicationException
     */
    public List list(int pageNo, int pageSize) throws ApplicationException {
        log.debug("Model list Started");
        ArrayList list = new ArrayList();
        StringBuffer sql = new StringBuffer("select * from Login");
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
                LoginBean bean = new LoginBean();
                bean.setId(rs.getLong(1));
                bean.setLogin(rs.getString(2));
                bean.setPassword(rs.getString(3));
                bean.setCreatedBy(rs.getString(4));
                bean.setModifiedBy(rs.getString(5));
                bean.setCreatedDatetime(rs.getTimestamp(6));
                bean.setModifiedDatetime(rs.getTimestamp(7));
                bean.setRole(rs.getLong(8));
                list.add(bean);
            }
            rs.close();
        } catch (Exception e) {
          //  log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting list of Login");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model list End");
        return list;

    }
    
    public LoginBean authenticate(String login, String password) throws ApplicationException {
		log.debug("Model authenticate Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM Login WHERE LOGIN = ? AND PASSWORD = ?");
		LoginBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, login);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new LoginBean();
				bean.setId(rs.getLong(1));
                bean.setLogin(rs.getString(2));
                bean.setPassword(rs.getString(3));
                bean.setCreatedBy(rs.getString(4));
                bean.setModifiedBy(rs.getString(5));
                bean.setCreatedDatetime(rs.getTimestamp(6));
                bean.setModifiedDatetime(rs.getTimestamp(7));
                bean.setRole(rs.getLong(8));
				System.out.println("Usermodel here");
			}
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in get Authenticate  Login");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model authenticate End");
		return bean;
	}

}
