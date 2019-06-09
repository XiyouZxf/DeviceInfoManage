<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>鉴定报告信息管理</title>
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        var url;

        function deleteEmployee(){
            var selectedRows=$("#dg").datagrid('getSelections');
            if(selectedRows.length==0){
                $.messager.alert("系统提示","请选择要删除的数据！");
                return;
            }
            var strIds=[];
            for(var i=0;i<selectedRows.length;i++){
                strIds.push(selectedRows[i].emp_id);
            }
            var ids=strIds.join(",");
            $.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
                if(r){
                    $.post("employeeDelete",{delIds:ids},function(result){
                        if(result.success){
                            $.messager.alert("系统提示","您已成功删除<font color=red>"+result.delNums+"</font>条数据！");
                            $("#dg").datagrid("reload");
                        }else{
                            $.messager.alert('系统提示',result.errorMsg);
                        }
                    },"json");
                }
            });
        }

        function searchEmployee(){

            $('#dg').datagrid('load',{
                no:$('#e_emp_no').val(),
                name:$('#e_emp_name').val(),
                sex:$('#e_emp_sex').combobox("getValue"),

            });

        }

        function openEmployeeAddDialog(){
            $("#dlg").dialog("open").dialog("setTitle","添加用户信息");
            url="employeeSave";
        }

        function saveEmployee(){
            $("#fm").form("submit",{
                url:url,
                onSubmit:function(){
                    if($('#emp_sex').combobox("getValue")==""){
                        $.messager.alert("系统提示","请选择性别");
                        return false;
                    }
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

        function resetValue(){
            $("#emp_no").val("");
            $("#emp_name").val("");
            $("#emp_sex").combobox("setValue","");
            $("#emp_tel_num").val("");
            $("#emp_addr").val("");
            $("#emp_email").val("");

        }

        function closeEmployeeDialog(){
            $("#dlg").dialog("close");
            resetValue();
        }

        function openEmployeeModifyDialog(){
            var selectedRows=$("#dg").datagrid('getSelections');
            if(selectedRows.length!=1){
                $.messager.alert("系统提示","请选择一条要编辑的数据！");
                return;
            }
            var row=selectedRows[0];
            $("#dlg").dialog("open").dialog("setTitle","编辑用户信息");
            $("#fm").form("load",row);
            url="employeeSave?emp_id="+row.emp_id;
        }
    </script>
</head>
<body style="margin: 5px;">
<table id="dg" title="用户信息" class="easyui-datagrid" fitColumns="true"
       pagination="true" rownumbers="true" url="employeeList" fit="true" toolbar="#tb">
    <thead>
    <tr>
        <th field="cb" checkbox="true"></th>
        <th field="emp_id" width="50" align="center">编号</th>
        <th field="emp_no" width="100" align="center">帐号</th>
        <th field="emp_name" width="50" align="center">姓名</th>
        <th field="emp_sex" width="50" align="center">性别</th>

        <th field="emp_tel_num" width="100" align="center">电话号码</th>
        <th field="emp_addr" width="100" align="center">地址</th>
        <th field="emp_email" width="100" align="center">E-mail</th>

    </tr>
    </thead>
</table>

<div id="tb">
    <div>
        <a href="javascript:openEmployeeAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        <a href="javascript:openEmployeeModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <a href="javascript:deleteEmployee()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </div>
    <div>&nbsp;帐号：&nbsp;<input type="text" name="e_emp_no" id="e_emp_no" size="10" required="true">
        &nbsp;姓名：&nbsp;<input type="text" name="e_emp_name" id="e_emp_name" size="10" required="true"/>
        &nbsp;性别：&nbsp;<select class="easyui-combobox" id="e_emp_sex" name="e_emp_sex" editable="false" panelHeight="auto">
                            <option value="">请选择...</option>
                            <option value="男">男</option>
                            <option value="女">女</option>
                         </select>

        <a href="javascript:searchEmployee()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a></div>
    </div>

<div id="dlg" class="easyui-dialog" style="width: 570px;height: 350px;padding: 10px 20px"
     closed="true" buttons="#dlg-buttons">
    <form id="fm" method="post">
        <table cellspacing="5px;">
            <tr>
                <td>帐   号：</td>
                <td><input type="text" name="emp_no" id="emp_no" class="easyui-validatebox" required="true"/></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>姓   名：</td>
                <td><input type="text" name="emp_name" id="emp_name" class="easyui-validatebox" required="true"/></td>
            </tr>
            <tr>
                <td>性   别：</td>
                <td><select class="easyui-combobox" id="emp_sex" name="emp_sex" editable="false" panelHeight="auto" style="width: 155px">
                    <option value="">请选择...</option>
                    <option value="男">男</option>
                    <option value="女">女</option>
                </select></td>
                <td></td>
                <td>电话号码：</td>
                <td><input class="easyui-validatebox" name="emp_tel_num" id="emp_tel_num" required="true" editable="false" /></td>
            </tr>
            <tr>
                <td>住   址：</td>
                <td><input class="easyui-validatebox" name="emp_addr" id="emp_addr"  required="true" editable="false" /></td>
                <td></td>
                <td>E-mail：</td>
                <td><input type="text" name="emp_email" id="emp_email" class="easyui-validatebox" required="true" validType="email"/></td>
            </tr>
        </table>
    </form>
</div>

<div id="dlg-buttons">
    <a href="javascript:saveEmployee()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:closeEmployeeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
</body>
</html>