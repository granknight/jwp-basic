package next.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by granknight on 2016. 7. 18..
 */
public abstract class SelectJdbcTemplate<E>{

    PreparedStatement pstmt = null;

    public List<E> quert(String sql){

        return null;
    }

    public E queryForObject(String sql){
        return null;
    }

    public abstract void setValues(PreparedStatement pstmt);

    public abstract E mapRow(ResultSet rs);

}
