<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户信息管理</title>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
    <table id="dg" title="班级信息" class="easyui-datagrid" fitColumns="true"
	 pagination="true" rownumbers="true" url="userList"  toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true"></th>
				<th field="id" width="50">编号</th>
				<th field="user_no" width="70">用户账号</th>
				<th field="user_name" width="70">姓名</th>
				<th field="user_tel_num" width="80">电话</th>
				
				<th field="user_addr" width="60">地址</th>
				<th field="user_email" width="60">邮箱</th>
				
			</tr>
		</thead>
	</table>
           

</body>
</html>