package core.di.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.reflections.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;
import org.springframework.beans.BeanUtils;

public class BeanFactory {
	private static final Logger logger = LoggerFactory.getLogger(BeanFactory.class);
	
	private Set<Class<?>> preInstanticateBeans;

	private Map<Class<?>, Object> beans = Maps.newHashMap();

	public BeanFactory(Set<Class<?>> preInstanticateBeans) {
		this.preInstanticateBeans = preInstanticateBeans;

		preInstanticateBeans.stream().forEach(i -> {
            try {
                recursiveByNoConstructorClass(i);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }

        });

	}

	private Object recursiveByNoConstructorClass(Class<?> clazz) throws IllegalAccessException, InstantiationException {

		Object obj = beans.get(clazz.getDeclaringClass());

		if(obj != null)
			return obj;

        Constructor[] constructors = clazz.getConstructors();
        for(Constructor constructor : constructors){
            if(constructor.getParameterTypes().length>0){

                Set<Method> methods = ReflectionUtils.getAllMethods(clazz, ReflectionUtils.withAnnotation(core.annotation.Inject.class));

                for(Method method : methods){
                    Class<?>[] parameterClasses = method.getParameterTypes();

                    for(Class<?> parameter : parameterClasses){
                        if(beans.get(parameter) == null){
                            recursiveByNoConstructorClass(clazz);
                        }
                    }


                    //beans.put(clazz.getDeclaringClass(), BeanUtils.instantiateClass(constructor, beans.get));

                }

            }else{
                beans.put(clazz.getDeclaringClass(), clazz.newInstance());
            }
        }



        return obj;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getBean(Class<T> requiredType) {
		return (T)beans.get(requiredType);
	}
	
	public void initialize() {
		
	}
}
