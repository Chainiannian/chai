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
            $("#addBtn").click(function () { 
                location.href="${path}/medicineReqPlan/loadBoss.action";
             })
            $(".update").click(function () {
                var aa = $(this).parent().attr("name")
                if (aa==1 || aa==2){
                    var reqplnno = $(this).attr("name");
                    location.href="${path}/medicineReqPlan/loadBosss.action?reqplnno="+reqplnno;
                }else {
                    alert("只有审批中和已审批的药剂才能修改")
                }
             })
            $(".delete").click(function () {
                var aa = $(this).parent().attr("name")
                if (aa==1 || aa==2){
                    var reqplnno = $(this).attr("name");
                    location.href="${path}/medicineReqPlan/deleteBoss.action?reqplnno="+reqplnno;
                }else {
                    alert("只有审批中和已审批的药剂才能删除")
                }
             })
            $("#search").click(function () { 
                var medicineCodeid = $("#medicineCodeid").val();
                location.href = "${path}/medicineReqPlan/searchBoss.action?medicineCodeid="+medicineCodeid;
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
                    location.href = "${path}/medicineReqPlan/updateBossTowe.action?a="+a;
                }else {
                    alert("只有审批中的可以审批")
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
                    location.href = "${path}/medicineReqPlan/updateBossThere.action?a="+a;
                }else {
                  alert("只有已审批的可以取消")
                }
             })
        })  
    </script> 
</head> 
<body class="main"> 
    <div class="search"> 
        <span> 
            药品：<%--<input type="text" id="medicineCodeid" value="${searchObject.medicineCodeid}"> --%>
            <select id="medicineCodeid" value="${searchObject.medicineCodeid}">
                <c:forEach items="${medicineCode}" var="mc">
                    <c:if test="${searchObject.medicineCodeid==mc.codeId}">
                        <option value="${mc.codeId}">${mc.medicineName}</option>
                    </c:if>
                </c:forEach>
                <option></option>
                <c:forEach items="${medicineCode}" var="mc">
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
            <button id="addBtn">增加</button> 
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
            <td>需求数量</td> 
            <td>申请人</td> 
            <td>申请日期</td> 
            <td>用途</td> 
            <td>状态</td> 
            <td>审批人</td> 
            <td>审批日期</td> 
            <td>编辑</td> 
            <td>删除</td> 
        </thead>
        <c:forEach items="${list}" var="medicineReqPlan" varStatus="status"> 
            <tr> 
                <td><input type="checkbox" class="che" name="${medicineReqPlan.reqplnno},${medicineReqPlan.status}"></td>
                <td>${status.index+1}</td>
                <td>
                    <c:forEach items="${medicineCode}" var="mc">
                        <c:if test="${medicineReqPlan.medicineCodeid==mc.codeId}">
                            ${mc.medicineName}
                        </c:if>
                    </c:forEach>
                </td>
                <td>${medicineReqPlan.reqamt}</td> 
                <td>
                    <c:forEach items="${baseUser}" var="bu">
                        <c:if test="${medicineReqPlan.appUserid==bu.userId}">
                            ${bu.cname}
                        </c:if>
                    </c:forEach>
                </td>
                <td>${medicineReqPlan.appDate}</td> 
                <td>${medicineReqPlan.purpose}</td> 
                <td>
                    <c:if test="${medicineReqPlan.status==1}">
                        审批中
                    </c:if>
                    <c:if test="${medicineReqPlan.status==2}">
                        已审批
                    </c:if>
                    <c:if test="${medicineReqPlan.status==3}">
                        已汇总
                    </c:if>
                    <c:if test="${medicineReqPlan.status==4}">
                        已采购
                    </c:if>
                </td>
                <td><%--${medicineReqPlan.apprvUserid}--%>
                    <c:forEach items="${baseUser}" var="bu">
                        <c:if test="${medicineReqPlan.apprvUserid==bu.userId}">
                            ${bu.cname}
                        </c:if>
                    </c:forEach>
                </td>
                <td>${medicineReqPlan.apprvDate}</td> 
                <td name="${medicineReqPlan.status}"><img src="${path}/images/edit.gif" class="update" name="${medicineReqPlan.reqplnno}"></td>
                <td name="${medicineReqPlan.status}"><img src="${path}/images/del.gif" class="delete" name="${medicineReqPlan.reqplnno}"></td>
            </tr> 
        </c:forEach> 
    </table> 
</body> 
</html> 
