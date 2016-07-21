package core.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by granknight on 2016. 7. 18..
 */
public interface View {

    void render(HttpServletRequest req, HttpServletResponse resp) throws Exception;
}
