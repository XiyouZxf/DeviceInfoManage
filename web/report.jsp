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

        function deleteReport(){
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
                    $.post("reportDelete",{delIds:ids},function(result){
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

        function searchReport(){

            $('#dg').datagrid('load',{
                r_report_no:$('#r_report_no').val(),
                r_report_serialNumber:$('#r_report_serialNumber').val(),
                r_report_devName:$('#r_report_devName').val(),

            });

        }

        function openReportAddDialog(){
            $("#dlg").dialog("open").dialog("setTitle","添加鉴定报告信息");
            url="reportSave";
        }

        function saveReport(){
            $("#fm").form("submit",{
                url:url,
                onSubmit:function(){
                    if($('#').combobox("getValue")==""){
                        $.messager.alert("系统提示","请选择");
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
            $("#report_no").val("");
            $("#report_name").val("");
            $("#report_serialNumber").val("");
            $("#report_identifyDepart").val("");
            $("#report_identifier").val("");


        }

        function closeReportDialog(){
            $("#dlg").dialog("close");
            resetValue();
        }

        function openReportModifyDialog(){
            var selectedRows=$("#dg").datagrid('getSelections');
            if(selectedRows.length!=1){
                $.messager.alert("系统提示","请选择一条要编辑的数据！");
                return;
            }
            var row=selectedRows[0];
            $("#dlg").dialog("open").dialog("setTitle","编辑报告信息");
            $("#fm").form("load",row);
            url="reportSave?report_id="+row.report_id;
        }
    </script>
</head>
<body style="margin: 5px;">
<table id="dg" title="报告信息" class="easyui-datagrid" fitColumns="true"
       pagination="true" rownumbers="true" url="reportList" fit="true" toolbar="#tb">
    <thead>
    <tr>
        <th field="cb" checkbox="true"></th>
        <th field="report_id" width="50" align="center">ID</th>
        <th field="report_no" width="70" align="center">证书编号</th>
        <th field="report_name" width="100" align="center">报告名称</th>
        <th field="dev_name" width="100" align="center">设备名称</th>
        <th field="report_serialNumber" width="70" align="center">出厂编号</th>
        <th field="dev_specification" width="100" align="center">型号/规格</th>
        <th field="report_identifyDepart" width="100" align="center">检定单位</th>
        <th field="report_identifier" width="100" align="center">检定员</th>
        <th field="report_identifyTime" width="100" align="center">检定日期</th>


    </tr>
    </thead>
</table>

<div id="tb">
    <div>
        <a href="javascript:openReportAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        <a href="javascript:openReportModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <a href="javascript:deleteReport()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </div>
    <div>&nbsp;证书编号：&nbsp;<input type="text" name="r_report_no" id="r_report_no" size="10" required="true">
        &nbsp;出厂编号：&nbsp;<input type="text" name="r_report_serialNumber" id="r_report_serialNumber" size="10" required="true"/>
        &nbsp;设备名称：&nbsp;<input type="text" name="r_report_devName" id="r_report_devName" size="10" required="true"/>

        <a href="javascript:searchReport()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a></div>
    </div>

<div id="dlg" class="easyui-dialog" style="width: 570px;height: 350px;padding: 10px 20px"
     closed="true" buttons="#dlg-buttons">
    <form id="fm" method="post">
        <table cellspacing="5px;">
            <tr>
                <td>证书编号：</td>
                <td><input type="text" name="report_no" id="report_no" class="easyui-validatebox" required="true"/></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>证书名称：</td>
                <td><input type="text" name="report_name" id="report_name" class="easyui-validatebox" required="true"/></td>
            </tr>
            <tr>
                <td>出厂编号：</td>
                <td><input class="easyui-validatebox" name="report_serialNumber" id="report_serialNumber"  required="true" editable="false" /></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>设备编号：</td>
                <td><input class="easyui-combobox" id="dev_identity" name="dev_identity"  data-options="panelHeight:'auto',editable:false,valueField:'dev_id',textField:'dev_identity',url:'deviceComboList'"/></td>
            </tr>
            <tr>
                <td>检定单位：</td>
                <td><input type="text" name="report_identifyDepart" id="report_identifyDepart" class="easyui-validatebox" required="true" /></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>检定员：</td>
                <td><input type="text" name="report_identifier" id="report_identifier" class="easyui-validatebox" required="true"/></td>
            </tr>
            <tr>
                <td>检定时间：</td>
                <td><input class="easyui-datebox" name="report_identifyTime" id="report_identifyTime" editable="true" size="10"/></td>

            </tr>
        </table>
    </form>
</div>

<div id="dlg-buttons">
    <a href="javascript:saveReport()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:closeReportDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
</body>
</html>