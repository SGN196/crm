<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/webpage/include/taglib.jsp" %>
<html>
<head>
    <title>线索商机跟进</title>
    <meta name="decorator" content="ani"/>
    <script type="text/javascript">

        $(document).ready(function () {
            $("textarea").css("resize", "none");
        });

        function save() {
            var isValidate = jp.validateForm('#inputForm');//校验表单
            if (!isValidate) {
                return false;
            } else {
                jp.loading();
                jp.post("${ctx}/opportunity/opportunity/save", $('#inputForm').serialize(), function (data) {
                    if (data.success) {
                        jp.getParent().refresh();
                        var dialogIndex = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                        parent.layer.close(dialogIndex);
                        jp.success(data.msg)

                    } else {
                        jp.error(data.msg);
                    }
                })
            }
        }
    </script>
</head>
<body class="bg-white">
<form:form id="inputForm" modelAttribute="opportunity" class="form-horizontal">
    <form:hidden path="id"/>
    <table class="table table-bordered">
        <tbody>
        <tr>
            <td class="width-15 active"><label class="pull-right"><font color="red">*</font>商机名称：</label></td>
            <td class="width-35">
                <form:input path="oppNo" htmlEscape="false" class="form-control required"/>
            </td>
            <td class="width-15 active"><label class="pull-right"><font color="red">*</font>活动类型：</label></td>
            <td class="width-35">
                <form:input path="oppName" htmlEscape="false" class="form-control required"/>
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right"><font color="red">*</font>联系人：</label></td>
            <td class="width-35">
                <form:input path="decisionmaker" htmlEscape="false" class="form-control required"/>
            </td>
            <td class="width-15 active"><label class="pull-right"><font color="red">*</font>跟进时间：</label></td>
            <td class="width-35">
                <form:input path="custId" htmlEscape="false" class="form-control required"/>
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">活动内容：</label></td>
            <td colspan="3">
                <form:textarea path="factors" htmlEscape="false" rows="4" class="form-control "/>
            </td>
        </tr>
        <tr>
            <td class="width-15 active"><label class="pull-right">跟进人员：</label></td>
            <td class="width-35">
                <form:input path="empId" htmlEscape="false" class="form-control "/>
            </td>
            <td class="width-15 active"><label class="pull-right">记录时间：</label></td>
            <td class="width-35">
                <form:input path="lastTime" htmlEscape="false" class="form-control "/>
            </td>
        </tr>
        </tbody>
    </table>
</form:form>
</body>
</html>