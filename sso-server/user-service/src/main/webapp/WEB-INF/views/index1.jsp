<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/taglibs.jsp" %>
<html>
<head>
    <title>首页</title>
    <script src="${ctx}/resources/js/jquery-1.11.0.min.js"></script>
	<script src="${ctx}/resources/js/sweetalert.js"></script>
	<script src="${ctx}/resources/js/public.js"></script>
</head>
<body>
<script type="text/javascript">
    function logout(){
    	 $.ajax({
             url:"${ctx}/logout",
             type: "post",
             data: {"access_token": getAuth().access_token},
             success: function (data) {
             /* delCookie("access_token"); */
                clearAuth();
                window.location.href = '${ctx}/';
             }
         });
    }
   /*  function getCookie(c_name){
	    if (document.cookie.length>0){//先查询cookie是否为空，为空就return ""
	    	c_start=document.cookie.indexOf(c_name + "=")//通过String对象的indexOf()来检查这个cookie是否存在，不存在就为 -1
		    　　if (c_start!=-1){
		    	c_start=c_start + c_name.length+1//最后这个+1其实就是表示"="号啦，这样就获取到了cookie值的开始位置
		    	c_end=document.cookie.indexOf(";",c_start)//其实我刚看见indexOf()第二个参数的时候猛然有点晕，后来想起来表示指定的开始索引的位置...这句是为了得到值的结束位置。因为需要考虑是否是最后一项，所以通过";"号是否存在来判断
		    if (c_end==-1)
		    	c_end=document.cookie.length
		    	return unescape(document.cookie.substring(c_start,c_end))//通过substring()得到了值。想了解unescape()得先知道escape()是做什么的，都是很重要的基础，想了解的可以搜索下，在文章结尾处也会进行讲解cookie编码细节
			}
    	}
    	return ""
    } */
    
    /* function getCookie(name)
    {
	    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	    if(arr=document.cookie.match(reg))
	    	return unescape(arr[2]);
	    else
	    	return null;
    } */
    /* function delCookie(name)
    {
	    var exp = new Date();
	    exp.setTime(exp.getTime() - 1);
	    var cval=getCookie(name);
	    if(cval!=null)
	    document.cookie= name + "="+cval+";expires="+exp.toGMTString();
    }
     */
    function refreshToken(){
    	var auth = getAuth();
    	$("#oldToken").html(auth.access_token);
    	$("#oldRefreshToken").html(auth.refresh_token);
    	$.ajax({
            url: '${ctx}/oauth/refreshToken',
            method: 'post',
            data: {"refresh_token":auth.refresh_token},
            success: function (data) {
            	$("#newRefreshToken").html(data.refresh_token);
            	$("#newToken").html(data.access_token);
            	saveAuth(data);
            }
        })
    }
</script>
	<div class="content">
    <div class="about-main">
        <p>
            <strong>登陆后的首页</strong>

        </p>
        <input type="button" value="注销" onclick="logout()"/>
        <input type="button" value="刷新token" onclick="refreshToken()"/>
        <div>原刷新token为</div><div id="oldRefreshToken"></div>
        <div>新刷新token为</div><div id="newRefreshToken"></div>
        <div>原token为</div><div id="oldToken"></div>
        <div>新token为</div><div id="newToken"></div>
    </div>
    <div class="clear"></div>
    </div>
</body>
</html>
