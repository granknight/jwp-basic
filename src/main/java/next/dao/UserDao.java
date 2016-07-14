package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.jdbc.ConnectionManager;
import next.model.User;

public class UserDao {

    public Connection getConnection() throws SQLException{
        return ConnectionManager.getConnection();
    }

	public PreparedStatement getPrepareStatement(Connection con, String sql) throws SQLException{

        PreparedStatement pstmt = null;
        pstmt = con.prepareStatement(sql);
        return pstmt;
    }

//    public void closeResource(Connection con, PreparedStatement pstmt, ResultSet set){
//        try {
//            if(set != null){
//                set.close();
//            }
//            if (pstmt != null) {
//                pstmt.close();
//            }
//
//            if (con != null) {
//                con.close();
//            }
//        }catch(Exception e){
//
//        }
//    }

	public void insert(User user) throws SQLException {

        InsertJdbcTemplate template = new InsertJdbcTemplate(getConnection()) {

            @Override
            public void createQuery() {
            }

            @Override
            public void update(User user) {

            }

            @Override
            public void setValuesForInsert(User user) throws SQLException {
                this.pstmt.setString(1, user.getUserId());
                this.pstmt.setString(2, user.getPassword());
                this.pstmt.setString(3, user.getName());
                this.pstmt.setString(4, user.getEmail());
            }

            @Override
            public void createQueryForInsert() throws SQLException {
                String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
                this.pstmt = this.conn.prepareStatement(sql);
            }
        };
        template.createQueryForInsert();
        template.setValuesForInsert(user);

        template.executeQuery();

	}

	public void update(User user) throws SQLException {


        UpdateJdbcTemplate template = new UpdateJdbcTemplate(getConnection()) {

            @Override
            public void createQuery() throws SQLException {

            }

            @Override
            public void update(User user) throws SQLException {

            }

            @Override
            public void setValuesForUpdate(User user) throws SQLException {
                this.pstmt.setString(1, user.getPassword());
                this.pstmt.setString(2, user.getName());
                this.pstmt.setString(3, user.getEmail());
                this.pstmt.setString(4, user.getUserId());
            }

            @Override
            public void createQueryForUpdate() throws SQLException {
                String sql = "UPDATE USERS SET password=?, name=?, email=? WHERE userId=?";
                this.pstmt = this.conn.prepareStatement(sql);
            }
        };
        template.createQueryForUpdate();
        template.setValuesForUpdate(user);

        template.executeQuery();
	}

	public List<User> findAll() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<User> userList = new ArrayList<>();
		try {
			con = ConnectionManager.getConnection();
			String sql = "SELECT userId, password, name, email FROM USERS";
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			User user = null;
			if (rs.next()) {
				user = new User(
						rs.getString("userId"),
						rs.getString("password"),
						rs.getString("name"),
						rs.getString("email"));

				userList.add(user);
			}


		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return userList;
	}

	public User findByUserId(String userId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ConnectionManager.getConnection();
			String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);

			rs = pstmt.executeQuery();

			User user = null;
			if (rs.next()) {
				user = new User(
						rs.getString("userId"),
						rs.getString("password"),
						rs.getString("name"),
						rs.getString("email"));
			}

			return user;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}
}
