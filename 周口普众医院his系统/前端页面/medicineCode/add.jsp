<%-- 
  Created by IntelliJ IDEA. 
  2018/12/03 
  To change this template use File | Settings | File Templates. 
--%> 
<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@include file="/common/common.jsp"%> 
<html> 
<head> 
    <title>Title</title>
</head> 

<body class="main"> 
<form action="${path}/medicineCode/insert.action"> 
    <h1>添加药品编号</h1> 
    <div class="update"> 
        <div class="left">
            <span>药品名</span>
            <input type="text" name="medicineName" value=""> 
        </div> 
        <div class="right">
            <span>别名名称</span> 
            <input type="text" name="aliasName" value=""> 
        </div> 
        <div class="left">
            <span>规格</span> 
            <input type="text" name="specification" value=""> 
        </div> 
        <div class="right">
            <span>生产厂商名称</span>
            <%--<input type="text" name="manCode" value=""> --%>
            <select name="manCode">
                <c:forEach items="${searchManu}" var="manu">
                    <option value="${manu.manCode}">${manu.manChnName}</option>
                </c:forEach>
            </select>
        </div>
        <%--<div class="left">--%>
            <%--<span>药品分类名称</span>--%>
            <%--&lt;%&ndash;<input type="text" name="typeName" value="">&ndash;%&gt;--%>
            <%--<select name="typeName">--%>
                <%--<c:forEach items="${searchMedT}" var="manu">--%>
                    <%--<option value="${manu.typeName}">${manu.remark}</option>--%>
                <%--</c:forEach>--%>
            <%--</select>--%>
        <%--</div> --%>
        <div class="left">
            <span>药品分类名称</span>
            <%--<input type="text" name="typeCode" value="">--%>
            <select name="typeCode">
                <c:forEach items="${searchMedT}" var="manu">
                    <option value="${manu.typeId}">${manu.remark}</option>
                </c:forEach>
            </select>
        </div> 
        <div class="right">
            <span>库存单位</span> 
            <input type="text" name="stockUnit" value=""> 
        </div> 
        <div class="left">
            <span>零售价</span> 
            <input type="text" name="retailPrice" value=""> 
        </div> 
        <div class="right">
            <span>库存平均价 (加权平均成本)</span> 
            <input type="text" name="stockPrice" value=""> 
        </div> 
        <div class="left">
            <span>用药注意事项</span> 
            <input type="text" name="drugNotesPatient" value=""> 
        </div> 
        <div class="right">
            <span>药袋说明</span> 
            <input type="text" name="drugNote" value=""> 
        </div> 
        <div class="left">
            <span>药品外形说明</span> 
            <input type="text" name="drugForm" value=""> 
        </div> 
        <div class="right">
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
 
