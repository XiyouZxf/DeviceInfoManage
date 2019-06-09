<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>部门信息管理</title>
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        var url;

        function deleteDepartment(){
            var selectedRows=$("#dg").datagrid('getSelections');
            if(selectedRows.length==0){
                $.messager.alert("系统提示","请选择要删除的数据！");
                return;
            }
            var strIds=[];
            for(var i=0;i<selectedRows.length;i++){
                strIds.push(selectedRows[i].depart_id);
            }
            var ids=strIds.join(",");
            $.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
                if(r){
                    $.post("departmentDelete",{delIds:ids},function(result){
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

        function searchDepartment(){

            $('#dg').datagrid('load',{
                identity:$('#d_depart_identity').val(),
                name:$('#d_depart_name').val(),


            });

        }

        function openDepartmentAddDialog(){
            $("#dlg").dialog("open").dialog("setTitle","添加部门信息");
            url="departmentSave";
        }

        function saveDepartment(){
            $("#fm").form("submit",{
                url:url,
                onSubmit:function(){
                    if($('#depart_status').combobox("getValue")==""){
                        $.messager.alert("系统提示","请选择状态");
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
            $("#depart_identity").val("");
            $("#depart_name").val("");
            $("#depart_status").combobox("setValue","");
            $("#depart_tel_num").val("");
            $("#depart_includePeople").val("");
            $("#depart_desc").val("");

        }

        function closeDepartmentDialog(){
            $("#dlg").dialog("close");
            resetValue();
        }

        function openDepartmentModifyDialog(){
            var selectedRows=$("#dg").datagrid('getSelections');
            if(selectedRows.length!=1){
                $.messager.alert("系统提示","请选择一条要编辑的数据！");
                return;
            }
            var row=selectedRows[0];
            $("#dlg").dialog("open").dialog("setTitle","编辑部门信息");
            $("#fm").form("load",row);
            url="departmentSave?depart_id="+row.depart_id;
        }
    </script>
</head>
<body style="margin: 5px;">
<table id="dg" title="部门信息" class="easyui-datagrid" fitColumns="true"
       pagination="true" rownumbers="true" url="departmentList" fit="true" toolbar="#tb">
    <thead>
    <tr>
        <th field="cb" checkbox="true"></th>
        <th field="depart_id" width="50" align="center">编号</th>
        <th field="depart_identity" width="100" align="center">标识</th>
        <th field="depart_name" width="50" align="center">部门名称</th>
        <th field="depart_includePeople" width="50" align="center">部门人数</th>
        <th field="depart_tel_num" width="100" align="center">电话号码</th>
        <th field="depart_status" width="100" align="center">状态</th>
        <th field="depart_desc" width="100" align="center">备注</th>
    </tr>
    </thead>
</table>

<div id="tb">
    <div>
        <a href="javascript:openDepartmentAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        <a href="javascript:openDepartmentModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <a href="javascript:deleteDepartment()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </div>
    <div>&nbsp;标识：&nbsp;<input type="text" name="d_depart_identity" id="d_depart_identity" size="10" required="true">
        &nbsp;名称：&nbsp;<input type="text" name="d_depart_name" id="d_depart_name" size="10" required="true"/>
        <a href="javascript:searchDepartment()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a></div>
</div>

<div id="dlg" class="easyui-dialog" style="width: 570px;height: 350px;padding: 10px 20px"
     closed="true" buttons="#dlg-buttons">
    <form id="fm" method="post">
        <table cellspacing="5px;">
            <tr>
                <td>ID 标识:</td>
                <td><input type="text" placeholder="例如：KS10" name="depart_identity" id="depart_identity" class="easyui-validatebox" required="true"/></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>名    称:</td>
                <td><input type="text" placeholder="例如：生产部" name="depart_name" id="depart_name" class="easyui-validatebox" required="true"/></td>
            </tr>
            <tr>
                <td>状      态:</td>
                <td><select class="easyui-combobox" id="depart_status" name="depart_status" editable="false" panelHeight="auto" style="width: 100px">
                    <option value="">请选择...</option>
                    <option value="运营中">运营中</option>
                    <option value="休整中">休整中</option>
                </select></td>
                <td></td>
                <td>联系电话:</td>
                <td><input class="easyui-validatebox" name="depart_tel_num" id="depart_tel_num" required="true" editable="false" /></td>
            </tr>
            <tr>
                <td>员工数:</td>
                <td><input class="easyui-validatebox" placeholder="例如：10" name="depart_includePeople" id="depart_includePeople"  required="true" editable="false" /></td>
                <td></td>
            </tr>
            <tr>
                <td valign="top">备     注:</td>
                <td colspan="4"><textarea rows="7" cols="50" placeholder="有关部门信息的具体描述" name="depart_desc" id="depart_desc"></textarea></td>
            </tr>
        </table>
    </form>
</div>
<div id="dlg-buttons">
    <a href="javascript:saveDepartment()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:closeDepartmentDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
</body>
</html>