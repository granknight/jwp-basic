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

    PreparedStatement pstmt;

    public JdbcTemplate() {

    }


    public void update() throws SQLException{
        this.pstmt.executeUpdate();
        closeResource();
    }

    public abstract void setValues(PreparedStatement pstmt) throws SQLException;



    public void closeResource(){
        try {
//            if(rs != null){
//                rs.close();
//            }
            if (pstmt != null) {
                pstmt.close();
            }

//            if (conn != null) {
//                conn.close();
//            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
