<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<title>设备管理登录</title>
<meta name="author" content="DeathGhost" />
<link rel="stylesheet" type="text/css" href="login/css/style.css" tppabs="login/css/style.css" />
<style>
body{height:100%;background:#16a085;overflow:hidden;}
canvas{z-index:-1;position:absolute;}
</style>
<script src="login/js/jquery.js"></script>
<script src="login/js/verificationNumbers.js" tppabs="login/js/verificationNumbers.js"></script>
<script src="login/js/Particleground.js" tppabs="login/js/Particleground.js"></script>
<script>
$(document).ready(function() {
  //粒子背景特效
  $('body').particleground({
    dotColor: '#5cbdaa',
    lineColor: '#5cbdaa'
  });
  //验证码
  createCode();
  //测试提交，对接程序删除即可
  $(".submit_btn").click(function(){
	  location.href="javascrpt:;"
	  });
});
</script>
</head>
<body>
<dl class="admin_login">
 <dt>
  <strong>设备信息管理系统</strong>
  <em>Device Management System</em>
 </dt>
 
 <form action="" method="post">
 <dd class="user_icon">
  <input type="text" id="username" name="username" value="${username }" placeholder="账号" class="login_txtbx"/>
 </dd>
 <dd class="pwd_icon">
  <input type="password" value="${password }" name="password" id="password" placeholder="密码" class="login_txtbx"/>
 </dd>
 <dd class="val_icon">
  <div class="checkcode">
    <input type="text" id="J_codetext" placeholder="验证码" maxlength="4" class="login_txtbx">
    <canvas class="J_codeimg" id="myCanvas" onclick="createCode()">对不起，您的浏览器不支持canvas，请下载最新版浏览器!</canvas>
  </div>
  <input type="button" value="验证码核验" class="ver_btn" onClick="validate();">
 </dd>
 <dd>
  <input type="submit" value="立即登陆" class="submit_btn"/>
 </dd>
 </form>
 <dd>
  <p>版权所有</p>
  <font color="red">${error }</font>
 </dd>
</dl>
</body>
</html>
