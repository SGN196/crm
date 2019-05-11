<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>线索商机跟进管理</title>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta name="decorator" content="ani"/>
    <%@ include file="/webpage/include/bootstraptable.jsp" %>
    <%@include file="/webpage/include/treeview.jsp" %>
    <script charset="UTF-8" type="text/javascript">
        <%@include file="followRecordList.js" %>
    </script>


</head>
<body>
<div class="wrapper wrapper-content">
    <div class="panel panel-primary">
        <div class="panel-body">
            <form:form id="searchForm" modelAttribute="opportunity" class="form form-horizontal well clearfix">
                <div class="col-xs-12 col-sm-6 col-md-4">
                    <label class="label-item single-overflow pull-left" title="联系人：">联系人：</label>
                    <form:input path="custId" htmlEscape="false" maxlength="100" class=" form-control"/>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-4">
                    <label class="label-item single-overflow pull-left" title="活动类型：">活动类型：</label>
                    <form:input path="oppStageId" htmlEscape="false" maxlength="100" class=" form-control"/>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-4">
                    <div style="margin-top:26px">
                        <a id="search" class="btn btn-primary btn-rounded  btn-bordered btn-sm">
                            <i class="fa fa-search"></i> 查询
                        </a>
                        <a id="reset" class="btn btn-primary btn-rounded  btn-bordered btn-sm">
                            <i class="fa fa-refresh"></i> 重置
                        </a>
                    </div>
                </div>
            </form:form>
            <!-- 工具栏 -->
            <div id="toolbar">
                <shiro:hasPermission name="opportunity:opportunity:add">
                    <button id="add" class="btn btn-primary" onclick="follow_add()">
                        <i class="glyphicon glyphicon-plus"></i> 新建
                    </button>
                </shiro:hasPermission>
                <shiro:hasPermission name="opportunity:opportunity:edit">
                    <button id="edit" class="btn btn-success" disabled onclick="follow_edit()">
                        <i class="glyphicon glyphicon-edit"></i> 修改
                    </button>
                </shiro:hasPermission>
                <shiro:hasPermission name="opportunity:opportunity:del">
                    <button id="remove" class="btn btn-danger" disabled onclick="follow_del()">
                        <i class="glyphicon glyphicon-remove"></i> 删除
                    </button>
                </shiro:hasPermission>
            </div>

            <!-- 表格 -->
            <table id="followUpTable" data-toolbar="#toolbar"></table>
        </div>
    </div>
</div>
</body>
</html>