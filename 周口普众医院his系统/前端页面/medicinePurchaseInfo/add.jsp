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
    <script>
        $(function () {
            var a = $("#fa").val();
            if (a!=null){
                $("#fl").attr("type","submit")
            }else {
                $("#fl").click(function () {
                    alert("已经没有可汇总的数据")
                })
            }
        })
    </script>
</head> 

<body class="main"> 
<form action="${path}/medicinePurchaseInfo/insert.action"> 
    <h1>汇总采购表</h1>
    <div class="update"> 
        <div class="left"> 
            <span>药品</span> 
            <%--<input type="text" name="medicineCodeid" value=""> --%>
            <select id="fa" name="medicineCodeid" value="${code.codeId}">
                <c:forEach items="${cod}" var="code">
                    <option value="${code.codeId}">${code.medicineName}</option>
                </c:forEach>
            </select>
        </div> 
        <div class="right"> 
            <span>供应商</span> 
            <%--<input type="text" name="manCode" value=""> --%>
            <select name="manCode" value="">
                <c:forEach items="${mft}" var="mftt">
                    <option value="${mftt.manCode}">${mftt.manChnName}</option>
                </c:forEach>
            </select>
        </div> 
        <%--<div class="left"> --%>
            <%--<span>采购数量</span> --%>
            <%--<input type="text" name="pchAmt" value=""> --%>
        <%--</div> --%>
        <div class="left">
            <span>采购单价</span> 
            <input type="text" name="pchPrice" value="10">
        </div> 
        <%--<div class="left"> --%>
            <%--<span>采购总价</span> --%>
            <%--<input type="text" name="pchTotal" value=""> --%>
        <%--</div> --%>
        <%--<div class="right"> --%>
            <%--<span>状态</span> --%>
            <input type="hidden" name="status" value="1">
        <%--</div> --%>
        <div class="right">
            <span>汇总人</span> 
            <%--<input type="text" name="pchUserid" value="">--%>
            <input type="text" readonly="readonly" value="${bucct.cname}">
            <input type="hidden" name="pchUserid" value="${bucct.userId}">
        </div> 
        <div class="left">
            <span>汇总日期</span> 
            <input type="date" name="pchDate" value="<%=format%>">
        </div> 
        <%--<div class="left"> --%>
            <%--<span>审批人</span> --%>
            <%--<input type="text" name="apprvUserid" value=""> --%>
        <%--</div> --%>
        <%--<div class="right"> --%>
            <%--<span>审批日期</span> --%>
            <%--<input type="date" name="apprvDate" value=""> --%>
        <%--</div> --%>
        <div id="error"></div> 
        <div class="buttons"> 
            <input id="fl" type="button" value="提交">
            <input type="button" onclick="history.back()" value="返回"> 
        </div> 
    </div> 
</form> 
</body> 
</html> 
 
