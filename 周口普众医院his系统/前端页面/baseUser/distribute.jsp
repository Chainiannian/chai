<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/29 0029
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>下拉框</title>
    <style type="text/css">
        body{
            background-color: #A6FFA6;
        }
        select{
            width: 300px;
            height: 450px;
            padding: 10px;
            font-size: 14px;
            background-color: #bfbf2b;color: white;
        }
        select option{
            height: 30px;
            line-height: 30px;
            vertical-align: middle;
            padding-left: 10px;
            padding-top: 10px;
        }
        .title{
            display: inline-block;
            width: 45%;
            font-size: 30px;text-align: center;
            padding: 20px 0px;
        }
        #left{
            margin-left: 100px;
        }
        #right{
            margin-left: 20px;
        }
        .buttons{
            display: inline-block;height: 450px;width: 260px;
            vertical-align: top;
            text-align: center;
        }
        .buttons button{
            text-align: center;
            width: 160px;
            height: 40px;
            border-radius: 5px;
            margin-top: 35px;
            background-color: #00EC00;color: white;border: 0px;
        }
    </style>
    <script type="text/javascript">
        $(function(){
            $(".buttons button:eq(0)").click(function(){
                var $selected = $("#left option:selected");
                $selected.remove();
                var $right = $("#right");
                $selected.appendTo($right);
            })
            $(".buttons button:eq(1)").click(function(){
                var $selected = $("#left option");
                $selected.remove();
                var $right = $("#right");
                $selected.appendTo($right);
            })
            $(".buttons button:eq(2)").click(function(){
                var $selected = $("#right option:selected");
                $selected.remove();
                var $left = $("#left");
                $selected.appendTo($left);
            })
            $(".buttons button:eq(3)").click(function(){
                var $selected = $("#right option");
                $selected.remove();
                var $left = $("#left");
                $selected.appendTo($left);
            })
            $(".buttons button:eq(4)").click(function(){
                var userid = $(this).val();
                var s = "";
                $("#right option").each(function () {
                    s+=$(this).val()+",";
                });
                location.href = "${path}/baseUser/inserts.action?userid="+userid+"&s="+s;
            })

        })

    </script>
</head>
<body>
<div>
    <span class="title">未拥有角色</span>
    <span class="title">已拥有角色</span>
</div>
<select multiple="multiple" id="left" >
    <c:forEach items="${left}" var="dis">
        <option value="${dis.rid}">${dis.rname}</option>
    </c:forEach>

</select>
<div class="buttons">
    <button>></button>
    <button>>></button>
    <button><</button>
    <button><<</button>
    <button value="${userId}">提交</button>
    <button onclick="history.back()">返回</button>
</div>
<select multiple="multiple" id="right" >
    <c:forEach items="${right}" var="dis">
        <option value="${dis.rid}">${dis.rname}</option>
    </c:forEach>
</select>

</body>
</html>
