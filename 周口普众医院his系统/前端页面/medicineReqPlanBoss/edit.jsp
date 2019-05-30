<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
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
<form action="${path}/medicineReqPlan/updateBoss.action">
    <h1>修改药剂</h1>
    <input type="hidden" name="reqplnno" value="${medicineReqPlan.reqplnno}"> 
    <div class="update"> 
        <div class="left"> 
            <span>药品</span> 
            <%--<input type="text" name="medicineCodeid" value="${medicineReqPlan.medicineCodeid}">--%>
            <select id="medicineCodeid" name="medicineCodeid"  value="${medicineReqPlan.medicineCodeid}">
                <c:forEach items="${medicineCode}" var="mc">
                    <c:if test="${medicineReqPlan.medicineCodeid==mc.codeId}">
                        <option value="${mc.codeId}">${mc.medicineName}</option>
                    </c:if>
                </c:forEach>
                <c:forEach items="${medicineCode}" var="mc">
                    <c:if test="${medicineReqPlan.medicineCodeid!=mc.codeId}">
                        <option value="${mc.codeId}">${mc.medicineName}</option>
                    </c:if>
                </c:forEach>
            </select>
        </div> 
        <div class="right"> 
            <span>需求数量</span> 
            <input type="text" name="reqamt" value="${medicineReqPlan.reqamt}"> 
        </div> 
        <div class="left"> 
            <span>申请人</span>
            <c:forEach items="${baseUser}" var="bu">
                <c:if test="${bu.userId==medicineReqPlan.appUserid}">
                    <input type="text" readonly="readonly" value="${bu.cname}">
                    <input type="hidden" name="appUserid" value="${bu.userId}">
                </c:if>
            </c:forEach>
        </div> 
        <div class="right"> 
            <span>申请日期</span> 
            <input type="date" name="appDate" value="${medicineReqPlan.appDate}">
        </div> 
        <div class="left"> 
            <span>用途</span> 
            <input type="text" name="purpose" value="${medicineReqPlan.purpose}"> 
        </div> 
        <div class="right"> 
            <span>状态</span>
            <input type="hidden" readonly="readonly" name="status" value="2">
            <span style="color: red">修改后状态变更成已审批</span>
        </div>
        <div class="left">
            <span>审批人</span>
            <%--<input type="text" name="apprvUserid" value="${medicineReqPlan.apprvUserid}"> --%>
            <c:if test="${medicineReqPlan.apprvUserid!=null}">
                <c:forEach items="${baseUser}" var="bu">
                    <c:if test="${medicineReqPlan.apprvUserid==bu.userId}">
                        <input type="text" readonly value="${bu.cname}">
                        <input type="hidden" name="apprvUserid" value="${bu.userId}">
                    </c:if>
                </c:forEach>
            </c:if>
            <c:if test="${medicineReqPlan.apprvUserid==null}">
                <%--<input type="text" readonly name="apprvUserid" value="">--%>
                <input type="text" readonly value="${bucct.cname}">
                <input type="hidden" name="apprvUserid" value="${bucct.userId}">
            </c:if>
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
 
