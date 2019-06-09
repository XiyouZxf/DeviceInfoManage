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

        function deleteExamineSchedule(){
            var selectedRows=$("#dg").datagrid('getSelections');
            if(selectedRows.length==0){
                $.messager.alert("系统提示","请选择要删除的数据！");
                return;
            }
            var strIds=[];
            for(var i=0;i<selectedRows.length;i++){
                strIds.push(selectedRows[i].exam_id);
            }
            var ids=strIds.join(",");
            $.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
                if(r){
                    $.post("examineScheduleDelete",{delIds:ids},function(result){
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

        function searchExamineSchedule(){
            $('#dg').datagrid('load',{
                d_dev_identity:$('#d_dev_identity').val(),
                d_dev_name:$('#d_dev_name').val(),
                d_dev_status:$('#d_dev_status').combobox("getValue"),
                d_sdev_buytime:$('#d_sdev_buytime').datebox("getValue"),
                d_edev_buytime:$('#d_edev_buytime').datebox("getValue"),
                d_depart_id:$('#d_depart_id').combobox("getValue"),
            });


        }

        function openExamineScheduleAddDialog(){
            $("#dlg").dialog("open").dialog("setTitle","添加设备信息");
            url="examineScheduleSave";
        }

        function saveExamineSchedule(){
            $("#fm").form("submit",{
                url:url,
                onSubmit:function(){
                    if($('#exam_status').combobox("getValue")==""){
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
            $("#exam_status").combobox("setValue","");
            $("#exam_way").combobox("setValue","");
            $("#exam_period").combobox("setValue","");
            $("#exam_desc").val("");
        }

        function closeExamineScheduleDialog(){
            $("#dlg").dialog("close");
            resetValue();
        }

        function openExamineScheduleModifyDialog(){
            var selectedRows=$("#dg").datagrid('getSelections');
            if(selectedRows.length!=1){
                $.messager.alert("系统提示","请选择一条要编辑的数据！");
                return;
            }
            var row=selectedRows[0];
            $("#dlg").dialog("open").dialog("setTitle","编辑检验计划信息");
            $("#fm").form("load",row);
            url="examineScheduleSave?exam_id="+row.exam_id;
        }
    </script>
</head>
<body style="margin: 5px;">
<table id="dg" title="检测计划信息" class="easyui-datagrid" fitColumns="true"
       pagination="true" rownumbers="true" url="examineScheduleList" fit="true" toolbar="#tb">
    <thead>
    <tr>
        <th field="cb" checkbox="true"></th>
        <th field="exam_id" width="50" align="center">检测计划ID</th>
        <th field="exam_status" width="70" align="center">状态</th>
        <th field="dev_identity" width="70" align="center">设备编号</th>
        <th field="dev_name" width="100" align="center">设备名称</th>
        <th field="dev_specification" width="150" align="center">规格型号</th>
        <th field="depart_name" width="100" align="center">所在部门</th>
        <th field="exam_way" width="70" align="center">检验方式</th>
        <th field="exam_period" width="70" align="center">检验周期</th>
        <th field="exam_lasttime" width="100" align="center" hidden="true">上次检验</th>
        <th field="exam_nexttime" width="100" align="center" hidden="true">下次检验</th>
        <th field="dev_buytime" width="100" align="center">购置时间</th>
        <th field="dev_installaddr" width="100" align="center">安装地点</th>
        <th field="exam_desc" width="100" align="center">备注</th>

    </tr>
    </thead>
</table>

<div id="tb">
    <div>
        <a href="javascript:openExamineScheduleAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
        <a href="javascript:openExamineScheduleModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <a href="javascript:deleteExamineSchedule()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </div>
    <div>
        &nbsp;状态：&nbsp;<select class="easyui-combobox" id="d_dev_status" name="d_dev_status" editable="false" panelHeight="auto">
        <option value="">请选择状态</option>
        <option value="待送检">待送检</option>
        <option value="已送检">已送检</option>
        <option value="已检验">已检验</option>
        </select>
        &nbsp;设备编号：&nbsp;<input type="text" name="d_dev_identity" id="d_dev_identity" size="10" required="true">
        &nbsp;设备名称：&nbsp;<input type="text" name="d_dev_name" id="d_dev_name" size="10" required="true"/>
        &nbsp;购置日期：&nbsp;<input class="easyui-datebox" name="d_sdev_buytime" id="d_sdev_buytime" editable="true" size="10"/>-><input class="easyui-datebox" name="d_edev_buytime" id="d_edev_buytime" editable="true" size="10"/>
        &nbsp;所在部门：&nbsp;<input class="easyui-combobox" id="d_depart_id" name="d_depart_id"  data-options="panelHeight:'auto',editable:false,valueField:'depart_id',textField:'depart_name',url:'departmentComboList'"/>
        <a href="javascript:searchExamineSchedule()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a></div>
</div>

<div id="dlg" class="easyui-dialog" style="width: 700px;height: 400px;padding: 10px 20px"
     closed="true" buttons="#dlg-buttons">
    <form id="fm" method="post">
        <table cellspacing="10px;">
            <tr>
                <td>计划状态：</td>
                <td><select class="easyui-combobox" id="exam_status" name="exam_status" editable="false" panelHeight="auto">
                    <option value="">请选择状态</option>
                    <option value="待送检">待送检</option>
                    <option value="已送检">已送检</option>
                    <option value="已检验">已检验</option>
                    </select>
                </td>
                <td>&nbsp;&nbsp;</td>
                <td>设备编号：</td>
                <td><input class="easyui-combobox" id="dev_identity" name="dev_identity"  data-options="panelHeight:'auto',editable:false,valueField:'dev_id',textField:'dev_identity',url:'deviceComboList'"/></td>
            </tr>
            <tr>
                <td>检验方式：</td>
                <td><select class="easyui-combobox" id="exam_way" name="exam_way" editable="false" panelHeight="auto">
                    <option value="">请选择检验方式</option>
                    <option value="第三方">第三方</option>
                </select></td>
                <td>&nbsp;&nbsp;</td>
                <<td>检验周期：</td>
                <td><select class="easyui-combobox" id="exam_period" name="exam_period" editable="false" panelHeight="auto">
                    <option value="">请选择周期</option>
                    <option value="一天">一天</option>
                    <option value="一周">一周</option>
                    <option value="一月">一月</option>
                     </select>
                </td>
            </tr>
            <tr>
                <td>备注：</td>
                <td><textarea rows="7" cols="30" name="exam_desc" id="exam_desc"></textarea></td>
            </tr>
        </table>
    </form>
</div>

<div id="dlg-buttons">
    <a href="javascript:saveExamineSchedule()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:closeExamineScheduleDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
</body>
</html>