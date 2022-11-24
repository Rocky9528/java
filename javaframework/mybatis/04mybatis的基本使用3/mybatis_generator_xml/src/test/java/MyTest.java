import com.rocky.bean.Dept;
import com.rocky.bean.DeptExample;
import com.rocky.bean.Emp;
import com.rocky.bean.EmpExample;
import com.rocky.dao.DeptMapper;
import com.rocky.dao.EmpMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTest {
    SqlSessionFactory sqlSessionFactory = null;

    @Before
    public void init() {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void test01() throws IOException {

        //获取与数据库相关的会话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取对应的映射接口对象
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp=new Emp();
        emp.setEmpno(7369);
        emp.setEname("zhangsan");
        Emp emp1 = mapper.selectByPrimaryKey(7369);
        System.out.println(emp1);
        //关闭会话
        sqlSession.close();

    }

    @Test
    public void test02() throws IOException {
        //排序 orderByClause
        //去重 distinct
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        EmpExample empExample=new EmpExample();
        empExample.setOrderByClause("deptno desc ,ename");
        empExample.setDistinct(true);

        List<Emp> emps = mapper.selectByExample(empExample);
        for(Emp emp :emps){
            System.out.println(emp);
        }

        sqlSession.close();

    }

    @Test
    public void test03() throws IOException {
        // criteria
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        EmpExample empExample=new EmpExample();
        empExample.setOrderByClause("deptno desc ,ename");
        EmpExample.Criteria criteria = empExample.createCriteria().andDeptnoBetween(0, 20).andCommIsNull();

        EmpExample.Criteria criteria2 = empExample.createCriteria().andDeptnoGreaterThan(20).andCommIsNotNull();

        EmpExample.Criteria criteria3 = empExample.createCriteria().andEmpnoEqualTo(7369);

        EmpExample.Criteria criteria4 = empExample.createCriteria().andEnameIn(Arrays.asList("zhangsan", "lisi", "zhaoliu"));

        empExample.or(criteria2);
        empExample.or(criteria3);
        empExample.or(criteria4);

        List<Emp> emps = mapper.selectByExample(empExample);
        for(Emp emp :emps){
            System.out.println(emp);
        }

        long l = mapper.countByExample(empExample);

        sqlSession.close();

    }

    @Test
    public void test04() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp=new Emp();
        emp.setEmpno(123456);
        emp.setEname("testName");
        int insert = mapper.insertSelective(emp);
        sqlSession.commit();

        EmpExample empExample=new EmpExample();
        EmpExample.Criteria criteria = empExample.createCriteria().andEmpnoEqualTo(123456).andEnameEqualTo("testName-");

        int i = mapper.deleteByExample(empExample);
        sqlSession.commit();


        empExample.clear();
        EmpExample.Criteria testName = empExample.createCriteria().andEmpnoEqualTo(123456).andEnameEqualTo("testName");
        mapper.deleteByExample(empExample);
        sqlSession.commit();


        sqlSession.close();

    }

    @Test
    public void test05() throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp=new Emp();
        emp.setTestName("test1");

        EmpExample empExample=new EmpExample();
        empExample.createCriteria().andEmpnoEqualTo(7369);

        EmpExample.Criteria zhangsan = empExample.createCriteria().andEnameEqualTo("zhangsan");

        empExample.or(zhangsan);

        int i1 = mapper.updateByExampleSelective(emp, empExample);
        sqlSession.commit();
        sqlSession.close();

    }

}
