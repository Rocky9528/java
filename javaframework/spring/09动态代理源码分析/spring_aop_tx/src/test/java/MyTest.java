import com.alibaba.druid.pool.DruidDataSource;
import com.mashibing.dao.EmpDao;
import com.mashibing.entity.Emp;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTest {

  ClassPathXmlApplicationContext context =   new ClassPathXmlApplicationContext("applicationContext.xml");
    @Test
    public void test01() throws SQLException {
        DruidDataSource dataSource = context.getBean("dataSource", DruidDataSource.class);
        System.out.println(dataSource.getConnection());
        JdbcTemplate jdbcTemplate = context.getBean("jdbcTemplate", JdbcTemplate.class);
        System.out.println(jdbcTemplate);
    }

    @Test
    public void test02(){
        JdbcTemplate jdbcTemplate = context.getBean("jdbcTemplate", JdbcTemplate.class);
        String sql = "insert into emp(empno,ename) values(?,?)";
        int zhangsan = jdbcTemplate.update(sql, 1111, "zhangsan");
        System.out.println(zhangsan);
    }

    @Test
    public void test03(){
        JdbcTemplate jdbcTemplate = context.getBean("jdbcTemplate", JdbcTemplate.class);
        String sql = "insert into emp(empno,ename) values(?,?)";
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[]{2222,"lisi"});
        list.add(new Object[]{3333,"wangwu"});
        list.add(new Object[]{4444,"maliu"});
        int[] result = jdbcTemplate.batchUpdate(sql,list);
        for (int i : result) {
            System.out.println(i);
        }
    }

    @Test
    public void test04(){
        JdbcTemplate jdbcTemplate = context.getBean("jdbcTemplate", JdbcTemplate.class);
        String sql = "delete from emp where empno = ?";
        int zhangsan = jdbcTemplate.update(sql, 1111);
        System.out.println(zhangsan);
    }

    @Test
    public void test05(){
        JdbcTemplate jdbcTemplate = context.getBean("jdbcTemplate", JdbcTemplate.class);
        String sql = "update  emp set ename=? where empno=?";
        int zhangsan = jdbcTemplate.update(sql,  "mashibing",2222);
        System.out.println(zhangsan);
    }

    @Test
    public void test06(){
        JdbcTemplate jdbcTemplate = context.getBean("jdbcTemplate", JdbcTemplate.class);
        String sql = "select * from emp where empno=?";
        Emp result = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Emp.class),7369);

        System.out.println(result);
    }

    @Test
    public void test07(){
        JdbcTemplate jdbcTemplate = context.getBean("jdbcTemplate", JdbcTemplate.class);
        String sql = "select * from emp where sal >?";
        List<Emp> result = jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Emp.class),1500);
        for (Emp emp : result) {
            System.out.println(emp);
        }
    }

    @Test
    public void test08(){
        EmpDao empDao = context.getBean("empDao", EmpDao.class);
        empDao.save(new Emp(1111,"zhangsan"));
    }

     //NamedParameterJdbcTemplate
    @Test
    public  void test09_1(){

        NamedParameterJdbcTemplate jdbcTemplate = context.getBean("namedParameterJDBCTemplate", NamedParameterJdbcTemplate.class);
        String sql = "insert into emp(empno,ename) values(:empno,:ename)";
        Map<String,Object> map = new HashMap<>();
        map.put("empno",2222);
        map.put("ename","sili");
        int update = jdbcTemplate.update(sql, map);
        System.out.println(update);

    }
    @Test
    public  void test09_2(){

        NamedParameterJdbcTemplate jdbcTemplate = context.getBean("namedParameterJDBCTemplate", NamedParameterJdbcTemplate.class);
        String sql = "insert into emp(empno,ename) values(:empno,:ename)";
        Emp emp=new Emp();
        emp.setEmpno(3333);
        emp.setEname("wangwu");
        SqlParameterSource sqlParameterSource=new BeanPropertySqlParameterSource(emp);
        int update = jdbcTemplate.update(sql, sqlParameterSource);
        System.out.println(update);

    }

    @Test
    public  void test09_3(){

        NamedParameterJdbcTemplate jdbcTemplate = context.getBean("namedParameterJDBCTemplate", NamedParameterJdbcTemplate.class);
        String sql = "insert into emp(empno,ename) values(:empno,:ename)";
        Emp emp=new Emp();
        emp.setEmpno(4444);
        emp.setEname("zhaoliu");
        SqlParameterSource sqlParameterSource=new BeanPropertySqlParameterSource(emp);

        KeyHolder keyHolder=new GeneratedKeyHolder();

        int update = jdbcTemplate.update(sql, sqlParameterSource,keyHolder,new String[]{"empno"});

        int k = keyHolder.getKey().intValue();
        System.out.println(k);

        System.out.println(update);

    }



}
