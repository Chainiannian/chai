<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA. 
  2018/12/21 
  To change this template use File | Settings | File Templates. 
--%> 
<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@include file="/common/common.jsp"%> 
<html> 
<head> 
    <title>Title</title>
    <%
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(new Date());
    %>
</head> 

<body class="main"> 
<form action="${path}/basePatientInfo/insert.action"> 
    <h1>添加办理就诊卡</h1> 
    <div class="update"> 
        <div class="left"> 
            <span>病人姓名</span> 
            <input type="text" name="patientName" value=""> 
        </div> 
        <div class="right"> 
            <span>出生日期</span> 
            <input type="date" name="birth" value="<%=format%>">
        </div> 
        <div class="left"> 
            <span>性别</span> 
            <input type="text" name="sex" value=""> 
        </div> 
        <div class="right"> 
            <span>家庭住址</span> 
            <input type="text" name="address" value=""> 
        </div> 
        <div class="left"> 
            <span>电话号码</span> 
            <input type="text" name="phonenum" value=""> 
        </div> 
        <div class="right"> 
            <span>身份证号</span> 
            <input type="text" name="personid" value="" required>
        </div> 
        <div id="error"></div> 
        <div class="buttons"> 
            <input type="submit" value="提交"> 
            <input type="button" onclick="history.back()" value="返回"> 
        </div> 
    </div> 
</form> 
</body> 
</html> 
 
