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
	<script type="text/javascript">
        var url;
        function searchUser(){
            $('#dg').datagrid('load',{
                userName:$('#s_userName').val()
            });
        }
        function deleteUser(){
            var selectedRows=$("#dg").datagrid('getSelections');
            if(selectedRows.length==0){
                $.messager.alert("系统提示","请选择要删除的数据！");
                return;
            }
            var strIds=[];
            for(var i=0;i<selectedRows.length;i++){
                strIds.push(selectedRows[i].id);
            }
            var ids=strIds.join(",");
            $.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
                if(r){
                    $.post("userDelete",{delIds:ids},function(result){
                        if(result.success){
                            $.messager.alert("系统提示","您已成功删除<font color=red>"+result.delNums+"</font>条数据！");
                            $("#dg").datagrid("reload");
                        }else{
                            $.messager.alert('系统提示','<font color=red>'+selectedRows[result.errorIndex].gradeName+'</font>'+result.errorMsg);
                        }
                    },"json");
                }
            });
        }
        function openUserAddDialog(){
            $("#dlg").dialog("open").dialog("setTitle","添加用户");
            url="userSave";   //////////////////////////////
        }
        function closeUserDialog(){
            $("#dlg").dialog("close");
            resetValue();
        }
        function resetValue(){
            $("#userNo").val("");
            $("#userName").val("");
            $("#telNumber").val("");
            $("#userPass").val("");
            $("#userAddr").val("");
            $("#userEmail").val("");
        }
        function saveUser(){
            $("#fm").form("submit",{
                url:url,
                onSubmit:function(){
                    return $(this).form("validate");
                },
                success:function(result){
                    if(result.errorMsg){
                        $.messager.alert("系统提示",result.errorMsg);
                        return;
                    }else{
                        $.messager.alert("系统提示","保存成功");
                        resetValue();
                        $("#dlg").dialog("close");
                        $("#dg").datagrid("reload");
                    }
                }
            });
        }
        function openUserModifyDialog(){
            var selectedRows=$("#dg").datagrid('getSelections');
            if(selectedRows.length!=1){
                $.messager.alert("系统提示","请选择一条要编辑的数据！");
                return;
            }
            var row=selectedRows[0];
            $("#dlg").dialog("open").dialog("setTitle","编辑员工信息");
            $("#fm").form("load",row);
            url="userSave?id="+row.id;
        }
	</script>
</head>
<body style="margin: 5px;">
    <table id="dg" title="用户信息" class="easyui-datagrid" fitColumns="true"
	 pagination="true" rownumbers="true" url="userList"  toolbar="#tb" fit="true">
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

	<div id="tb">
		<div>
			<a href="javascript:openUserAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:openUserModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a href="javascript:deleteUser()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</div>
		<div>&nbsp;员工名称：&nbsp;<input type="text" name="s_userName" id="s_userName"/>
			<a href="javascript:searchUser()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
		</div>
	</div>

	<div id="dlg" class="easyui-dialog" style="width: 400px;height: 280px;padding: 10px 20px"
		 closed="true" buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table>
				<tr>
					<td>用户账号：</td>
					<td><input type="text" name="userNo" id="userNo" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td>姓   名：</td>
					<td><input type="text" name="userName" id="userName" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td>电   话：</td>
					<td><input type="text" name="telNumber" id="telNumber" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td>密   码：</td>
					<td><input type="text" name="userPass" id="userPass" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td>地   址：</td>
					<td><input type="text" name="userAddr" id="userAddr" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td>邮   箱：</td>
					<td><input type="text" name="userEmail" id="userEmail" class="easyui-validatebox" required="true"/></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:saveUser()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeUserDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
           

</body>
</html>