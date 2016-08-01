package core.ref;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Junit4TestRunner {

	private static final Logger logger = LoggerFactory.getLogger(Junit4TestRunner.class);

	@Test
	public void run() throws Exception {
		Class<Junit4Test> clazz = Junit4Test.class;

		Method[] methodList = clazz.getDeclaredMethods();

		Arrays.asList(methodList).stream().forEach(i -> {
				if(i.isAnnotationPresent(MyTest.class)) {
					try {
						i.invoke(clazz.newInstance());
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					} catch (InstantiationException e) {
						e.printStackTrace();
					}
				}

		});

	}
}
