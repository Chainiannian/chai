<%-- 
  Created by IntelliJ IDEA. 
  2018/12/11 
  To change this template use File | Settings | File Templates. 
--%> 
<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@include file="/common/common.jsp"%> 
<html> 
<head> 
    <script type="text/javascript"> 
        $(function () { 
            $("#addBtn").click(function () { 
                location.href="${path}/jsp/medicineInstock/add.jsp";
             })
            $(".update").click(function () { 
                var instockId = $(this).attr("name");
                location.href="${path}/medicineInstock/load.action?instockId="+instockId;
             })
            $(".delete").click(function () { 
                var instockId = $(this).attr("name");
                location.href="${path}/medicineInstock/delete.action?instockId="+instockId;
             })
            $("#search").click(function () { 
                var invno = $("#invno").val();
                location.href = "${path}/medicineInstock/search.action?invno="+invno;
             })
        })  
    </script> 
</head> 
<body class="main"> 
    <div class="search"> 
        <span> 
            发票号：<input type="text" id="invno" value="${searchObject.invno}"> 
        </span> 
        <span> 
            <button id="search">查询</button> 
        </span> 
        <span> 

        </span> 
    </div> 
    <table> 
        <thead> 
            <td>序号</td> 
            <td>发票号</td> 
            <td>药品</td> 
            <td>入库数量</td> 
            <td>入库单价</td> 
            <td>入库总金额</td> 
            <td>入库人</td> 
            <td>入库日期</td> 
            <td>供应商</td>
            <%--<td>编辑</td>--%>
            <%--<td>删除</td> --%>
        </thead> 
        <c:forEach items="${list}" var="medicineInstock" varStatus="status"> 
            <tr> 
                <td>${status.index+1}</td> 
                <td>${medicineInstock.invno}</td> 
                <td><%--${medicineInstock.medicineCodeid}--%>
                    <c:forEach items="${mdc}" var="mc">
                        <c:if test="${medicineInstock.medicineCodeid==mc.codeId}">
                            ${mc.medicineName}
                        </c:if>
                    </c:forEach>
                </td>
                <td>${medicineInstock.inamt}</td> 
                <td>${medicineInstock.unitprc}</td> 
                <td>${medicineInstock.zje}</td> 
                <td><%--${medicineInstock.instockUserid}--%>
                    <c:forEach items="${bus}" var="bu">
                        <c:if test="${medicineInstock.instockUserid==bu.userId}">
                            ${bu.cname}
                        </c:if>
                    </c:forEach>
                </td>
                <td>${medicineInstock.instockDate}</td> 
                <td><%--${medicineInstock.manCode}--%>
                    <c:forEach items="${bmf}" var="bm">
                        <c:if test="${medicineInstock.manCode==bm.manCode}">
                            ${bm.manChnName}
                        </c:if>
                    </c:forEach>
                </td>
                <%--<td><img src="${path}/images/edit.gif" class="update" name="${medicineInstock.instockId}"></td>--%>
                <%--<td><img src="${path}/images/del.gif" class="delete" name="${medicineInstock.instockId}"></td>--%>
            </tr> 
        </c:forEach> 
    </table> 
</body> 
</html> 
