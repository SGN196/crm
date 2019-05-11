<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>线索商机管理</title>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta name="decorator" content="ani"/>
    <%@ include file="/webpage/include/bootstraptable.jsp" %>
    <%@include file="/webpage/include/treeview.jsp" %>
    <%@include file="opportunityList.js" %>
</head>
<body>
<div class="wrapper wrapper-content">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">线索商机列表</h3>
        </div>
        <div class="panel-body">

            <!-- 搜索 -->
            <div id="search-collapse" class="collapse">
                <div class="accordion-inner">
                    <form:form id="searchForm" modelAttribute="opportunity" class="form form-horizontal well clearfix">
                        <div class="col-xs-12 col-sm-6 col-md-4">
                            <label class="label-item single-overflow pull-left" title="商机名称：">商机名称：</label>
                            <form:input path="oppName" htmlEscape="false" maxlength="255" class=" form-control"/>
                        </div>
                        <div class="col-xs-12 col-sm-6 col-md-4">
                            <label class="label-item single-overflow pull-left" title="客户编号：">客户编号：</label>
                            <form:input path="custId" htmlEscape="false" maxlength="100" class=" form-control"/>
                        </div>
                        <div class="col-xs-12 col-sm-6 col-md-4">
                            <label class="label-item single-overflow pull-left" title="商机阶段编号：">商机阶段编号：</label>
                            <form:input path="oppStageId" htmlEscape="false" maxlength="100" class=" form-control"/>
                        </div>
                        <div class="col-xs-12 col-sm-6 col-md-4">
                            <div style="margin-top:26px">
                                <a id="search" class="btn btn-primary btn-rounded  btn-bordered btn-sm"><i
                                        class="fa fa-search"></i> 查询</a>
                                <a id="reset" class="btn btn-primary btn-rounded  btn-bordered btn-sm"><i
                                        class="fa fa-refresh"></i> 重置</a>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>

            <!-- 工具栏 -->
            <div id="toolbar">
                <shiro:hasPermission name="opportunity:opportunity:add">
                    <button id="add" class="btn btn-primary" onclick="add()">
                        <i class="glyphicon glyphicon-plus"></i> 新建
                    </button>
                </shiro:hasPermission>
                <shiro:hasPermission name="opportunity:opportunity:export">
                    <button id="export" class="btn btn-warning">
                        <i class="fa fa-file-excel-o"></i> 导出
                    </button>
                </shiro:hasPermission>
                <shiro:hasPermission name="opportunity:opportunity:edit">
                    <button id="edit" class="btn btn-success" disabled onclick="edit()">
                        <i class="glyphicon glyphicon-edit"></i> 修改
                    </button>
                </shiro:hasPermission>
                <shiro:hasPermission name="opportunity:opportunity:del">
                    <button id="remove" class="btn btn-danger" disabled onclick="deleteAll()">
                        <i class="glyphicon glyphicon-remove"></i> 删除
                    </button>
                </shiro:hasPermission>
                <%--<shiro:hasPermission name="opportunity:opportunity:import">
                    <button id="btnImport" class="btn btn-info"><i class="fa fa-folder-open-o"></i> 导入</button>
                </shiro:hasPermission>--%>
                <%--<shiro:hasPermission name="opportunity:opportunity:view">
                    <button id="view" class="btn btn-default" disabled onclick="view()">
                        <i class="fa fa-search-plus"></i> 查看
                    </button>
                </shiro:hasPermission>--%>
                <button id="delay" class="btn btn-info" disabled onclick="delay()">
                    <i class="fa fa-clock-o"></i> 延时申请
                </button>
                <button id="follow-up" class="btn btn-mini" disabled onclick="follow_up()">
                    <i class="glyphicon glyphicon-phone-alt"></i> 跟进
                </button>
                <button id="follow-up-record" class="btn btn-default" disabled onclick="followUpRecord()">
                    <i class="fa fa-list-alt"></i> 跟进记录
                </button>
            </div>

            <!-- 表格 -->
            <table id="opportunityTable" data-toolbar="#toolbar"></table>

            <!-- context menu -->
            <ul id="context-menu" class="dropdown-menu">
                <shiro:hasPermission name="opportunity:opportunity:view">
                    <li data-item="view"><a>查看</a></li>
                </shiro:hasPermission>
                <shiro:hasPermission name="opportunity:opportunity:edit">
                    <li data-item="edit"><a>编辑</a></li>
                </shiro:hasPermission>
                <shiro:hasPermission name="opportunity:opportunity:del">
                    <li data-item="delete"><a>删除</a></li>
                </shiro:hasPermission>
                <li data-item="action1"><a>取消</a></li>
            </ul>
        </div>
    </div>
</div>
</body>
</html>