<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
	<title>客户联系人管理</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8">
	<meta name="decorator" content="ani"/>
	<%@ include file="/webpage/include/bootstraptable.jsp"%>
	<%@include file="/webpage/include/treeview.jsp" %>
	<%@include file="contactsList.js" %>
</head>
<body>
	<div class="wrapper wrapper-content">
	<div class="panel panel-primary">
	<div class="panel-heading">
		<h3 class="panel-title">客户联系人列表</h3>
	</div>
	<div class="panel-body">
	
	<!-- 搜索 -->
	<div id="search-collapse" class="collapse">
		<div class="accordion-inner">
			<form:form id="searchForm" modelAttribute="contacts" class="form form-horizontal well clearfix">
			 <div class="col-xs-12 col-sm-6 col-md-2">
				<label class="label-item single-overflow pull-left" title="联系人姓名：">联系人姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="200"  class=" form-control"/>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-2">
				<label class="label-item single-overflow pull-left" title="性别：">性别：</label>
				<form:select path="gender"  class="form-control m-b">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('sex')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-2">
				<label class="label-item single-overflow pull-left" title="部门：">部门：</label>
				<form:select path="department"  class="form-control m-b">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('sys_office_common')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
			 <div class="col-xs-12 col-sm-6 col-md-2">
				<label class="label-item single-overflow pull-left" title="职务：">职务：</label>
				<form:select path="title"  class="form-control m-b">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		 <div class="col-xs-12 col-sm-6 col-md-4">
			<div style="margin-top:26px">
			  <a  id="search" class="btn btn-primary btn-rounded  btn-bordered btn-sm"><i class="fa fa-search"></i> 查询</a>
			  <a  id="reset" class="btn btn-primary btn-rounded  btn-bordered btn-sm" ><i class="fa fa-refresh"></i> 重置</a>
			 </div>
	    </div>	
	</form:form>
	</div>
	</div>
	
	<!-- 工具栏 -->
	<div id="toolbar">
			<shiro:hasPermission name="contacts:contacts:add">
				<button id="add" class="btn btn-primary" onclick="add()">
					<i class="glyphicon glyphicon-plus"></i> 新建
				</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="contacts:contacts:edit">
			    <button id="edit" class="btn btn-success" disabled onclick="edit()">
	            	<i class="glyphicon glyphicon-edit"></i> 修改
	        	</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="contacts:contacts:del">
				<button id="remove" class="btn btn-danger" disabled onclick="deleteAll()">
	            	<i class="glyphicon glyphicon-remove"></i> 删除
	        	</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="contacts:contacts:import">
				<button id="btnImport" class="btn btn-info"><i class="fa fa-folder-open-o"></i> 导入</button>
			</shiro:hasPermission>
			<shiro:hasPermission name="contacts:contacts:export">
	        		<button id="export" class="btn btn-warning">
					<i class="fa fa-file-excel-o"></i> 导出
				</button>
			 </shiro:hasPermission>
	                 <shiro:hasPermission name="contacts:contacts:view">
				<button id="view" class="btn btn-default" disabled onclick="view()">
					<i class="fa fa-search-plus"></i> 查看
				</button>
			</shiro:hasPermission>
		<button id="openSearch" class="btn btn-default" onclick="search()">
			<i class="glyphicon glyphicon-search"></i> 搜索
		</button>
		    </div>
		
	<!-- 表格 -->
	<table id="contactsTable"   data-toolbar="#toolbar"></table>

    <!-- context menu -->
    <ul id="context-menu" class="dropdown-menu">
    	<shiro:hasPermission name="contacts:contacts:view">
        <li data-item="view"><a>查看</a></li>
        </shiro:hasPermission>
    	<shiro:hasPermission name="contacts:contacts:edit">
        <li data-item="edit"><a>编辑</a></li>
        </shiro:hasPermission>
        <shiro:hasPermission name="contacts:contacts:del">
        <li data-item="delete"><a>删除</a></li>
        </shiro:hasPermission>
        <li data-item="action1"><a>取消</a></li>
    </ul>  
	</div>
	</div>
	</div>

	<script type="text/javascript" charset="UTF-8">
		<%@include file="../../../static/common/js/search.js" %>
	</script>
</body>
</html>