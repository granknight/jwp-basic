package core.nmvc;

import com.google.common.base.Predicate;
import core.annotation.RequestMapping;
import org.junit.Before;
import org.junit.Test;
import org.reflections.Configuration;
import org.reflections.Reflections;
import org.reflections.adapters.MetadataAdapter;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.MethodParameterScanner;
import org.reflections.scanners.Scanner;
import org.reflections.serializers.Serializer;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.ExecutorService;

/**
 * Created by granknight on 2016. 8. 1..
 */
public class ReflectionsTest {

    public static final Logger log = LoggerFactory.getLogger(ReflectionsTest.class);

    Reflections reflections;

//    Set<Class<?>> controllers = reflections.getTypesAnnotatedWith(core.annotation.Controller.class);
//
//    for(Class controller : controllers){
//
//        Set<Method> methods = reflections.getMethodsAnnotatedWith(core.annotation.RequestMapping.class);
//
//        //Set<Class<?>> annotations = reflections.getTypesAnnotatedWith(core.annotation.RequestMapping.class);
//
//
//
//    }

    @Before
    public void setUp() throws Exception{
        reflections = //new Reflections("core.nmvc");
            new Reflections(new ConfigurationBuilder().setUrls(ClasspathHelper.forPackage("core.nmvc")).addScanners(new MethodAnnotationsScanner(), new MethodParameterScanner()));

    }

    @Test
    public void getControllerAnnotationControllers() throws Exception{
        Set<Class<?>> controllers = reflections.getTypesAnnotatedWith(core.annotation.Controller.class);

        for(Class clazz : controllers){
            log.debug("Controller Annotation : " + clazz);
        }

    }

    @Test
    public void getRequestMappingAnnotationMethods() throws Exception{
        Set<Method> methods = reflections.getMethodsAnnotatedWith(core.annotation.RequestMapping.class);

        for(Method method : methods){
            log.debug("Method Annotation : " + method);
        }
    }

    @Test
    public void getAnnotationWithValue() throws Exception{
        Set<Method> methods = reflections.getMethodsAnnotatedWith(core.annotation.RequestMapping.class);

        for(Method method : methods){
            log.debug("Method Annotation : " + method);

            RequestMapping requestMapping = method.getAnnotation(core.annotation.RequestMapping.class);

            log.debug("value : {}", requestMapping.value());

            log.debug("method : {}", requestMapping.method());

        }
    }

}
