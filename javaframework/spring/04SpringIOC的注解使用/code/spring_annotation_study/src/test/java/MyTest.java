import com.mashibing.bean.Teacher;
import com.mashibing.controller.PersonController;
import com.mashibing.controller.StudentController;
import com.mashibing.controller.TeacherController;
import com.mashibing.dao.PersonDao;
import com.mashibing.service.PersonService;
import com.mashibing.service.StudentService;
import com.mashibing.service.TeacherService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 注意，给测试类起名字的时候千万不要定义成Test
 * 测试的方法不可以有参数，不可以有返回值
 */
public class MyTest {

    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    @Test
    public void test01() {
        PersonController personController = context.getBean("personController", PersonController.class);
        personController.save();

        PersonDao personDao = context.getBean("personDao2", PersonDao.class);
        personController.test(personDao,"哈哈");

        personController.test1(personDao);

        StudentController studentController = context.getBean("studentController", StudentController.class);
        studentController.save();

        //bean对象teacher，注入到TeacherDao中，TeacherDao中的注入对象修改不会影响到原始bean对象。
        //修改bean对象teacher，才会影响该bean对象的值
        Teacher teacher1 = context.getBean("teacher", Teacher.class);
        System.out.println(teacher1);//name =张三

        TeacherController teacherController=context.getBean("teacherControllerx",TeacherController.class);
        teacherController.save();//name =99

        System.out.println(teacher1);//name依然 =张三
        Teacher teacher2 = context.getBean("teacher", Teacher.class);
        System.out.println(teacher2);//name依然 =张三

        teacher1.name="100";
        System.out.println(teacher1);//name变为99
        System.out.println(teacher2);//name变为99
    }

    @Test
    public void test02() {

        StudentService studentService = context.getBean("studentService", StudentService.class);
        studentService.save();

        TeacherService teacherService = context.getBean("teacherService",TeacherService.class);
        teacherService.save();

    }

}
