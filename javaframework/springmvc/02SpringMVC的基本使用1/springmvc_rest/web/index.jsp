<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 2020/3/14
  Time: 20:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  pageContext.setAttribute("ctp",request.getContextPath());
%>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <form action="${ctp}/user/user" method="post">
    <input type="submit" value="新增">
  </form>
  <form action="${ctp}/user/user" method="post">
    <input type="hidden" name="_method" value="put">
    <input type="text" name="name">
    <input type="text" name="id">
    <input type="submit" value="更新">
  </form>
  <form action="${ctp}/user/user" method="post">
    <input type="hidden" name="_method" value="delete">
    <input type="submit" value="删除">
  </form>


  <a href="${ctp}/user/user?id=1&name=张三">查询</a>
  </body>
</html>
