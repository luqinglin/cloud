<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/taglibs.jsp" %>
<html>
<head>
    <title>首页</title>
</head>
<body>
<script type="text/javascript">
    function login(){
    	window.location.href="/login";
    }
</script>
	<div class="content">
    <div class="about-main">
        <p>
            <strong>首页</strong>

        </p>
        <input type="button" value="登录" onclick="login()"/>
        
    </div>
    <div class="clear"></div>
    </div>
</body>
</html>
