<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.awt.*" %><%--
  Created by IntelliJ IDEA. 
  2018/12/04 
  To change this template use File | Settings | File Templates. 
--%> 
<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@include file="/common/common.jsp"%> 
<html> 
<head> 
    <title>Title</title>
    <%
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
    %>
</head> 

<body class="main"> 
<form action="${path}/medicineReqPlan/insertBoss.action">
    <h1>添加药剂</h1>
    <div class="update"> 
        <div class="left"> 
            <span>药品</span>
            <select name="medicineCodeid" value="">
                <c:forEach items="${medicineCode}" var="mc">
                    <option value="${mc.codeId}">${mc.medicineName}</option>
                </c:forEach>
            </select>
        </div> 
        <div class="right"> 
            <span>需求数量</span> 
            <input type="text" name="reqamt" value=""> 
        </div> 
        <div class="left"> 
            <span>申请人</span> 
            <input type="text" readonly value="${bucct.cname}">
            <input type="hidden" name="appUserid" value="${bucct.userId}">
        </div>
        <div class="right"> 
            <span>申请日期</span> 
            <input type="date" name="appDate" value="<%=format%>">
        </div> 
        <div class="left"> 
            <span>用途</span> 
            <input type="text" name="purpose" value=""> 
        </div> 
        <div class="right">
            <span>状态</span>
            <input type="hidden" readonly="readonly" name="status" value="2">
            <span style="color: red">修改后状态变更成已审批</span>
        </div> 
        <div class="left">
            <span>审批人</span>
            <input type="text" readonly value="${bucct.cname}">
            <input type="hidden" name="apprvUserid" value="${bucct.userId}">
        </div>
        <div class="right">
            <span>审批日期</span>
            <input type="date" name="apprvDate" value="<%=format%>">
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
 
