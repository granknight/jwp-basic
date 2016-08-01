package core.ref;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by granknight on 2016. 7. 28..
 */
public class StudentTest {

    private static final Logger logger = LoggerFactory.getLogger(StudentTest.class);

    @Test
    public void assignValueToStudentInstance(){

        Student student = new Student();

        Class<Student> clazz = Student.class;

        try {
            Field nameField = clazz.getDeclaredField("name");
            Field ageField = clazz.getDeclaredField("age");
            Field.setAccessible(new Field[]{nameField, ageField}, true);

            nameField.set(student, "김종택");
            ageField.set(student, 31);

            logger.info("name :: {} :: ", clazz.getDeclaredMethod("getName").invoke(student));
            logger.info("age :: {} :: ", clazz.getDeclaredMethod("getAge").invoke(student));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
