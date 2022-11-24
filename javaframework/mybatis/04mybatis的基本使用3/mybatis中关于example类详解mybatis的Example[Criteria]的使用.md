# mybatis中关于example类详解mybatis的Example[Criteria\]的使用

一、什么是example类

 

   mybatis-generator会为每个字段产生如上的Criterion，如果表的字段比较多,产生的Example类会十分庞大。理论上通过example类可以构造你想到的任何筛选条件。在mybatis-generator中加以配置，配置数据表的生成操作就可以自动生成example了。具体配置可以参考MBG有关配置。

   下面是mybatis自动生成example的使用。

 

二、了解example成员变量

 

   //升序还是降序

   //参数格式：字段+空格+asc(desc)

   protected String orderByClause;

   //去除重复

   //true是选择不重复记录

   protected boolean distinct;

   //自定义查询条件

   //Criteria的集合，集合中对象是由or连接

   protected List<Criteria> oredCriteria;

   //内部类Criteria包含一个Cretiron的集合，

   //每一个Criteria对象内包含的Cretiron之间

   //是由AND连接的

   public static class Criteria extends GeneratedCriteria {

​     protected Criteria() {

​       super(); 

​     }

   }

   //是mybatis中逆向工程中的代码模型

   protected abstract static class GeneratedCriteria

   {…..}

   //是最基本,最底层的Where条件，用于字段级的筛选

   public static class Criterion {……}

 

三、example使用前的准备

 

   比如我的example是根据user表生成的，UserMapper属于dao层，UserMapper.xml是对应的映射文件

   UserMapper接口：

 

long countByExample(CompetingStoreExample example);

List<CompetingStore> selectByExample(CompetingStoreExample example);

  在我们的测试类里：

 

   UserExample example = new UserExample();

   UserExample.Criteria criteria = example.createCriteria();

四、查询用户数量

 

 long count = UserMapper.countByExample(example);

   类似于：select count(*) from user

 

