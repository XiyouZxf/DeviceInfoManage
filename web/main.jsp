<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>设备信息管理系统主界面</title>
 <%
	if(session.getAttribute("currentUser")==null){
		response.sendRedirect("login.jsp");
		return;
	}
%>
	<% String path = request.getContextPath(); %>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="<%=path %>/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	$(function(){
		// 数据
		var treeData=[{
			text:"根",
			children:[{
                text:"设备管理",
                attributes:{
                    url:"deviceManage.jsp"
                }
            },{
                text:"鉴定报告管理",
                attributes:{
                    url:"report.jsp"
                }
            },{
                text:"计划管理",
                children:[{
                    text:"检验计划",
                    attributes:{
                        url:"examineScheduleManage.jsp"
                    }
                }]
            }]
		}];
		
		// 实例化树菜单
		$("#tree").tree({
			data:treeData,
			lines:true,
			onClick:function(node){
				if(node.attributes){
					openTab(node.text,node.attributes.url);
				}
			}
		});
		
		// 新增Tab
		function openTab(text,url){
			if($("#tabs").tabs('exists',text)){
				$("#tabs").tabs('select',text);
			}else{
				var content="<iframe frameborder='0' scrolling='auto' style='width:100%;height:100%' src="+url+"></iframe>";
				$("#tabs").tabs('add',{
					title:text,
					closable:true,
					content:content
				});
			}
		}
	});
</script>
</head>
<body class="easyui-layout">
	<div region="north" style="height: 80px;background-color: #E0EDFF">
		<div align="left" style="width: 80%;float: left"></div>
		<div style="padding-top: 50px;padding-right: 20px;">
		     当前用户：&nbsp;<font color="red" >${currentUser.emp_no }</font>

			<%
				if(session.getAttribute("currentUser")==null){
					response.sendRedirect("login.jsp");
					return;
				}
			%>


		</div>
	</div>
	<div region="center">
		<div class="easyui-tabs" fit="true" border="false" id="tabs">
			<div title="首页" >
				<div align="center" style="padding-top: 100px;"><font color="red" size="10">欢迎使用</font></div>
			</div>
		</div>
	</div>
	<div region="west" style="width: 150px;" title="导航菜单" split="true">
		<ul id="tree"></ul>
	</div>
	<div region="south" style="height: 25px;" align="center">版权所有<a href="">www.zxf.com</a></div>
</body>
</html>