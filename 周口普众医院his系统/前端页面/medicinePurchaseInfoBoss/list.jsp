<%-- 
  Created by IntelliJ IDEA. 
  2018/12/04 
  To change this template use File | Settings | File Templates. 
--%> 
<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@include file="/common/common.jsp"%> 
<html> 
<head> 
    <script type="text/javascript"> 
        $(function () {
            $(".update").click(function () {
                var pchId = $(this).attr("name");
                var aa = $(this).parent().attr("name")
                if (aa==2){
                    location.href="${path}/medicinePurchaseInfo/loadse.action?pchId="+pchId;
                }else {
                    alert("只有采购未审批的药剂才能修改")
                }
             })
            $(".delete").click(function () { 
                var pchId = $(this).attr("name");
                var aa = $(this).parent().attr("name")
                if (aa==2){
                    location.href="${path}/medicinePurchaseInfo/deletes.action?pchId="+pchId;
                }else {
                    alert("只有采购未审批的药剂才能删除")
                }
             })
            $("#search").click(function () { 
                var medicineCodeid = $("#medicineCodeid").val();
                location.href = "${path}/medicinePurchaseInfo/searchs.action?medicineCodeid="+medicineCodeid;
             })
            $("#update").click(function () {
                var a = "";
                $(".che:checked").each(function () {
                    var b = $(this).attr("name")
                    var c = b.split(",");
                    if (c[1]==1){
                        a+=c[0]+",";
                    }
                })
                if(a!=""){
                    location.href = "${path}/medicinePurchaseInfo/updateBossTowe.action?a="+a;
                }else {
                    alert("只有采购已汇总的可以审批")
                }
            })
            $("#updateCt").click(function () {
                var a = "";
                $(".che:checked").each(function () {
                    var b = $(this).attr("name")
                    var c = b.split(",");
                    if (c[1]==2){
                        a+=c[0]+",";
                    }
                })
                if (a!=""){
                    location.href = "${path}/medicinePurchaseInfo/updateBossThere.action?a="+a;
                }else {
                    alert("只有采购已审批的可以取消")
                }
            })
        })  
    </script> 
</head> 
<body class="main"> 
    <div class="search"> 
        <span> 
            药品：<%--<input type="text" id="medicineCodeid" value="${searchObject.medicineCodeid}"> --%>
            <select id="medicineCodeid" value="">
                <c:forEach items="${mdc}" var="mc">
                    <c:if test="${searchObject.medicineCodeid==mc.codeId}">
                        <option value="${mc.codeId}">${mc.medicineName}</option>
                    </c:if>
                </c:forEach>
                <option></option>
                <c:forEach items="${mdc}" var="mc">
                    <c:if test="${searchObject.medicineCodeid!=mc.codeId}">
                        <option value="${mc.codeId}">${mc.medicineName}</option>
                    </c:if>
                </c:forEach>
            </select>
        </span> 
        <span> 
            <button id="search">查询</button> 
        </span>
        <span>
            <button id="update">批量审批</button>
        </span>
        <span>
            <button id="updateCt">取消审批</button>
        </span>
    </div> 
    <table> 
        <thead>
            <td></td>
            <td>序号</td> 
            <td>药品</td> 
            <td>供应商</td> 
            <td>采购数量</td> 
            <td>采购单价</td> 
            <td>采购总价</td> 
            <td>状态</td> 
            <td>汇总人</td> 
            <td>汇总日期</td> 
            <td>审批人</td> 
            <td>审批日期</td> 
            <td>编辑</td> 
            <td>删除</td> 
        </thead> 
        <c:forEach items="${list}" var="medicinePurchaseInfo" varStatus="status"> 
            <tr>
                <td><input type="checkbox" class="che" name="${medicinePurchaseInfo.pchId},${medicinePurchaseInfo.status}"></td>
                <td>${status.index+1}</td> 
                <td><%--${medicinePurchaseInfo.medicineCodeid}--%>
                    <c:forEach items="${mdc}" var="mc">
                        <c:if test="${medicinePurchaseInfo.medicineCodeid==mc.codeId}">
                            ${mc.medicineName}
                        </c:if>
                    </c:forEach>
                </td>
                <td><%--${medicinePurchaseInfo.manCode}--%>
                    <c:forEach items="${bmf}" var="bm">
                        <c:if test="${medicinePurchaseInfo.manCode==bm.manCode}">
                            ${bm.manChnName}
                        </c:if>
                    </c:forEach>
                </td>
                <td>${medicinePurchaseInfo.pchAmt}</td> 
                <td>${medicinePurchaseInfo.pchPrice}</td> 
                <td>${medicinePurchaseInfo.pchTotal}</td> 
                <td><%--${medicinePurchaseInfo.status}--%>
                    <c:if test="${medicinePurchaseInfo.status==1}">
                        采购未审批
                    </c:if>
                    <c:if test="${medicinePurchaseInfo.status==2}">
                        采购已审批
                    </c:if>
                    <c:if test="${medicinePurchaseInfo.status==3}">
                        已采购入库
                    </c:if>
                </td>
                <td><%--${medicinePurchaseInfo.pchUserid}--%>
                    <c:forEach items="${bus}" var="bu">
                        <c:if test="${medicinePurchaseInfo.pchUserid==bu.userId}">
                            ${bu.cname}
                        </c:if>
                    </c:forEach>
                </td>
                <td>${medicinePurchaseInfo.pchDate}</td> 
                <td><%--${medicinePurchaseInfo.apprvUserid}--%>
                    <c:forEach items="${bus}" var="bu">
                        <c:if test="${medicinePurchaseInfo.apprvUserid==bu.userId}">
                            ${bu.cname}
                        </c:if>
                    </c:forEach>
                </td>
                <td>${medicinePurchaseInfo.apprvDate}</td> 
                <td name="${medicinePurchaseInfo.status}"><img src="${path}/images/edit.gif" class="update" name="${medicinePurchaseInfo.pchId}"></td>
                <td name="${medicinePurchaseInfo.status}"><img src="${path}/images/del.gif" class="delete" name="${medicinePurchaseInfo.pchId}"></td>
            </tr> 
        </c:forEach> 
    </table> 
</body> 
</html> 
