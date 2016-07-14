package next.dao;

import next.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by granknight on 2016. 7. 14..
 */
public abstract class UpdateJdbcTemplate extends JdbcTemplate {

    Connection conn = null;
    PreparedStatement pstmt = null;

    UpdateJdbcTemplate(Connection conn){
        super(conn);
        this.conn = conn;
    }

    public abstract void setValuesForUpdate(User user) throws SQLException;

    public abstract void createQueryForUpdate() throws SQLException;

    public void executeQuery() throws SQLException{
        pstmt.executeUpdate();
        closeResource(conn, pstmt, null);
    }
}
