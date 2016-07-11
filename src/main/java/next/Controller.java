package next;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by granknight on 2016. 7. 11..
 */
public interface Controller {
    String execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
