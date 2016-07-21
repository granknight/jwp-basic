package core.view;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by granknight on 2016. 7. 18..
 */
public class JsonView implements View {

    @Override
    public void render(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        resp.setContentType("application/json;charset=UTF-8");

        Enumeration parameterEnum = req.getAttributeNames();

        Map<String, Object> map = new HashMap<>();

        while(parameterEnum.hasMoreElements()){
            String name = parameterEnum.nextElement().toString();
            map.put(name, req.getParameter(name));
        }

        ObjectMapper mapper = new ObjectMapper();

        PrintWriter writer = resp.getWriter();

        writer.print(mapper.writeValueAsString(map));
    }
}
