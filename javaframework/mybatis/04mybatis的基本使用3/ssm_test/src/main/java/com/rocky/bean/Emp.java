package com.rocky.bean;

import java.math.BigDecimal;
import java.util.Date;

public class Emp {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column emp.EMPNO
     *
     * @mbg.generated Thu Nov 24 23:11:59 CST 2022
     */
    private Integer empno;

    @Override
    public String toString() {
        return "Emp{" +
                "empno=" + empno +
                ", ename='" + ename + '\'' +
                ", job='" + job + '\'' +
                ", mgr=" + mgr +
                ", hiredate=" + hiredate +
                ", sal=" + sal +
                ", comm=" + comm +
                ", deptno=" + deptno +
                '}';
    }

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column emp.ENAME
     *
     * @mbg.generated Thu Nov 24 23:11:59 CST 2022
     */
    private String ename;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column emp.JOB
     *
     * @mbg.generated Thu Nov 24 23:11:59 CST 2022
     */
    private String job;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column emp.MGR
     *
     * @mbg.generated Thu Nov 24 23:11:59 CST 2022
     */
    private Integer mgr;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column emp.HIREDATE
     *
     * @mbg.generated Thu Nov 24 23:11:59 CST 2022
     */
    private Date hiredate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column emp.SAL
     *
     * @mbg.generated Thu Nov 24 23:11:59 CST 2022
     */
    private BigDecimal sal;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column emp.COMM
     *
     * @mbg.generated Thu Nov 24 23:11:59 CST 2022
     */
    private BigDecimal comm;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column emp.DEPTNO
     *
     * @mbg.generated Thu Nov 24 23:11:59 CST 2022
     */
    private Integer deptno;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column emp.EMPNO
     *
     * @return the value of emp.EMPNO
     *
     * @mbg.generated Thu Nov 24 23:11:59 CST 2022
     */
    public Integer getEmpno() {
        return empno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column emp.EMPNO
     *
     * @param empno the value for emp.EMPNO
     *
     * @mbg.generated Thu Nov 24 23:11:59 CST 2022
     */
    public void setEmpno(Integer empno) {
        this.empno = empno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column emp.ENAME
     *
     * @return the value of emp.ENAME
     *
     * @mbg.generated Thu Nov 24 23:11:59 CST 2022
     */
    public String getEname() {
        return ename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column emp.ENAME
     *
     * @param ename the value for emp.ENAME
     *
     * @mbg.generated Thu Nov 24 23:11:59 CST 2022
     */
    public void setEname(String ename) {
        this.ename = ename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column emp.JOB
     *
     * @return the value of emp.JOB
     *
     * @mbg.generated Thu Nov 24 23:11:59 CST 2022
     */
    public String getJob() {
        return job;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column emp.JOB
     *
     * @param job the value for emp.JOB
     *
     * @mbg.generated Thu Nov 24 23:11:59 CST 2022
     */
    public void setJob(String job) {
        this.job = job;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column emp.MGR
     *
     * @return the value of emp.MGR
     *
     * @mbg.generated Thu Nov 24 23:11:59 CST 2022
     */
    public Integer getMgr() {
        return mgr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column emp.MGR
     *
     * @param mgr the value for emp.MGR
     *
     * @mbg.generated Thu Nov 24 23:11:59 CST 2022
     */
    public void setMgr(Integer mgr) {
        this.mgr = mgr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column emp.HIREDATE
     *
     * @return the value of emp.HIREDATE
     *
     * @mbg.generated Thu Nov 24 23:11:59 CST 2022
     */
    public Date getHiredate() {
        return hiredate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column emp.HIREDATE
     *
     * @param hiredate the value for emp.HIREDATE
     *
     * @mbg.generated Thu Nov 24 23:11:59 CST 2022
     */
    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column emp.SAL
     *
     * @return the value of emp.SAL
     *
     * @mbg.generated Thu Nov 24 23:11:59 CST 2022
     */
    public BigDecimal getSal() {
        return sal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column emp.SAL
     *
     * @param sal the value for emp.SAL
     *
     * @mbg.generated Thu Nov 24 23:11:59 CST 2022
     */
    public void setSal(BigDecimal sal) {
        this.sal = sal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column emp.COMM
     *
     * @return the value of emp.COMM
     *
     * @mbg.generated Thu Nov 24 23:11:59 CST 2022
     */
    public BigDecimal getComm() {
        return comm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column emp.COMM
     *
     * @param comm the value for emp.COMM
     *
     * @mbg.generated Thu Nov 24 23:11:59 CST 2022
     */
    public void setComm(BigDecimal comm) {
        this.comm = comm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column emp.DEPTNO
     *
     * @return the value of emp.DEPTNO
     *
     * @mbg.generated Thu Nov 24 23:11:59 CST 2022
     */
    public Integer getDeptno() {
        return deptno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column emp.DEPTNO
     *
     * @param deptno the value for emp.DEPTNO
     *
     * @mbg.generated Thu Nov 24 23:11:59 CST 2022
     */
    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }
}