五、where条件查询或多条件查询

 

   example.setOrderByClause("age asc");//升序

   example.setDistinct(false);//不去重

 

   if(!StringUtils.isNotBlank(user.getName())){

​     Criteria.andNameEqualTo(user.getName());

   }

 

   if(!StringUtils.isNotBlank(user.getSex())){

​     Criteria.andSexEqualTo(user.getSex());

   }

 

   List<User> userList=userMapper.selectByExample(example);

   类似于：select * from user where name={#user.name} and sex={#user.sex} order by age asc;

 

   UserExample.Criteria criteria1 = example.createCriteria();

   UserExample.Criteria criteria2 = example.createCriteria();

 

   if(!StringUtils.isNotBlank(user.getName())){

​     Criteria1.andNameEqualTo(user.getName());

   }

 

   if(!StringUtils.isNotBlank(user.getSex())){

​     Criteria2.andSexEqualTo(user.getSex());

   }

 

   Example.or(criteria2);

   List<User> userList=userMapper.selectByExample(example);

   类似于：select * from user where name={#user.name} or sex={#user.sex} ;

 

六、模糊查询

 

   if(!StringUtils.isNotBlank(user.getName())){

​      criteria.andNameLIke(‘%’+name+’%’);

   }

 

   List<User> userList=userMapper.selectByExample(example);

   类似于：select * from user where name like %{#user.name}%

 

七、分页查询

 

​    int start = (currentPage - 1) * rows;

​    //分页查询中的一页数量

​    example.setPageSize(rows);  

​    //开始查询的位置

​    example.setStartRow(start);

​    List<User> userList=userMapper.selectByExample(example);

​    类似于：select * from user limit start to rows

 

在使用常规的mybatis时，我们经常碰到的问题就是条件式查询。在一个查询界面，查询条件较多，并且运算符并不总是=时，在后台就需要拼装sql语句。这种处理方式肯定不是使用mybatis的初衷，对于使用了hibernate的我来说，如果mybatis也有一套criteria查询就好了。在具体实现中，我们只需要按照hibernate的处理方式定义好相应的criteria，最后传递给mybatis，其自身处理相应的条件和参数信息，最终返回相应的数据即可.

 

在我们前台查询的时候会有许多的条件传过来：先看个例子：

 

public List<Contact> searchByExample(Contact contact) { 

​    System.out.println("searchByExampleContact"); 

​    ContactExample example = new ContactExample(); 

​    ContactExample.Criteria cri = example.createCriteria(); 

​    // ////////////////////////////////////////////////////////// 

​    if (this.objectAttrNullCheck(contact, "username")) 

​      cri.andUsernameEqualTo(contact.getUsername()); 

​    if (this.objectAttrNullCheck(contact, "password")) 

​      cri.andPasswordEqualTo(contact.getPassword()); 

  

​    ContactMapper vcontactMapper = sqlSession 

​        .getMapper(ContactMapper.class); 

​    List<Contact> returnList = vcontactMapper.selectByExample(example); 

​    return returnList; 

  }

 

 

这是简单的用户登录的后台代码，example中有一个Criterria的方法，里面

andUsernameEqualTo 

andPasswordEqualTo

都是在生成example的时候生成的。这两个方法是判断单值的。

简单介绍下，都是百度的： 

 

 

Criteria

 

Criteria包含一个Cretiron的集合,每一个Criteria对象内包含的Cretiron之间是由AND连接的,是逻辑与的关系。

 

oredCriteria

 

Example内有一个成员叫oredCriteria,是Criteria的集合,就想其名字所预示的一样，这个集合中的Criteria是由OR连接的，是逻辑或关系。oredCriteria就是ORed Criteria。

 

其他

 

Example类的distinct字段用于指定DISTINCT查询。

 

orderByClause字段用于指定ORDER BY条件,这个条件没有构造方法,直接通过传递字符串值指定。

 

 

import java.io.IOException;

import java.io.Reader;

import java.util.ArrayList;

import java.util.List;

 

import org.apache.ibatis.io.Resources;

import org.apache.ibatis.session.SqlSession;

import org.apache.ibatis.session.SqlSessionFactory;

import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import org.apache.log4j.pattern.ClassNamePatternConverter;

import org.springframework.context.ApplicationContext;

import org.springframework.context.support.ClassPathXmlApplicationContext;

 

import cn.itcast.ssm.mapper.ItemsMapper;

import cn.itcast.ssm.po.ItemsExample;

 

public class Student {

 

  public static void main(String[] args) throws IOException {

 

​    /*方式一 */

​    ItemsExample itemsExample1 = new ItemsExample();

 

​    itemsExample1.or().andIdEqualTo(5).andNameIsNotNull();

​    itemsExample1.or().andPicEqualTo("xxx").andPicIsNull();

 

​    List<Integer> fieldValues = new ArrayList<Integer>();

​    fieldValues.add(8);

​    fieldValues.add(11);

​    fieldValues.add(14);

​    fieldValues.add(22);

​    itemsExample1.or().andIdIn(fieldValues);

​    itemsExample1.or().andIdBetween(5, 9);

 

​    /* 方式二 criteria1与criteria2是or的关系 */

 

​    ItemsExample itemsExample2 = new ItemsExample();

​    ItemsExample.Criteria criteria1 = itemsExample2.createCriteria();

​    criteria1.andIdIsNull();

​    criteria1.andPriceEqualTo((float) 3);

 

​    ItemsExample.Criteria criteria2 = itemsExample2.createCriteria();

​    criteria2.andNameIsNull();

​    criteria2.andIdGreaterThanOrEqualTo(5);

​    itemsExample2.or(criteria2);

 

​    //方式一和方式二是等价的

​     

​     

​    // spring获取mapper代理对象

​    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

​    ItemsMapper itemsMapper = (ItemsMapper) applicationContext.getBean("itemsMapper");

​    itemsMapper.countByExample(itemsExample2);

 

​    // 获取SqlSessionFactory

​    String resource = "SqlMapConfig.xml";

​    Reader reader = Resources.getResourceAsReader(resource);

​    SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);

​    // 获取SqlSession

​    SqlSession sqlSession = sqlMapper.openSession();

 

  }

}

avaBeans类的成员变量一般称为属性（property）。对每个属性访问权限一般定义为private或protected，而不是定义为public的。注意：属性名必须以小写字母开头。 

对每个属性，一般定义两个public方法，它们分别称为访问方法（getter）和修改方法（setter），允许容器访问和修改bean的属性。 

 

   public String getColor();

 

   public void setColor(String);

 

一个例外是当属性是boolean类型时，访问器方法应该定义为isXxx()形式。

 

 

 

对象类型

 

虽然可以明确的引用对象的属性名了,但如果要在if元素中测试传入的user参数,仍然要使用_parameter来引用传递进来的实际参数,因为传递进来的User对象的名字是不可考的。如果测试对象的属性,则直接引用属性名字就可以了。

 

测试user对象和传入对象属性

 

<if test="_parameter != null">

<if test="name != null">

example如何使用？

简单查询

这个例子展示了如何用生成后的Example类去生成一个简单的where子句:

 

TestTableExample example = new TestTableExample();

 

example.createCriteria().andField1EqualTo(5); 

作为另一种选择, 下面的方式也是可以的:

 

TestTableExample example = new TestTableExample();

 

example.or().andField1EqualTo(5); 

在上面的例子中, 动态生成的where子句是:

 

where field1 = 5

 

下面的例子展示了如何用生成后的Example类去生成一个复杂的where子句 (用到了 JSE 5.0 的泛型):

 

TestTableExample example = new TestTableExample();

 

example.or() 

.andField1EqualTo(5) 

.andField2IsNull();

 

example.or() 

.andField3NotEqualTo(9) 

.andField4IsNotNull();

 

List field5Values = new ArrayList(); 

field5Values.add(8); 

field5Values.add(11); 

field5Values.add(14); 

field5Values.add(22);

 

example.or() 

.andField5In(field5Values);

 

example.or() 

.andField6Between(3, 7);

 

在上面的例子中, 动态生成的where子句是:

 

where (field1 = 5 and field2 is null) 

or (field3 <> 9 and field4 is not null) 

or (field5 in (8, 11, 14, 22)) 

or (field6 between 3 and 7) 

将会返回满足这些条件的记录结果.

 

去重复查询 

您可以在所有的Example类中调用 setDistinct(true) 方法进行强制去重复查询.

 

Criteria类 

Criteria 内部类的每个属性都包含 andXXX 方法，以及如下的标准的SQL查询方法:

 

IS NULL - 指相关的列必须为NULL 

IS NOT NULL - 指相关的列必须不为NULL 

= (equal) - 指相关的列必须等于方法参数中的值 

<> (not equal) - 指相关的列必须不等于方法参数中的值

 

(greater than) - 指相关的列必须大于方法参数中的值 

= (greater than or equal) - 指相关的列必须大于等于方法参数中的值 

< (less than) - 指相关的列必须小于于方法参数中的值 

<= (less than or equal) - 指相关的列必须小于等于方法参数中的值 

LIKE - 指相关的列必须 “like” 方法参数中的值. 这个方法不用必须加入 ‘%’, 您必须设置方法参数中的值. 

NOT LIKE - 指相关的列必须 “not like” 方法参数中的值. 这个方法不用必须加入 ‘%’, 您必须设置方法参数中的值. 

BETWEEN - 指相关的列必须在 “between” 方法参数中的两个值之间. 

NOT BETWEEN - 指相关的列必须不在 “not between” 方法参数中的两个值之间. 

IN - 指相关的列必须在传入的方法参数的list中. 

NOT IN - 指相关的列必须不在传入的方法参数的list中. 



如何生成Example类？

mybatis的的配置文件可以使用mybatis-generator工具生成，它就可以帮我们生成example类。