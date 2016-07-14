package next.dao;

import next.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by granknight on 2016. 7. 14..
 */
public abstract class JdbcTemplate {

    String sql;
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;

    public JdbcTemplate(Connection conn) {
        this.conn = conn;
    }

    public abstract void createQuery() throws SQLException;

    public abstract void update(User user) throws SQLException;

    public void closeResource(Connection conn, PreparedStatement pstmt, ResultSet rs){
        try {
            if(rs != null){
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }

            if (conn != null) {
                conn.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
