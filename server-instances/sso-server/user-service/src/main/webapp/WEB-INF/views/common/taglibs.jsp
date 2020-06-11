<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String ctx = request.getContextPath();
    pageContext.setAttribute("ctx", ctx);
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
    pageContext.setAttribute("basePath", basePath);
%>
<script type="text/javascript">
    var rootPath = '<%=ctx%>';

    /* const FULL_CHARTER = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopgrstuvwxyz';
    const oauth_server='http://127.0.0.1:3000';
    const redirect_uri='http://127.0.0.1:9090';
    const client_id='sso-user';
    const client_secret='123456'; */
    const token_storage = localStorage;
    //格式化时间
    function dateTimeFormatter(val) {

        if (val == undefined || val == "")
            return "";
        var date;
        if (val instanceof Date) {
            date = val;
        } else {
            date = new Date(val);
        }
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        var d = date.getDate();

        var h = date.getHours();
        var mm = date.getMinutes();
        var s = date.getSeconds();

        var dateStr = y + '-' + (m < 10 ? ('0' + m) : m) + '-'
            + (d < 10 ? ('0' + d) : d);
        var TimeStr = h + ':' + (mm < 10 ? ('0' + mm) : mm) + ':'
            + (s < 10 ? ('0' + s) : s);

        return dateStr + ' ' + TimeStr;
    }

    /* function ajaxSetup() {
        $.ajaxSetup({
            timeout : 30000,
            beforeSend : function(xhr) {
                if(this.url.endsWith('/oauth/token')){
                    return true;
                }
                if (getAuth() == null){
                    fetchToken();
                }
                var auth = getAuth();
                if(auth != null){
                    xhr.setRequestHeader("Authorization", auth.token_type + ' ' + auth.access_token);
                } else {
                    return false;
                }
                return true;
            },
            complete : function(xhr, ts) {
                if (xhr.status == 401 && xhr.responseJSON.error =='invalid_token') {
                   refreshToken();
                }
            }
        }); */
        function getAuth(){
            let auth = token_storage.getItem('auth');
            return JSON.parse(auth);
        }
         
        function saveAuth(sResponse){
            token_storage.setItem("auth", JSON.stringify(sResponse));
        }
         
        function clearAuth(){
            token_storage.removeItem('auth');
        }
         
        /* function logout(){
            token_storage.removeItem('auth');
            window.location.href = oauth_server+"/logout?redirect_uri="+redirect_uri;
        } */
         
        /* function getCode(){
        var state='';
        for (var a=0;a<6;a++){
            state+=FULL_CHARTER[Math.floor(Math.random() * 52)];
        }
        var url = oauth_server+"/oauth/authorize?client_id="+client_id+"&client_secret="+client_secret+
                  "&response_type=code&state="+state+"&redirect_uri="+redirect_uri;
         window.location = url;
         //window.open(url);
        }*/         
        /* function refreshToken(){
            var auth = getAuth();
            var data={
                    'client_id': client_id,
                    'client_secret': client_secret,
                    'grant_type':'refresh_token',
                    'refresh_token':auth.refresh_token
                    };
                $.ajax({
                   url: oauth_server+"/oauth/token",
                   type:"post",
                   data:data,
                   async: false,
                   contentType: 'application/x-www-form-urlencoded',
                   success: function (sResponse) {
                    saveAuth(sResponse);
                    console.log('refresh_token ok: ' + sResponse.access_token+'  expires_in:'+sResponse.expires_in);
                   },
                   error:function(a,b,c){
                    if (a.status==400 && a.responseJSON.error=='invalid_grant'){
                        console.log('refresh token invalid');
                        clearAuth();
                    }
                   }
                });
        } */
         
       /*  function checkToken(){
                    $.ajax({
                       url: oauth_server+"/oauth/check_token",
                       type:"get",
                       async: false,
                       data: {'token': getAuth().access_token},
                       contentType: 'application/x-www-form-urlencoded',
                       success: function (sResponse) {
                        console.log('check_token : ' + sResponse);
                       },
                       error:function(a,b,c){
                        console.log(a.responseJSON);
                       }
                    });
        } */
         
        /* function getServerdata(){
            $.get(oauth_server+"/msg", function(data) {
                    $("#user").html(data);
                  });
        }
          */
         
        /* $(function() {
            ajaxSetup();
        }); */
</script>
<div style='display:none'>
    <script type="text/javascript">
        var cnzz_protocol =(("https:" == document.location.protocol)?"https://":"http://");document.write(unescape("%3Cspanid='cnzz_stat_icon_1277582788'%3E%3C/span%3E%3Cscript src='"+cnzz_protocol+"s23.cnzz.com/z_stat.php%3Fid%3D1277582788%26show%3Dpic1'type='text/javascript'%3E%3C/script%3E"));
    </script>
</div>