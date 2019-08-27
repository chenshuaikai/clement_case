<%--
  Created by IntelliJ IDEA.
  User: clement
  Date: 2019/8/22
  Time: 12:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
 <p> <a href="/routeForward/formPageGoto?flag=one">进入表单提交页面1（使用ifram进行防止表单提交后页面跳转）</a></p>
 <p> <a href="/routeForward/formPageGoto?flag=two">进入表单提交页面2（使用原生js进行防止表单提交后页面跳转）</a></p>
  <p><a href="/routeForward/formPageGoto?flag=three">进入表单提交页面3（使用jquery.form.min.js进行防止表单提交后页面跳转）</a></p>
  </body>
</html>
