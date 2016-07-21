package core.view;

import java.util.HashMap;
import java.util.Map;

import static javafx.scene.input.KeyCode.V;

/**
 * Created by granknight on 2016. 7. 18..
 */
public class ModelAndView {

    private Map<String, Object> model = new HashMap<String, Object>();
    private View view;
    private String viewName;

    public ModelAndView() {
    }

    public ModelAndView(View view) {
        this.view = view;
    }

    public ModelAndView(String viewName) {
        this.viewName = viewName;
    }

    public void addAttribute(String attrName, Object attr){
        model.put(attrName, attr);
    }

    public void deleteAttribute(String attrName){
        model.remove(attrName);
    }
}
