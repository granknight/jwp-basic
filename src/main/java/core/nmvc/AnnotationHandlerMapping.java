package core.nmvc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.google.common.collect.Maps;

import core.annotation.RequestMapping;
import core.annotation.RequestMethod;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AnnotationHandlerMapping {

    private static final Logger log = LoggerFactory.getLogger(AnnotationHandlerMapping.class);

	private Object[] basePackage;
	
	private Map<HandlerKey, HandlerExecution> handlerExecutions = Maps.newHashMap();

	public AnnotationHandlerMapping(Object... basePackage) {
		this.basePackage = basePackage;
	}
	
    public void initialize() {
        for(Object obj : basePackage){
            Reflections reflections = new Reflections(String.valueOf(obj));

            Set<Class<?>> controllers = reflections.getTypesAnnotatedWith(core.annotation.Controller.class);

//            for(Class controller : controllers){
//
//                Set<Method> methods = reflections.getMethodsAnnotatedWith(core.annotation.RequestMapping.class);
//
//                //Set<Class<?>> annotations = reflections.getTypesAnnotatedWith(core.annotation.RequestMapping.class);
//
//
//
//            }

            Set<Method> methods = reflections.getMethodsAnnotatedWith(core.annotation.RequestMapping.class);

            for(Method method : methods){
                RequestMapping requestMapping = method.getAnnotation(core.annotation.RequestMapping.class);

                String url = requestMapping.value();
                RequestMethod requestMethod = requestMapping.method();

                HandlerKey handlerKey = new HandlerKey(url, requestMethod);




            }

        }

//        for(Object obj : basePackage){
//            Reflections reflections = new Reflections(String.valueOf(obj));
//            Set<Method> methods = reflections.getMethodsAnnotatedWith(core.annotation.RequestMapping.class);
//
//            for(Method method : methods){
//                log.debug("Method  :: {}", method);
//            }
//        }
    }

	public HandlerExecution getHandler(HttpServletRequest request) {
		String requestUri = request.getRequestURI();
		RequestMethod rm = RequestMethod.valueOf(request.getMethod().toUpperCase());
		return handlerExecutions.get(new HandlerKey(requestUri, rm));
	}
}
