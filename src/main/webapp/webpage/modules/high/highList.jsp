<%--
  Created by IntelliJ IDEA.
  User: li
  Date: 2019/5/24
  Time: 12:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp"%>
<html>
<head>
    <title>公海客户档案管理</title>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta name="decorator" content="ani"/>
    <%@ include file="/webpage/include/bootstraptable.jsp"%>
    <%@include file="/webpage/include/treeview.jsp" %>
    <%@include file="highList.js" %>
</head>
<body>
<div class="wrapper wrapper-content">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">公海客户档案列表</h3>
        </div>
        <div class="panel-body">

            <!-- 搜索 -->
            <div id="search-collapse" class="collapse">
                <div class="accordion-inner">
                    <form:form id="searchForm" modelAttribute="customer" class="form form-horizontal well clearfix">
                        <div class="col-xs-12 col-sm-6 col-md-2">
                            <label class="label-item single-overflow pull-left" title="客户名称：">客户名称：</label>
                            <form:input path="name" htmlEscape="false" maxlength="255"  class=" form-control"/>
                        </div>
                        <div class="col-xs-12 col-sm-6 col-md-2">
                            <label class="label-item single-overflow pull-left" title="客户法人：">客户法人：</label>
                            <form:input path="legalPerson" htmlEscape="false" maxlength="100"  class=" form-control"/>
                        </div>
                        <div class="col-xs-12 col-sm-6 col-md-2">
                            <label class="label-item single-overflow pull-left" title="所属部门Id：">所属部门Id：</label>
                            <form:input path="deptId" htmlEscape="false" maxlength="64"  class=" form-control"/>
                        </div>
                        <div class="col-xs-12 col-sm-6 col-md-2">
                            <label class="label-item single-overflow pull-left" title="跟进状态Id：">跟进状态Id：</label>
                            <form:select path="statusId"  class="form-control m-b">
                                <form:option value="" label=""/>
                                <form:options items="${fns:getDictList('follow_up_state')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                            </form:select>
                        </div>
                        <div class="col-xs-12 col-sm-6 col-md-2">
                            <label class="label-item single-overflow pull-left" title="客户等级：">客户等级：</label>
                            <form:select path="cusLevel"  class="form-control m-b">
                                <form:option value="" label=""/>
                                <form:options items="${fns:getDictList('customer_level')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
                            </form:select>
                        </div>
                        <div class="col-xs-12 col-sm-6 col-md-2">
                            <label class="label-item single-overflow pull-left" title="行业Id：">行业Id：</label>
                            <form:select path="industoryId"  class="form-control m-b">
                                <form:option value="" label=""/>
                                <form:options items="${fns:getDictList('sys_industry')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
                <shiro:hasPermission name="customer:customer:add">
                    <button id="add" class="btn btn-primary" onclick="add()">
                        <i class="glyphicon glyphicon-plus"></i> 新建
                    </button>
                </shiro:hasPermission>
                <shiro:hasPermission name="customer:customer:edit">
                    <button id="edit" class="btn btn-success" disabled onclick="edit()">
                        <i class="glyphicon glyphicon-edit"></i> 修改
                    </button>
                </shiro:hasPermission>
                <shiro:hasPermission name="customer:customer:del">
                    <button id="remove" class="btn btn-danger" disabled onclick="deleteAll()">
                        <i class="glyphicon glyphicon-remove"></i> 删除
                    </button>
                </shiro:hasPermission>
                <shiro:hasPermission name="customer:customer:import">
                    <button id="btnImport" class="btn btn-info"><i class="fa fa-folder-open-o"></i> 导入</button>
                </shiro:hasPermission>
                <shiro:hasPermission name="customer:customer:export">
                    <button id="export" class="btn btn-warning">
                        <i class="fa fa-file-excel-o"></i> 导出
                    </button>
                </shiro:hasPermission>
                <shiro:hasPermission name="customer:customer:view">
                    <button id="view" class="btn btn-default" disabled onclick="view()">
                        <i class="fa fa-search-plus"></i> 查看
                    </button>
                </shiro:hasPermission>
                <button id="openSearch" class="btn btn-default" onclick="search()">
                    <i class="glyphicon glyphicon-search"></i> 搜索
                </button>
            </div>

            <!-- 表格 -->
            <table id="customerTable"   data-toolbar="#toolbar"></table>

            <!-- context menu -->
            <ul id="context-menu" class="dropdown-menu">
                <shiro:hasPermission name="customer:customer:view">
                    <li data-item="view"><a>查看</a></li>
                </shiro:hasPermission>
                <shiro:hasPermission name="customer:customer:edit">
                    <li data-item="edit"><a>编辑</a></li>
                </shiro:hasPermission>
                <shiro:hasPermission name="customer:customer:del">
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
