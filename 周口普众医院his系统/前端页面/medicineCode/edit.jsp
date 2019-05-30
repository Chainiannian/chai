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
<form action="${path}/medicineCode/update.action"> 
    <h1>修改药品编号</h1> 
    <input type="hidden" name="codeId" value="${medicineCode.codeId}"> 
    <div class="update"> 
        <div class="left"> 
            <span>药品名</span> 
            <input type="text" name="medicineName" value="${medicineCode.medicineName}"> 
        </div> 
        <div class="right"> 
            <span>别名名称</span> 
            <input type="text" name="aliasName" value="${medicineCode.aliasName}"> 
        </div> 
        <div class="left"> 
            <span>规格</span> 
            <input type="text" name="specification" value="${medicineCode.specification}"> 
        </div> 
        <div class="right"> 
            <span>生产厂商名称</span>
            <%--<input type="text" name="manCode" value="${medicineCode.manCode}">--%>
            <select name="manCode">
                <c:forEach items="${searchManu}" var="manu">
                    <c:if test="${medicineCode.manCode==manu.manCode}">
                        <option value="${manu.manCode}">${manu.manChnName}</option>
                    </c:if>
                </c:forEach>
                <c:forEach items="${searchManu}" var="manu">
                    <c:if test="${medicineCode.manCode!=manu.manCode}">
                        <option value="${manu.manCode}">${manu.manChnName}</option>
                    </c:if>
                </c:forEach>
            </select>
        </div> 
        <%--<div class="left"> --%>
            <%--<span>生产厂商名称</span> --%>
            <%--<input type="text" name="manChnName" value="${medicineCode.manChnName}"> --%>
        <%--</div> --%>
        <div class="right"> 
            <span>药品分类名称</span>
            <%--<input type="text" name="typeName" value="${medicineCode.typeName}">--%>
            <select name="typeCode">
                <c:forEach items="${searchMedT}" var="manu">
                    <c:if test="${medicineCode.typeCode==manu.typeId}">
                        <option value="${manu.typeId}">${manu.remark}</option>
                    </c:if>
                </c:forEach>
                <c:forEach items="${searchMedT}" var="manu">
                    <c:if test="${medicineCode.typeCode!=manu.typeId}">
                        <option value="${manu.typeId}">${manu.remark}</option>
                    </c:if>
                </c:forEach>
            </select>
        </div> 
        <%--<div class="left"> --%>
            <%--<span>药品分类代码</span> --%>
            <%--<input type="text" name="typeCode" value="${medicineCode.typeCode}"> --%>
        <%--</div> --%>
        <div class="right"> 
            <span>库存单位</span> 
            <input type="text" name="stockUnit" value="${medicineCode.stockUnit}"> 
        </div> 
        <div class="left"> 
            <span>零售价</span> 
            <input type="text" name="retailPrice" value="${medicineCode.retailPrice}"> 
        </div> 
        <div class="right"> 
            <span>库存平均价 (加权平均成本)</span> 
            <input type="text" name="stockPrice" value="${medicineCode.stockPrice}"> 
        </div> 
        <div class="left"> 
            <span>用药注意事项</span> 
            <input type="text" name="drugNotesPatient" value="${medicineCode.drugNotesPatient}"> 
        </div> 
        <div class="right"> 
            <span>药袋说明</span> 
            <input type="text" name="drugNote" value="${medicineCode.drugNote}"> 
        </div> 
        <div class="left"> 
            <span>药品外形说明</span> 
            <input type="text" name="drugForm" value="${medicineCode.drugForm}"> 
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
 
