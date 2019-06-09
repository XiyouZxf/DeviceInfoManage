<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>设备管理</title>
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        var url;

        function deleteDevice(){
            var selectedRows=$("#dg").datagrid('getSelections');
            if(selectedRows.length==0){
                $.messager.alert("系统提示","请选择要删除的数据！");
                return;
            }
            var strIds=[];
            for(var i=0;i<selectedRows.length;i++){
                strIds.push(selectedRows[i].dev_id);
            }
            var ids=strIds.join(",");
            $.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
                if(r){
                    $.post("deviceDelete",{delIds:ids},function(result){
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

        function searchDevice(){

            $('#dg').datagrid('load',{
                d_dev_identity:$('#d_dev_identity').val(),
                d_dev_name:$('#d_dev_name').val(),
                d_dev_type:$('#d_dev_type').val(),
                d_dev_manufacturer:$('#d_dev_manufacturer').val(),
                d_dev_usingstatus:$('#d_dev_usingstatus').combobox("getValue"),
                d_sdev_buytime:$('#d_sdev_buytime').datebox("getValue"),
                d_edev_buytime:$('#d_edev_buytime').datebox("getValue"),
                d_dev_identification:$('#d_dev_identification').combobox("getValue"),
                d_depart_id:$('#d_depart_id').combobox("getValue"),

            });


        }

        function openDeviceAddDialog(){
            $("#dlg").dialog("open").dialog("setTitle","添加设备信息");
            url="deviceSave";
        }

        function saveDevice(){
            $("#fm").form("submit",{
                url:url,
                onSubmit:function(){
                    if($('#dev_usingstatus').combobox("getValue")==""){
                        $.messager.alert("系统提示","请选择使用状态");
                        return false;
                    }
                    // if($('#dev_buytime').datebox("getValue")==""){
                    //     $.messager.alert("系统提示","请选择购置时间");
                    //     return false;
                    // }
                    // if($('#dev_identification').combobox("getValue")==""){
                    //     $.messager.alert("系统提示","请选择设备标识");
                    //     return false;
                    // }
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

            $("#dev_identity").val("");
            $("#dev_name").val("");
            $("#dev_specification").val("");
            $("#dev_type").val("");
            $("#dev_manufacturer").val("");
            $("#dev_value").val("");

            $("#dev_installaddr").val("");
            $("#dev_usingstatus").combobox("setValue","");
            $("#dev_operater").val("");
            $("#dev_buytime").datebox("setValue","");
            $("#dev_identification").combobox("setValue","");

        }

        function closeDeviceDialog(){
            $("#dlg").dialog("close");
            resetValue();
        }

        function openDeviceModifyDialog(){
            var selectedRows=$("#dg").datagrid('getSelections');
            if(selectedRows.length!=1){
                $.messager.alert("系统提示","请选择一条要编辑的数据！");
                return;
            }
            var row=selectedRows[0];
            $("#dlg").dialog("open").dialog("setTitle","编辑设备信息");
            $("#fm").form("load",row);
            url="deviceSave?dev_id="+row.dev_id;
        }
    </script>
</head>
<body style="margin: 5px;">
<table id="dg" title="设备信息" class="easyui-datagrid" fitColumns="true"
       pagination="true" rownumbers="true" url="deviceList" fit="true" toolbar="#tb">
    <thead>
    <tr>
        <th field="cb" checkbox="true"></th>
        <th field="dev_id" width="50" align="center">ID</th>
        <th field="dev_identity" width="70" align="center">设备编号</th>
        <th field="dev_name" width="70" align="center">设备名称</th>
        <th field="dev_specification" width="100" align="center">规格型号</th>
        <th field="dev_type" width="70" align="center">设备类型</th>
        <th field="dev_manufacturer" width="100" align="center">生产厂商</th>
        <th field="dev_value" width="70" align="center">价值</th>
        <th field="depart_id" width="100" align="center" hidden="true">部门id</th>
        <th field="depart_name" width="100" align="center">所在部门</th>
        <th field="dev_installaddr" width="100" align="center">安装地点</th>
        <th field="dev_usingstatus" width="50" align="center">使用状态</th>
        <th field="dev_operater" width="50" align="center">操作人员</th>
        <th field="dev_buytime" width="100" align="center">购置时间</th>
        <%--<th field="dev_maintaintime" width="100" align="center" >上次维修</th>--%>
        <th field="dev_identification" width="100" align="center">设备标识</th>
    </tr>
    </thead>
</table>

<div id="tb">
    <div>
        <a href="javascript:openDeviceAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        <a href="javascript:openDeviceModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <a href="javascript:deleteDevice()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </div>
    <div>&nbsp;设备编号：&nbsp;<input type="text" name="d_dev_identity" id="d_dev_identity" size="10" required="true">
        &nbsp;设备名称：&nbsp;<input type="text" name="d_dev_name" id="d_dev_name" size="10" required="true"/>
        &nbsp;设备类型：&nbsp;<input type="text" name="d_dev_type" id="d_dev_type" size="10" required="true"/>
        &nbsp;生产厂商：&nbsp;<input type="text" name="d_dev_manufacturer" id="d_dev_manufacturer" size="10" required="true"/>
        &nbsp;使用状态：&nbsp;<select class="easyui-combobox" id="d_dev_usingstatus" name="d_dev_usingstatus" editable="false" panelHeight="auto">
                            <option value="">请选择状态</option>
                            <option value="在用">在用</option>
                            <option value="停用">停用</option>
                         </select>
        &nbsp;购置日期：&nbsp;<input class="easyui-datebox" name="d_sdev_buytime" id="d_sdev_buytime" editable="true" size="10"/>-><input class="easyui-datebox" name="d_edev_buytime" id="d_edev_buytime" editable="true" size="10"/>
        &nbsp;设备标识：&nbsp;<select class="easyui-combobox" id="d_dev_identification" name="d_dev_identification" editable="false" panelHeight="auto">
            <option value="">请选择标识</option>
            <option value="小型设备">小型设备</option>
            <option value="一般设备">一般设备</option>
            <option value="大型设备">大型设备</option>
        </select>
        &nbsp;所在部门：&nbsp;<input class="easyui-combobox" id="d_depart_id" name="d_depart_id"  data-options="panelHeight:'auto',editable:false,valueField:'depart_id',textField:'depart_name',url:'departmentComboList'"/>
        <a href="javascript:searchDevice()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a></div>
</div>

<div id="dlg" class="easyui-dialog" style="width: 600px;height: 320px;padding: 10px 20px"
     closed="true" buttons="#dlg-buttons">
    <form id="fm" method="post">
        <table cellspacing="10px;">
            <tr>
                <td>设备编号：</td>
                <td><input type="text" name="dev_identity" id="dev_identity" class="easyui-validatebox"  required="true"/></td>
                <td>&nbsp;&nbsp;</td>
                <td>设备名称：</td>
                <td><input type="text" name="dev_name" id="dev_name"class="easyui-validatebox"  required="true"/></td>
            </tr>
            <tr>
                <td>规格型号：</td>
                <td><input type="text" name="dev_specification" id="dev_specification" class="easyui-validatebox"  required="true"/></td>
                <td>&nbsp;&nbsp;</td>
                <td>设备类型：</td>
                <td><input type="text" name="dev_type" id="dev_type"  class="easyui-validatebox" required="true"/></td>
            </tr>
            <tr>
                <td>生产厂商：</td>
                <td><input type="text" name="dev_manufacturer" id="dev_manufacturer" class="easyui-validatebox"  required="true"/></td>
                <td>&nbsp;&nbsp;</td>
                <td>设备价值：</td>
                <td><input type="text" name="dev_value" id="dev_value" class="easyui-validatebox"  required="true"/></td>
            </tr>
            <tr>
                <td>所在部门：</td>
                <td><input class="easyui-combobox" id="depart_id" name="depart_id"  data-options="panelHeight:'auto',editable:false,valueField:'depart_id',textField:'depart_name',url:'departmentComboList'"/></td>
                <td>&nbsp;&nbsp;</td>
                <td>安装地点：</td>
                <td><input type="text" name="dev_installaddr" id="dev_installaddr" class="easyui-validatebox"  required="true"/></td>
            </tr>
            <tr>
                <td>使用状态：</td>
                <td><select class="easyui-combobox" id="dev_usingstatus" name="dev_usingstatus" editable="false" panelHeight="auto" style="width: 155px">
                    <option value="">请选择状态</option>
                    <option value="在用">在用</option>
                    <option value="停用">停用</option>
                </select></td>
                <td>&nbsp;&nbsp;</td>
                <td>操作人员：</td>
                <td><input type="text" name="dev_operater" id="dev_operater" class="easyui-validatebox" required="true"/></td>
            </tr>
            <tr>
                <td>购置时间：</td>
                <td><input class="easyui-datebox" name="dev_buytime" id="dev_buytime" editable="true" size="10"/></td>
                <td>&nbsp;&nbsp;</td>
                <td>设备标识：</td>
                <td><select class="easyui-combobox" id="dev_identification" name="dev_identification" editable="false" panelHeight="auto" style="width: 155px">
                    <option value="">请选择标识</option>
                    <option value="小型设备">小型设备</option>
                    <option value="一般设备">一般设备</option>
                    <option value="大型设备">大型设备</option>
                </select></td>
            </tr>
        </table>
    </form>
</div>

<div id="dlg-buttons">
    <a href="javascript:saveDevice()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:closeDeviceDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
</body>
</html>