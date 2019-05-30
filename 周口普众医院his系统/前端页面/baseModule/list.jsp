<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/25 0025
  Time: 21:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/common.jsp"%>
<html>
<head>
    <title>Title</title>

    <script>
        $(function () {
            $("#addBtn").click(function () {
                location.href="${path}/jsp/baseModule/add.jsp";
            })
            $(".update").click(function () {
                var mid = $(this).attr("name");
                location.href="${path}/baseModule/load.action?mid="+mid;
            })
            $(".delete").click(function () {
                var mid = $(this).attr("name");
                location.href="${path}/baseModule/delete.action?mid="+mid;
            })
            $("#search").click(function () {
                var mname = $("#mname").val();
                location.href = "${path}/baseModule/lists.action?mname="+mname;
            })
        })
    </script>
</head>
<body class="main">
    <div class="search">
        <span>
            模块名：<input type="text" id="mname" value="${searchObject.mname}">
        </span>
        <span>
            <button id="search">查询</button>
        </span>
        <span>
            <button id="addBtn">增加</button>
        </span>
    </div>
    <table>
        <thead>
            <td>序号</td>
            <td>模块名</td>
            <td>修改</td>
            <td>删除</td>
        </thead>
        <c:forEach items="${list}" var="baseModule" varStatus="status">
            <tr>
                <td>${status.index+1}</td>
                <td>${baseModule.mname}</td>
                <td><img src="${path}/images/edit.gif" class="update" name="${baseModule.mid}"></td>
                <td><img src="${path}/images/del.gif" class="delete" name="${baseModule.mid}"></td>
            </tr>
        </c:forEach>
    </table>


</body>
</html>
