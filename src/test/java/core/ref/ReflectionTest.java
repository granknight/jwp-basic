package core.ref;

import next.model.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import next.model.Question;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ReflectionTest {
	private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);
	
	@Test
	public void showClass() {
		Class<Question> clazz = Question.class;

		logger.info("className ::::::::::::::::::::");
		logger.info("className ::: {} :::", clazz.getName());


		logger.info("constructor ::::::::::::::::::::");


		Constructor[] constructor = clazz.getConstructors();

		Arrays.asList(constructor).stream().forEach(i -> logger.info("constructor ::: {} :::", i.getName()));

		logger.info("Field ::::::::::::::::::::");

		Field[] fieldList = clazz.getDeclaredFields();

		Arrays.stream(fieldList).forEach(i -> logger.info("fieldName ::: {} :::", i.getName()));

		logger.info("Method ::::::::::::::::::::");

		Method[] methodList = clazz.getDeclaredMethods();

		Arrays.stream(methodList).forEach(i -> logger.info("methodName ::: {} :::", i.getName()));


	}

	@Test
	public void createUserInstanceTest() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

		Class<User> userClass = User.class;

		Constructor[] userConstructor = userClass.getDeclaredConstructors();

		logger.info("length :: {} :: ",userConstructor.length);

		User user = (User) userConstructor[0].newInstance("1","2","3","4");

		//User user = userConstructor.newInstance("1", "2", "3", "4");

		logger.info(" user :: {} :: ", user);
	}
}
