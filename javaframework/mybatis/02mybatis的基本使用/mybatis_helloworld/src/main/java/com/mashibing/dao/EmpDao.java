package com.mashibing.dao;

import com.mashibing.bean.Emp;
import com.sun.tracing.dtrace.ProviderAttributes;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface EmpDao {

    public Emp selectEmpByEmpno1(Integer empno);
    public Emp selectEmpByEmpno2(@Param("empno") Integer empno,@Param("sal") Integer sal);
    public Integer save(Emp emp);
    public Integer update(Emp emp);
    public Integer delete(Integer empno);

    public List<Emp> selectAll();
    @MapKey("ename")
    public Map<String,Emp> selectAll2();

    public List<Emp> selectEmpByEmpnoAndSal(Emp emp);
    public List<Emp> selectEmpByEmpnoAndSal22(Integer param1,Double param2);
    public List<Emp> selectEmpByEmpnoAndSal2(@Param("empno") Integer empno, @Param("sal") Double sal);
    public List<Emp> selectEmpByEmpnoAndSal3(Map<String,Object> map);

    public Map<Object,Object> selectEmpByEmpnoReturnMap(Integer empno);





}
