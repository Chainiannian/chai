<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/3 0003
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/common.jsp"%>
<html>
<head>
    <title>Title</title>
    <style>
        span{
            display: inline-block;
            width: 30%;
        }
        input[type='text'],input[type='datetime']{
            margin-top: 10px;
        }
    </style>
    <script>
        $(function () {
            $("#oldPassword").blur(function () {
                var oldPassword = $("#oldPassword").val();
                $.get("${path}/baseUser/oldPassword.action",{password:oldPassword,userId:${bucct.userId}},function (data) {
                    if (data=="Y"){
                        $("#flagOldPassword").html("") ;
                    }else {
                        $("#flagOldPassword").html("旧密码错误").css("color","red");
                    }
                })
            })
            $("#repeatPassword").keyup(function () {
                var password = $("#password").val();
                var repeatPassword = $("#repeatPassword").val();
                if (password==repeatPassword){
                    $("#flagRepeatPassword").html("") ;
                    $("#sub").attr("type","submit");
                }else {
                    $("#flagRepeatPassword").html("请确认密码").css("color","red");
                }
            })
            <%--$("#sub").click(function () {--%>
                <%--window.close();--%>
                <%--window.opener.parent.location.href="${path}/baseUser/logout.action"--%>
            <%--})--%>
            if (${flag=="true"}){
                window.close();
                window.opener.parent.location.href="${path}/baseUser/logout.action"
            }
        })
    </script>
</head>

<body class="main">
    <h1>修改密码</h1>
    <form action="${path}/baseUser/updatePassword.action">
        <div class="update">
            <div class="left">
                <span>请输入旧密码</span>
                <input type="text" id="oldPassword"><span id="flagOldPassword"></span>
            </div>
            <div class="right">
            </div>
            <div class="left">
                <span>新密码</span>
                <input type="password" name="password" id="password">
            </div>
            <div class="right">
            </div>
            <div class="left">
                <span>确认密码</span>
                <input type="password" id="repeatPassword">
                <span id="flagRepeatPassword"></span>
            </div>
            <div class="buttons">
                <input type="button" id="sub" value="提交">
                <input type="button" onclick="history.back()" value="返回">
            </div>
        </div>
    </form>
</body>

</html>
